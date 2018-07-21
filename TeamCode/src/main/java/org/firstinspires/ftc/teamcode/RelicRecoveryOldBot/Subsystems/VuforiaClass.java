package org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.Subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.genlib.CloseableVuforiaLocalizer;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class VuforiaClass {
    private HardwareMap hardwaremap;
    private Telemetry telemetry;

    public CloseableVuforiaLocalizer vuforia;
    public VuforiaTrackable relictrackable;
    public VuforiaTrackables relicTrackables;

    public VuforiaClass(HardwareMap hwMap, Telemetry telemetry) {
        hardwaremap = hwMap;
        this.telemetry = telemetry;

        int cameraMonitorViewId = hardwaremap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwaremap.appContext.getPackageName());
        CloseableVuforiaLocalizer.Parameters parameters = new CloseableVuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "Aa07QPX/////AAAAGT4IBGftwkAmodz5uX1NKehqWSuZYAizMXyJgDjbMQz+h5mPdKPRRA9id11R2ad9e3w3E6aS1Nep0aXgwwqRtAAmh6tizyQQZRM5qF+foaOh9zbuyAis/ANMODT0X5fAo3J6DqPNlOT9Es04EMKR5rIGhrb91rn3X+ferq2phtQ/PhQGHt44rkhNXSI1OV2GaY4BErnIgSktLZB6bWf49Jd3RtnybC9BfsuOv/2re0pEiGAiF+GyTV5pvuyVVFXFMKaiIR+aDe8qBpKV5z+ZUIWUC+z989ERqh9SKWdfJkOJt6glYFx/fEy3o4g8HwYfVbU+xU1fxufN+M3A2uZZaSSowVbbDDgr9CGxSd6/Dskg";
        parameters.cameraDirection = CloseableVuforiaLocalizer.CameraDirection.FRONT;
        this.vuforia = new CloseableVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relictrackable = relicTrackables.get(0);
        relictrackable.setName("relicVuMark");

    }


    public String vuMarkPosition() {
        relicTrackables.activate();
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relictrackable);

        if (vuMark == RelicRecoveryVuMark.UNKNOWN || vuMark == RelicRecoveryVuMark.LEFT) {
            telemetry.addData("LEFT", vuMark);
            telemetry.update();
            return "LEFT";
        }
            if (vuMark == RelicRecoveryVuMark.RIGHT) {
                telemetry.addData("RIGHT", vuMark);
                telemetry.update();
                return "RIGHT";
            }
        else{
                telemetry.addData("CENTER", vuMark);
                telemetry.update();
                return "CENTER";
            }
        }
    }
