package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import limelightvision.limelight.frc.LimeLightSrc;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.PhotonCamera;

/** LimeLight sensing and function wrapper */
public class LimeLight extends SubsystemBase {
   private LimeLightSrc limeLight;
   private DriveTrain driveTrain;

   public static PhotonCamera limeCamera;
   
   /** Aligns robot to a limelight target */
   public void allignTargetLime() {
      if (limeLight.getIsTargetFound() && limeLight.getdegRotationToTarget() != 0) {
         boolean negative = false;
         negative = -limeLight.getdegRotationToTarget() < 0;
         double turn = 0.3 + (0.02592 * Math.abs(limeLight.getdegRotationToTarget()));
         if (negative)
            turn = -turn;
         driveTrain.arcadeDrive(0, turn);
         System.out.println("turn is " + turn);
      }
   }

   /** Aligns robot to an april tag */
   public boolean allignTargetTag() {
      PhotonPipelineResult result = getResult();
      PhotonTrackedTarget target = result.getBestTarget();
      if (result.hasTargets() && target.getSkew() != 0) {
         boolean negative = false;
         negative = -target.getSkew() < 0;
         double turn = 0.3 + (0.02592 * Math.abs(limeLight.getdegRotationToTarget()));
         if (negative) {
            turn = -turn;
         }
         driveTrain.arcadeDrive(0, turn);
      }
      if (result.hasTargets() && 1.0 > target.getSkew() && target.getSkew() < -1) {
         return true;
      }
      return false;
   }

   /** Aligns robot to a limelight target (Uses command API for ease of use) */
   public CommandBase c_AllignTargetLime(){
      return this.runOnce(() -> {
         allignTargetLime();
      });
   }

   public CommandBase c_AllignTargetTag(){
      return this.runOnce(() -> {
         allignTargetTag();
      });
   }

   public static PhotonPipelineResult getResult() {
      return limeCamera.getLatestResult();
   }
}
