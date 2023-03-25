// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DriverControls;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LimeLight;

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
   public static Intake m_intake;

   public static LimeLight m_limelight;

   public RobotContainer() {
      m_xboxController = new XboxController(Constants.XBOX_USB_NUM);
      m_joystick = new Joystick(Constants.JOYSTICK_USB_NUM);

      m_driveTrain = new DriveTrain();
      m_driverControls = new DriverControls();

      m_arm = new Arm();

      m_intake = new Intake();

      m_driveTrain.setDefaultCommand(Commands.run(() -> {
         controlWrap();
      },m_driveTrain));

      // controlWrapInit();
   }

   private void controlWrap() {
      m_driverControls.triggerHybridMode();
      double degreeTranslate;
      if (Constants.ARM_JOYSTICK_MODE == "Explicit"){//Test
         //Takes the -1 to 1 range of the joystick axis and translates it to degree measurements for arm orientation
         degreeTranslate = Constants.ARM_DEGREE_RANGE[0] + (m_joystick.getY() + 1) * ((Constants.ARM_DEGREE_RANGE[1] - Constants.ARM_DEGREE_RANGE[0]) / 2);
      }

      if (m_joystick.getRawButton(11)){
         m_arm.setPosition(90);
      } else if (m_joystick.getRawButton(12)){
         m_arm.setPosition(180);
      } else if (m_joystick.getRawButton(9)){
         m_arm.setPosition(30);
      } else {//Test
         if (Constants.ARM_JOYSTICK_MODE == "Explicit"){
            m_arm.setPosition(degreeTranslate);
         } else {
            m_arm.setArmSpeed(m_joystick.getY() * Constants.ARM_SPEED);
         }
      }

      if (m_xboxController.getBButton()){
         m_intake.on();
      } else m_intake.off();
      if (m_xboxController.getAButton()){
         //Test
         System.out.println(m_driveTrain.gyro.getPitch());
      }
      if (m_xboxController.getXButton()){
         System.out.println(m_driveTrain.getEncoderDistance());
      }
   }
}
