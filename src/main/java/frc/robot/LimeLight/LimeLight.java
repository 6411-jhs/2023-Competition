package frc.robot.LimeLight;

// import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import java.util.ArrayList;

public class LimeLight extends SubsystemBase {
   NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
   NetworkTableEntry tx = table.getEntry("tx");
   NetworkTableEntry ty = table.getEntry("ty");
   NetworkTableEntry ta = table.getEntry("ta");
   DriveTrain drive;
   double x, y, area;

   public LimeLight(DriveTrain driveTrain){
      drive = driveTrain;
   }

   public void update(){
      x = tx.getDouble(0.0);
      y = ty.getDouble(0.0);
      area = ta.getDouble(0.0);
      SmartDashboard.putNumber("LimelightX", x);
      SmartDashboard.putNumber("LimelightY", y);
      SmartDashboard.putNumber("LimelightArea", area);
   }

   public CommandBase follow(){
      return runOnce(() -> {
         // System.out.println(getVerticalSpeed(0, 1,Constants.VERTICAL_TARGET));
         // drive.arcadeDrive(0, getHorizontalTurn(0.25, 0.7));
         
         double params[] = {getVerticalSpeed(0.35, 0.75,Constants.VERTICAL_TARGET), getHorizontalTurn(0.25, 0.7, Constants.HORIZONTAL_TARGET)};
         double vThresholdCalc[] = {
            Constants.VERTICAL_TARGET - (Constants.VERTICAL_THRESHOLD * Constants.THRESHOLD_PERCENT),
            Constants.VERTICAL_TARGET + (Constants.VERTICAL_THRESHOLD * Constants.THRESHOLD_PERCENT)
         };
         double hThresholdCalc[] = {
            Constants.HORIZONTAL_TARGET - (Constants.HORIZONTAL_THRESHOLD * Constants.THRESHOLD_PERCENT),
            Constants.HORIZONTAL_TARGET + (Constants.HORIZONTAL_THRESHOLD * Constants.THRESHOLD_PERCENT)
         };
         if (y <= 0 || (y > vThresholdCalc[0] && y < vThresholdCalc[1])) params[0] = 0;
         if (x > hThresholdCalc[0] && x < hThresholdCalc[1]) params[1] = 0;
         // System.out.println(params[0] + ", " + params[1]);
         drive.arcadeDrive(params[0], params[1]);
      });
   };
   private double getHorizontalTurn(double minTurn, double maxTurn, double target){
      double value = ((maxTurn - minTurn) / 27) * (x - target);
      if (value < 0) value -= minTurn;
      else if (value > 0) value += minTurn;
      return -value;
   }
   private double getVerticalSpeed(double minSpeed, double maxSpeed, double target){
      double value = ((maxSpeed - minSpeed) / 20.5) * (y - target);
      if (value < 0) value -= minSpeed;
      else if (value > 0) value += minSpeed;
      return -value;
   }

   @Override
   public void periodic(){
      update();
   }
}
