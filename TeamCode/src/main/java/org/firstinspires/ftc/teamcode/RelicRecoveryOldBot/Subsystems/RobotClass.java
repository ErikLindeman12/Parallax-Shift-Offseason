package org.firstinspires.ftc.teamcode.RelicRecoveryOldBot.Subsystems;

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
    public JewelClass jewelclass;
    public FlipperClass flipperclass;
    public VuforiaClass vuforiaclass;

    public RobotClass(HardwareMap hwmap,Gamepad gamepad,Telemetry telemetry) {
        driveclass = new DriveClass(hwmap,gamepad,telemetry);
        intakeclass = new IntakeClass(hwmap,gamepad,telemetry);
        jewelclass = new JewelClass(hwmap,telemetry);
        flipperclass = new FlipperClass(hwmap,gamepad,telemetry);
        //vuforiaclass = new VuforiaClass(hwmap,telemetry);
    }

    public void initializeServos(){
        jewelclass.initialize();
        flipperclass.initialize();
    }
}
