package frc.robot.commands;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class MountChargingStation extends CommandBase {
   private Accelerometer accelerometer = new BuiltInAccelerometer();
   private DriveTrain driveTrain;
   private XboxController xbox;

   public double yAcc, previousYAcc;
   public double zAcc, previousZAcc;
   public double yJerk, zJerk;

   private boolean contactDetected;
   private int mountStage = 0;

   public void test(){
      update();
      if (xbox.getAButtonPressed()){
         System.out.println("" + yAcc + " " + yJerk + " " + contactDetected);
      }
      if (xbox.getBButtonPressed()){
         System.out.println("" + zAcc + " " + zJerk);
      }
   }

   public MountChargingStation(DriveTrain m_DriveTrain, XboxController m_XboxController){
      this.driveTrain = m_DriveTrain;
      this.xbox = m_XboxController;
   }

   @Override
   public void execute(){
      // update();
      switch (mountStage){
         case 0:
            // contact();
            break;
         case 1:
            break;
         case 2:
            break;
      }
   }

   private void contact(){
      if (!contactDetected) driveTrain.arcadeDrive(Constants.CHARGE_DRIVE_SPEED, 0);
      else mountStage++;
   }
   private void mount(){
      
   }

   /**Updates local data inside the command */
   private void update(){
      yAcc = accelerometer.getY();
      zAcc = accelerometer.getZ();
      yJerk = (yAcc - previousYAcc) / 0.02;
      zJerk = (zAcc - previousZAcc) / 0.02;
      previousYAcc = yAcc;
      previousZAcc = zAcc;

      if (yJerk > Constants.CHARGE_CONTACT_THRESHOLD && !contactDetected) contactDetected = true;
   }
}
