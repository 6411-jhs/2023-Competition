package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.IntakePWM;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimeLight;

import frc.robot.commands.*;
import frc.robot.Constants;

public class Auto {
   Arm arm;
   IntakePWM intake;
   // IntakeCAN intake;
   DriveTrain drive;
   LimeLight limeLight;

   MountChargingStation mountChargingStation;
   BalanceChargingStation balanceChargingStation;

   public Auto(Arm armSubsystem, IntakePWM intakeSubsystem,DriveTrain driveTrainSubsystem,LimeLight limeLightSubsystem){
      arm = armSubsystem;
      intake = intakeSubsystem;
      drive = driveTrainSubsystem;
      limeLight = limeLightSubsystem;

      mountChargingStation = new MountChargingStation(drive, Constants.AUTO_DRIVE_TRAIN_SPEED);
      balanceChargingStation = new BalanceChargingStation(driveTrainSubsystem, Constants.AUTO_DRIVE_TRAIN_SPEED);
   }

   public Command getCommand(){
      switch (Constants.AUTO_MODE){
         case "Left":
            return leftPosition();
         case "Center":
            return centerPosition();
         case "Right":
            return rightPosition();
         default: 
            return null;
      }
   }

   public Command leftPosition(){
      return Commands.sequence(
         Commands.run(() -> {
            // intake.on();
            drive.arcadeDrive(0.5, 0);
         })
      );
   }
   public Command centerPosition(){
      return Commands.sequence(
         mountChargingStation
         // balanceChargingStation
         // null
      );
   }
   public Command rightPosition(){
      return Commands.sequence(null);
   }
}
