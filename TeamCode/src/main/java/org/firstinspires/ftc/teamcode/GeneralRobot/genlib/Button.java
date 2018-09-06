package org.firstinspires.ftc.teamcode.GeneralRobot.genlib;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Button {
    public boolean gamepadValue;
    public boolean toggle;
    public boolean toggleBool;

    public Button(boolean gamepadValue,) {
        this.gamepadValue = gamepadValue;
    }

    public boolean getToggle(){
        return toggle;
    }

    public boolean getToggleBool(){
        return toggleBool;
    }

    public void update(){
    }
}