package org.firstinspires.ftc.teamcode.GeneralRobot.genlib;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadToggleClass extends JoyStickClass {

    public boolean toggleRStick,toggleLStick = true;
    public boolean toggleRTrig,toggleLTrig = true;
    public boolean toggleLBut,toggleRBut = true;
    public boolean toggleA,toggleB,toggleX,toggleY = true;
    public boolean toggeUp,toggleDown,toggleRight,toggleLeft = true;
    public boolean toggleStart = true;

    public boolean rStickBool,lStickBool = true;
    public boolean rTrigBool,lTrigBool = true;
    public boolean lButBool,rButBool = true;
    public boolean aBool,bBool,xBool,yBool = true;
    public boolean upBool,downBool,rightBool,leftBool = true;
    public boolean startBool = true;

    public GamepadToggleClass(Gamepad gamepad){
        super(gamepad);
    }

    public void update(){
        super.update();

        if(a && toggleA){
            toggleA = false;
            aBool = !aBool;
        }
        else if(!a && !toggleA)
            toggleA = true;

        if(b && toggleB){
            toggleB = false;
            bBool = !bBool;
        }
        else if(!b && !toggleB)
            toggleB = true;

        if(x && toggleX){
            toggleX = false;
            xBool = !xBool;
        }
        else if(!x && !toggleX)
            toggleX = true;

        if(y && toggleY){
            toggleY = false;
            yBool = !yBool;
        }
        else if(!y && !toggleY)
            toggleY = true;

        if(rStick && toggleRStick){
            toggleRStick = false;
            rStickBool = !rStickBool;
        }
        else if(!rStick && !toggleRStick)
            toggleRStick = true;

        if(lStick && toggleLStick){
           toggleLStick = false;
            lStickBool = !lStickBool;
        }
        else if(!lStick && !toggleLStick)
             toggleLStick = true;

        if(start && toggleStart){
            toggleStart = false;
            startBool = !startBool;
        }
        else if(!start && !toggleStart)
            toggleStart = true;

        if(up && toggeUp){
           toggeUp = false;
            upBool = !upBool;
        }
        else if(!up && !toggeUp)
            upBool = true;

        if(down && toggleDown){
           toggleDown = false;
            downBool = !downBool;
        }
        else if(!down && !toggleDown)
            toggleDown = true;

        if(left && toggleLeft){
           toggleLeft = false;
            leftBool = !leftBool;
        }
        else if(!left && !toggleLeft)
            toggleLeft = true;

        if(right && toggleRight){
           toggleRight = false;
            rightBool = !rightBool;
        }
        else if(!right && !toggleRight)
            toggleRight = true;

        if(lBut && toggleLBut){
           toggleLBut = false;
            lButBool = !lButBool;
        }
        else if(!lBut && !toggleLBut)
            toggleLBut = true;

        if(rBut && toggleRBut){
           toggleRBut = false;
            rButBool = !rButBool;
        }
        else if(!rBut && !toggleRBut)
            toggleRBut = true;

        if(rTrig && toggleRTrig){
           toggleRTrig = false;
            rTrigBool = !rTrigBool;
        }
        else if(!rTrig && !toggleRTrig)
            toggleRTrig = true;

        if(lTrig && toggleLTrig){
           toggleLTrig = false;
            lTrigBool = !lTrigBool;
        }
        else if(!lTrig && !toggleLTrig)
            toggleLTrig = true;
    }
}
