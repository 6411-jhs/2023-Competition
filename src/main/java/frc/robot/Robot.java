// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

   private Command m_autonomousCommand;
   private RobotContainer m_robotContainer;

   /** Gets called when robot is started up */
   @Override
   public void robotInit() {
      // Initializes create robot code and begins running
      m_robotContainer = new RobotContainer();
      m_autonomousCommand = m_robotContainer.getAutoCommand();
      if (m_autonomousCommand != null){
         m_autonomousCommand.schedule();
      }
      // m_autonomousCommand = m_robotContainer.m_auto.leftPosition();
   }

   /**
    * This function is called every robot packet, no matter the mode. Use this for
    * items like
    * diagnostics that you want ran during disabled, autonomous, teleoperated and
    * test.
    *
    * <p>
    * This runs after the mode specific periodic functions, but before LiveWindow
    * and
    * SmartDashboard integrated updating.
    */
   @Override
   public void robotPeriodic() {
      // Runs entire robot schedule system
      CommandScheduler.getInstance().run();
   }

   //*DISABLED
   /** Gets called when robot is disabled. */
   @Override
   public void disabledInit() {
   }

   /** This function is called periodically while robot is disabled. */
   @Override
   public void disabledPeriodic() {
   }

   //*AUTONOMOUS MODE
   /** Gets called when autonmous mode is enabled. */
   @Override
   public void autonomousInit() {
      m_autonomousCommand = m_robotContainer.getAutoCommand();

      // schedule the autonomous command (example)
      if (m_autonomousCommand != null) {
         m_autonomousCommand.schedule();
      }
      else{
         System.out.println("auto is null");
      }
   }

   /** This function is called periodically during autonomous. */
   @Override
   public void autonomousPeriodic() {

   }

   //*TELEOP MODE
   /** Gets called when teleop mode is enabled */
   @Override
   public void teleopInit() {
      //Disables autonomous code
      if (m_autonomousCommand != null) {
         m_autonomousCommand.cancel();
      }
   }

   /** This function is called periodically during operator control. */
   @Override
   public void teleopPeriodic() {
   }

   //*TEST MODE
   /** Gets called when test mode is enabled */
   @Override
   public void testInit() {
      // Cancels all running commands at the start of test mode.
      CommandScheduler.getInstance().cancelAll();
   }

   /** This function is called periodically during test mode. */
   @Override
   public void testPeriodic() {
   }

   //*SIMULATION MODE
   /** This function is called once when the robot is first started up. */
   @Override
   public void simulationInit() {
   }

   /** This function is called periodically whilst in simulation. */
   @Override
   public void simulationPeriodic() {
   }
}
