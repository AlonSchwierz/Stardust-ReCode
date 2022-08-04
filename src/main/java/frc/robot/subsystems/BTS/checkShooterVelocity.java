package frc.robot.subsystems.BTS;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.shooter.Shooter;

public class checkShooterVelocity extends SequentialCommandGroup {

    public checkShooterVelocity(Shooter shooter) {
        addRequirements(shooter);
        addCommands(
                new RunCommand(() -> shooter.setVelocity(1000)),
                new RunCommand(() -> shooter.getVelocity()),
                new WaitCommand(5),
                new InstantCommand(()-> shooter.setPower(0))
        );

    }
}
