// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.naming.spi.DirStateFactory.Result;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.AllignTarget;

import frc.robot.commands.EngageChargingStation;

import frc.robot.commands.ArmTest;
import frc.robot.commands.AutoMoveOut;
import frc.robot.commands.AutoPlaceMove;
import frc.robot.commands.EncoderTest;
import frc.robot.subsystems.Arm;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DriverControls;
import frc.robot.commands.Gripp;
import frc.robot.commands.Squeeze;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gripper;
import frc.robot.view.Frame;
import frc.robot.subsystems.DriverControls;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
 public static XboxController m_xboxController;
 public static XboxController m_joystick;
  public static AllignTarget m_AllignTarget;

 public static Gripper m_gripper;
 public static Gripp m_gripp;
 
 public static Squeeze m_squeeze;

//  public static  DigitalInput topLimit;
//  public static  DigitalInput bottomLimit;


  public static EngageChargingStation m_EngageChargingStation;
  public static DriveTrain m_driveTrain;
  public static DriverControls m_driverControls;

  public static DigitalInput topLimit;
  public static DigitalInput bottomLimit;

  public static PhotonCamera limeCamera;

  public static Arm m_arm;
  public static ArmTest m_armTest;
  public static Encoder m_encoder;
  public static EncoderTest m_encoderTest;
  public static AutoMoveOut m_autoMoveOut;
  public static AutoPlaceMove m_autoPlaceMove;
  public Frame m_frame;
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_xboxController = new XboxController(Constants.XBOX_USB_NUM);
    m_joystick = new XboxController(Constants.JOYSTICK_USB_NUM);
    m_driveTrain = new DriveTrain();
    m_AllignTarget = new AllignTarget();

    limeCamera = new PhotonCamera("limeCamera");
    m_gripper = new Gripper();
    m_gripp = new Gripp();
    m_squeeze = new Squeeze(); 

    m_encoder = new Encoder(7,8,9);
    m_encoderTest = new EncoderTest();
    m_armTest = new ArmTest();
    m_autoMoveOut = new AutoMoveOut();
    m_autoPlaceMove = new AutoPlaceMove();
    // topLimit = new DigitalInput(Constants.TOP_LIMIT_DIO);
    // bottomLimit = new DigitalInput(Constants.BOTTOM_LIMIT_DIO);
    // tankDrive();
    // arcadeDrive();
    
    m_driverControls = new DriverControls(m_driveTrain,m_xboxController);

    m_arm = new Arm();
    m_armTest = new ArmTest();

    m_driverControls = new DriverControls(m_driveTrain, m_xboxController);
    m_EngageChargingStation = new EngageChargingStation(m_driveTrain, m_xboxController);

    m_frame = new Frame();

    m_driveTrain.setDefaultCommand(
        Commands.run(() -> controlWrap(), m_driveTrain)
    // m_EngageChargingStation

    );

    // Configure the button bindings
    configureButtonBindings();
  }

  public void controlWrap() {
    m_driverControls.triggerHybridMode();
    // m_EngageChargingStation.test();

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
  {
   Trigger limeButton = new JoystickButton(m_xboxController,XboxController.Button.kRightBumper.value);
   try 
   {
    limeButton.whileTrue(m_AllignTarget);
   }
   catch(Exception e)
   {
    System.out.println("problem is " + e.getLocalizedMessage());
   }

   JoystickButton gripButton = new JoystickButton(m_joystick, Constants.GRIPP_BUTTON);
   gripButton.whileTrue(m_gripp);

   JoystickButton armButton = new JoystickButton(m_joystick, Constants.ARM_BUTTON);
armButton.whileTrue(m_armTest);
   JoystickButton encodeTestButton = new JoystickButton(m_joystick, 5);
   encodeTestButton.whileTrue(m_encoderTest);

   JoystickButton squeezeButton = new JoystickButton(m_joystick, 7);
   squeezeButton.whileTrue(m_squeeze);
  }

  public static PhotonPipelineResult getResult() {
    return limeCamera.getLatestResult();
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
  // An ExampleCommand will run in autonomous
  // return m_EngageChargingStation;
  return m_autoPlaceMove;
  }

}
