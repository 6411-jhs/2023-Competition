package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;

public class MountChargingStation extends CommandBase {
   DriveTrain drive;
   double driveSpeed;

   boolean finished = false;

   public MountChargingStation(DriveTrain driveTrainSubsystem){
      drive = driveTrainSubsystem;
      driveSpeed = Constants.CHARGE_STATION_DRIVE_SPEED;
   }

   @Override
   public void execute(){
      if (!finished){
         // System.out.println(drive.getPitch());
         if ((drive.getPitch() > Constants.GYRO_MOUNTING_VALUE - Constants.GYRO_THRESHOLD_RANGE && drive.getPitch() < Constants.GYRO_MOUNTING_VALUE + Constants.GYRO_THRESHOLD_RANGE)){
            drive.arcadeDrive(0, 0);
            finished = true;
         } else {
            drive.arcadeDrive(-driveSpeed, 0);
            finished = false;
         }
      }
   }

   @Override
   public boolean isFinished(){
      return finished;
   }
}
