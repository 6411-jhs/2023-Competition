package frc.robot.LimeLight;

// import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
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
   double x, y, area;
   double avrg[] = {50.0,0.0,-50.0};
   ArrayList<Double> data = new ArrayList<Double>();

   public LimeLight(){}

   public void update(){
      double sum = 0;
      x = tx.getDouble(0.0);
      y = ty.getDouble(0.0);
      area = ta.getDouble(0.0);
      SmartDashboard.putNumber("LimelightX", x);
      SmartDashboard.putNumber("LimelightY", y);
      SmartDashboard.putNumber("LimelightArea", area);
      data.add(x);
      if (x < avrg[0]) avrg[0] = x;
      if (y > avrg[2]) avrg[2] = y;
      for (int i = 0; i < data.size(); i++){
         sum += data.get(i);
      }
      sum = sum / data.size();
      System.out.println(avrg[0] + ", " + avrg[1] + ", " + avrg[2]);
   }

   private CommandBase follow(){
      return runOnce(() -> {

      });
   };

   @Override
   public void periodic(){
      update();
   }
}
