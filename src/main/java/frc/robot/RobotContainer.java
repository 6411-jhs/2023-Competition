// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import edu.wpi.first.wpilibj.XboxController;
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
   public static XboxController m_joystick;

   public static DriveTrain m_driveTrain;
   public static DriverControls m_driverControls;

   public static Arm m_arm;

   public RobotContainer() {
      m_xboxController = new XboxController(Constants.XBOX_USB_NUM);
      m_joystick = new XboxController(Constants.JOYSTICK_USB_NUM);

      m_driveTrain = new DriveTrain();
      m_driverControls = new DriverControls();

      m_arm = new Arm();

      m_driveTrain.setDefaultCommand(Commands.run(() -> {
         controlWrap();
      },m_driveTrain));

      controlWrapInit();
   }

   private void controlWrap() {
      m_driverControls.triggerHybridMode();
   }
   
   private void controlWrapInit(){
      JoystickButton armButton = new JoystickButton(m_joystick, Constants.ARM_BUTTON);
      armButton.whileTrue(Commands.run(() -> {
         System.out.println(m_arm.getMotorPosition());
      }));

      JoystickButton ninetyDegree = new JoystickButton(m_joystick, 11);
      ninetyDegree.whileTrue(Commands.run(() -> {
         m_arm.setPosition(90);
      }));

      JoystickButton oneEightyDegree = new JoystickButton(m_joystick, 12);
      oneEightyDegree.whileTrue(Commands.run(() -> {
         m_arm.setPosition(180);
      }));

      JoystickButton backDegree = new JoystickButton(m_joystick, 9);
      backDegree.whileTrue(Commands.run(() -> {
         m_arm.setPosition(30);
      }));

      JoystickButton fourtyFiveDegree = new JoystickButton(m_joystick, 10);
      fourtyFiveDegree.whileTrue(Commands.run(() -> {
         m_arm.setPosition(45);
      }));
   }
}
