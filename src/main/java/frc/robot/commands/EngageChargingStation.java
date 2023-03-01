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

   // Local Constants
   private double thresholdRange = 0.027;
   private double targetIncline = 0.96;
   private double targetBalance = 0.99;


   //Data Collection
   //Holds the z axis acceleration of the robot; gravity is also detected as an accleration
   private double zAcc, yAcc;

   //These variables hold the data for mounting, balancing and direction sensing data
   //Since accelerometer values are extremely inconsistent, the program has to use a different tecnique when it comes to sensing
   //The program essentially takes the condition of which the bot's accelerometer values matching that of mounting, balancing
   //and direction targets. When a condition is met it is added to a list to be counted into a DPS (Detections Per Second)
   //It uses the rate of condition detection to sense whether or not it's balanced, mounted, etc.

   private int mountDPS;
   private int mountDPSThreshold = 20; //<---- This needs to be set!
   private ArrayList<Boolean> mountDetections = new ArrayList<Boolean>();
   private int mountDetectionsLimit = 50;

   private int balanceDPS;
   private int balanceDPSThreshold = 10; //<----This needs to be set!
   private ArrayList<Boolean> balanceDetections = new ArrayList<Boolean>();
   private int balanceDetectionsLimit = 50;

   private int directionDPS;
   private int directionDPSThreshold = 10; //<----This needs to be set!
   private ArrayList<Boolean> directionDetections = new ArrayList<Boolean>();
   private int directionDetectionsLimit = 50;

   //Delete these after
   private boolean mounted;
   private boolean balanced;

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
      
   }

   public void test() {
      if (xbox.getYButton()) {
         update();
         System.out.println(mounted + " " + mountDPS); //1
         // System.out.println(yAcc); //2
         // System.out.println(balanced + " " + balanceDPS);//3
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
      if (balanceDPS <= balanceDPSThreshold){
         System.out.println("Balanced!");
      } else {
         //Direction code!
         System.out.println("Not Balanced!");
      }
   }

   // Moves robot to sit on the incline of the charging station ramp
   private void mount() {
      if (mountDPS >= mountDPSThreshold){
         System.out.println("Mounted!");
      } else {
         driveTrain.arcadeDrive(Constants.CHARGE_DRIVE_SPEED, 0);
         System.out.println("Not Mounted!");
      }
   }
   
   //Updates all the data the charging station program uses
   private void update() {
      // Uses an average of accelerometer values rather than raw data; this makes the
      // robot move smoother.
      // System.out.println(mountSample.toString());
      zAcc = accelerometer.getZ();
      yAcc = accelerometer.getY();

      //Mounting data collection
      if (mountDetections.size() == mountDetectionsLimit) mountDetections.remove(0);
      if (zAcc >= targetIncline - thresholdRange && zAcc <= targetIncline + thresholdRange) {
         mountDetections.add(true);
         mounted = true; //Delete this after
      } else {
         mountDetections.add(false);
         mounted = false; //Delete this after
      }
      mountDPS = 0;
      for (int i = 0; i < mountDetections.size(); i++){
         if (mountDetections.get(i)) mountDPS++;
      }

      //Balancing data collection
      if (balanceDetections.size() == balanceDetectionsLimit) balanceDetections.remove(0);
      if (zAcc >= targetBalance - thresholdRange && zAcc <= targetBalance + thresholdRange) {
         balanceDetections.add(true);
         balanced = true; //Delete this after
      } else {
         balanceDetections.add(false);
         balanced = false; //Delete this after
      }
      balanceDPS = 0;
      for (int i = 0; i < balanceDetections.size(); i++){
         if (balanceDetections.get(i)) balanceDPS++;
      }

      //Direction sensing data collection
      if (directionDetections.size() == directionDetectionsLimit) directionDetections.remove(0);
      if (yAcc >= 0) {
         directionDetections.add(true);
      } else {
         directionDetections.add(false);
      }
      directionDPS = 0;
      for (int i = 0; i < directionDetections.size(); i++){
         if (directionDetections.get(i)) directionDPS++;
      }
   }
}
