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


@Autonomous(name = "Blue Close", group = "Autonomous")

public class Blue_Close extends LinearOpMode {

    RobotClass robot;

    public static final int CLOSE_STONE_CLOSE_SLOT = 300;
    public static final int CLOSE_STONE_MIDDLE_SLOT = 950;
    public static final int CLOSE_STONE_FAR_SLOT = 1600;

    public String strafeDistance;
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

        currentphase = currentPhase.HIT_JEWEL;

        waitForStart();
        robot.jewelclass.moveDown();
        jewelColor = robot.jewelclass.scanColor();
        if(jewelColor == "RED")
            robot.jewelclass.hitJewel("RIGHT");
        else
            robot.jewelclass.hitJewel("LEFT");
        robot.jewelclass.reset();
        currentphase = currentPhase.DRIVE_TO_CRYPTO;
    }

}