package org.firstinspires.ftc.teamcode.RelicRecovery.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RelicRecovery.Subsystems.RobotClass;

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
@Disabled
@TeleOp(name = "Holonomic FC TeleOp Far Red", group = "Holonomic")
public class FCTeleOpFarRed extends OpMode
{
    RobotClass robot;
    int multiplier = 2;
    boolean firstTime = true;

    @Override
    public void init()
    {
        robot = new RobotClass(hardwareMap, telemetry, true, true, gamepad1,0);
    }

    @Override
    public void loop()
    {
        if(firstTime == true || gamepad1.start)
        {
            robot.initialize();
            firstTime = false;
        }

        robot.driveclass.FCDrive(multiplier);
        robot.intakeclass.intake();
        robot.flipperclass.flipper();
        robot.driveclass.telemetryReadings();
    }
}