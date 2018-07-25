package org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class JewelClass
{
    private HardwareMap hardwareMap;
    public Telemetry telemetry;

    public Servo topServo,bottomServo;
    public ColorSensor color;

    public static final double TOP_STARTING_POSITION = .3;
    public static final double BOTTOM_ENDING_POSITION = .65;
    public static final double BOTTOM_STARTING_POSITION = 0.05;
    public static final double TOP_MIDDLE_POSITION = .77;
    public static final double TOP_RIGHT_POSITION = .6;
    public static final double TOP_LEFT_POSITION = .9;


    public JewelClass(HardwareMap hwmap,Telemetry telemetry)
    {
        this.hardwareMap = hwmap;
        this.telemetry = telemetry;

        topServo = hardwareMap.servo.get("topServo");
        bottomServo = hardwareMap.servo.get("bottomServo");

        color = hardwareMap.colorSensor.get("color");

        bottomServo.setDirection(Servo.Direction.REVERSE);
    }

    public void initialize()
    {
        reset();

    }

    public void reset(){
        topServo.setPosition(TOP_STARTING_POSITION);
        bottomServo.setPosition(BOTTOM_STARTING_POSITION);
    }

    public void moveDown(){
        bottomServo.setPosition(BOTTOM_ENDING_POSITION);
        topServo.setPosition(TOP_MIDDLE_POSITION);
    }

    public String scanColor(){
        if(color.red()> color.blue())
            return "RED";
        else
            return "BLUE";
    }

    public void hitJewel(String direction){
        if(direction == "RIGHT")
            topServo.setPosition(TOP_RIGHT_POSITION);
        else
            topServo.setPosition(TOP_LEFT_POSITION);
    }

}