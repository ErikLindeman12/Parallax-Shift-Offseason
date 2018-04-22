package org.firstinspires.ftc.teamcode.Templates.HolonomicOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/*
- Name: Holonomic Field-Centric Tele-Op
- Creator[s]: Erik
- Date Created: 10/23/17
- Objective: To drive our holonomic robot around in a field centric manner, which is easier for drivers
           since they don't have to keep track of the front of the robot.
- Controls: The left joystick controls translational movement, while the right joystick controls rotation.
          and holding both bumpers sends the robot into ultra-turbo mode, which enhances its speed.
- Sensor Usage: Our Adafruit IMU gyro sensor is used in this program for detecting the robot's heading,
                which is used to determine what direction the robot needs to go with respect to its front.
- Key Algorithms: Lines (x-y) contains the algorithm that converts joystick input into actual motor
                  values. First the rectangular vector obtained from the joystick values is converted
                  into a polar vector. Then the robot heading is subtracted to find the angle that the
                  robot must travel with respect to its front. This angle is run through some trigonometry
                  to find ratios for the motor values, which are then adjusted to match the desired motor power.
- Uniqueness: Although this is a fairly standard field centric drive, the added customizations to give
              the driver more options(A/start/bumpers) is what makes this program stand out.
- Possible Improvements: The main algorithm is a bit lengthy possibly causing some lag, so if needed
                         the joystick polar vector could be converted back to rectangular and plugged
                         into the motors like in the robot centric program instead of using all of the
                         trigonometry. Also pivoting could be added in if found to be useful, but
                         I have not yet thought of a practical use for it.
 */
@Disabled
@TeleOp(name = "Holonomic FieldCentric TeleOp", group = "Holonomic")
public class Holonomic_FC extends OpMode
{
    Hardware robot;

    @Override
    public void init ()
    {
        robot = new Hardware(hardwareMap, telemetry, true, gamepad1,gamepad2);

    }

    @Override
    public void loop ()
    {
        robot.telemetryReadings();
        robot.FCDrive();
    }
}