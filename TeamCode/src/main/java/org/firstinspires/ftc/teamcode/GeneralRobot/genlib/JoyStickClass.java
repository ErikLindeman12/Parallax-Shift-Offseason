package org.firstinspires.ftc.teamcode.GeneralRobot.genlib;

import com.qualcomm.robotcore.hardware.Gamepad;

public class JoyStickClass {
    public Gamepad gamepad;
    public boolean rightStick,leftStick;
    public boolean rightTrigger,leftTrigger;
    public boolean leftButton,rightButton;
    public boolean aButton,bButton,xButton,yButton;
    public boolean dPadUp,dPadDown,dPadRight,dPadLeft;
    public boolean start;

    public JoyStickClass(Gamepad gamepad){
        this.gamepad = gamepad;
    }

    public void runJoystick(){
        if(gamepad.right_stick_button)
            rightStick = true;
        if(gamepad.left_stick_button)
            leftStick = true;
        if(gamepad.right_bumper)
            rightButton = true;
        if(gamepad.left_bumper)
            leftButton = true;
        if(gamepad.right_trigger != 0)
            rightTrigger = true;
        if(gamepad.left_trigger != 0)
            leftTrigger = true;
        if(gamepad.a)
            aButton = true;
        if(gamepad.b)
            bButton = true;
        if(gamepad.x)
            xButton = true;
        if(gamepad.y)
            yButton = true;
        if(gamepad.start)
            start = true;
        if(gamepad.dpad_down)
            dPadDown = true;
        if(gamepad.dpad_up)
            dPadUp = true;
        if(gamepad.dpad_left)
            dPadLeft = true;
        if(gamepad.dpad_right)
            dPadRight = true;
    }
}
