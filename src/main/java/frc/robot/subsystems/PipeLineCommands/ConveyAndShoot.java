package frc.robot.subsystems.PipeLineCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.Flap.Flap;
import frc.robot.subsystems.shooter.Shooter;

public class ConveyAndShoot extends CommandBase {
    private final Flap flap;

    public ConveyAndShoot() {
        flap = Flap.getInstance();
        addRequirements(flap, Conveyor.getInstance(), Shooter.getInstance());
    }

    @Override
    public void initialize() {
        Shooter.getInstance().setPower(Shooter.getInstance().returnSpeedForDistance());
        Flap.getInstance().ShallPass();
    }

    @Override
    public void execute() {
        Conveyor.getInstance().setPower(8);
        Shooter.getInstance().setPower(Shooter.getInstance().returnSpeedForDistance());
    }

    @Override
    public void end(boolean interrupted) {
        Flap.getInstance().ShallNotPass();
        Conveyor.getInstance().setPower(0);
        Shooter.getInstance().setPower(0);
    }
}
