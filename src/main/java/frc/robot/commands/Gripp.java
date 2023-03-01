// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.SerialPort.StopBits;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Gripp extends CommandBase {
  /** Creates a new Gripp. */

  public Gripp() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    // if (RobotContainer.grippLimit.get())
    // {
    //   RobotContainer.m_gripper.stopGrippMotor();
    // }
    // else
    // {
      RobotContainer.m_gripper.setGrippMotor(RobotContainer.m_xboxController.getRawAxis(3)*Constants.GRIPP_SPEED );
    // }
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
