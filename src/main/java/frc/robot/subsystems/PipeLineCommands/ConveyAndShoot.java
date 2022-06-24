package frc.robot.subsystems.PipeLineCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.Flap.Flap;
import frc.robot.subsystems.shooter.Shooter;

public class ConveyAndShoot extends CommandBase {
    @Override
    public void initialize() {
        Shooter.setPower(Shooter.returnSpeedForDistance());
        Flap.ShallPass();
    }

    @Override
    public void execute() {
        Conveyor.setPower(8);
        Shooter.setPower(Shooter.returnSpeedForDistance());
    }

    @Override
    public void end(boolean interrupted) {
        Flap.ShallNotPass();
        Conveyor.setPower(0);
        Shooter.setPower(0);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
