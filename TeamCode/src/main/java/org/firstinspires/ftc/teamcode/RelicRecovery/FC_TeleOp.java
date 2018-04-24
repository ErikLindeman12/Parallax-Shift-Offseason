package org.firstinspires.ftc.teamcode.RelicRecovery;

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

@TeleOp(name = " FC TeleOp", group = "TeleOp")
public class FC_TeleOp extends OpMode
{
    Drive_Hardware hwDrive;
    Robot_Hardware hwRobot;
    int multiplier = 1;
    boolean firstTime = true;

    @Override
    public void init()
    {
        hwDrive = new Drive_Hardware(hardwareMap, telemetry, true, true, gamepad1);
        hwRobot = new Robot_Hardware(hardwareMap,telemetry);
    }

    @Override
    public void loop()
    {
        if(firstTime || gamepad1.start)
        {
            hwRobot.servos();
            firstTime = false;
        }
        hwDrive.FCDrive(multiplier);


        hwDrive.telemetryReadings(false,false,true);
    }
}