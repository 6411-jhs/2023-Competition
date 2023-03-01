package frc.robot.commands;

import java.util.ArrayList;

import javax.swing.text.StyleContext.SmallAttributeSet;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import limelightvision.limelight.frc.ControlMode.CamMode;

public class EngageChargingStation extends CommandBase {
   private Accelerometer accelerometer = new BuiltInAccelerometer();
   private DriveTrain driveTrain;
   private XboxController xbox;

   // Local Constants
   private double thresholdRange = 0.027;
   private int sampleSize = 5;
   private double targetIncline = 0.96;
   private double targetBalance = 0.99;

   private ArrayList<Boolean> mountSample = new ArrayList<Boolean>();
   private ArrayList<Boolean> secondMountSample = new ArrayList<Boolean>();
   private ArrayList<Boolean> balanceSample = new ArrayList<Boolean>();
   private double zAcc;
   private boolean mounted;
   private boolean balanced;

   private int engageStage = 0;

   public EngageChargingStation(DriveTrain m_DriveTrain, XboxController m_XboxController) {
      this.driveTrain = m_DriveTrain;
      this.xbox = m_XboxController;
   }

   @Override
   public void initialize() {
      zAcc = accelerometer.getZ();
   }

   @Override
   public void execute() {
      update();
      if (!mounted && engageStage == 0) {
         mount();
      } else if (mounted && engageStage == 0) {
         engageStage = 1;
         System.out.println("Stage 1 done!");
      }

      if (!balanced && engageStage == 1) {
         balance();
      } else {
         System.out.println("Stage 2 done!");
      }
   }

   public void test() {
      if (xbox.getYButton()) {
         update();
         // if (zSample.size() == zSampleSize || (zSample.size() > zSampleSize)) {
         //    System.out.println(zAcc);
         // }
      }
      if (xbox.getBButton()) {
         update();
         mount();
      }
      if (xbox.getAButton()) {
         update();
         balance();
      }
      if (xbox.getXButton()) {
         update();
         execute();
      }
   }

   // Adjusts robot to balance and engage on the charging station
   private void balance() {
      if (!(zAcc >= targetBalance - thresholdRange && zAcc <= targetBalance + thresholdRange)) {
         driveTrain.arcadeDrive((targetBalance - zAcc) * 12, 0);
         balanced = false;
      } else {
         balanced = true;
         System.out.println("Balanced!");
      }
   }

   // Moves robot to sit on the incline of the charging station ramp
   private void mount() {
      int count = 0;
      for (int i = 0; i < secondMountSample.size()-1; i++){
         if (secondMountSample.get(i)) count++;
      }

      if (count >= 2) {
         System.out.println("Mounted! " + secondMountSample.toString());
      } else {
         driveTrain.arcadeDrive(Constants.CHARGE_DRIVE_SPEED, 0);
         System.out.println("not mounted " + secondMountSample.toString());
      }
   }

   private void update() {
      // Uses an average of accelerometer values rather than raw data; this makes the
      // robot move smoother.
      // System.out.println(mountSample.toString());
      zAcc = accelerometer.getZ();
      if (mountSample.size() >= sampleSize) {
         mountSample.clear();
      };
      if (!(zAcc >= targetIncline - thresholdRange && zAcc <= targetIncline + thresholdRange)) {
         mounted = false;
         mountSample.add(false);
      } else {
         mounted = true;
         mountSample.add(true);
      }

      int count = 0;
      for (int i = 0; i < mountSample.size()-1; i++){
         if (mountSample.get(i)) count++;
      }

      if (secondMountSample.size() == 3) secondMountSample.remove(0);
      if (count >= Math.round(mountSample.size()/2)){
         secondMountSample.add(true);
      } else {
         secondMountSample.add(false);
      }
      System.out.println(secondMountSample.toString());
   }
}
