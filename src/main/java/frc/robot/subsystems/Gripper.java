// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Gripper extends SubsystemBase {
  /** Creates a new Gripper. */
  private final WPI_VictorSPX grippMotor;
  public Gripper() 
  {
    grippMotor = new WPI_VictorSPX(Constants.GRIPP_MOTOR);
  }

  public void setGrippMotor(double speed )
  {
    grippMotor.set(speed);
  }

  public void stopGrippMotor()
  {
    grippMotor.set(0);
  }
  
  public void stall()
  {
    grippMotor.set(Constants.STALL_SPEED);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
