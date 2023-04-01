package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.DriveTrain;

import frc.robot.commands.BalanceChargingStation;
import frc.robot.commands.MountChargingStation;

public class ActivateChargingStation extends CommandBase {
    BalanceChargingStation balance;
    MountChargingStation mount;
    DriveTrain driveTrain;

    boolean balanced;
    boolean mounted;
    int stageLog = 0;

    public ActivateChargingStation(DriveTrain drive){
        balance = new BalanceChargingStation(drive);
        mount = new MountChargingStation(drive);
        driveTrain = drive;
    }

    @Override
    public void execute(){
        if (!mounted){
            if (stageLog == 0) {
                System.out.println("Mount");
                stageLog++;
            }
            mount.execute();
            mounted = mount.finished;
        } else {
            if (!balanced){
                if (stageLog == 1) {
                    System.out.println("Balance");
                    stageLog++;
                }
                balance.execute();
                balanced = balance.finished;
            } else {
                if (stageLog == 2) {
                    System.out.println("Charging Station Activated!");
                    stageLog++;
                }
            }
        }
    }

    @Override
    public boolean isFinished(){
        return mounted && balanced;
    }

    @Override
    public void end(boolean interrupted){
        driveTrain.arcadeDrive(0, 0);
    }
}
