// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// import frc.robot.commands.ArcadeDrive;

public class DriveTrain extends SubsystemBase {

private VictorSP leftFrontMotor;
private VictorSP leftBackMotor;
private VictorSP rightFrontMotor;
private VictorSP rightBackMotor; 
private MotorControllerGroup leftMotors;
private MotorControllerGroup rightMotors;
private DifferentialDrive drive;
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    leftFrontMotor = new VictorSP(Constants.LEFT_FRONT_MOTOR);
    leftBackMotor = new VictorSP(Constants.LEFT_BACK_MOTOR);
    rightFrontMotor = new VictorSP(Constants.RIGHT_FRONT_MOTOR);
    rightBackMotor = new VictorSP(Constants.RIGHT_BACK_MOTOR);
    leftMotors = new MotorControllerGroup(leftFrontMotor, leftBackMotor);
    rightMotors = new MotorControllerGroup(rightFrontMotor, rightBackMotor);
    rightMotors.setInverted(true);
    drive = new DifferentialDrive(leftMotors, rightMotors);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setRightMotors(double speed)
  {
    rightMotors.set(speed*Constants.DRIVE_TRAIN_SPEED);
  }
  public void setLeftMotors(double speed)
  {
    leftMotors.set(speed*Constants.DRIVE_TRAIN_SPEED);
  }
  public void stop()
  {
    drive.stopMotor();
  }

  public void arcadeDrive(double speed, double turn){
    drive.arcadeDrive(speed, turn);
  }

  public void tankDrive(double left,  double right)
  {
    drive.tankDrive(left,right);
  }

//   public void initDefaultCommand() {
//     // Set the default command for a subsystem here.
//     setDefaultCommand(new ArcadeDrive());
// }


}
