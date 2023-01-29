// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //motor pwm
    public static final int LEFT_FRONT_MOTOR = 0;
    public static final int LEFT_BACK_MOTOR = 1;
    public static final int RIGHT_FRONT_MOTOR = 2;
    public static final int RIGHT_BACK_MOTOR = 3;
   
    //axis and button indexes 
    public static final int XBOX_LEFT_Y_AXIS = 1;
    public static final int XBOX_LEFT_X_AXIS = 0;
    public static final int XBOX_RIGHT_Y_AXIS = 5;

    //speeds
    public static final double DRIVE_TRAIN_SPEED = 0.5;
    public static final int XBOX_USB_NUM = 0;
    public static final String PRIMARY_JOYSTICK = "Left";

    public static final float DRIVE_TRAIN_RATIO = 1; //Cannot go above 1; this multiplies the trigger input on the controller to set a speed limit
}
