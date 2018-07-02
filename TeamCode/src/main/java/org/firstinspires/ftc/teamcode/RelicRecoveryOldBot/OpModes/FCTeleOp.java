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

@TeleOp(name = "Holonomic FC TeleOp", group = "Holonomic")
public class FCTeleOp extends OpMode
{
    RobotClass robot;
    int multiplier = 2;

    @Override
    public void init()
    {
        robot = new RobotClass(hardwareMap,true, gamepad1,0);
    }

    @Override
    public void loop()
    {
        robot.driveclass.FCDrive(multiplier);
    }
}