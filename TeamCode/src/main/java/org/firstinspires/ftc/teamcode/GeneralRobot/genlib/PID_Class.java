package org.firstinspires.ftc.teamcode.GeneralRobot.genlib;

public class PID_Class {
    public double KP,KI,KD,currentAngle;
    double LastTime = 0;
    double Integral = 0;
    double  LastError = 0;
    double error;
    public double currentDrivePower;

    public PID_Class(double KP){
        this.KP=KP;
        this.KI=0;
        this.KD=0;
    }

    public PID_Class(double KP,double KI,double KD){
        this.KP=KP;
        this.KI=KI;
        this.KD=KD;
    }


    public void resetCoefficients()
    {
        LastTime = 0;
        Integral = 0;
        LastError = 0;
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

    public void setPIDPower(double destinationAngle, double currentAngle){
        this.currentAngle = currentAngle;
        error = (destinationAngle-currentAngle);
        error = (error +360)%360;
        if (error > 180)
            error -= 360;
        double Time = System.currentTimeMillis()-LastTime;
        Integral += error * Time;
        double Derivative = (error-LastError)/Time;
        currentDrivePower = error * KP + Integral * KI + Derivative * KD;
        LastTime = System.currentTimeMillis();
        LastError = error;
    }

    public double getPIDPower(){
        return currentDrivePower;
    }

    public double getError(){
        return error;
    }

    public boolean checkErrorLinear(double errorThreshhold){
        if(error>errorThreshhold)
            return true;
        else
            return false;
    }

    public boolean checkErrorScalar(double errorThreshhold){
        if((error/(2*Math.PI))>errorThreshhold)
            return true;
        else
            return false;
    }
}
