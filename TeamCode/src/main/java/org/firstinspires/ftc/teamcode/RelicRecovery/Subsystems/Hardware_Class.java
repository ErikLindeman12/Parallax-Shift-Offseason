package org.firstinspires.ftc.teamcode.RelicRecovery.Subsystems;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/*
- Name: Holonomic Hardware_Class Map
- Creator[s]: Talon
- Date Created: 6/16/17
- Objective: To create a class that sets up the hardware map for our holonomic robot and has basic
             functions to reduce redundancies in other programs.
 */

public class Hardware_Class
{
    //Declaring variables
    public BNO055IMU gyro;
    public DcMotor fleft, fright, bleft, bright, lintake, rintake, flipper;
    public Servo leftIntakeArm, rightIntakeArm, topServo, bottomServo;
    public ColorSensor colorSensor;
    private HardwareMap hwMap;
    private Telemetry telemetry;

    //Constructor; Put program's hardwaremap first, then telemetry,  then put true if gyro will be used or false if it won't
    public Hardware_Class(HardwareMap hwmap, Telemetry telem, boolean usesGyro, Gamepad gamepad1val)
    {
        hwMap = hwmap;
        telemetry = telem;

        telemetryReadings(true,false);

        fleft = hwMap.dcMotor.get("fleft");
        fright = hwMap.dcMotor.get("fright");
        bleft = hwMap.dcMotor.get("bleft");
        bright = hwMap.dcMotor.get("bright");
        lintake = hwMap.dcMotor.get("lintake");
        rintake = hwMap.dcMotor.get("rintake");
        flipper = hwMap.dcMotor.get("flipper");

        fright.setDirection(DcMotor.Direction.REVERSE);
        bright.setDirection(DcMotor.Direction.REVERSE);

        leftIntakeArm= hwMap.servo.get("left intake arm");
        rightIntakeArm = hwMap.servo.get("right intake arm");
        bottomServo = hwMap.servo.get("bottom servo");
        topServo = hwMap.servo.get("top servo");

        rightIntakeArm.setDirection(Servo.Direction.REVERSE);

        colorSensor = hwMap.colorSensor.get("color sensor");

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

        telemetryReadings(true,true);
    }

    public void telemetryReadings(boolean Init,boolean Ready)
    {
        if(Init)
        {
            if (Ready)
                telemetry.addData("Ready to go", true);
            else
                telemetry.addData("Ready to go", false);
        }
        telemetry.update();
    }
}
