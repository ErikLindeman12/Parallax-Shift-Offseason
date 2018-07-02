package org.firstinspires.ftc.teamcode.RelicRecovery.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Brown on 4/24/2018.
 */

public class IntakeClass
{
    private HardwareClass hardwaremap;
    private Gamepad gamepad1;
    private double rightpower;
    private double leftpower;


    public static final double INTAKE_POWER = -1;

    public IntakeClass(HardwareMap hwmap, Telemetry telem, boolean usesGyro, Gamepad gamepad1val)
    {
        hardwaremap = new HardwareClass(hwmap, telem, usesGyro, gamepad1val);
        gamepad1 = gamepad1val;
    }


    public void intake()
    {
        if (gamepad1.left_bumper)
        {
            leftpower = INTAKE_POWER;
            rightpower = INTAKE_POWER;
        }
        else if (gamepad1.right_bumper)
        {
            leftpower = -INTAKE_POWER;
            rightpower = -INTAKE_POWER;
        }
        else
        {
            leftpower = 0;
            rightpower = 0;

        }
        runIntake(leftpower,rightpower);
    }

    public void runIntake(double leftPower, double rightPower)
    {
        hardwaremap.leftIntake.setPower(leftPower);
        hardwaremap.rightIntake.setPower(rightPower);
    }
}
