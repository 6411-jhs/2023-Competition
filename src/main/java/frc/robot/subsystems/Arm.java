package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;

public class Arm extends SubsystemBase {
   private final WPI_TalonFX armMotor;
   private double degreeSet;

   public Arm() {
      armMotor = new WPI_TalonFX(Constants.ARM_MOTOR);
   }

   /** 
    * Gets the motor encoder position of the arm
    * @returns Encoder value (0 <-> -1);
   */
   public double getMotorPosition() {
      return armMotor.getSelectedSensorPosition() / Constants.FALCON_ENCODER_UNITS / Constants.ARM_GEAR_RATIO;
   }

   /** Sets arm speed based on set limitations; if speed is set to go a direction the arm cannot physically got it will set to 0. */
   public void setArmSpeed(double speed) {
      if ((speed > 0 && getMotorPosition() >= Constants.ARM_ENCODER_RANGE[0]) || (speed < 0 && getMotorPosition() <= Constants.ARM_ENCODER_RANGE[1])){
         armMotor.set(0);
      } else {
         armMotor.set(speed * Constants.MAX_ARM_SPEED);
      }
   }

   /** 
    * Set position of arm in degrees. (90 degrees = straight up)
    * @returns Whether the motor has reached that position
    */
   public boolean setPosition(double degree){
      //Calculations
      double targetEncoderValue = -0.002777 * degree;
      double speedCalc = (targetEncoderValue - getMotorPosition()) * 2.3;

      System.out.println(speedCalc + " " + targetEncoderValue);
      return false;

      // if (getMotorPosition() <= targetEncoderValue - Constants.ARM_ENCODER_THRESHOLD_RANGE || getMotorPosition() >= targetEncoderValue + Constants.ARM_ENCODER_THRESHOLD_RANGE){
      //    if (Math.abs(speedCalc) > Constants.MAX_ARM_SPEED){
      //       if (speedCalc < 0) setArmSpeed(-Constants.MAX_ARM_SPEED);
      //       else setArmSpeed(Constants.MAX_ARM_SPEED);
      //    } else {
      //       setArmSpeed(speedCalc);
      //       System.out.println(speedCalc);
      //    }
      //    return false;
      // } else {
      //   setArmSpeed(0);
      //   return true;
      // }
   }
}
