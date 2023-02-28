// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.naming.spi.DirStateFactory.Result;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.AllignTarget;
import frc.robot.commands.Gripp;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gripper;
import frc.robot.DriverControls;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
 public static XboxController m_xboxController;
  public static AllignTarget m_AllignTarget;
 public static  DriveTrain m_driveTrain;
 public static DriverControls m_driverControls;
 public static PhotonCamera limeCamera;
 public static Gripper m_gripper;
 public static Gripp m_gripp;

//  public static  DigitalInput topLimit;
//  public static  DigitalInput bottomLimit;



  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() 
  {
    m_xboxController = new XboxController(Constants.XBOX_USB_NUM);
    m_driveTrain = new DriveTrain();
    m_AllignTarget = new AllignTarget();
    limeCamera = new PhotonCamera("limeCamera");
    m_gripper = new Gripper();
    m_gripp = new Gripp();
    
    // topLimit = new DigitalInput(Constants.TOP_LIMIT_DIO);
    // bottomLimit = new DigitalInput(Constants.BOTTOM_LIMIT_DIO);
    // tankDrive();
    // arcadeDrive();
    
    m_driverControls = new DriverControls(m_driveTrain,m_xboxController);

     m_driveTrain.setDefaultCommand(Commands.run(
      () -> 
        m_driverControls.ModeSwitchMode(null)

      ,m_driveTrain));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
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

   JoystickButton armButton = new JoystickButton(m_xboxController, Constants.ARM_BUTTON);
   armButton.whileTrue(m_gripp);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_arcadeDrive;
  // }
//photon helper methods
    public static PhotonPipelineResult getResult()
    {
      return limeCamera.getLatestResult();
    }

    

}
