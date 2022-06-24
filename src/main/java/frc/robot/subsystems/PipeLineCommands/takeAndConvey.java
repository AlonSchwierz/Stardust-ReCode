package frc.robot.subsystems.PipeLineCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.Flap.Flap;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

public class takeAndConvey extends CommandBase {
    @Override
    public void initialize() {
        Intake.openREEEtractor();
        Flap.ShallNotPass();
    }

    @Override
    public void execute() {
        Intake.setPower(8);
        Conveyor.setPower(8);


    }

    @Override
    public void end(boolean interrupted) {
        Flap.ShallPass();
        Intake.setPower(0);
        Conveyor.setPower(0);

    }


}
