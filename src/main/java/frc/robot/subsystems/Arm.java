// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  private Talon armMotor;
  private Encoder falconencoder;
  public Arm() 
  {
    armMotor = new Talon(Constants.ARM_MOTOR);
    falconencoder = new Encoder(Constants.ENCODE_A, Constants.ENCODE_B);
  }

  public void setArmSpeed(double speed)
  {
    armMotor.set(speed *Constants.ARM_SPEED);
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
