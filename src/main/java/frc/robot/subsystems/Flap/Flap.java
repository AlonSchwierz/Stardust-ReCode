package frc.robot.subsystems.Flap;

import edu.wpi.first.util.datalog.BooleanLogEntry;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Superstructure;

import java.util.function.Supplier;

import static frc.robot.Ports.Flap.SOLENOID;

public class Flap extends SubsystemBase {
    private Supplier<Superstructure.State.StateName> pipelineState;
    private static Flap INSTANCE;
    private final Solenoid flap = new Solenoid(PneumaticsModuleType.CTREPCM, SOLENOID);
    private final BooleanLogEntry isStopping;

    private Flap() {
        DataLog log = DataLogManager.getLog();
        isStopping = new BooleanLogEntry(log, "/flap/isBlocking");
    }

    public static Flap getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Flap();
        }
        return INSTANCE;
    }
    private void pipelineState (Supplier<Superstructure.State.StateName> pipelineState){
        this.pipelineState = pipelineState;
    }

    public void ShallPass() {
        flap.set(true);
    }

    public void ShallNotPass() {
        flap.set(false);
    }

    public void ShallToggle() {
        flap.toggle();
    }

    @Override
    public void periodic() {
        switch (pipelineState.get()) {

            case Idle:
                break;

            case WARMUP:
                ShallNotPass();
                break;
            case FEED_AND_CONVEY:
                ShallNotPass();
                break;
            case CONVEY_AND_SHOOT:
                ShallPass();
                break;
            case REVERSE_PIPELINE:
                break;
            default:
                throw new IllegalStateException("Unknown State " + Superstructure.State.StateName.Idle);
        }
    }
}
