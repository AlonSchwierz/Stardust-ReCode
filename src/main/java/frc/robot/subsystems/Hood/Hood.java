package frc.robot.subsystems.Hood;

import edu.wpi.first.util.datalog.BooleanLogEntry;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.subsystems.Superstructure;

import java.util.function.Supplier;

public class Hood extends SubsystemBase {
    private Supplier<Superstructure.State.StateName> pipelineState;
    private static Hood INSTANCE;
    private final Solenoid angleChanger = new Solenoid(PneumaticsModuleType.CTREPCM, Ports.Hood.SOLENOID);
    private final BooleanLogEntry shortDistance;

    private void pipelineState (Supplier<Superstructure.State.StateName> pipelineState){
        this.pipelineState = pipelineState;
    }

    private Hood() {
        DataLog log = DataLogManager.getLog();
        shortDistance = new BooleanLogEntry(log, "/hood/isOpen");
    }

    public static Hood getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Hood();
        }
        return INSTANCE;
    }

    public void bigAngle() {
        angleChanger.set(true);
    }

    public void smallAngle() {
        angleChanger.set(false);
    }

    public void changeAngleForDistance() {
        if (Constants.Hood.DISTANCE_FROM_TARGET < Constants.Hood.DISTANCE_FOR_ANGLE) {
            smallAngle();
        } else {
            bigAngle();
        }

    }

    @Override
    public void periodic() {
        switch (pipelineState.get()) {

            case Idle:
                break;

            case WARMUP:
                changeAngleForDistance();
                break;
            case FEED_AND_CONVEY:
                break;
            case CONVEY_AND_SHOOT:
                changeAngleForDistance();
                break;
            case REVERSE_PIPELINE:
                break;
            default:
                throw new IllegalStateException("Unknown State " + Superstructure.State.StateName.Idle);
        }
    }
}
