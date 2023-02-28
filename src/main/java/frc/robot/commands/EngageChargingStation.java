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

   //Local Constants
   private double thresholdRange = 0.05;
   private int zSampleSize = 10;
   private double targetIncline = 0.96;

   private ArrayList<Double> zSample = new ArrayList<Double>();
   private double zAcc;
   private boolean mounted;
   private boolean balanced;

   private int engageStage = 0;

   public EngageChargingStation(DriveTrain m_DriveTrain, XboxController m_XboxController){
      this.driveTrain = m_DriveTrain;
      this.xbox = m_XboxController;
   }

   @Override
   public void initialize(){
      zAcc = accelerometer.getZ();
   }

   @Override
   public void execute(){
      update();
      if (!mounted && engageStage == 0){
         mount();
      } else if (mounted && engageStage == 0){
         engageStage = 1;
         if (zSampleSize - 3 >= 1) {
            zSampleSize -= 3;
         }
         System.out.println("Stage 1 done!");
      }

      if (!balanced && engageStage == 1){
         balance();
      } else {
         System.out.println("Stage 2 done!");
      }
   }

   public void test(){
      if (xbox.getYButton()){
         update();
         if (zSample.size() == zSampleSize || (zSample.size() > zSampleSize)){
            System.out.println(zAcc);
         }
      }
      if (xbox.getBButton()){
         mount();
      }
      if (xbox.getAButton()){
         balance();
      }
      if (xbox.getXButton()){
         execute();
      }
   }

   //Adjusts robot to balance and engage on the charging station
   private void balance(){
      if (!(zAcc >= 0.988 - thresholdRange && zAcc <= 0.988 + thresholdRange)){
         driveTrain.arcadeDrive((0.988 - zAcc) * 12, 0);
         balanced = false;
      } else {
         balanced = true;
         System.out.println("Balanced!");
      }
   }
   //Moves robot to sit on the incline of the charging station ramp
   private void mount(){
      if (!(zAcc >= targetIncline - thresholdRange && zAcc <= targetIncline + thresholdRange)){
         driveTrain.arcadeDrive(Constants.CHARGE_DRIVE_SPEED, 0);
         mounted = false;
      } else {
         mounted = true;
         System.out.println("Mounted!");
      }
   }
   private void update(){
      //Uses an average of accelerometer values rather than raw data; this makes the robot move smoother.
      if (zSample.size() != zSampleSize && !(zSample.size() > zSampleSize)){
         zSample.add(accelerometer.getZ());
      } else {
         int sum = 0;
         for (int i = 0; i < zSample.size()-1; i++){
            sum += zSample.get(i);
         }
         zAcc = sum / zSample.size();
         zSample.clear();
      }
   }
}
