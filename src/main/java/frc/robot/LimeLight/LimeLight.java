package frc.robot.LimeLight;

// import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
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
         // System.out.println(getHorizontalTurn(0.4, 0.75));
         drive.arcadeDrive(0, getHorizontalTurn(0.25, 0.7));
      });
   };
   private double getHorizontalTurn(double minTurn, double maxTurn){
      double value = ((maxTurn - minTurn) / 27) * x;
      if (value < 0) value -= minTurn;
      else if (value > 0) value += minTurn;
      return -value;
   }

   @Override
   public void periodic(){
      update();
   }
}
