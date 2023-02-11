// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.photonvision.PhotonUtils;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;

public class PositionArm extends CommandBase {
  /** Creates a new PositionArm. */

  public PositionArm() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    PhotonPipelineResult result = RobotContainer.getResult();
    if (!RobotContainer.topLimit.get()&& !RobotContainer.bottomLimit.get())
    {
     if (result.hasTargets())
     {
      PhotonTrackedTarget target = result.getBestTarget();
      if (RobotContainer.m_driveTrain.allignTargetLime())
      {
        double angle = calculateAngle(target);
        double groundDistance = calculateDistance(target);
      }
     }
    }
  }

  private double calculateAngle(PhotonTrackedTarget target)
  {
   //The equations are only using constant values; you will always get the same answer
   double heightOffset = Constants.TOTAL_HEIGHT - (Constants.POLE_HEIGHT + Constants.OFFSET);
   return Math.acos(heightOffset/Constants.ARM_LENGTH);
  }

  private double calculateDistance(PhotonTrackedTarget target)
  {
    //You need the hypotinuse of the triangle to finish the equation. cos = a/h;
    //The diagonal distance from the camera and the pole point is h
    //The angle of the point on the camera is the resulting degree (d)
    // cos(d) * h = a
    //As far as I could tell, this is only measuring vertical height (The a of a^2 + b^2 = c^2)
    return (Constants.POLE_HEIGHT -Constants.CAM_HEIGHT )/ Math.tan(target.getPitch());
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
