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
        Intake.getInstance().closeREEEtractor();
        Intake.getInstance().setPower(0);
        Conveyor.getInstance().setPower(0);
        Shooter.getInstance().setPower(0);
        Flap.getInstance().ShallNotPass();
    }


}
