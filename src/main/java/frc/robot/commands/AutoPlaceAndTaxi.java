// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class AutoPlaceAndTaxi extends CommandBase {
  /** Creates a new autoPlaceAndTaxi. */
    // Use addRequirements() here to declare subsystem dependencies.
      public Timer moveTime;
  public Timer autoTimer;
  public Timer dropTimer;
  private boolean postitionCondition;
  public AutoPlaceAndTaxi() {
    // Use addRequirements() here to declare subsystem dependencies.
    moveTime = new Timer();
    autoTimer = new Timer();
    dropTimer = new Timer();

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
 {
   System.out.println("autoStart");
    RobotContainer.m_intake.set(Constants.IDLE_SPEED);
    autoTimer.reset();
    autoTimer.start();
    
    
 }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
   if (autoTimer.get()< 2 )
   {
   RobotContainer.m_arm.setPosition(-120);
   }
   else if (autoTimer.get()> 2&& autoTimer.get() <4)
   {
    RobotContainer.m_intake.set(-Constants.INTAKE_SPEED);
   }
   else if (autoTimer.get()>4 && autoTimer.get() <4+Constants.MOVE_FORWARD_TIME);
{
  RobotContainer.m_driveTrain.driveForward(-Constants.AUTO_DRIVE_TRAIN_SPEED);
}
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    RobotContainer.m_driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
