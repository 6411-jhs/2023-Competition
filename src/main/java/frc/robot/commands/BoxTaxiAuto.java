// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class BoxTaxiAuto extends CommandBase {
  /** Creates a new TaxiAuto. */
  Timer DriveTime = new Timer();
  public BoxTaxiAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    DriveTime.reset();
    DriveTime.start();
    addRequirements(RobotContainer.m_driveTrain);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
   {
    if (DriveTime.get()<.5){
    RobotContainer.m_driveTrain.driveForward(-Constants.AUTO_DRIVE_TRAIN_SPEED);
    }
    else if (DriveTime.get()>.5 && DriveTime.get() <1.5)
    {
      RobotContainer.m_driveTrain.driveForward(Constants.AUTO_DRIVE_TRAIN_SPEED);
    }
    else if (DriveTime.get()>1.5  )
    {
      RobotContainer.m_driveTrain.driveForward(-Constants.AUTO_DRIVE_TRAIN_SPEED);
    }
   }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(DriveTime.get()>Constants.MOVE_FORWARD_TIME+1.5)
    {
      return true;
    }
    return false;
  }
}
