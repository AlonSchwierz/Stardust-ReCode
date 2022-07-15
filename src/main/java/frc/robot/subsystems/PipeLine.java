package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.Flap.Flap;
import frc.robot.subsystems.Hood.Hood;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

import java.util.function.Supplier;

public class PipeLine extends CommandBase {
    private final Supplier<Cases> pipelineState;
    private final Intake intake = Intake.getInstance();
    private final Conveyor conveyor = Conveyor.getInstance();
    private final Shooter shooter = Shooter.getInstance();
    private final Flap flap = Flap.getInstance();
    private final Hood hood = Hood.getInstance();

    private final Cases state = Cases.Idle;

    public PipeLine(Supplier<Cases> pipelineState) {
        this.pipelineState = pipelineState;
        addRequirements(flap, conveyor, shooter, intake, hood);
    }

    @Override
    public void execute() {
        switch (pipelineState.get()) {

            case Idle:
                intake.closeREEEtractor();
                intake.setPower(0);
                conveyor.setPower(0);
                flap.ShallNotPass();
                shooter.setPower(0);
                hood.changeAngleForDistance();
                break;

            case feed:
                intake.openREEEtractor();
                intake.setPower(8);
                break;

            case shoot:
                flap.ShallPass();
                shooter.setVelocity(shooter.returnSpeedForDistance());
                break;

            case convey:
                flap.ShallPass();
                conveyor.setPower(8);
                break;

            case warmup:
                shooter.setVelocity(shooter.returnSpeedForDistance());
                break;
            case feedAndConvey:
                intake.openREEEtractor();
                intake.setPower(8);
                conveyor.setPower(8);
                break;

            case conveyAndShoot:
                flap.ShallPass();
                conveyor.setPower(8);
                shooter.setPower(8);
                hood.changeAngleForDistance();
                break;
            case reversePipeline:
                conveyor.setPower(-8);
                intake.setPower(-8);
            default:
                throw new IllegalStateException("Unknown State " + state.name());
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.setPower(0);
        conveyor.setPower(0);
        flap.ShallNotPass();
        shooter.setPower(0);
    }

    public enum Cases {
        feed, convey, shoot, feedAndConvey, conveyAndShoot, Idle, reversePipeline, warmup

    }
}





