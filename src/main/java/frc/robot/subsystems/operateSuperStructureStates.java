package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.Flap.Flap;
import frc.robot.subsystems.Hood.Hood;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

import java.util.function.Supplier;

public class operateSuperStructureStates extends CommandBase {
    private final Supplier<Superstructure.State.stateName> pipelineState;
    private final Intake intake = Intake.getInstance();
    private final Conveyor conveyor = Conveyor.getInstance();
    private final Shooter shooter = Shooter.getInstance();
    private final Flap flap = Flap.getInstance();
    private final Hood hood = Hood.getInstance();

    public operateSuperStructureStates(Supplier<Superstructure.State.stateName> pipelineState) {
        this.pipelineState = pipelineState;
        addRequirements(intake, conveyor, shooter, flap, hood);
    }



}
