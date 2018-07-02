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
    public static final double TOP_STARTING_POSITION = .25;
    public static final double BOTTOM_ENDING_POSITION = .5;
    public static final double TOP_RIGHT_POSITION = .5;
    public static final double TOP_LEFT_POSITION = 0;

    public enum direction{
        right,
        left
    }

    public enum color{
        red,
        blue
    }
    public enum allianceColor{
        red,
        blue
    }
    private allianceColor alliancecolor;
    private direction flickDirection;
    private color jewelColor;

    public JewelClass(HardwareMap hwmap, Telemetry telem, boolean usesGyro, Gamepad gamepad1val)
    {
        hardwaremap = new HardwareClass(hwmap, telem, usesGyro, gamepad1val);
        gamepad1 = gamepad1val;
    }

    public void initialize()
    {
        reset();

    }

    public void reset(){
        hardwaremap.topServo.setPosition(TOP_STARTING_POSITION);
        hardwaremap.bottomServo.setPosition(0);
    }

    public void moveDown(){
        hardwaremap.bottomServo.setPosition(BOTTOM_ENDING_POSITION);
    }

    public void detectDirection(){
        if(allianceColor.blue == alliancecolor){
            switch (jewelColor){
                case blue:
                    flickDirection = direction.right;
                case red:
                    flickDirection = direction.left;
            }
        }
        else{
            switch (jewelColor){
                case blue:
                    flickDirection = direction.left;
                case red:
                    flickDirection = direction.right;
            }
        }
    }

    public void flick(){
        detectDirection();
        switch (flickDirection){
            case right:
                hardwaremap.topServo.setPosition(TOP_RIGHT_POSITION);
            case left:
                hardwaremap.topServo.setPosition(TOP_LEFT_POSITION);
        }
    }

    public void detectColor(){
            if(hardwaremap.colorSensor.blue()>hardwaremap.colorSensor.red()){
                jewelColor = color.blue;
            }
            else{
                jewelColor = color.red;
            }
    }
    public void runJewel(allianceColor currentalliancecolor){
        alliancecolor = currentalliancecolor;
        moveDown();
        detectColor();
        flick();
        reset();
    }
}