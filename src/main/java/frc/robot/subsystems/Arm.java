// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.math.controller.PIDController;

public class Arm extends SubsystemBase {
   /** Creates a new Arm. */
   private final WPI_TalonFX armMotor;
   private final PIDController armPID;

   private double armMotorPosition;

   public Arm() {
      armMotor = new WPI_TalonFX(Constants.ARM_MOTOR);
      armPID = new PIDController(Constants.ARM_PROPORTIONAL, Constants.ARM_INTEGRAL, Constants.ARM_DERIVITIVE);
      armMotorPosition = getArmMotorPosition();
   }

   public double getArmMotorPosition() {
      return armMotor.getSelectedSensorPosition() / Constants.FALCON_ENCODER_UNITS / Constants.ARM_GEAR_RATIO;
   }

   public void setArmSpeed(double speed) {
      if (!(speed > 0 && getArmMotorPosition() >= -.02) && !(speed < 0 && getArmMotorPosition() < Constants.GROUND_LIMIT)) {
         armMotor.set(speed * Constants.ARM_SPEED);
         System.out.println("before" + speed + "after" + speed * Constants.ARM_SPEED);
      }
   }

   public void setArmPosition(double degree) {
      armMotorPosition = getArmMotorPosition();
      armMotor.set(armPID.calculate(armMotorPosition, degree));
   }

   public void setDegree(double degree){
      //Local Constants
      double maxArmSpeed = 0.4;
      double encoderThesholdRange = 0;
      int degreeRange[] = {30,270};
      double encoderRange[] = {-0.00626,-0.878};

      double targetEncoderValue = (encoderRange[0] + ((encoderRange[1] - encoderRange[0]) / (degreeRange[1] - degreeRange[0])) * (degree - degreeRange[0]));
      double speedCalc = (targetEncoderValue - getArmMotorPosition()) * 1.8;

      System.out.println(speedCalc + " " + getArmMotorPosition());
      if (getArmMotorPosition() <= targetEncoderValue - encoderThesholdRange || getArmMotorPosition() >= targetEncoderValue + encoderThesholdRange){
         if (Math.abs(speedCalc) > maxArmSpeed){
            if (speedCalc < 0) armMotor.set(-maxArmSpeed);
            else armMotor.set(maxArmSpeed);
         } else {
            armMotor.set(speedCalc);
         }
      } else {
        armMotor.set(0);
      }
   }

   @Override
   public void periodic() {
      // This method will be called once per scheduler run
   }
}
