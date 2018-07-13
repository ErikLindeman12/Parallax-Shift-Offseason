package org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.OpModes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.Subsystems.RobotClass;

@Autonomous(name = "test", group = "Autonomous")

public class PidTest extends LinearOpMode {
    RobotClass robot;
    double error;
    double LastTime;
    double LastError;
    double Integral;
    double Derivative;
    double currentDrivePower;
    double angle;
    double Time;
    double KP = .35;
    double KI = 0;
    double KD = 0;

    public void runOpMode() {
        robot = new RobotClass(hardwareMap,gamepad1,telemetry);
        waitForStart();
        angle = 3*Math.PI/2;
        error = 0;
        LastTime = System.currentTimeMillis();
        LastError = 0;
        Integral = 0;
        do{
            robot.driveclass.updateGyro();
            error = robot.driveclass.heading - angle;
            telemetry.addData("Error",error);
            telemetry.addData("Ideal Angle",angle);
            telemetry.update();
            Time = System.currentTimeMillis() - LastTime;
            Integral += error * Time;
            Derivative = (error - LastError) / Time;
            currentDrivePower = error * KP + Integral * KI + Derivative * KD;
            LastTime = System.currentTimeMillis();
            LastError = error;
            robot.driveclass.drive(-currentDrivePower, currentDrivePower, -currentDrivePower, currentDrivePower);
        }while (Math.abs(error)>.1 && opModeIsActive());
    }

}