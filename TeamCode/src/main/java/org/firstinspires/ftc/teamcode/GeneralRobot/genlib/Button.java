package org.firstinspires.ftc.teamcode.GeneralRobot.genlib;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Button {
    boolean gamepadValue;
    boolean toggle = true;
    boolean toggleBool = true;

    public Button(boolean gamepadValue) {
        this.gamepadValue = gamepadValue;
    }

    public void updateVar(boolean gamepadValue){
        this.gamepadValue = gamepadValue;
    }

    public void checkToggle(boolean gamepadValue){
        this.gamepadValue = gamepadValue;

        if(toggle &&  gamepadValue){
            toggle = false;
            toggleBool = !toggleBool;
        }
        else if(!toggle && !gamepadValue)
            toggle = true;
    }

    public boolean getToggle(){
        return toggleBool;
    }

    public boolean getgamepadVal(){
        return gamepadValue;
    }
}