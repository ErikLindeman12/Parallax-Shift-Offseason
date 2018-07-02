package org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveFunctionClass extends DriveClass {
    double angle;

    public DriveFunctionClass(HardwareMap hwmap, boolean isFC, Gamepad gamepad1val, double angle) {
        super(hwmap, isFC, gamepad1val, angle);
    }
    public void runPID(double angle) {
        this.angle = angle+angleFromDriver;
        error = 1;
        LastTime = System.currentTimeMillis();
        LastError = 0;
        Integral = 0;
        while (error  > .1) {
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
