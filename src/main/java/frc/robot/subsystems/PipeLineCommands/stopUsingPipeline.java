package frc.robot.subsystems.PipeLineCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Ports;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.Flap.Flap;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

public class stopUsingPipeline extends CommandBase {
    @Override
    public void initialize() {
        Intake.closeREEEtractor();
        Intake.setPower(0);
        Conveyor.setPower(0);
        Shooter.setPower(0);
        Flap.ShallNotPass();
    }


}
