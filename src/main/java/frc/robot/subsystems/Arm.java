package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Arm extends SubsystemBase {
   private final WPI_TalonFX armMotor;
   private double degreeSet;

   public Arm() {
      armMotor = new WPI_TalonFX(Constants.ARM_MOTOR);
   }

   /** 
    * Gets the motor encoder position of the arm
    * @returns Encoder value (0 <-> 360);
   */
   public double getMotorPosition() {
      return (armMotor.getSelectedSensorPosition() / Constants.FALCON_ENCODER_UNITS / Constants.ARM_GEAR_RATIO) *360;
   }

   /** Sets arm speed based on set limitations; if speed is set to go a direction the arm cannot physically got it will set to 0. */
   public void setArmSpeed(double speed) {
      // System.out.println(getMotorPosition() + " " + speed);
      if (speed < 0 && getMotorPosition() > -205){
         armMotor.set(speed);
      } else if (speed > 0 && getMotorPosition() < -15){
         armMotor.set(speed);
      }

      else if(RobotContainer.m_joystick.getRawButtonPressed(2))
      {
         armMotor.set(speed);
      }
      // System.out.println(getMotorPosition());
   }

   /** 
    * Set position of arm in degrees. (90 degrees = straight up)
    * @returns Whether the motor has reached that position
    */
   public boolean setPosition(double degree){
      //Calculations
      double targetDegree = -degree;
      double speedCalc = (targetDegree - getMotorPosition()) * 0.013;

      // System.out.println(speedCalc + " " + targetDegree + " " + getMotorPosition());

      if (getMotorPosition() <= targetDegree - Constants.ARM_ENCODER_THRESHOLD_RANGE || getMotorPosition() >= targetDegree + Constants.ARM_ENCODER_THRESHOLD_RANGE){
         if (Math.abs(speedCalc) > Constants.MAX_ARM_SPEED){
            if (speedCalc < 0) setArmSpeed(-Constants.MAX_ARM_SPEED);
            else setArmSpeed(Constants.MAX_ARM_SPEED);
         } else {
            if (Math.abs(speedCalc) < Constants.MIN_ARM_SPEED){
               if (speedCalc < 0) setArmSpeed(-Constants.MIN_ARM_SPEED);
               else setArmSpeed(Constants.MIN_ARM_SPEED);
            } else {
               setArmSpeed(speedCalc);
            }
         }
         return false;
      } else {
        setArmSpeed(0);
        return true;
      }
   }
}
