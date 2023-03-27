package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.IntakePWM;
import frc.robot.subsystems.IntakeCAN;
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
   PickUpCube pickUpCube;

   public Auto(Arm armSubsystem, IntakePWM intakeSubsystem,/* IntakeCAN intakeSubsystem*/DriveTrain driveTrainSubsystem,LimeLight limeLightSubsystem){
      arm = armSubsystem;
      intake = intakeSubsystem;
      drive = driveTrainSubsystem;
      limeLight = limeLightSubsystem;

      mountChargingStation = new MountChargingStation(drive, Constants.AUTO_DRIVE_TRAIN_SPEED);
      balanceChargingStation = new BalanceChargingStation(driveTrainSubsystem, Constants.AUTO_DRIVE_TRAIN_SPEED);
      // pickUpCube = new PickUpCube(intake);
   }

   public Command leftPosition(){
      return Commands.sequence(null);
   }
   public Command centerPosition(){
      return Commands.sequence(
         mountChargingStation,
         balanceChargingStation
      );
   }
}
