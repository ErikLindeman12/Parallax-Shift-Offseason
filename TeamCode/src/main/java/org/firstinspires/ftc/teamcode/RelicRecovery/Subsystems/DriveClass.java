package org.firstinspires.ftc.teamcode.RelicRecovery.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Brown on 4/24/2018.
 */

public class DriveClass {
    private HardwareClass hardwaremap;
    private Telemetry telemetry;
    private Gamepad gamepad1;
    public double heading;
    private double jp;
    private double jTheta;
    private double theta;
    private boolean FC;
    double error;
    boolean begin;
    double LastTime;
    double LastError;
    double Integral;
    double Derivative;
    double currentDrivePower;
    double Time;
    double KP = .02;
    double KI = 0;
    double KD = 0;

    public double angleFromDriver;
    private double DRIVE_POWER = 0.4f;


    public DriveClass(HardwareMap hwmap, Telemetry telem, boolean usesGyro, boolean isFC, Gamepad gamepad1val, double angle) {
        hardwaremap = new HardwareClass(hwmap, telem, usesGyro, gamepad1val);
        telemetry = telem;
        FC = isFC;
        gamepad1 = gamepad1val;
        angleFromDriver = angle;
    }


    private double clipValue(double value) {
        if (value > DRIVE_POWER || value < -DRIVE_POWER)
            return ((Math.abs(value) / value) * DRIVE_POWER);
        else
            return value;
    }

    public void drive(double fl, double fr, double bl, double br) {
        hardwaremap.frontLeft.setPower(clipValue(fl));
        hardwaremap.frontRight.setPower(clipValue(fr));
        hardwaremap.backLeft.setPower(clipValue(bl));
        hardwaremap.backRight.setPower(clipValue(br));
    }

    public void updateGyro() {
        heading = hardwaremap.gyro.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX).firstAngle;

        if (heading > 0)
            heading = heading + 0;
        else
            heading = heading + 2 * Math.PI;
    }


    public void FCDrive(int multiplier) {
        if (gamepad1.y) {
            angleFromDriver = heading;
        }
        updateGyro();

        jTheta = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x);
        jp = Math.sqrt(gamepad1.left_stick_x * gamepad1.left_stick_x + gamepad1.left_stick_y * gamepad1.left_stick_y);
        if (jp > 1)
            jp = 1;
        theta = (jTheta + angleFromDriver - heading);

        double fl = (Math.sin(theta) + Math.cos(theta)) * jp / 2 - gamepad1.right_stick_x;
        double fr = (Math.sin(theta) - Math.cos(theta)) * jp / 2 + gamepad1.right_stick_x;
        double bl = (Math.sin(theta) - Math.cos(theta)) * jp / 2 - gamepad1.right_stick_x;
        double br = (Math.sin(theta) + Math.cos(theta)) * jp / 2 + gamepad1.right_stick_x;

        drive(
                Math.pow(fl, multiplier),
                Math.pow(fr, multiplier),
                Math.pow(bl, multiplier),
                Math.pow(br, multiplier)
        );
    }

    public void RCDrive(int multiplier) {
        double fl = gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x;
        double fr = gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x;
        double bl = gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x;
        double br = gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x;

        drive(
                Math.pow(fl, multiplier),
                Math.pow(fr, multiplier),
                Math.pow(bl, multiplier),
                Math.pow(br, multiplier)
        );
    }

    public void brake() {
        hardwaremap.frontLeft.setPower(0);
        hardwaremap.frontRight.setPower(0);
        hardwaremap.backLeft.setPower(0);
        hardwaremap.backRight.setPower(0);
    }

    public void setDriveEncoders(double powerfl, double powerfr, double powerbl, double powerbr, int fl, int fr, int bl, int br) {
        hardwaremap.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardwaremap.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardwaremap.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardwaremap.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        hardwaremap.frontLeft.setTargetPosition(fl + hardwaremap.frontLeft.getCurrentPosition());
        hardwaremap.frontRight.setTargetPosition(fr + hardwaremap.frontRight.getCurrentPosition());
        hardwaremap.backLeft.setTargetPosition(bl + hardwaremap.backLeft.getCurrentPosition());
        hardwaremap.backRight.setTargetPosition(br + hardwaremap.backRight.getCurrentPosition());

        hardwaremap.frontLeft.setPower(powerfl);
        hardwaremap.frontRight.setPower(powerfr);
        hardwaremap.backLeft.setPower(powerbl);
        hardwaremap.backRight.setPower(powerbr);
    }

    public void telemetryReadings() {
        telemetry.addData(" Right Joystick X Axis:", gamepad1.right_stick_x);
        telemetry.addData(" Left Joystick Y Axis:", gamepad1.left_stick_y);
        telemetry.addData(" Left Joystick X Axis:", gamepad1.left_stick_x);

        if (FC) {
            telemetry.addData("Joystick Direction", Math.toDegrees(jTheta));
            telemetry.addData("Joystick Magnitude", jp);
            telemetry.addData("Gyro Heading", heading);
        }
        telemetry.update();
    }

    public void turnPID(double angle) {
        error = 0;
        begin = true;
        LastTime = System.currentTimeMillis();
        LastError = 0;
        Integral = 0;
        while (error > .1 || begin) {
            if (begin)
                begin = false;
            updateGyro();
            error = angle - heading;
            Time = System.currentTimeMillis() - LastTime;
            Integral += error * Time;
            Derivative = (error - LastError) / Time;
            currentDrivePower = error * KP + Integral * KI + Derivative * KD;
            LastTime = System.currentTimeMillis();
            LastError = error;
            drive(-currentDrivePower, currentDrivePower, -currentDrivePower, currentDrivePower);
        }

    }
}
