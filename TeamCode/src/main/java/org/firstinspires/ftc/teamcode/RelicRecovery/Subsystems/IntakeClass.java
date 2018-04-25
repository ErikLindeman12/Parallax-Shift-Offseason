package org.firstinspires.ftc.teamcode.RelicRecovery.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Brown on 4/24/2018.
 */

public class IntakeClass
{
    private Hardware_Class hardwaremap;
    private Gamepad gamepad1;

    public static final double INTAKE_POWER = -1;

    public IntakeClass(HardwareMap hwmap, Telemetry telem, boolean usesGyro, Gamepad gamepad1val)
    {
        hardwaremap = new Hardware_Class(hwmap, telem, usesGyro, gamepad1val);
        gamepad1 = gamepad1val;
    }

    public void deployIntake()
    {
        hardwaremap.leftIntakeArm.setPosition(.755);
        hardwaremap.rightIntakeArm.setPosition(.845);
    }

    public void farOut()
    {
        hardwaremap.leftIntakeArm.setPosition(.9);
        hardwaremap.rightIntakeArm.setPosition(.9);
    }

    public void intake()
    {
        if (gamepad1.x)
            farOut();
        if (gamepad1.b)
            deployIntake();

        if (gamepad1.left_bumper)
        {
            double leftpower = INTAKE_POWER;
            double rightpower = INTAKE_POWER;
            runIntake(leftpower,rightpower);
        }
        else if (gamepad1.right_bumper)
        {
            double leftpower = -INTAKE_POWER;
            double rightpower = -INTAKE_POWER;
            runIntake(leftpower,rightpower);
        }
        else
        {
            double leftpower = 0.15 * (gamepad1.left_trigger - gamepad1.right_trigger);
            double rightpower = 0.15 * ( - gamepad1.left_trigger + gamepad1.right_trigger);
            runIntake(leftpower,rightpower);
        }
    }

    public void runIntake(double leftPower, double rightPower)
    {
        hardwaremap.lintake.setPower(leftPower);
        hardwaremap.rintake.setPower(rightPower);
    }
}
