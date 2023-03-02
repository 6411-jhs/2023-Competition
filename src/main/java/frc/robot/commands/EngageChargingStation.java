package frc.robot.commands;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class EngageChargingStation extends CommandBase {
   private Accelerometer accelerometer = new BuiltInAccelerometer();
   private DriveTrain driveTrain;
   private XboxController xbox;

   //Data Collection
   //Holds the z axis acceleration of the robot; gravity is also detected as an accleration
   private double zAcc, yAcc, xAcc;
   private double prevZAcc, prevYAcc, prevXAcc;
   private double zJerk, yJerk, xJerk;

   private int clock;
   private int clockThresholds[] = {200,150};
   private boolean collisionDetected, finished;
   private double collisionThreshold = 0.2;
   private int stage = 0;

   public EngageChargingStation(DriveTrain m_DriveTrain, XboxController m_XboxController) {
      this.driveTrain = m_DriveTrain;
      this.xbox = m_XboxController;
   }

   @Override
   public void initialize() {
      zAcc = accelerometer.getZ();
      xAcc = accelerometer.getX();
      yAcc = accelerometer.getY();
   }

   @Override
   public void execute() {
      update();
      switch (stage){
         case 0:
            travel();
            break;
         case 1:
            mount();
            break;
         case 2: finished = true;
      }
   }

   public void test() {
      if (xbox.getAButton()){
         update();
         mount();
      }
      if (xbox.getBButton()){
         update();
         travel();
      }
      if (xbox.getXButton()){
         update();
         System.out.println(yJerk);
      }
      // System.out.println(yAcc + " " + yJerk);
   }

   private void travel(){
      if (clock < clockThresholds[0]){
         driveTrain.arcadeDrive(Constants.AUTO_DRIVE_TRAIN_SPEED, 0);
         clock++;
      } else {
         stage = 1;
      }
   }

   // Moves robot to sit on the incline of the charging station ramp
   private void mount() {
      if (collisionDetected){
         if (clock < clockThresholds[1]){
            driveTrain.arcadeDrive(Constants.AUTO_DRIVE_TRAIN_SPEED, 0);
            clock++;
         } else {
            stage = 2;
         }
      } else {
         driveTrain.arcadeDrive(Constants.AUTO_DRIVE_TRAIN_SPEED, 0);
      }
   }
   
   //Updates all the data the charging station program uses
   private void update() {
      zAcc = accelerometer.getZ();
      xAcc = accelerometer.getX();
      yAcc = accelerometer.getY();

      zJerk = (zAcc - prevZAcc) / 0.2;
      yJerk = (yAcc - prevYAcc) / 0.2;
      xJerk = (xAcc - prevXAcc) / 0.2;

      prevZAcc = zAcc;
      prevYAcc = yAcc;
      prevXAcc = xAcc;

      if (yJerk > collisionThreshold) collisionDetected = true;
   }
}
