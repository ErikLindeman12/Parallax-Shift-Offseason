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

public class DriveClass
{
    private HardwareClass hardwaremap;
    private Telemetry telemetry;
    private Gamepad gamepad1;
    public double heading;
    private double jp;
    private double jTheta;
    private double theta;
    private boolean FC;

    private double angleFromDriver;
    private double DRIVE_POWER = 0.4f;


    public DriveClass(HardwareMap hwmap, Telemetry telem, boolean usesGyro, boolean isFC, Gamepad gamepad1val, double angle)
    {
        hardwaremap = new HardwareClass(hwmap, telem, usesGyro, gamepad1val);
        telemetry = telem;
        FC = isFC;
        gamepad1 = gamepad1val;
        angleFromDriver = angle;
    }


    private double clipValue(double value)
    {
        if(value > DRIVE_POWER || value < - DRIVE_POWER)
            return ((Math.abs(value) / value) * DRIVE_POWER);
        else
            return value;
    }

    public void drive(double fl, double fr, double bl, double br) {
        hardwaremap.fleft.setPower(clipValue(fl));
        hardwaremap.fright.setPower(clipValue(fr));
        hardwaremap.bleft.setPower(clipValue(bl));
        hardwaremap.bright.setPower(clipValue(br));
    }

    public void updateGyro()
    {
        heading =  hardwaremap.gyro.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX).firstAngle;

        if(heading > 0)
            heading = heading +0;
        else
            heading = heading+2*Math.PI;
    }


    public void FCDrive(int multiplier)
    {
        if(gamepad1.y)
        {
            angleFromDriver = heading;
        }
        updateGyro();

        jTheta = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x);
        jp = Math.sqrt(gamepad1.left_stick_x * gamepad1.left_stick_x + gamepad1.left_stick_y * gamepad1.left_stick_y);
        if(jp > 1)
            jp = 1;
        theta = (jTheta + angleFromDriver - heading);

        double fl = (Math.sin(theta)+Math.cos(theta))*jp/2 - gamepad1.right_stick_x;
        double fr = (Math.sin(theta)-Math.cos(theta))*jp/2 + gamepad1.right_stick_x;
        double bl = (Math.sin(theta)-Math.cos(theta))*jp/2 - gamepad1.right_stick_x;
        double br = (Math.sin(theta)+Math.cos(theta))*jp/2 + gamepad1.right_stick_x;

        drive(
                Math.pow(fl,multiplier),
                Math.pow(fr,multiplier),
                Math.pow(bl,multiplier),
                Math.pow(br,multiplier)
        );
    }

    public void RCDrive(int multiplier)
    {
        double fl = gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x;
        double fr = gamepad1.left_stick_y -gamepad1.left_stick_x + gamepad1.right_stick_x;
        double bl = gamepad1.left_stick_y -gamepad1.left_stick_x - gamepad1.right_stick_x;
        double br = gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x;

        drive(
                Math.pow(fl,multiplier),
                Math.pow(fr,multiplier),
                Math.pow(bl,multiplier),
                Math.pow(br,multiplier)
        );
    }

    public void brake() {
        hardwaremap.fleft.setPower(0);
        hardwaremap.fright.setPower(0);
        hardwaremap.bleft.setPower(0);
        hardwaremap.bright.setPower(0);
    }

    public void setDriveEncoders(double powerfl, double powerfr, double powerbl, double powerbr, int fl, int fr, int bl, int br) {
        hardwaremap.fleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardwaremap.fright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardwaremap.bleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hardwaremap.bright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        hardwaremap.fleft.setTargetPosition(fl + hardwaremap.fleft.getCurrentPosition());
        hardwaremap.fright.setTargetPosition(fr + hardwaremap.fright.getCurrentPosition());
        hardwaremap.bleft.setTargetPosition(bl + hardwaremap.bleft.getCurrentPosition());
        hardwaremap.bright.setTargetPosition(br + hardwaremap.bright.getCurrentPosition());

        hardwaremap.fleft.setPower(powerfl);
        hardwaremap.fright.setPower(powerfr);
        hardwaremap.bleft.setPower(powerbl);
        hardwaremap.bright.setPower(powerbr);
    }

    public void telemetryReadings()
    {
        telemetry.addData(" Right Joystick X Axis:", gamepad1.right_stick_x);
        telemetry.addData(" Left Joystick Y Axis:", gamepad1.left_stick_y);
        telemetry.addData(" Left Joystick X Axis:", gamepad1.left_stick_x);

        if (FC)
        {
            telemetry.addData("Joystick Direction", Math.toDegrees(jTheta));
            telemetry.addData("Joystick Magnitude", jp);
            telemetry.addData("Gyro Heading", heading);
        }
        telemetry.update();
    }

    public void runPID(double angle){
        double error = 0;
        boolean begin = true;
        double LastTime = System.currentTimeMillis();
        double LastError = 0;
        double Integral = 0;
        double Derivative;
        double currentDrivePower;
        while(error>.1 || begin){
            if(begin)
                begin = false;
            updateGyro();
            error = angle-heading;
            double Time = System.currentTimeMillis()-LastTime;
            Integral += error * Time;
            Derivative = (error-LastError)/Time;
            currentDrivePower= error * .02 + Integral * 0 + Derivative * 0;
            LastTime = System.currentTimeMillis();
            LastError = error;
            drive(-currentDrivePower,currentDrivePower,-currentDrivePower,currentDrivePower);
        }

    }
}
