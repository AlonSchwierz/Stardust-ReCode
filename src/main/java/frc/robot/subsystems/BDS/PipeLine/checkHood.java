package frc.robot.subsystems.BDS.PipeLine;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Hood.Hood;

public class checkHood extends SequentialCommandGroup {
    public checkHood(Hood hood) {
        addRequirements(hood);
        addCommands(
                new InstantCommand(() -> hood.bigAngle()),
                new WaitCommand(3),
                new InstantCommand(() -> hood.smallAngle()),
                new WaitCommand(3),
                new InstantCommand(() -> hood.bigAngle()),
                new WaitCommand(3),
                new InstantCommand(() -> hood.smallAngle()),
                new WaitCommand(3),
                new InstantCommand(() -> hood.bigAngle()),
                new WaitCommand(3),
                new InstantCommand(() -> hood.smallAngle())
        );

    }
}
