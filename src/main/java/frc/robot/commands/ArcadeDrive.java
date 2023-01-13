// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArcadeDrive extends CommandBase {



  private double leftStickX;
  private double leftStickY; 

  /** Creates a new ArcadeDrive. */
  public ArcadeDrive( ) {
    // Use addRequirements() here to declare subsystem dependencies.

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    leftStickY = Constants.DRIVE_TRAIN_SPEED * RobotContainer.m_xboxController.getRawAxis(Constants.XBOX_LEFT_X_AXIS);
    leftStickX = Constants.DRIVE_TRAIN_SPEED * RobotContainer.m_xboxController.getRawAxis(Constants.XBOX_LEFT_Y_AXIS);
    RobotContainer.m_driveTrain.arcadeDrive(leftStickY, leftStickX);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
