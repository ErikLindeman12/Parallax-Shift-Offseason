package org.firstinspires.ftc.teamcode.GeneralRobot.genlib;

import com.qualcomm.robotcore.hardware.Gamepad;

public class JoyStickClass {
    public Gamepad gamepad;
    public boolean rStick,lStick;
    public boolean rTrig,lTrig;
    public boolean lBut,rBut;
    public boolean a,b,x,y;
    public boolean up,down,right,left;
    public boolean start;
    public double lastLoop;
    boolean goAnyways = true;

    public JoyStickClass(Gamepad gamepad){
        this.gamepad = gamepad;
    }

    public void update(){
        if(System.currentTimeMillis()-lastLoop > 10 || goAnyways) {
            goAnyways = false;
            if (gamepad.right_stick_button)
                rStick = true;
            if (gamepad.left_stick_button)
                lStick = true;
            if (gamepad.right_bumper)
                rBut = true;
            if (gamepad.left_bumper)
                lBut = true;
            if (gamepad.a)
                a = true;
            if (gamepad.b)
                b = true;
            if (gamepad.x)
                x = true;
            if (gamepad.y)
                y = true;
            if (gamepad.start)
                start = true;
            if (gamepad.dpad_down)
                down = true;
            if (gamepad.dpad_up)
                up = true;
            if (gamepad.dpad_left)
                left = true;
            if (gamepad.dpad_right)
                right = true;
        }
        if (gamepad.right_trigger != 0)
            rTrig = true;
        if (gamepad.left_trigger != 0)
            lTrig = true;
        
        lastLoop = System.currentTimeMillis();
    }
}
