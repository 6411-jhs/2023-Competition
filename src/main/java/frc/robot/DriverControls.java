package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveTrain;

/**
 * Class containing all control modes for the robot. Currently only compatible for Xbox controller integration.
 * Use Constants.PRIMARY_JOYSTICK to set which joystick you want as the main functionality for some of the modes
 */
public class DriverControls {
   private XboxController xbox;
   private DriveTrain drive;
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
    * Left stick controls left wheels and right stick controls right wheels
    */
   public void tankMode(){
      drive.tankDrive(-xbox.getLeftY() * Constants.DRIVE_TRAIN_SPEED, -xbox.getRightY() * Constants.DRIVE_TRAIN_SPEED);
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
      boolean reverse = triggerCalc < 0, right;
      triggerCalc = Math.abs(triggerCalc);
      if (Constants.PRIMARY_JOYSTICK == "Left"){
         if (xbox.getLeftX() != 0 && triggerCalc != 0) stickCalc = ((triggerCalc) + Math.abs(xbox.getLeftX())) / 2;
         right = xbox.getLeftX() > 0;
      } else {
         if (xbox.getRightX() != 0 && triggerCalc != 0) stickCalc = ((triggerCalc) + Math.abs(xbox.getRightX())) / 2;
         right = xbox.getRightX() > 0;
      }
      if (reverse) triggerCalc = -triggerCalc;
      if (right) stickCalc = -stickCalc;
      System.out.println(stickCalc);
      drive.arcadeDrive(triggerCalc * Constants.DRIVE_TRAIN_SPEED, stickCalc * Constants.DRIVE_TRAIN_SPEED);
   }
}
