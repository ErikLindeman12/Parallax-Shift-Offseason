package org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.Subsystems.RobotClass;

/*
- Name:
- Creator[s]:
- Date Created:
- Objective:
- Controls:
- Sensor Usage:
- Key Algorithms:
- Uniqueness:
- Possible Improvements:
 */

@TeleOp(name = "Holonomic RC TeleOp", group = "Holonomic")
public class RCTeleOp extends OpMode
{
    RobotClass robot;

    int multiplier = 1;

    @Override
    public void init()
    {
        robot = new RobotClass(hardwareMap,gamepad1, telemetry);
        robot.initializeServos();
    }

    @Override
    public void loop()
    {
        robot.driveclass.RCDrive(multiplier);
        robot.intakeclass.Intake();
        robot.flipperclass.Flipper();

        if(gamepad1.x)
            robot.initializeServos();
    }
}