package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;

import com.kauailabs.navx.frc.AHRS;


import frc.robot.Constants;

/** Drive train container; holds all functionality for the robot drive train. */
public class DriveTrain extends SubsystemBase {

   private WPI_VictorSPX leftBackMotor;
   private WPI_VictorSPX rightFrontMotor;
   private WPI_VictorSPX rightBackMotor;
   private WPI_VictorSPX leftFrontMotor;
   private MotorControllerGroup rightMotors;
   private MotorControllerGroup leftMotors;
   private DifferentialDrive drive;

   public Encoder encoder;
   public AHRS gyro;

   private boolean brakesActive = false;


   /** Creates a new DriveTrain. */
   public DriveTrain() {
      leftFrontMotor = new WPI_VictorSPX(Constants.LEFT_FRONT_MOTOR);
      leftBackMotor = new WPI_VictorSPX(Constants.LEFT_BACK_MOTOR);

      rightFrontMotor = new WPI_VictorSPX(Constants.RIGHT_FRONT_MOTOR);
      rightBackMotor = new WPI_VictorSPX(Constants.RIGHT_BACK_MOTOR);

      rightMotors = new MotorControllerGroup(rightFrontMotor, rightBackMotor);
      leftMotors = new MotorControllerGroup(leftBackMotor, leftFrontMotor);

      leftMotors.setInverted(true);

      drive = new DifferentialDrive(rightMotors, leftMotors);

      encoder = new Encoder(7, 8, 9);
      encoder.reset();

      gyro = new AHRS(SerialPort.Port.kUSB);
   }

   /** Set right side motor speed */
   public void setRightMotors(double speed) {
      rightFrontMotor.set(ControlMode.PercentOutput, speed * Constants.DRIVE_TRAIN_SPEED);
      rightBackMotor.set(ControlMode.PercentOutput, speed * Constants.DRIVE_TRAIN_SPEED);
   }

   /** Set left side motor speed */
   public void setLeftMotors(double speed) {
      leftFrontMotor.set(ControlMode.PercentOutput, speed * Constants.DRIVE_TRAIN_SPEED);
      leftBackMotor.set(ControlMode.PercentOutput, speed * Constants.DRIVE_TRAIN_SPEED);
   }

   /** Set both left and right side motors to a certain speed */
   public void driveForward(double speed) {
      setLeftMotors(speed);
      setRightMotors(-speed);
   }

   /** Uses DifferentialDrive to create arcade drive */
   public void arcadeDrive(double speed, double turn) {
      drive.arcadeDrive(speed, turn);
   }
   /** Uses DifferentialDrive to create tank drive */
   public void tankDrive(double left, double right) {
      drive.tankDrive(left, right);
   }

   /** Stops the differential drive */
   public void stop() {
      drive.stopMotor();
   }

   /** Get encoder value of the drive train */
   public double getEncoderDistance(){
      return encoder.getDistance();
   }

   /**
    * Whether or not to enable brake mode for the drive train.
    * This makes it so the motor controllers will adjust the output accordingly so that the robot doesn't move
    * @param enable Whether or not to enable brake mode
    */
   public void setBrakeMode(boolean enable){//test
      brakesActive = enable;
      if (enable){
         leftBackMotor.setNeutralMode(NeutralMode.Brake);
         leftFrontMotor.setNeutralMode(NeutralMode.Brake);
         rightBackMotor.setNeutralMode(NeutralMode.Brake);
         rightFrontMotor.setNeutralMode(NeutralMode.Brake);
      } else {
         leftBackMotor.setNeutralMode(NeutralMode.Coast);
         leftFrontMotor.setNeutralMode(NeutralMode.Coast);
         rightBackMotor.setNeutralMode(NeutralMode.Coast);
         rightFrontMotor.setNeutralMode(NeutralMode.Coast);
      }
   }
   /**
    * Get whether or not the drive train motors are set to brake mode
    * @return Whether or not the drive train is in brake mode
    */
   public boolean getBrakeMode(){
      return brakesActive;
   }
}
