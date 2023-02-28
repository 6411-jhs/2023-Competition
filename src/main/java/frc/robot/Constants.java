// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalSource;

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
    public static final int ARM_MOTOR = 5;
    public static final double ARM_SPEED = .2;
    public static final DigitalSource ENCODE_A = null;
    public static final DigitalSource ENCODE_B = null;
    public static final double ARM_DERIVITIVE = 0;
    public static final double ARM_INTEGRAL = 0;
    public static final double ARM_PROPORTIONAL = 0;
    public static final double FALCON_ENCODER_UNITS = 0;
    public static final double ARM_GEAR_RATIO = 0;
    public static final double ARM_PULLEY_RATIO = 0;
    public static final int TOP_LIMIT_DIO = 0;
    public static final int BOTTOM_LIMIT_DIO = 0;
    public static final int POLE_HEIGHT = 0;
    public static final int CAM_HEIGHT = 0;
    public static final int TOTAL_HEIGHT = 0;
    public static final int OFFSET = 0;
    public static final double ARM_LENGTH = 0;
    public static final String ALLIGN_SPEED = null;
}
