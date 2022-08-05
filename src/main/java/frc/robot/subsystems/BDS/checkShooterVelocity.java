package frc.robot.subsystems.BDS;

import edu.wpi.first.wpilibj2.command.*;
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
