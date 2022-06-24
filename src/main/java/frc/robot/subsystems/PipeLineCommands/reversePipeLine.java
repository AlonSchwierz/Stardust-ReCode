package frc.robot.subsystems.PipeLineCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.Flap.Flap;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

public class reversePipeLine extends CommandBase {
    @Override
    public void initialize() {
        Flap.ShallPass();
    }

    @Override
    public void execute() {
        Intake.setPower(-8);
        Conveyor.setPower(-8);
        Shooter.setPower(-8);
    }

    @Override
    public void end(boolean interrupted) {
        Intake.setPower(0);
        Conveyor.setPower(0);
        Shooter.setPower(0);

    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
