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

   // Use this to change auto between "Left", "Center", and "Right" positions
   public static final String AUTO_MODE = "Center";

   // * PWM PORTS/ MOTOR IDS
   // Drive Train
   public static final int LEFT_FRONT_MOTOR = 2;
   public static final int LEFT_BACK_MOTOR = 1;
   public static final int RIGHT_FRONT_MOTOR = 0;
   public static final int RIGHT_BACK_MOTOR = 3;
   // Other
   public static final int ARM_MOTOR = 7;
   public static final int INTAKE_MOTOR_PWM = 0;
   public static final int INTAKE_MOTOR_CAN = 0;

   // axis and button indexes
   public static final int XBOX_LEFT_Y_AXIS = 1;
   public static final int XBOX_LEFT_X_AXIS = 0;
   public static final int XBOX_RIGHT_Y_AXIS = 5;

   // Speeds for all systems of robot
   public static final String SPEED_SWITCH_MODE = "Button"; //"Button" || "Bumper"
   public static final double Y_BUTTON_SPEED = 1;
   public static final double DRIVE_TRAIN_SPEED = 0.8; //X Button
   public static final double A_BUTTON_SPEED = 0.5;
   public static final double AUTO_DRIVE_TRAIN_SPEED = 0.5;
   public static final double CHARGE_STATION_DRIVE_SPEED = 0.73;
   public static final double CHARGE_STATION_DRIVE_SPEED_BALANCE = 0.55;
   public static final double MAX_ARM_SPEED = 0.35;
   public static final double MIN_ARM_SPEED = 0.07;
   public static final double INTAKE_SPEED = 1;

   // -> Arm system constants
   // These are the value ranges the arm creates; the min and max values the
   // encoder can create based on physical limitations of the arm.
   public static final double ARM_DEGREE_RANGE[] = { -10, -210.83 };
   public static final double ARM_ENCODER_RANGE[] = { -0.008125, -0.5673 };
   public static final double ARM_ENCODER_THRESHOLD_RANGE = 0.4;
   public static final double FALCON_ENCODER_UNITS = 2048;
   public static final double ARM_GEAR_RATIO = 50;
   public static final int POLE_HEIGHT = 36;
   public static final int CAM_HEIGHT = 6;
   public static final double ALLIGN_SPEED = 0.5;
   public static final double MID_ANGLE = 120;

   // -> Intake system constants
   public static final double STALL_TIME_THRESHOLD = 0.5;
   public static final double IDLE_SPEED = 0.08;

   // -> Charging station system consstants
   public static final double GYRO_MOUNTING_VALUE = -14.5;
   public static final double GYRO_BALANCING_VALUE = 0;
   public static final double GYRO_THRESHOLD_RANGE = 0.3;
   public static final double GYRO_BALANCING_DETECTION_THRESHOLD = 2.5;
   public static final double DROP_TIME = 3;
   public static final double MOVE_FORWARD_TIME = 3;
}
