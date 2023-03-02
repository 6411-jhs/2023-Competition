// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  private final WPI_TalonFX armMotor;
  private final PIDController armPID;



  private double armMotorPosition;

  public Arm() 
  {
    armMotor = new WPI_TalonFX(Constants.ARM_MOTOR);
    armPID = new PIDController(Constants.ARM_PROPORTIONAL, Constants.ARM_INTEGRAL, Constants.ARM_DERIVITIVE);
    armMotorPosition = getArmMotorPostion();


  }

  public double getArmMotorPostion() {
    return armMotor.getSelectedSensorPosition()/Constants.FALCON_ENCODER_UNITS / Constants.ARM_GEAR_RATIO ;
  }

  public void setArmSpeed(double speed)
  {
    // if (speed <0&&getArmMotorPostion() <= .02)
    // {
    armMotor.set(speed *Constants.ARM_SPEED);
    // }
  }

  

  public void setArmPostion(double degree)
  {
    armMotorPosition = getArmMotorPostion();
    armMotor.set(armPID.calculate(armMotorPosition, degree));
  }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
