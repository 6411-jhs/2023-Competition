// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalSource;

/**
 * 
 */
public final class Constants {
   // USB control ports that the controllers go to on the computer
   public static final int XBOX_USB_NUM = 0;
   public static final int JOYSTICK_USB_NUM = 1;

   //Primary joystick control mode for the drive train controls
   public static final String PRIMARY_JOYSTICK = "Left";

   //* PWM PORTS
   //Drive Train
   public static final int LEFT_FRONT_MOTOR = 2;
   public static final int LEFT_BACK_MOTOR = 3;
   public static final int RIGHT_FRONT_MOTOR = 0;
   public static final int RIGHT_BACK_MOTOR = 1;
   //Other
   public static final int ARM_MOTOR = 7;

   // axis and button indexes
   public static final int XBOX_LEFT_Y_AXIS = 1;
   public static final int XBOX_LEFT_X_AXIS = 0;
   public static final int XBOX_RIGHT_Y_AXIS = 5;

   //Speeds for all systems of robot
   public static final double DRIVE_TRAIN_SPEED = 0.8;
   public static final double AUTO_DRIVE_TRAIN_SPEED = 0.8;
   public static final double ARM_SPEED = 0.4;

   //-> Arm system constants 
   //These are the value ranges the arm creates; the min and max values the encoder can create based on physical limitations of the arm.
   public static final double ARM_DEGREE_RANGE[] = {30,270};
   public static final double ARM_ENCODER_RANGE[] = {-0.00626,-0.878};
   public static final double ARM_ENCODER_THRESHOLD_RANGE = 0;

   
   public static final DigitalSource ENCODE_A = null;
   public static final DigitalSource ENCODE_B = null;

   public static final double FALCON_ENCODER_UNITS = 2048;
   public static final double ARM_GEAR_RATIO = 25;
   public static final double ARM_PULLEY_RATIO = 0;
   public static final int TOP_LIMIT_DIO = 0;
   public static final int BOTTOM_LIMIT_DIO = 0;
   public static final int POLE_HEIGHT = 0;
   public static final int CAM_HEIGHT = 0;
   public static final int TOTAL_HEIGHT = 0;
   public static final int OFFSET = 0;
   public static final double ARM_LENGTH = 0;
   public static final double ALLIGN_SPEED = 0;
   public static final int GRIPP_MOTOR = 6;
   public static final double STALL_SPEED = 0;
   public static final double GRIPP_SPEED = .7;
   public static final int ARM_BUTTON = 1;
   public static final int GRIPP_BUTTON = 2;

   public static final double ARM_VERTICAL_POS = -.2;
   public static final double GROUND_LIMIT = -.683;
   public static final double SIT_PRETTY_SPEED = .176;
   public static final double DRIVE_TURN_SPEED = 0.6;
   public static final double ENCODER_FULL_REV = 819.5;
   public static final double MOVE_FORWARD_TIME = 1.0;
   public static final double FALL_TIME = 2.5;
   public static final double DROP_TIME = 1;

}
