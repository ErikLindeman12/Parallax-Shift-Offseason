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
    public Telemetry telemetry;

    private Gamepad gamepad;
    public DcMotor frontRight, frontLeft, backRight, backLeft;
    public BNO055IMU gyro;

    private double jp,jTheta,theta;
    private double leftY,leftX,rightX;
    private double angleFromDriver;
    public double heading;
    public double DRIVE_POWER = 1.0f;
    public double yRotation;

    private boolean begin = true;

    public DriveClass(HardwareMap hwmap,Gamepad gamepad,Telemetry telemetry) {
        this.hardwareMap = hwmap;
        this.gamepad = gamepad;
        this.telemetry = telemetry;

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
        telemetry.addData("Gyro Heading",heading);

        yRotation = gyro.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX).secondAngle;
    }


    public void FCDrive(int multiplier, double angleFromDriver) {
        if(begin)
            this.angleFromDriver = angleFromDriver;

        updateGyro();

        if (gamepad.y)
            this.angleFromDriver += heading;

        if(multiplier != 1){
            rightX = Math.signum(gamepad.right_stick_x) * Math.pow(gamepad.right_stick_x, multiplier);
            leftX = Math.signum(gamepad.left_stick_x) * Math.pow(gamepad.left_stick_x, multiplier);
            leftY = Math.signum(gamepad.left_stick_y) * Math.pow(gamepad.left_stick_y, multiplier);
        }
        else{
            rightX = gamepad.right_stick_x;
            leftX = gamepad.left_stick_x;
            leftY = gamepad.left_stick_y;
        }

        jTheta = Math.atan2(-leftY,leftX);
        jp = Math.sqrt(leftX * leftX + leftY * leftY);
        if (jp > 1)
            jp = 1;
        theta = (jTheta + angleFromDriver - heading);

        double fl = (Math.sin(theta) + Math.cos(theta)) * jp / 2 + rightX;
        double fr = (Math.sin(theta) - Math.cos(theta)) * jp / 2 - rightX;
        double bl = (Math.sin(theta) - Math.cos(theta)) * jp / 2 + rightX;
        double br = (Math.sin(theta) + Math.cos(theta)) * jp / 2 - rightX;

        drive(
                fl,fr,bl,br
        );
    }

    public void RCDrive(int multiplier) {
        if(multiplier != 1){
            rightX = Math.signum(gamepad.right_stick_x) * Math.pow(gamepad.right_stick_x, multiplier);
            leftX = Math.signum(gamepad.left_stick_x) * Math.pow(gamepad.left_stick_x, multiplier);
            leftY = Math.signum(gamepad.left_stick_y) * Math.pow(gamepad.left_stick_y, multiplier);
        }
        else{
            rightX = gamepad.right_stick_x;
            leftX = gamepad.left_stick_x;
            leftY = gamepad.left_stick_y;
        }
        if(gamepad.dpad_down)
            DRIVE_POWER = .4;
        else
            DRIVE_POWER = 1;
        double bl = -leftY - leftX + rightX;
        double br = -leftY + leftX - rightX;
        double fl = -leftY + leftX + rightX;
        double fr = -leftY - leftX - rightX;

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

    public void resetEncoders(){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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