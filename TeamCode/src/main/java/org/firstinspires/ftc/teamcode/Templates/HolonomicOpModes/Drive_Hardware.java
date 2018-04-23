package org.firstinspires.ftc.teamcode.Templates.HolonomicOpModes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/*
- Name: Holonomic Drive_Hardware Map
- Creator[s]: Talon
- Date Created: 6/16/17
- Objective: To create a class that sets up the hardware map for our holonomic robot and has basic
             functions to reduce redundancies in other programs.
 */

public class Drive_Hardware {

    //Declaring variables
    public BNO055IMU gyro;
    public DcMotor fleft, fright, bleft, bright;
    private Gamepad gamepad1;
    private HardwareMap hwMap;
    private Telemetry telemetry;

    boolean FC;
    double heading;
    double jp;
    double jTheta;
    double theta;

    double angleFromDriver = Math.PI;
    double DRIVE_POWER = 0.4f;

    //Constructor; Put program's hardwaremap first, then telemetry,  then put true if gyro will be used or false if it won't
    public Drive_Hardware(HardwareMap hwmap, Telemetry telem, boolean usesGyro, boolean isFC, Gamepad gamepad1val)
    {
        gamepad1 = gamepad1val;
        hwMap = hwmap;
        telemetry = telem;

        FC = isFC;

        telemetryReadings(true,false,false);

        fleft = hwMap.dcMotor.get("fleft");
        fright = hwMap.dcMotor.get("fright");
        bleft = hwMap.dcMotor.get("bleft");
        bright = hwMap.dcMotor.get("bright");

        fright.setDirection(DcMotor.Direction.REVERSE);
        bright.setDirection(DcMotor.Direction.REVERSE);

        if(usesGyro)
        {
            gyro = hwMap.get(BNO055IMU.class, "imu");
            BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
            parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
            parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
            parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
            parameters.loggingEnabled = true;
            parameters.loggingTag = "IMU";
            parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
            gyro.initialize(parameters);
        }

        telemetryReadings(true,true,false);
    }

    double clipValue(double value)
    {
        if(value > DRIVE_POWER || value < - DRIVE_POWER)
            return ((Math.abs(value) / value) * DRIVE_POWER);
        else
            return value;
    }

    public void drive(double fl, double fr, double bl, double br) {
        fleft.setPower(clipValue(fl));
        fright.setPower(clipValue(fr));
        bleft.setPower(clipValue(bl));
        bright.setPower(clipValue(br));
    }

    public void updateGyro()
    {
         heading =  gyro.getAngularOrientation().toAxesReference(AxesReference.INTRINSIC).toAxesOrder(AxesOrder.ZYX).firstAngle;

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

    public void telemetryReadings(boolean Init,boolean Ready,boolean Driving)
    {
        if(Init)
        {
            if (Ready)
                telemetry.addData("Ready to go", true);
            else
                telemetry.addData("Ready to go", false);
        }
        if(Driving)
        {
            telemetry.addData(" Right Joystick X Axis:", gamepad1.right_stick_x);
            telemetry.addData(" Left Joystick Y Axis:", gamepad1.left_stick_y);
            telemetry.addData(" Left Joystick X Axis:", gamepad1.left_stick_x);
            if (FC) {
                telemetry.addData("Joystick Direction", Math.toDegrees(jTheta));
                telemetry.addData("Joystick Magnitude", jp);
                telemetry.addData("Gyro Heading", heading);
            }
        }
        telemetry.update();
    }
}
