package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveTrain;

public class TurnNinetyDegrees extends CommandBase {
   DriveTrain drive;
   double driveSpeed;
   int driveDirection;

   boolean finished = false;

   public TurnNinetyDegrees(DriveTrain driveTrainSubsystem, double speed, String direction){
      drive = driveTrainSubsystem;
      driveSpeed = speed;
      if (direction == "Left"){
         driveDirection = -1;
      } else if (direction == "Right"){
         driveDirection = 1;
      }
   }

   @Override
   public void execute(){
      
   }

   @Override
   public boolean isFinished(){
      return finished;
   }
}
