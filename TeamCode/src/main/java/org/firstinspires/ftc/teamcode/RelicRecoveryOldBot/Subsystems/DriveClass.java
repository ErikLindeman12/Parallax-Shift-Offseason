package org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.Subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by Brown on 4/24/2018.
 */

public class DriveClass {
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    private Gamepad gamepad;
    public DcMotor frontRight, frontLeft, backRight, backLeft;
    public BNO055IMU gyro;
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
    double KP = .5;
    double KI = 0;
    double KD = 0;

    public double angleFromDriver;
    private double DRIVE_POWER = 1.0f;


    public DriveClass(HardwareMap hwmap, boolean isFC, Gamepad gamepad, double angle) {
        this.hardwareMap = hwmap;
        this.FC = isFC;
        this.gamepad = gamepad;
        this.angleFromDriver = angle;

        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        
        InitializeGyro();
    }


    private double clipValue(double value) {
        if (value > DRIVE_POWER || value < -DRIVE_POWER)
            return ((Math.abs(value) / value) * DRIVE_POWER);
        else
            return value;
    }

    public void drive(double fl, double fr, double bl, double br) {
        frontLeft.setPower(clipValue(fl));
        frontRight.setPower(clipValue(fr));
        backLeft.setPower(clipValue(bl));
        backRight.setPower(clipValue(br));
    }

    public void updateGyro() {
        heading = gyro.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX).firstAngle;

        if (heading > 0)
            heading = heading + 0;
        else
            heading = heading + 2 * Math.PI;
    }


    public void FCDrive(int multiplier) {
        if (gamepad.y) {
            angleFromDriver = heading;
        }
        updateGyro();

        double rightX = Math.signum(gamepad.right_stick_x)*Math.pow(gamepad.right_stick_x,multiplier);
        double leftX = Math.signum(gamepad.left_stick_x)*Math.pow(gamepad.left_stick_x,multiplier);
        double leftY = Math.signum(gamepad.left_stick_y)*Math.pow(gamepad.left_stick_y,multiplier);

        jTheta = Math.atan2(-leftY,leftX);
        jp = Math.sqrt(leftX * leftX + leftY * leftY);
        if (jp > 1)
            jp = 1;
        theta = (jTheta + angleFromDriver - heading);

        double fl = (Math.sin(theta) + Math.cos(theta)) * jp / 2 - rightX;
        double fr = (Math.sin(theta) - Math.cos(theta)) * jp / 2 + rightX;
        double bl = (Math.sin(theta) - Math.cos(theta)) * jp / 2 - rightX;
        double br = (Math.sin(theta) + Math.cos(theta)) * jp / 2 + rightX;

        drive(
                fl,fr,bl,br
        );
    }

    public void RCDrive(int multiplier) {
        double rightX = Math.signum(gamepad.right_stick_x)*Math.pow(gamepad.right_stick_x,multiplier);
        double leftX = Math.signum(gamepad.left_stick_x)*Math.pow(gamepad.left_stick_x,multiplier);
        double leftY = Math.signum(gamepad.left_stick_y)*Math.pow(gamepad.left_stick_y,multiplier);

        double fl = gamepad.left_stick_y + gamepad.left_stick_x - gamepad.right_stick_x;
        double fr = gamepad.left_stick_y - gamepad.left_stick_x + gamepad.right_stick_x;
        double bl = gamepad.left_stick_y - gamepad.left_stick_x - gamepad.right_stick_x;
        double br = gamepad.left_stick_y + gamepad.left_stick_x + gamepad.right_stick_x;

        drive(
                fl,fr,bl,br
        );
    }

    public void brake() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void setDriveEncoders(double powerfl, double powerfr, double powerbl, double powerbr, int fl, int fr, int bl, int br) {
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setTargetPosition(fl + frontLeft.getCurrentPosition());
        frontRight.setTargetPosition(fr + frontRight.getCurrentPosition());
        backLeft.setTargetPosition(bl + backLeft.getCurrentPosition());
        backRight.setTargetPosition(br + backRight.getCurrentPosition());

        frontLeft.setPower(powerfl);
        frontRight.setPower(powerfr);
        backLeft.setPower(powerbl);
        backRight.setPower(powerbr);
    }

    public void telemetryReadings() {
        telemetry.addData(" Right Joystick X Axis:", gamepad.right_stick_x);
        telemetry.addData(" Left Joystick Y Axis:", gamepad.left_stick_y);
        telemetry.addData(" Left Joystick X Axis:", gamepad.left_stick_x);

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

    public void InitializeGyro() {
        gyro = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        gyro.initialize(parameters);
    }
}