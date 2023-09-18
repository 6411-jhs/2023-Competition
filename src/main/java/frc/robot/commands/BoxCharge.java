// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class BoxCharge extends CommandBase {
  /** Creates a new BoxCharge. */
  Timer DriveTime = new Timer();
private boolean mounted;
private int stageLog=-2;
BalanceChargingStation balance;
MountChargingStation mount;
private boolean balanced;

  public BoxCharge() {
    // Use addRequirements() here to declare subsystem dependencies.
    balance = new BalanceChargingStation(RobotContainer.m_driveTrain);
    mount = new MountChargingStation(RobotContainer.m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() { 
     stageLog=-2;
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
      stageLog++;

      }
      else if (DriveTime.get()>.5 && DriveTime.get() <1.5)
      {
        RobotContainer.m_driveTrain.driveForward(Constants.AUTO_DRIVE_TRAIN_SPEED);
        stageLog++;

      }
      if (!mounted){
        if (stageLog == 0) {
            System.out.println("Mount");
            stageLog++;
        }
        mount.execute();
        mounted = mount.finished;
    } else {
        if (!balanced){
            if (stageLog == 1) {
                System.out.println("Balance");
                stageLog++;
            }
            balance.execute();
            balanced = balance.finished;
        } else {
            if (stageLog == 2) {
                System.out.println("Charging Station Activated!");
                stageLog++;
            }
        }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
   {
    RobotContainer.m_driveTrain.arcadeDrive(0, 0);
   }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return mounted && balanced && DriveTime.get()>1.5;
  }
}
