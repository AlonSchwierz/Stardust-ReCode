package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.subsystems.Superstructure;

import java.util.function.Supplier;

import static frc.robot.subsystems.Superstructure.State.StateName.Idle;

public class Intake extends SubsystemBase {
    private Supplier<Superstructure.State.StateName> pipelineState;
    private static final WPI_TalonFX motor = new WPI_TalonFX(Ports.Intake.MOTOR);
    private static final Solenoid retractor = new Solenoid(PneumaticsModuleType.CTREPCM, Ports.Intake.Solenoid);
    private static Intake INSTANCE;

    private void pipelineState (Supplier<Superstructure.State.StateName> pipelineState){
        this.pipelineState = pipelineState;
    }

    private Intake() {
        motor.setInverted(Ports.Intake.isMotorInverted);

        motor.enableVoltageCompensation(true);

        motor.configVoltageCompSaturation(Constants.NOMINAL_VOLTAGE, Constants.TALON_TIMEOUT);

        motor.setNeutralMode(NeutralMode.Brake);

        motor.configClosedloopRamp(0);

    }

    public static Intake getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Intake();
        }
        return INSTANCE;
    }

    public double getPower() {
        return motor.get();
    }

    public void setPower(double power) {
        motor.set(power);
    }

    public void openREEEtractor() {
        retractor.set(true);
    }

    public void closeREEEtractor() {
        retractor.set(false);
    }

    @Override
    public void periodic() {
        switch (pipelineState.get()) {
            case Idle: case WARMUP: case CONVEY_AND_SHOOT:
                setPower(0);
                closeREEEtractor();
                break;
            case FEED_AND_CONVEY:
                openREEEtractor();
                setPower(0.5);
                break;
            case REVERSE_PIPELINE:
                openREEEtractor();
                setPower(-0.5);
                break;
            default:
                throw new IllegalStateException("Unknown State " + Idle);
        }
    }
}
