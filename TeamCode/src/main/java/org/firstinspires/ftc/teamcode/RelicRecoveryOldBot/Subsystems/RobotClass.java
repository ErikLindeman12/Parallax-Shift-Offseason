package org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Brown on 4/24/2018.
 */

public class RobotClass
{
    public DriveFunctionClass driveclass;

    public RobotClass(HardwareMap hwmap, boolean isFC, Gamepad gamepad, double angle) {
        driveclass = new DriveFunctionClass(hwmap,isFC, gamepad, angle);
    }
}
