package org.firstinspires.ftc.teamcode.GeneralRobot.genlib;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadClass {
    Gamepad gamepad;
    public Button A, B, X, Y;
    boolean begin = true;
    double  LastTime = 0;
    ElapsedTime timer;


    public GamepadClass(Gamepad gamepad) {
        this.gamepad = gamepad;
        A = new Button(gamepad.a);
        B = new Button(gamepad.b);
        X = new Button(gamepad.x);
        Y = new Button(gamepad.y);
        timer.reset();
    }

    public void update() {
        if (timer.milliseconds() - LastTime > 10){
            begin = false;
            //Can just update the gamepad value if not using a toggle
            B.updateVar(gamepad.b);
            X.updateVar(gamepad.x);
            //Otherwise update the gamepad value and toggle value
            A.checkToggle(gamepad.a);
            Y.updateVar(gamepad.y);
            LastTime = timer.milliseconds();
        }
    }
}
