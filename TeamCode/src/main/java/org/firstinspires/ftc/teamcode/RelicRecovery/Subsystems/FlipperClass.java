package org.firstinspires.ftc.teamcode.RelicRecovery.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Brown on 4/24/2018.
 */

public class FlipperClass
{
    private Hardware_Class hardwaremap;
    private Gamepad gamepad1;

    public static final double FLIPPER_POWER = -.45;

    public FlipperClass(HardwareMap hwmap, Telemetry telem, boolean usesGyro, Gamepad gamepad1val)
    {
        hardwaremap = new Hardware_Class(hwmap, telem, usesGyro, gamepad1val);
        gamepad1 = gamepad1val;
    }

    public void flipper()
    {
        if (gamepad1.dpad_up)
            hardwaremap.flipper.setPower(FLIPPER_POWER);
        else if (gamepad1.dpad_down)
            hardwaremap.flipper.setPower(-FLIPPER_POWER);
        else
            hardwaremap.flipper.setPower(0);
    }
}