package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveTrain;

import java.time.Instant;
import java.time.Duration;

/**
 * Class containing all control modes for the robot. Currently only compatible for Xbox controller integration.
 * Use Constants.PRIMARY_JOYSTICK to set which joystick you want as the main functionality for some of the modes
 */
public class DriverControls {
   private XboxController xbox;
   private DriveTrain drive;
   private int currentMode = 0;
   private boolean modeSwitchEToggle = false;
   /**
    * Defines the xbox controller and sets the drive train
    * @param driveTrain Activated drive train class for motor controlling
    * @param xboxController Activated xbox controller instance for control routing
    */
   public DriverControls(DriveTrain driveTrain, XboxController xboxController){
      this.xbox = xboxController;
      this.drive = driveTrain;
   }

   /**
    * Uses a configuration that allows the user to change which control mode they're using. Press LB + RB to cycle through modes.
    */
   public void ModeSwitchMode(){
      String modes[] = {"TankJoystick","TankTrigger","Arcade","SingleStick","TriggerHybrid","Game","MarioCart"};
      if (xbox.getLeftBumperPressed() && xbox.getRightBumperPressed()){
         if (!modeSwitchEToggle) {
            currentMode++;
            if (currentMode > 6) currentMode = 0;
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
            break;
         case "Game":
            gameMode();
            break;
         case "MarioCart":
            marioCartMode();
            break;
      }
   }

   /**
    * Left stick controls left wheels and right stick controls right wheels
    */
   public void tankJoystickMode(){
      drive.tankDrive(-xbox.getLeftY() * Constants.DRIVE_TRAIN_SPEED, -xbox.getRightY() * Constants.DRIVE_TRAIN_SPEED);
   }

   /**
    * Use the trigger instead of joysticks for tank driving.
    */
   public void tankTriggerMode(){
      double triggerCalc = (xbox.getLeftTriggerAxis() + xbox.getRightTriggerAxis()) / 2;
      double turnCalc = xbox.getLeftTriggerAxis() - xbox.getRightTriggerAxis();
      drive.arcadeDrive(triggerCalc * Constants.DRIVE_TRAIN_SPEED, turnCalc * Constants.DRIVE_TRAIN_SPEED);
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
   }

   /**
    * Uses the primary joystick for all direction and speed
    */
   public void singleStickMode(){
      if (Constants.PRIMARY_JOYSTICK == "Left"){
         drive.arcadeDrive(-xbox.getLeftY() * Constants.DRIVE_TRAIN_SPEED, -xbox.getLeftX() * Constants.DRIVE_TRAIN_SPEED);
      } else {
         drive.arcadeDrive(-xbox.getRightY() * Constants.DRIVE_TRAIN_SPEED, -xbox.getRightX() * Constants.DRIVE_TRAIN_SPEED);
      }
   }
   /**
    * Uses primary stick X as the arcade turn and uses the right and left trigger for forward and back.
    */
   public void triggerHybridMode(){
      if (Constants.PRIMARY_JOYSTICK == "Left"){
         drive.arcadeDrive(xbox.getRightTriggerAxis() * Constants.DRIVE_TRAIN_SPEED - xbox.getLeftTriggerAxis(), -xbox.getLeftX());
      } else {
         drive.arcadeDrive(xbox.getRightTriggerAxis() * Constants.DRIVE_TRAIN_SPEED - xbox.getLeftTriggerAxis(), -xbox.getRightX());
      }
   }
   /**
    * Uses primary stick as the direction of the bot; Uses right and left trigger as forward and back.
    */
   public void gameMode(){
      double triggerCalc = -xbox.getLeftTriggerAxis() + xbox.getRightTriggerAxis(), stickCalc = 0;
      boolean reverse = triggerCalc < 0, left;
      triggerCalc = Math.abs(triggerCalc);
      if (Constants.PRIMARY_JOYSTICK == "Left"){
         if (xbox.getLeftX() != 0 && triggerCalc != 0) stickCalc = ((triggerCalc) + Math.abs(xbox.getLeftX())) / 2;
         left = xbox.getLeftX() < 0;
      } else {
         if (xbox.getRightX() != 0 && triggerCalc != 0) stickCalc = ((triggerCalc) + Math.abs(xbox.getRightX())) / 2;
         left = xbox.getRightX() < 0;
      }
      if (reverse) triggerCalc = -triggerCalc;
      if (!reverse) stickCalc = -stickCalc;
      if (left) stickCalc = -stickCalc;
      drive.arcadeDrive(triggerCalc * Constants.DRIVE_TRAIN_SPEED, stickCalc * Constants.DRIVE_TRAIN_SPEED);
   }

   //todo - Make mario cart mode
   private boolean marioCartHopToggle = false;
   private boolean marioCartAbilityToggle = false;

   public void marioCartMode(){
      //CONTROL
      double joyStick[] = {xbox.getLeftX(),xbox.getLeftY()};
      if (xbox.getBButton()){
         System.out.println("B!");
         if (joyStick[1] > 0) joyStick[1] = 0;
         drive.arcadeDrive(-joyStick[1] * Constants.DRIVE_TRAIN_SPEED, joyStick[0] * Constants.DRIVE_TRAIN_SPEED);
      } else if (xbox.getAButton()){
         if (joyStick[1] < 0) joyStick[1] = 0;
         drive.arcadeDrive(-joyStick[1] * Constants.DRIVE_TRAIN_SPEED, joyStick[0] * Constants.DRIVE_TRAIN_SPEED);
      } else drive.arcadeDrive(0, 0);
      
      //HOP
      if (xbox.getRightBumper() && !xbox.getLeftBumper() && !marioCartHopToggle){
         System.out.println("Hop!");

         Instant now = Instant.now();
         Duration time = Duration.ofMillis(200);

         // while (Duration.between(now, Instant.now()).toMillis() <= time.toMillis()){
         //    drive.arcadeDrive((joyStick[1] * Constants.DRIVE_TRAIN_SPEED)+0.2, joyStick[0] * Constants.DRIVE_TRAIN_SPEED);
         // }

         marioCartHopToggle = true;
         new Thread(() -> {
            try {
               drive.arcadeDrive((joyStick[1] * Constants.DRIVE_TRAIN_SPEED)+0.2, joyStick[0] * Constants.DRIVE_TRAIN_SPEED);
               Thread.sleep(1000);
               marioCartHopToggle = false;
            } catch (Exception e){
               System.err.println(e);
            }
         }).start();
      }
      // if (marioCartHopToggle) drive.arcadeDrive((joyStick[1] * Constants.DRIVE_TRAIN_SPEED)+0.2, joyStick[0]);

      //ABILITY
      if (xbox.getLeftBumperPressed() && xbox.getRightBumperPressed() && !marioCartAbilityToggle){
         System.out.println("Ability!");
         marioCartAbilityToggle = true;
         new Thread(() -> {
            try {
               Thread.sleep(3000);
               marioCartHopToggle = false;
            } catch (Exception e){
               System.err.println(e);
            }
         }).start();
      }
      if (marioCartAbilityToggle) drive.arcadeDrive(0, Constants.DRIVE_TRAIN_SPEED);
   }
}
