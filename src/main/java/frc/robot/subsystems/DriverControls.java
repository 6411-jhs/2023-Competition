package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.Arrays;

import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants;

import frc.robot.commands.BalanceChargingStation;
import frc.robot.commands.MountChargingStation;

/**
 * Class containing all control modes for the robot. 
 * Currently only compatible for Xbox controller integration.
 * Use Constants.PRIMARY_JOYSTICK to set which joystick you want as the main functionality for some of the modes
 * note:  setting the primary joystick still needs testing
 */
public class DriverControls extends SubsystemBase {
   private XboxController xbox;
   private DriveTrain drive;

   private int currentMode = -1;
   private boolean modeSwitchEToggle = false;

   private double driveSpeedCycle[] = {0,0,0};
   private int driveSpeedSelect = 1;

   BalanceChargingStation balanceChargingStation;
   MountChargingStation mountChargingStation;

   public DriverControls(XboxController xbox ){
      this.xbox = xbox;
      drive = RobotContainer.m_driveTrain;

      balanceChargingStation = new BalanceChargingStation(drive);
      mountChargingStation = new MountChargingStation(drive);

      driveSpeedCycle[0] = Constants.A_BUTTON_SPEED;
      driveSpeedCycle[1] = Constants.DRIVE_TRAIN_SPEED;
      driveSpeedCycle[2] = Constants.Y_BUTTON_SPEED;
   }

   /**
    * Uses a configuration that allows the user to change which control mode they're using. Press LB + RB to cycle through modes.
    * @param defaultMode Default mode for the bot to start on
    */
   public void ModeSwitchMode(String defaultMode){
      String modes[] = {"TankJoystick","TankTrigger","Arcade","SingleStick","TriggerHybrid"};
      if (currentMode == -1){
         if (defaultMode == null) currentMode = 0;
         else currentMode = Arrays.asList(modes).indexOf(defaultMode);
      }
      if (xbox.getLeftBumperPressed() && xbox.getRightBumperPressed()){
         if (!modeSwitchEToggle) {
            currentMode++;
            if (currentMode > modes.length-1) currentMode = 0;
            modeSwitchEToggle = true;
            System.out.println("> Switched to " + modes[currentMode] + " mode");
         }
      }
      if (!xbox.getLeftBumperPressed() && !xbox.getRightBumperPressed()){
         modeSwitchEToggle = false;
      }
      switch (modes[currentMode]){
         case "TankJoystick":
            tankJoystickMode();
            break;
         case "TankTrigger":
            tankTriggerMode();
            break;
         case "Arcade":
            arcadeMode();
            break;
         case "SingleStick":
            singleStickMode();
            break;
         case "TriggerHybrid":
            triggerHybridMode();
      }
   }

   /**
    * Uses a configuration that allows the user to change which control mode they're using. Press LB + RB to cycle through modes.
    * @param defaultMode Default mode for the bot to start on
    * (Uses command based API for ease of use)
    */
   public CommandBase c_ModeSwitchMode(String defaultMode){
      return this.runOnce(() -> {
         ModeSwitchMode(defaultMode);
      });
   }

   /** Left stick controls left wheels and right stick controls right wheels. */
   public void tankJoystickMode(){
      drive.tankDrive(-xbox.getLeftY() * Constants.DRIVE_TRAIN_SPEED, -xbox.getRightY() * Constants.DRIVE_TRAIN_SPEED);
      otherControls();
   }

   /** Use the trigger instead of joysticks for tank driving. */
   public void tankTriggerMode(){
      double triggerCalc = (xbox.getLeftTriggerAxis() + xbox.getRightTriggerAxis()) / 2;
      double turnCalc = xbox.getLeftTriggerAxis() - xbox.getRightTriggerAxis();
      drive.arcadeDrive(triggerCalc * Constants.DRIVE_TRAIN_SPEED, turnCalc * Constants.DRIVE_TRAIN_SPEED);
      otherControls();
   }
   /**
    * This arcade mode is a bit different from the original arcade mode. The y value of the primary stick controls
    * forward and back (arcade speed) and the x value fo the secondary stick controls direction (arcade turn)
    */
   public void arcadeMode(){
      if (Constants.PRIMARY_JOYSTICK == "Left"){
         drive.arcadeDrive(-xbox.getLeftY() * Constants.DRIVE_TRAIN_SPEED, -xbox.getRightX() * Constants.DRIVE_TRAIN_SPEED);
      } else {
         drive.arcadeDrive(-xbox.getRightY() * Constants.DRIVE_TRAIN_SPEED, -xbox.getLeftX() * Constants.DRIVE_TRAIN_SPEED);
      }
      otherControls();
   }

   /** Uses the primary joystick for all direction and speed. */
   public void singleStickMode(){
      if (Constants.PRIMARY_JOYSTICK == "Left"){
         drive.arcadeDrive(-xbox.getLeftY() * Constants.DRIVE_TRAIN_SPEED, -xbox.getLeftX() * Constants.DRIVE_TRAIN_SPEED);
      } else {
         drive.arcadeDrive(-xbox.getRightY() * Constants.DRIVE_TRAIN_SPEED, -xbox.getRightX() * Constants.DRIVE_TRAIN_SPEED);
      }
      otherControls();
   }
   /** Uses primary stick X as the arcade turn and uses the right and left trigger for forward and back. */
   public void triggerHybridMode(){
      if (Constants.PRIMARY_JOYSTICK == "Left"){
         drive.arcadeDrive(xbox.getRightTriggerAxis() * driveSpeedCycle[driveSpeedSelect] - (xbox.getLeftTriggerAxis() * driveSpeedCycle[driveSpeedSelect]), xbox.getLeftX() * driveSpeedCycle[driveSpeedSelect]);
      } else {
         drive.arcadeDrive(xbox.getRightTriggerAxis() * driveSpeedCycle[driveSpeedSelect] - (xbox.getLeftTriggerAxis() * driveSpeedCycle[driveSpeedSelect]), xbox.getRightX() * driveSpeedCycle[driveSpeedSelect]);
      }
      otherControls();
   }

   private void otherControls(){
      if (Constants.SPEED_SWITCH_MODE == "Button"){
         if (xbox.getYButton()){
            driveSpeedSelect = 2;
         } else if (xbox.getXButton()){
            driveSpeedSelect = 1;
         } else if (xbox.getAButton()){
            driveSpeedSelect = 0;
         }
      } else if (Constants.SPEED_SWITCH_MODE == "Bumper"){
         if (xbox.getRightBumper()){
            driveSpeedSelect++;
            if (driveSpeedSelect > 2) driveSpeedSelect = 0;
         } else if (xbox.getLeftBumper()){
            driveSpeedSelect--;
            if (driveSpeedSelect < 0) driveSpeedSelect = 2;
         }
      }
   }
}
