package frc.robot.subsystems.BTS;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Ports;
import frc.robot.subsystems.Flap.Flap;

public class openAndCloseFlap extends SequentialCommandGroup {
    public openAndCloseFlap(Flap flap) {
        addCommands(
                new InstantCommand(() -> flap.ShallPass()),
                new WaitCommand(3),
                new InstantCommand(() -> flap.ShallNotPass()),
                new WaitCommand(3),
                new InstantCommand(() -> flap.ShallPass()),
                new WaitCommand(3),
                new InstantCommand(() -> flap.ShallNotPass()),
                new WaitCommand(3),
                new InstantCommand(() -> flap.ShallPass()),
                new WaitCommand(3),
                new InstantCommand(() -> flap.ShallNotPass())
        );

    }
}
