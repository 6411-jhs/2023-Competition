// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DriverControls;

import frc.robot.subsystems.Arm;

/**
 * Main container of robot code; everything from commands, subsystems to
 * sensing. All functonality leads here to then be sent to the robot class.
 */
public class RobotContainer {
   public static Command m_autoCommand;

   public static XboxController m_xboxController;
   public static Joystick m_joystick;

   public static DriveTrain m_driveTrain;
   public static DriverControls m_driverControls;

   public static Arm m_arm;

   public RobotContainer() {
      m_xboxController = new XboxController(Constants.XBOX_USB_NUM);
      m_joystick = new Joystick(Constants.JOYSTICK_USB_NUM);

      m_driveTrain = new DriveTrain();
      m_driverControls = new DriverControls();

      m_arm = new Arm();

      m_driveTrain.setDefaultCommand(Commands.run(() -> {
         controlWrap();
      },m_driveTrain));

      // controlWrapInit();
   }

   private void controlWrap() {
      m_driverControls.triggerHybridMode();
      //Takes the -1 to 1 range of the joystick axis and translates it to degree measurements for arm orientation
      double degreeTranslate = Constants.ARM_DEGREE_RANGE[0] + (m_joystick.getY() + 1) * ((Constants.ARM_DEGREE_RANGE[1] - Constants.ARM_DEGREE_RANGE[0]) / 2);
      
      if (m_joystick.getRawButton(11)){
         m_arm.setPosition(90);
      } else if (m_joystick.getRawButton(12)){
         m_arm.setPosition(180);
      } else if (m_joystick.getRawButton(9)){
         m_arm.setPosition(30);
      } else {
         m_arm.setPosition(degreeTranslate);
      }
   }
}
