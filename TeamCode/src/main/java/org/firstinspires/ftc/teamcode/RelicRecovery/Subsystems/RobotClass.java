package org.firstinspires.ftc.teamcode.RelicRecovery.Subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Brown on 4/24/2018.
 */

public class RobotClass
{
    public DriveClass driveclass;
    public IntakeClass intakeclass;
    public FlipperClass flipperclass;
    public JewelClass jewelclass;
    public HardwareClass hardwareclass;

    public RobotClass(HardwareMap hwmap, Telemetry telem, boolean usesGyro, boolean isFC, Gamepad gamepad1val, double angle)
    {
       driveclass = new DriveClass(hwmap,telem,usesGyro,isFC,gamepad1val,angle);
       intakeclass = new IntakeClass(hwmap,telem,usesGyro,gamepad1val);
       flipperclass = new FlipperClass(hwmap,telem,usesGyro,gamepad1val);
       jewelclass = new JewelClass(hwmap,telem,usesGyro,gamepad1val);
       hardwareclass = new HardwareClass(hwmap,telem,usesGyro,gamepad1val);
    }
    public void initialize(){
    flipperclass.initialize();
    jewelclass.initialize();
    }
    public void resetServos(){
        flipperclass.reset();
        jewelclass.reset();
    }
}