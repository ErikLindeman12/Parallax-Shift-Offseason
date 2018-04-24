package org.firstinspires.ftc.teamcode.RelicRecovery;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;

public class Robot_Hardware
{
    private HardwareMap hwmap;
    private Telemetry telem;

    public Robot_Hardware(HardwareMap hardwaremap,Telemetry telemetry)
    {
        hwmap = hardwaremap;
        telem = telemetry;

    }
}
