package frc.robot.subsystems.BTS;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.intake.Intake;

public class openAndCloseRetractor extends SequentialCommandGroup {
public openAndCloseRetractor(Intake intake){
    addCommands(
            new InstantCommand(() -> intake.closeREEEtractor()),
            new WaitCommand(2),
            new InstantCommand(() -> intake.openREEEtractor()),
            new WaitCommand(2),
            new InstantCommand(() -> intake.setPower(0.5)));

}
}
