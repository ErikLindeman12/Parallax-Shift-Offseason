package org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class DriveFunctionClass extends DriveClass {
    double angle;

    public DriveFunctionClass(HardwareMap hwmap, Gamepad gamepad, Telemetry telemetry) {
        super(hwmap,gamepad,telemetry);
    }
    public void runPID(double angle) {
        this.angle = angle+angleFromDriver;
        error = 0;
        LastTime = System.currentTimeMillis();
        LastError = 0;
        Integral = 0;
        do{
            updateGyro();
            error = angle - heading;
            telemetry.addData("Error",error);
            telemetry.addData("Ideal Angle",angle);
            telemetry.update();
            Time = System.currentTimeMillis() - LastTime;
            Integral += error * Time;
            Derivative = (error - LastError) / Time;
            currentDrivePower = error * KP + Integral * KI + Derivative * KD;
            LastTime = System.currentTimeMillis();
            LastError = error;
            drive(-currentDrivePower, currentDrivePower, -currentDrivePower, currentDrivePower);
        }while (true);
    }
}
