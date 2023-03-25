// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * 
 */
public final class Constants {
   // USB control ports that the controllers go to on the computer
   public static final int XBOX_USB_NUM = 0;
   public static final int JOYSTICK_USB_NUM = 1;

   // Primary joystick control mode for the drive train controls
   public static final String PRIMARY_JOYSTICK = "Left";
   // Jostick mode for the arm mechanics
   // Explicit = every joystick value equals a static degree value the arm goes to
   // Progressive = it will throttle the arm speed based on the joystick
   public static final String ARM_JOYSTICK_MODE = "Explicit";

   // * PWM PORTS/ MOTOR IDS
   // Drive Train
   public static final int LEFT_FRONT_MOTOR = 2;
   public static final int LEFT_BACK_MOTOR = 3;
   public static final int RIGHT_FRONT_MOTOR = 0;
   public static final int RIGHT_BACK_MOTOR = 1;
   // Other
   public static final int ARM_MOTOR = 7;
   public static final int INTAKE_MOTOR = 0;

   // axis and button indexes
   public static final int XBOX_LEFT_Y_AXIS = 1;
   public static final int XBOX_LEFT_X_AXIS = 0;
   public static final int XBOX_RIGHT_Y_AXIS = 5;

   // Speeds for all systems of robot
   public static final double DRIVE_TRAIN_SPEED = 0.8;
   public static final double AUTO_DRIVE_TRAIN_SPEED = 0.8;
   public static final double ARM_SPEED = 0.7;
   public static final double INTAKE_SPEED = 0.5;

   // -> Arm system constants
   // These are the value ranges the arm creates; the min and max values the
   // encoder can create based on physical limitations of the arm.
   public static final double ARM_DEGREE_RANGE[] = { 30, 270 };
   public static final double ARM_ENCODER_RANGE[] = { -0.00626, -0.878 };
   public static final double ARM_ENCODER_THRESHOLD_RANGE = 0;
   public static final double FALCON_ENCODER_UNITS = 2048;
   public static final double ARM_GEAR_RATIO = 25;
   public static final int POLE_HEIGHT = 0;
   public static final int CAM_HEIGHT = 6;
   public static final double ALLIGN_SPEED = 0.5;
   public static final double MID_ANGLE = 0;
}
