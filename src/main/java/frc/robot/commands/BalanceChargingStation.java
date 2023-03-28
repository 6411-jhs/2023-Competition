package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;

public class BalanceChargingStation extends CommandBase {
   DriveTrain drive;
   double driveSpeed;

   int conditionCount = 0;
   int conditionThreshold = (int) Math.round(Constants.GYRO_BALANCING_DETECTION_THRESHOLD * 50);

   boolean finished = false;

   public BalanceChargingStation(DriveTrain driveTrainSubsystem, double speed){
      drive = driveTrainSubsystem;
      driveSpeed = speed;
   }

   @Override
   public void execute(){
      // System.out.println(drive.getPitch() + " " + conditionCount + " " + conditionThreshold);
      updateData();
      if (conditionCount < conditionThreshold){
         double targetSpeed = drive.getPitch() * 0.15;
         System.out.println(targetSpeed + " " + drive.getPitch() + " " + conditionCount + " " + conditionThreshold);
         if (targetSpeed < -driveSpeed){
            targetSpeed = -driveSpeed;
         } else if (targetSpeed > driveSpeed){
            targetSpeed = driveSpeed;
         }

         drive.arcadeDrive(targetSpeed, 0);
         finished = false;
      } else {
         drive.arcadeDrive(0, 0);
         finished = true;
      }
   }

   private void updateData(){
      if (drive.getPitch() > Constants.GYRO_BALANCING_VALUE - Constants.GYRO_THRESHOLD_RANGE && drive.getPitch() < Constants.GYRO_BALANCING_VALUE + Constants.GYRO_THRESHOLD_RANGE){
         conditionCount++;
      } else conditionCount = 0;
   }

   @Override
   public boolean isFinished(){
      return finished;
   }
}
