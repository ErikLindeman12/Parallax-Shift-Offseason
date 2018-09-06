package org.firstinspires.ftc.teamcode.GeneralRobot.genlib;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadClass {
    Gamepad gamepad;
    Button A,B,X,Y;

    public GamepadClass(Gamepad gamepad){
        this.gamepad = gamepad;
        A = new Button(gamepad.a);
        B = new Button(gamepad.b);
        X = new Button(gamepad.x);
        Y = new Button(gamepad.y);
    }

    public void update(){
        //Can just update the gamepad value if not using a toggle
        B.updateVar(gamepad.b);
        X.updateVar(gamepad.y);
        //Otherwise update the gamepad value and toggle value
        A.checkToggle(gamepad.a);
        Y.updateVar(gamepad.x);
    }
}
