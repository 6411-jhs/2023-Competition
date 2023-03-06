// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class AutoMoveOut extends CommandBase {
  /** Creates a new AutoMoveOut. */
  public Timer moveTime;
  public AutoMoveOut() {
    // Use addRequirements() here to declare subsystem dependencies.
    moveTime = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    System.out.println("autoStart");
  
    moveTime.reset();
    moveTime.start();
    while (moveTime.get() <Constants.MOVE_FORWARD_TIME)
    {
          RobotContainer.m_driveTrain.driveForward(-Constants.AUTO_DRIVE_TRAIN_SPEED);
          System.out.println(moveTime.get());
        }
     
    
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    RobotContainer.m_driveTrain.driveForward(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
