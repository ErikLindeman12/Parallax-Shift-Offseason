package org.firstinspires.ftc.teamcode.RelicRecovery.AutonomousFunctions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RelicRecovery.Subsystems.RobotClass;

public class Drive_Functions
{
    RobotClass robot;

    private double error;
    private boolean begin;
    private double LastTime;
    private double LastError;
    private double Integral;
    private double Derivative;
    private double currentDrivePower;
    private double Time;
    private double theta;
    private double KP = .02;
    private double KI = 0;
    private double KD = 0;

    public Drive_Functions(HardwareMap hwmap, Telemetry telem, boolean usesGyro, boolean isFC, Gamepad gamepad1val, double angle)
    {
        robot = new RobotClass(hwmap,telem,usesGyro,isFC,gamepad1val,angle);

    }
    public void turnPID(double angle){
        error = 0;
        begin = true;
        LastTime = System.currentTimeMillis();
        LastError = 0;
        Integral = 0;
        while(error>.1 || begin){
            if(begin)
                begin = false;
            robot.driveclass.updateGyro();
            error = angle-robot.driveclass.heading;
            Time = System.currentTimeMillis()-LastTime;
            Integral += error * Time;
            Derivative = (error-LastError)/Time;
            currentDrivePower= error * KP + Integral * KI + Derivative * KD;
            LastTime = System.currentTimeMillis();
            LastError = error;
            robot.driveclass.drive(-currentDrivePower,currentDrivePower,-currentDrivePower,currentDrivePower);
        }
    }
    public void strafeDistace(double direction,double power,int distance){
        robot.driveclass.updateGyro();
        theta = (direction + robot.driveclass.angleFromDriver - robot.driveclass.heading);

        double fl = (Math.sin(theta)+Math.cos(theta))*power/2;
        double fr = (Math.sin(theta)-Math.cos(theta))*power/2;
        double bl = (Math.sin(theta)-Math.cos(theta))*power/2;
        double br = (Math.sin(theta)+Math.cos(theta))*power/2;
        robot.hardwareclass.fleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.hardwareclass.fright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.hardwareclass.bleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.hardwareclass.bright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.hardwareclass.fleft.setTargetPosition((int)(distance*fl) + robot.hardwareclass.fleft.getCurrentPosition());
        robot.hardwareclass.fleft.setPower(fl*power);
        robot.hardwareclass.fright.setTargetPosition((int)(distance*fr) + robot.hardwareclass.fright.getCurrentPosition());
        robot.hardwareclass.fright.setPower(fr*power);
        robot.hardwareclass.bleft.setTargetPosition((int)(distance*bl) + robot.hardwareclass.bleft.getCurrentPosition());
        robot.hardwareclass.bleft.setPower(bl*power);
        robot.hardwareclass.bright.setTargetPosition((int)(distance*br) + robot.hardwareclass.bright.getCurrentPosition());
        robot.hardwareclass.bright.setPower(br*power);
    }
    public void testAccel(double acceleration){

    }
}