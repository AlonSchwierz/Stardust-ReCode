package frc.robot.subsystems.BTS;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.shooter.Shooter;

public class checkShooterVelocity extends ParallelCommandGroup {
    public checkShooterVelocity(Shooter shooter) {
        addCommands(
                new InstantCommand(() -> shooter.setVelocity(1000)),
                new InstantCommand(() -> shooter.getVelocity())
        );

    }
}
