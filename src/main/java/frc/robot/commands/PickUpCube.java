//** This command an only work if the intake interfaces with CAN. Meaning it only works with the IntakeCAN subsystem; not the PWM subsystem.
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.IntakeCAN;

public class PickUpCube extends CommandBase {
   private IntakeCAN intake;

   private boolean finished = false;

   public PickUpCube(IntakeCAN intakeSubsystem){
      intake = intakeSubsystem;
   }

   @Override
   public void execute(){
      if (intake.isStalling()){
         intake.off();
         finished = true;
      } else {
         intake.on();
         finished = false;
      }
   }

   @Override
   public boolean isFinished(){
      return finished;
   }
}
