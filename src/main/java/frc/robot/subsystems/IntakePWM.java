//** This subsystem is a version of the intake built for PWM interfacing instead of CAN

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import frc.robot.Constants;

public class IntakePWM extends SubsystemBase {
   private PWMSparkMax intakeMotor;
   private String motorDirection = "Inward";

   public IntakePWM(){
      intakeMotor = new PWMSparkMax(Constants.INTAKE_MOTOR_PWM);
      intakeMotor.setInverted(true);
   }

   //*OPERATION CODE
   /**Turns intake on; will move forward or backward depending on direction settings from subsystem */
   public void on(){
      if (motorDirection == "Inward"){
         intakeMotor.set(Constants.INTAKE_SPEED);
     } else if (motorDirection == "Outward"){
        intakeMotor.set(-Constants.INTAKE_SPEED);
     }
   }
   /**Turns intake off; writes a value of 0 to the motor */
   public void off(){
      intakeMotor.set(0);
   }
   /**
    * Sets a raw speed value to the motor itself (Used for more accessible  operation)
    * @param speed Value between -1 and 1
    */
   public void set(double speed){
      intakeMotor.set(speed);
   }

   //*DIRECTION CODE
   /**
    * Returns the direction that the motor is set to
    * @return Direction of robot ("Inward" or "Outward")
    */
   public String getDirection(){
      return motorDirection;
   }
   /**
    * Sets the direction of the intake to move
    * @param direction Can be set to either "Inward" or "Outward" or it will throw an error
    */
   public void setDirection(String direction){
      try {
         if (direction == "Inward"){
            motorDirection = direction;
         } else if (direction == "Outward"){
            motorDirection = direction;
         } else {
            throw new Error("Invalid value set to intake direction, can either be 'Inward' or 'Outward'");
         }
      } catch(Error e){
         System.out.println(e);
      }
   }
   /**Toggles the direction between Inward and Outward when called */
   public void toggleDirection(){
      if (motorDirection == "Inward") motorDirection = "Outward";
      else motorDirection = "Inward";
   }
}
