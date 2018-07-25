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
- Name: Holonomic HardwareClass Map
- Creator[s]: Talon
- Date Created: 6/16/17
- Objective: To create a class that sets up the hardware map for our holonomic robot and has basic
             functions to reduce redundancies in other programs.
 */

public class HardwareClass
{
    //Declaring variables
    public BNO055IMU gyro;
    public DcMotor frontLeft, frontRight, backLeft, backRight, leftIntake, rightIntake, flipperMotor;
    public Servo flipperServo, topServo, bottomServo,rightClamp,leftClamp;
    public ColorSensor colorSensor;
    private HardwareMap hwMap;
    private Telemetry telemetry;

    //Constructor; Put program's hardwaremap first, then telemetry,  then put true if gyro will be used or false if it won't
    public HardwareClass(HardwareMap hwmap, Telemetry telem, boolean usesGyro, Gamepad gamepad1val)
    {
        hwMap = hwmap;
        telemetry = telem;

        telemetryReadings(true,false);

        frontLeft = hwMap.dcMotor.get("frontLeft");
        frontRight = hwMap.dcMotor.get("frontRight");
        backLeft = hwMap.dcMotor.get("backLeft");
        backRight = hwMap.dcMotor.get("backRight");
        leftIntake = hwMap.dcMotor.get("leftIntake");
        rightIntake = hwMap.dcMotor.get("rightIntake");
        flipperMotor = hwMap.dcMotor.get("flipperMotor");
        

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        flipperServo = hwMap.servo.get("flipperServo");
        bottomServo = hwMap.servo.get("bottomServo");
        topServo = hwMap.servo.get("topServo");
        leftClamp = hwMap.servo.get("leftClamp");
        rightClamp = hwMap.servo.get("rightClamp");
        leftClamp.setDirection(Servo.Direction.REVERSE);

        colorSensor = hwMap.colorSensor.get("colorSensor");

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