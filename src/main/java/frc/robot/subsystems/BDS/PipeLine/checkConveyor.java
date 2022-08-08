package frc.robot.subsystems.BDS.PipeLine;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Ports;
import frc.robot.subsystems.Conveyor.Conveyor;

public class checkConveyor extends SequentialCommandGroup {
    public checkConveyor(Conveyor conveyor) {
        addRequirements(conveyor);
        addCommands(
                new InstantCommand(() -> conveyor.setPower(0.5)),
                new WaitCommand(3),
                new InstantCommand(() -> conveyor.setPower(-0.5)),
                new WaitCommand(3),
                new InstantCommand(() -> conveyor.setPower(0.5)),
                new WaitCommand(3),
                new InstantCommand(() -> conveyor.setPower(-0.5)),
                new WaitCommand(3),
                new InstantCommand(() -> conveyor.setPower(0.5)),
                new WaitCommand(3),
                new InstantCommand(() -> conveyor.setPower(-0.5)),
                new WaitCommand(3),
                new InstantCommand(() -> conveyor.setPower(0.5)),
                new WaitCommand(3),
                new InstantCommand(() -> conveyor.setPower(-0.5))
        );
    }

}
