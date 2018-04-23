package org.firstinspires.ftc.teamcode.Templates.HolonomicOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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
public class Holonomic_FC extends OpMode
{
    Hardware robot;
    int multiplier = 1;

    @Override
    public void init()
    {
        robot = new Hardware(hardwareMap, telemetry, true, true, gamepad1);
    }

    @Override
    public void loop()
    {
        robot.FCDrive(multiplier);
        robot.telemetryReadings(false,false,true);
    }
}