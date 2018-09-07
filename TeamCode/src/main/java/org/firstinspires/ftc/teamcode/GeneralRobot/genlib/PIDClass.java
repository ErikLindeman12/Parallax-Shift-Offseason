package org.firstinspires.ftc.teamcode.GeneralRobot.genlib;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDClass {
    double KP,KI,KD;
    double lastTime = 0;
    double integral = 0;
    double  lastError;
    double error;
    double currentDrivePower;
    ElapsedTime timer;

    public PIDClass(double KP){
        this.KP=KP;
        this.KI=0;
        this.KD=0;
    }

    public PIDClass(double KP, double KI, double KD){
        this.KP=KP;
        this.KI=KI;
        this.KD=KD;
    }


    public void resetCoefficients()
    {
        lastTime = 0;
        integral = 0;
        lastError = 0;
    }

    public void changeCoefficients(double KP,double KI, double KD){
        this.KP = KP;
        this.KI = KI;
        this.KD = KD;
    }

    public void changeKI(double KI){
        this.KI = KI;
    }

    public void changeKP(double KP){
        this.KP = KP;
    }

    public void changeKD(double KD){
        this.KD = KD;
    }

    public double getKP(){
        return KP;
    }

    public double getKI(){
        return KI;
    }

    public double getKD(){
        return KD;
    }

    public void setPIDPower(double desired, double current,boolean isAngular){
        if(lastTime == -1){
            timer.reset();
            lastTime = 0;
        }
        error = (desired-current);
        if(isAngular) {
            error = (error + 360) % 360;
            if (error > 180)
                error -= 360;
        }
        double Time = timer.seconds()-lastTime;
        integral += error * Time;
        double Derivative = (error-lastError)/Time;
        currentDrivePower = error * KP + integral * KI + Derivative * KD;
        lastTime = timer.seconds();
        lastError = error;
    }

    public double getPIDPower(){
        return currentDrivePower;
    }

    public double getError(){
        return error;
    }

    public boolean checkErrorLinear(double errorThreshhold){
        if(Math.abs(error)>errorThreshhold)
            return true;
        else
            return false;
    }

    public boolean checkErrorScalar(double errorThreshhold){
        if((Math.abs(error)/360)>errorThreshhold)
            return true;
        else
            return false;
    }
}
