package org.firstinspires.ftc.teamcode.GeneralRobot.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.GeneralRobot.genlib.VuforiaClass;

/**
 * Created by Brown on 4/24/2018.
 */

public class Robot
{
    public MecanumDrive driveclass;
    public VuforiaClass vuforiaclass;

    public Robot(HardwareMap hwmap, Gamepad gamepad, Telemetry telemetry) {
        driveclass = new MecanumDrive(hwmap,gamepad,telemetry);
        vuforiaclass = new VuforiaClass(hwmap,telemetry);
    }

    public void initializeServos() {
    }

    public void initializeRobot(){
        vuforiaclass.initializeVuforia();
    }
}
