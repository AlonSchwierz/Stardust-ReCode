package frc.robot.subsystems.BDS.PipeLine;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.shooter.Shooter;

public class checkShooterVelocity extends ParallelCommandGroup {

    public checkShooterVelocity(Shooter shooter) {
        addRequirements(shooter);
        addCommands(
                new RunCommand(() -> shooter.setVelocity(3500)).withTimeout(10),
                new RunCommand(() -> shooter.getVelocity()).withTimeout(10)
        );

    }
}
