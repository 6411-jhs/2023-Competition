package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveTrain;

public class CustomControl {
   private final XboxController controller = new XboxController(Constants.XBOX_USB_NUM);
   private final DriveTrain drive = new DriveTrain();

   public void init(){
      drive.arcadeDrive(controller.getRightTriggerAxis() * Constants.DRIVE_TRAIN_RATIO, controller.getLeftX());
   }
}
