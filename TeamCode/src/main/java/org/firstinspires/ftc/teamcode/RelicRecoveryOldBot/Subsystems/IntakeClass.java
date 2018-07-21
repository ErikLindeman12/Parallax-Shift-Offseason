package org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class IntakeClass {
    private HardwareMap hardwareMap;
    public Telemetry telemetry;
    private Gamepad gamepad;

    public DcMotor leftIntake,rightIntake;

    public float intakePower = 1f;

    public IntakeClass(HardwareMap hwmap, Gamepad gamepad, Telemetry telemetry) {
        this.hardwareMap = hwmap;
        this.gamepad = gamepad;
        this.telemetry = telemetry;

        leftIntake= hardwareMap.dcMotor.get("leftIntake");
        rightIntake = hardwareMap.dcMotor.get("rightIntake");

        leftIntake.setDirection(DcMotor.Direction.REVERSE);
    }

    public void runIntake(double power1,double power2){
        leftIntake.setPower(power1);
        rightIntake.setPower(power2);
    }

    public void Intake(){
        if(gamepad.left_bumper)
            runIntake(intakePower,intakePower);
        else if(gamepad.right_bumper)
            runIntake(-intakePower,-intakePower);
        else if(gamepad.dpad_right)
            runIntake(0,-.5*intakePower);
        else if(gamepad.dpad_left)
            runIntake(-.5*intakePower,0);
        else
            runIntake(0,0);

    }
}
