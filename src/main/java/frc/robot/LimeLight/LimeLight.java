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
   NetworkTableEntry tv = table.getEntry("tv");
   DriveTrain drive;
   double x, y, area;
   double posData[] = {0,0,0,0};
   boolean lightDetected;

   public LimeLight(DriveTrain driveTrain){
      drive = driveTrain;
   }

   public void update(){
      x = tx.getDouble(0.0);
      y = ty.getDouble(0.0);
      area = ta.getDouble(0.0);
      lightDetected = tv.getBoolean(false);
      SmartDashboard.putNumber("LimelightX", x);
      SmartDashboard.putNumber("LimelightY", y);
      SmartDashboard.putNumber("LimelightArea", area);
      SmartDashboard.putBoolean("LimeLightDeteced",lightDetected);

      if (lightDetected){
         posData[0] = posData[2];
         posData[1] = posData[3];
         posData[2] = x;
         posData[3] = y;
      }
   }

   public CommandBase roughAlign(){
      return runOnce(() -> {
         follow();
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
   private String getLightDirection(){
      double dist[] = {0.7592 * x, y};
      boolean neg = dist[0] < 0;
      if (Math.abs(dist[0]) > dist[1]){
         if (neg){
            return "Left";
         } else {
            return "Right";
         }
      } else return "Up";
   }
   public CommandBase follow(){
      return runOnce(() -> {
         double params[] = {getVerticalSpeed(0.35, 0.75,Constants.VERTICAL_TARGET), getHorizontalTurn(0.25, 0.7, Constants.HORIZONTAL_TARGET)};
         double vThresholdCalc[] = {
            Constants.VERTICAL_TARGET - (Constants.VERTICAL_THRESHOLD * Constants.THRESHOLD_PERCENT),
            Constants.VERTICAL_TARGET + (Constants.VERTICAL_THRESHOLD * Constants.THRESHOLD_PERCENT)
         };
         double hThresholdCalc[] = {
            Constants.HORIZONTAL_TARGET - (Constants.HORIZONTAL_THRESHOLD * Constants.THRESHOLD_PERCENT),
            Constants.HORIZONTAL_TARGET + (Constants.HORIZONTAL_THRESHOLD * Constants.THRESHOLD_PERCENT)
         };
         double areaThresholdCalc[] = {
            Constants.AREA_TARGET - (Constants.AREA_THRESHOLD * Constants.THRESHOLD_PERCENT),
            Constants.AREA_TARGET + (Constants.AREA_THRESHOLD * Constants.THRESHOLD_PERCENT)
         };
         if (area < areaThresholdCalc[0] || area > areaThresholdCalc[1]){
            params[0] = -area;
         } else {
            // if (y <= 0 || (y > vThresholdCalc[0] && y < vThresholdCalc[1])) params[0] = 0;
         }
         if (x > hThresholdCalc[0] && x < hThresholdCalc[1]) params[1] = 0;
         drive.arcadeDrive(params[0], params[1]);
      });
   }
   public CommandBase search(){
      return runOnce(() -> {
         if (!lightDetected){
            String lastKnownDirection = getLightDirection();
            switch (lastKnownDirection){
               case "Left":

                  break;
               case "Right":
                  break;
               case "Up":
                  break;
            }
         }
      });
   }

   @Override
   public void periodic(){
      update();
   }
}
