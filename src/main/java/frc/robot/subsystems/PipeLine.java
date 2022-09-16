package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.Flap.Flap;
import frc.robot.subsystems.Hood.Hood;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

import java.util.Stack;
import java.util.function.Supplier;

import static javax.print.attribute.standard.PrinterState.IDLE;

public class PipeLine extends CommandBase {
    private final Supplier<Superstructure.State.StateName> pipelineState;
    private final Intake intake = Intake.getInstance();
    private final Conveyor conveyor = Conveyor.getInstance();
    private final Shooter shooter = Shooter.getInstance();
    private final Flap flap = Flap.getInstance();
    private final Hood hood = Hood.getInstance();
    private final Superstructure.State.StateName state = Superstructure.State.StateName.Idle;

    public PipeLine(Supplier<Superstructure.State.StateName> pipelineState) {
        this.pipelineState = pipelineState;
        addRequirements(flap, conveyor, shooter, intake, hood);
    }

    @Override
    public void execute() {
        switch (pipelineState.get()) {

            case Idle:
                intake.setPower(0);
                conveyor.setPower(0);
                shooter.setPower(0);
                break;

            case WARMUP:
                intake.setPower(0);
                conveyor.setPower(0);
                flap.ShallNotPass();
                hood.changeAngleForDistance();
                shooter.setVelocity(shooter.returnSpeedForDistance());
                break;
            case FEED_AND_CONVEY:
                shooter.setPower(0);
                intake.openREEEtractor();
                flap.ShallNotPass();
                intake.setPower(0.5);
                conveyor.setPower(0.5);
                break;
            case CONVEY_AND_SHOOT:
                intake.setPower(0);
                flap.ShallPass();
                hood.changeAngleForDistance();
                conveyor.setPower(0.5);
                shooter.setVelocity(shooter.returnSpeedForDistance());
                break;
            case REVERSE_PIPELINE:
                conveyor.setPower(-0.5);
                intake.setPower(-0.5);
                shooter.setPower(0);
                break;
            default:
                throw new IllegalStateException("Unknown State " + Superstructure.State.StateName.Idle);
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.setPower(0);
        conveyor.setPower(0);
        flap.ShallNotPass();
        shooter.setPower(0);
    }


}





