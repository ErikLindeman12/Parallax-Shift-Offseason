package org.firstinspires.ftc.teamcode.RelicRecovery.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Brown on 4/24/2018.
 */

public class JewelClass
{
    private HardwareClass hardwaremap;
    private Gamepad gamepad1;

    public JewelClass(HardwareMap hwmap, Telemetry telem, boolean usesGyro, Gamepad gamepad1val)
    {
        hardwaremap = new HardwareClass(hwmap, telem, usesGyro, gamepad1val);
        gamepad1 = gamepad1val;
    }

    public void Initialize()
    {
        hardwaremap.bottomServo.setPosition(0);
        hardwaremap.topServo.setPosition(0.25);
    }
}