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
      
   }

   public void test() {
      update();
      System.out.println(yAcc + " " + yJerk);
   }

   // Adjusts robot to balance and engage on the charging station
   private void balance() {
   }

   // Moves robot to sit on the incline of the charging station ramp
   private void mount() {
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
   }
}
