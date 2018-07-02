package org.firstinspires.ftc.teamcode.RelicRecovery.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Brown on 4/24/2018.
 */

public class FlipperClass
{
    private HardwareClass hardwaremap;
    private Gamepad gamepad1;

    public static final double FLIPPER_POWER = -.45;
    public static final double CLAMP_POSITION = .3;
    public static final double FLIPPER_POSITION = .5;
    public boolean flipped,toggle,clamped = false;

    public FlipperClass(HardwareMap hwmap, Telemetry telem, boolean usesGyro, Gamepad gamepad1val)
    {
        hardwaremap = new HardwareClass(hwmap, telem, usesGyro, gamepad1val);
        gamepad1 = gamepad1val;
    }
    public void initialize(){
        reset();
    }
    public void flipper(){
        flipFlipper();
        clampClamps();
        runFlipper();
    }

    public void flipFlipper(){
        if(gamepad1.a && !toggle){
            toggle = true;
            if(flipped)
                flipDown();
            else
                flipUp();
        }
        else if(!gamepad1.a && toggle)
            toggle = false;
    }

    public void clampClamps(){
        if(gamepad1.b && !toggle){
            toggle = true;
            if(clamped)
                clampDown();
            else
                clampUp();
        }
        else if(!gamepad1.b && toggle)
            toggle = false;
    }

    public void runFlipper(){
        if(gamepad1.left_trigger != 0)
             hardwaremap.flipperMotor.setPower(FLIPPER_POWER*gamepad1.left_trigger);
        else if(gamepad1.right_trigger != 0)
            hardwaremap.flipperMotor.setPower(FLIPPER_POWER*-gamepad1.right_trigger);
        else
            hardwaremap.flipperMotor.setPower(0);
    }

    public void flipUp(){
        clampDown();
        if(!flipped){
            hardwaremap.flipperServo.setPosition(FLIPPER_POSITION);
            flipped = true;
        }
    }
    public void flipDown(){
        clampUp();
        if(flipped){
            hardwaremap.flipperServo.setPosition(0);
            flipped = false;
        }
    }

    public void clampDown(){
        if(!clamped){
            hardwaremap.leftClamp.setPosition(CLAMP_POSITION);
            hardwaremap.rightClamp.setPosition(CLAMP_POSITION);
            clamped = true;
        }
    }
    public void clampUp(){
        if(clamped){
            hardwaremap.leftClamp.setPosition(0);
            hardwaremap.rightClamp.setPosition(0);
            clamped = false;
        }
    }
    public void reset(){
        flipDown();
    }
}