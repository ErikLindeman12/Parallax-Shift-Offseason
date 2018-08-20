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
    Robot robotClass;
    @Override
    public void runOpMode() {
        drivePID = new PIDClass(.5);
        robotClass = new Robot(hardwareMap, gamepad1, telemetry);
        waitForStart();
        int x = 1;
        do {
            robotClass.driveclass.updateGyro();
            telemetry.addData("heading",robotClass.driveclass.heading);
            telemetry.addData("loop number",x);
            x+=1;
            telemetry.update();
            drivePID.setPIDPower(Math.PI/2, robotClass.driveclass.heading);
            double[] drivePowers = {-drivePID.getPIDPower(), drivePID.getPIDPower(), -drivePID.getPIDPower(), drivePID.getPIDPower()};
            robotClass.driveclass.drive(drivePowers);
        }while(drivePID.checkErrorLinear(0.0872665) && opModeIsActive());
        telemetry.addData("heading",drivePID.getError());
        telemetry.update();
        sleep(3000);
        robotClass.driveclass.brake();
        sleep(3000);
    }
}