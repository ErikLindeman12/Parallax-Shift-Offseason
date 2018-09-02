package org.firstinspires.ftc.teamcode.GeneralRobot.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.GeneralRobot.genlib.PIDClass;
import org.firstinspires.ftc.teamcode.GeneralRobot.subsystems.Robot;

@Autonomous(name="PID Test",group="Auto Tests")
public class TestPID extends LinearOpMode{

    PIDClass drivePID;
    Robot robotclass;
    @Override
    public void runOpMode() {
        drivePID = new PIDClass(.8  ,0,.2);
        robotclass = new Robot(hardwareMap, gamepad1, telemetry);
        waitForStart();
        do {
            robotclass.driveclass.updateGyro();
            drivePID.setPIDPower(140, robotclass.driveclass.heading,true);
            double[] drivePowers = {drivePID.getPIDPower(), -drivePID.getPIDPower(), drivePID.getPIDPower(), -drivePID.getPIDPower()};
            robotclass.driveclass.drive(drivePowers);
        }while(drivePID.checkErrorLinear(.2) && opModeIsActive());
        robotclass.driveclass.brake();
        sleep(5000);
    }
}