package frc.robot.subsystems.BDS;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.intake.Intake;

public class openAndCloseRetractor extends SequentialCommandGroup {
    public openAndCloseRetractor(Intake intake) {
        addRequirements(intake);
        addCommands(
                new RunCommand(()-> intake.setPower(0.5)).withTimeout(2),
                new InstantCommand(()-> intake.openREEEtractor()),
                new WaitCommand(1),
                new InstantCommand(()-> intake.closeREEEtractor()),
                new WaitCommand(1),
                new InstantCommand(()-> intake.openREEEtractor()),
                new WaitCommand(1),
                new InstantCommand(()-> intake.closeREEEtractor()),
                new WaitCommand(1),
                new InstantCommand(()-> intake.openREEEtractor()),
                new WaitCommand(1),
                new InstantCommand(()-> intake.closeREEEtractor()),
                new InstantCommand(()-> intake.setPower(0))

                );

    }
}
