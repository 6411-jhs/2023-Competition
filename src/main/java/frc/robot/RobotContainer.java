// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DriverControls;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.IntakePWM;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Auto;

import frc.robot.commands.*;

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
   public static IntakePWM m_intake;

   public static LimeLight m_limelight;
   public Auto m_auto;
   public TaxiAuto m_taxiAuto;

   BalanceChargingStation balanceChargingStation;
   MountChargingStation mountChargingStation;
   AutoPlaceAndTaxi autoPlaceAndTaxi;
   // AllignAndPlaceMidCube allignAndPlaceMidCube;

   public RobotContainer() {
      m_xboxController = new XboxController(Constants.XBOX_USB_NUM);
      m_joystick = new Joystick(Constants.JOYSTICK_USB_NUM);

      m_driveTrain = new DriveTrain();
      m_driverControls = new DriverControls(m_xboxController);

      m_arm = new Arm();
      m_intake = new IntakePWM();
      m_taxiAuto = new TaxiAuto();

      // balanceChargingStation = new BalanceChargingStation(m_driveTrain, Constants.DRIVE_TRAIN_SPEED);
      // mountChargingStation = new MountChargingStation(m_driveTrain, Constants.DRIVE_TRAIN_SPEED);
      m_auto = new Auto(m_arm, m_intake, m_driveTrain, m_limelight);
      autoPlaceAndTaxi = new AutoPlaceAndTaxi();
      // allignAndPlaceMidCube = new AllignAndPlaceMidCube(m_limelight);

      m_driveTrain.setDefaultCommand(Commands.run(() -> {
         controlWrap();
      },m_driveTrain));
   }

   private void controlWrap() {
      if (DriverStation.isTeleop()){
         m_driverControls.triggerHybridMode();
      if (m_joystick.getTrigger()){
         m_intake.on();
      } else {
         m_intake.off();
      }
      
      if (m_joystick.getThrottle() > 0.4){
         m_intake.setDirection("Inward");
      } else if (m_joystick.getThrottle() < -0.4){
         m_intake.setDirection("Outward");
      }

      if (m_joystick.getRawButtonPressed(11)){
         m_intake.toggleIdle();
      }

      m_arm.setArmSpeed(m_joystick.getY() * Constants.MAX_ARM_SPEED);
      
      }
      // JoystickButton limeFunctionButton = new JoystickButton(m_xboxController,6 );
      // limeFunctionButton.whileTrue(allignAndPlaceMidCube);
   }

   public Command getAutoCommand(){
      System.out.println("it got the auto command");
      return m_taxiAuto;
   }
}
