// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.lang.annotation.Target;

import org.photonvision.PhotonTargetSortMode;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
// import frc.robot.commands.ArcadeDrive;
import limelightvision.limelight.frc.LimeLight;

public class DriveTrain extends SubsystemBase {

private WPI_VictorSPX leftBackMotor;
private WPI_VictorSPX rightFrontMotor;
private WPI_VictorSPX  rightBackMotor; 
private WPI_VictorSPX leftFrontMotor;
private MotorControllerGroup rightMotors;
private MotorControllerGroup leftMotors;
private DifferentialDrive drive;
private LimeLight limeLight;
  /** Creates a new DriveTrain. */
  public DriveTrain() {
    leftFrontMotor = new WPI_VictorSPX(Constants.LEFT_FRONT_MOTOR);
    leftBackMotor = new WPI_VictorSPX(Constants.LEFT_BACK_MOTOR);
    rightFrontMotor = new WPI_VictorSPX(Constants.RIGHT_FRONT_MOTOR);
    rightFrontMotor.setInverted(true);
    rightBackMotor = new WPI_VictorSPX(Constants.RIGHT_BACK_MOTOR);
    rightBackMotor.setInverted(true);
    rightMotors = new MotorControllerGroup(rightFrontMotor,rightBackMotor);
    leftMotors = new MotorControllerGroup(leftBackMotor, leftFrontMotor);
    
    limeLight = new LimeLight();
    drive = new DifferentialDrive(rightMotors,leftMotors);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setRightMotors(double speed)
  {
    rightFrontMotor.set(ControlMode.PercentOutput, speed*Constants.DRIVE_TRAIN_SPEED);
    rightBackMotor.set(ControlMode.PercentOutput, speed*Constants.DRIVE_TRAIN_SPEED);
  }
  public void setLeftMotors(double speed)
  {
    leftFrontMotor.set(ControlMode.PercentOutput, speed*Constants.DRIVE_TRAIN_SPEED);
    leftBackMotor.set(ControlMode.PercentOutput, speed*Constants.DRIVE_TRAIN_SPEED);
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

  public void allignTarget()
  {
    if (limeLight.getIsTargetFound() && limeLight.getdegRotationToTarget() !=0)
    {
      boolean negative = false;
      negative = -limeLight.getdegRotationToTarget() < 0;
      double turn = 0.3 + (0.02592 * Math.abs(limeLight.getdegRotationToTarget()));
      if (negative) turn = -turn;
      drive.arcadeDrive(0, turn);
      System.out.println("turn is " + turn);
    }
  }

  public boolean allignTargetLime()
  {
    PhotonPipelineResult result = RobotContainer.getResult();
    PhotonTrackedTarget target = result.getBestTarget();
    if (result.hasTargets() && target.getSkew() != 0)
    {
      boolean negative = false;
      negative = -target.getSkew() <0;
      double turn = 0.3 + (0.02592 * Math.abs(limeLight.getdegRotationToTarget()));
      if (negative)
      {
        turn = -turn;
      }
      drive.arcadeDrive(0, turn);
    }
    if (result.hasTargets() && 1.0 > target.getSkew() && target.getSkew() <-1)
    {
      return true;
    }
    return false;
  }

public void driveForward(String allignSpeed) {
}

//   public void initDefaultCommand() {
//     // Set the default command for a subsystem here.
//     setDefaultCommand(new ArcadeDrive());
// }


}
