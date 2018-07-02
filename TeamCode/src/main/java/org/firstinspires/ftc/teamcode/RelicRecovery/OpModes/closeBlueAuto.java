package org.firstinspires.ftc.teamcode.RelicRecovery.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RelicRecovery.Subsystems.JewelClass;
import org.firstinspires.ftc.teamcode.RelicRecovery.Subsystems.RobotClass;


@Disabled
@Autonomous(name = "Far Blue Left Auto", group = "Autonomous")
public class closeBlueAuto extends LinearOpMode {

    RobotClass robot;

    @Override
    public void runOpMode() {
        //Initialization
        robot = new RobotClass(hardwareMap, telemetry, true, true, gamepad1,Math.PI/2);
        JewelClass.allianceColor alliancecolor;
        alliancecolor = JewelClass.allianceColor.blue;
        robot.initialize();


        //When it's time to hit the jewel
        robot.jewelclass.runJewel(alliancecolor);
    }
}