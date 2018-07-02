package org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.OpModes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.Subsystems.RobotClass;

@Autonomous(name = "test", group = "Autonomous")

public class PidTest extends LinearOpMode {
    RobotClass robot;

    public void runOpMode() {
        robot = new RobotClass(hardwareMap,true, gamepad1,Math.PI);
        waitForStart();
        robot.driveclass.runPID(Math.PI/2);
    }

}
