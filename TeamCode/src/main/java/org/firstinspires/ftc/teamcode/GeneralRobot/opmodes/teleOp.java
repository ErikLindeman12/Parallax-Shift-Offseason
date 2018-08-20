package org.firstinspires.ftc.teamcode.GeneralRobot.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.GeneralRobot.subsystems.Robot;

@TeleOp(name="Teleop", group="Teleop")
public class teleOp extends OpMode{
    Robot robotClass;

    @Override
    public void init(){
        robotClass = new Robot(hardwareMap, gamepad1, telemetry);
    }

    @Override
    public void loop(){
        robotClass.driveclass.genDrive();
    }

}
