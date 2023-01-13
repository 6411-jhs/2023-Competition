// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class TankDrive extends CommandBase {

  private final DriveTrain m_driveTrain;
  private final XboxController m_driveController;

  private double rightStickY;
  private double leftStickY; 

  /** Creates a new ArcadeDrive. */
  public TankDrive(DriveTrain p_driveTrain, XboxController p_driveController ) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = p_driveTrain;
    m_driveController = p_driveController;
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
    rightStickY = Constants.DRIVE_TRAIN_SPEED * m_driveController.getRawAxis(Constants.XBOX_RIGHT_Y_AXIS);
    leftStickY = Constants.DRIVE_TRAIN_SPEED * m_driveController.getRawAxis(Constants.XBOX_LEFT_Y_AXIS);
    m_driveTrain.tankDrive(leftStickY, rightStickY);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
