package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Encoder;

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

   private Encoder encoder;

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
}
