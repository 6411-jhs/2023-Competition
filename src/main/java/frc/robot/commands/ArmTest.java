// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArmTest extends CommandBase {
   /** Creates a new ArmTest. */
   public ArmTest() {
      // Use addRequirements() here to declare subsystem dependencies.
   }

   // Called when the command is initially scheduled.
   @Override
   public void initialize() {

   }

   // Called every time the scheduler runs while the command is scheduled.
   @Override
   public void execute() {
      if (!(Constants.ARM_SPEED * RobotContainer.m_joystick.getRawAxis(1) > 0
            && RobotContainer.m_arm.getArmMotorPosition() >= -.02)
            && !(Constants.ARM_SPEED * RobotContainer.m_joystick.getRawAxis(1) < 0
                  && RobotContainer.m_arm.getArmMotorPosition() < Constants.GROUND_LIMIT)) {
         RobotContainer.m_arm.setArmSpeed(Constants.ARM_SPEED * RobotContainer.m_joystick.getRawAxis(1));
      } else {
         RobotContainer.m_arm.setArmSpeed(Constants.SIT_PRETTY_SPEED);
      }
   }

   // Called once the command ends or is interrupted.
   @Override
   public void end(boolean interrupted) {
      if (RobotContainer.m_arm.getArmMotorPosition() < Constants.ARM_VERTICAL_POS) {
         RobotContainer.m_arm.setArmSpeed(Constants.SIT_PRETTY_SPEED);

      } else {
         RobotContainer.m_arm.setArmSpeed(0.0);
      }
   }

   // Returns true when the command should end.
   @Override
   public boolean isFinished() {
      return false;
   }
}
