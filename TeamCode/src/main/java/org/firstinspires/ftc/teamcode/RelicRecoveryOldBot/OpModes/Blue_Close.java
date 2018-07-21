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

    public CloseableVuforiaLocalizer vuforia;

    private enum currentPhase{
        HIT_JEWEL,
        DRIVE_TO_CRYPTO
    }

    private currentPhase currentphase;

    @Override
    public void runOpMode() {
        robot = new RobotClass(hardwareMap, gamepad1, telemetry);

        robot.initializeServos();
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        CloseableVuforiaLocalizer.Parameters parameters = new CloseableVuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "Aa07QPX/////AAAAGT4IBGftwkAmodz5uX1NKehqWSuZYAizMXyJgDjbMQz+h5mPdKPRRA9id11R2ad9e3w3E6aS1Nep0aXgwwqRtAAmh6tizyQQZRM5qF+foaOh9zbuyAis/ANMODT0X5fAo3J6DqPNlOT9Es04EMKR5rIGhrb91rn3X+ferq2phtQ/PhQGHt44rkhNXSI1OV2GaY4BErnIgSktLZB6bWf49Jd3RtnybC9BfsuOv/2re0pEiGAiF+GyTV5pvuyVVFXFMKaiIR+aDe8qBpKV5z+ZUIWUC+z989ERqh9SKWdfJkOJt6glYFx/fEy3o4g8HwYfVbU+xU1fxufN+M3A2uZZaSSowVbbDDgr9CGxSd6/Dskg";
        parameters.cameraDirection = CloseableVuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = new CloseableVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relictrackable = relicTrackables.get(0);
        relictrackable.setName("relicVuMark");

        currentphase = currentPhase.HIT_JEWEL;

        waitForStart();
        while(!isStarted()) {
            relicTrackables.activate();
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relictrackable);

            if (vuMark == RelicRecoveryVuMark.UNKNOWN || vuMark == RelicRecoveryVuMark.LEFT) {
                telemetry.addData("LEFT", vuMark);
                telemetry.update();
            }
            if (vuMark == RelicRecoveryVuMark.RIGHT) {
                telemetry.addData("RIGHT", vuMark);
                telemetry.update();
            } else {
                telemetry.addData("CENTER", vuMark);
                telemetry.update();
            }
        }
        vuforia.close();

        /*switch(currentphase){
            case HIT_JEWEL:*/
                robot.jewelclass.moveDown();

                jewelColor = robot.jewelclass.scanColor();

                if(jewelColor == "RED")
                    robot.jewelclass.hitJewel("RIGHT");
                else
                    robot.jewelclass.hitJewel("LEFT");

                robot.jewelclass.reset();

                currentphase = currentPhase.DRIVE_TO_CRYPTO;
/*
                break;
        }*/

    }

}