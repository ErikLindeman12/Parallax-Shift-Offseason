package org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FlipperClass {
    private HardwareMap hardwareMap;
    public Telemetry telemetry;
    private Gamepad gamepad;

    public DcMotor flipperMotor;
    public Servo flipperServo/*,clampRight,clampLeft*/;

    public float flipperPower = 1f;
    public double flippedPosition = .375;
    public double restingPosition = .83;

    private boolean flipToggle = true;
    public boolean isFlipped = false;

    public FlipperClass(HardwareMap hwmap, Gamepad gamepad, Telemetry telemetry) {
        this.hardwareMap = hwmap;
        this.gamepad = gamepad;
        this.telemetry = telemetry;

        flipperMotor = hardwareMap.dcMotor.get("flipperMotor");
        flipperServo = hardwareMap.servo.get("flipperServo");
    }
    public void Flipper(){
        flipperLift((gamepad.right_trigger - gamepad.left_trigger) * flipperPower);
        Flip();
    }

    public void Flip() {
        if (gamepad.a && flipToggle) {
            flipToggle = false;

            if (isFlipped) {
                flipperServo.setPosition(flippedPosition);
                isFlipped = false;
            } else {
                isFlipped = true;
                flipperServo.setPosition(restingPosition);
            }
        }
        else if (!flipToggle && !gamepad.a)
            flipToggle = true;
    }

    public void flipperLift(double power){
        flipperMotor.setPower(power);
    }

    public void initialize()
    {
        flipperServo.setPosition(restingPosition);
        isFlipped = false;
    }

}
