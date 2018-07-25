package org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.OpModes;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.Subsystems.RobotClass;
import org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.genlib.CloseableVuforiaLocalizer;


@Autonomous(name = "Use this", group = "Autonomous")

public class Gud extends LinearOpMode {

    RobotClass robot;

    public static final int CLOSE_STONE_CLOSE_SLOT = 300;
    public static final int CLOSE_STONE_MIDDLE_SLOT = 950;
    public static final int CLOSE_STONE_FAR_SLOT = 1600;

    public int strafeDistance = 300;
    public String jewelColor;

    /*public CloseableVuforiaLocalizer vuforia;*/

    private enum currentPhase{
        HIT_JEWEL,
        DRIVE_TO_CRYPTO
    }

    private currentPhase currentphase;

    @Override
    public void runOpMode() {
        robot = new RobotClass(hardwareMap, gamepad1, telemetry);

        robot.initializeServos();
        robot.driveclass.resetEncoders();

        currentphase = currentPhase.HIT_JEWEL;

        waitForStart();
        robot.jewelclass.moveDown();
        sleep(2000);
        jewelColor = robot.jewelclass.scanColor();
        if(jewelColor == "RED")
            robot.jewelclass.hitJewel("RIGHT");
        else
            robot.jewelclass.hitJewel("LEFT");
        sleep(800);
        robot.jewelclass.reset();
        sleep(800);
        currentphase = currentPhase.DRIVE_TO_CRYPTO;

        robot.driveclass.drive(1,1,1,1);
        sleep(150);
        robot.driveclass.brake();

        /*robot.driveclass.drive(.2,-.3,-.2,.3);
        sleep(600);
        robot.driveclass.brake();
        robot.driveclass.drive(.3,-.3,-.3,.3);
        sleep(1000);
        robot.driveclass.brake();
        robot.intakeclass.runIntake(-1,-1);
        sleep(5000);
        robot.intakeclass.runIntake(0,0);
        robot.driveclass.drive(-.2,-.3,-.2,-.3);
        sleep(800);
        robot.driveclass.brake();*/
        /*telemetry.addData("State", "Drive until Tilted");
        telemetry.update();
        do {``
            robot.driveclass.updateGyro();
            idle();
        }
        while(robot.driveclass.yRotation > Math.toRadians(-2) && opModeIsActive());
        telemetry.addData("State", "Drive until not Tilted");
        telemetry.update();
        sleep(250);
        do {
            robot.driveclass.updateGyro();
            idle();
        }
        while(robot.driveclass.yRotation < Math.toRadians(-2) && opModeIsActive());
        robot.driveclass.brake();*/
/*
        robot.driveclass.setDriveEncoders(.4,.4,.4,.4, 400,400,400,400);
        while(robot.driveclass.frontLeft.isBusy() && robot.driveclass.frontRight.isBusy() && opModeIsActive())
            idle();
        robot.driveclass.brake();

        robot.driveclass.updateGyro();
        double error = robot.driveclass.heading;
        while(error > Math.toRadians(6) && opModeIsActive()) {
            robot.driveclass.updateGyro();
            error = robot.driveclass.heading;
            robot.driveclass.drive( .1 + error * 0.4 / (Math.PI/2), - .1 - error * 0.4 / (Math.PI/2),  .1 + error * 0.4 / (Math.PI/2), - .1 - error * 0.4 / (Math.PI/2));
            idle();
        }
        robot.driveclass.brake();

        robot.driveclass.setDriveEncoders(-.4, -.4, -.4, -.4, -900, -900, -900, -900);
        while (robot.driveclass.frontLeft.isBusy() && robot.driveclass.frontRight.isBusy() && opModeIsActive())
            idle();
        robot.driveclass.brake();

        robot.driveclass.setDriveEncoders(.2, -.2, -.2, .2, strafeDistance, -strafeDistance, -strafeDistance, strafeDistance);
        while (robot.driveclass.frontLeft.isBusy() && robot.driveclass.frontRight.isBusy() && opModeIsActive())
            idle();
        robot.driveclass.brake();*/
    }


}
