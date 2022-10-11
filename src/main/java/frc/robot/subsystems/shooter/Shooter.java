package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.subsystems.Superstructure;
import frc.robot.subsystems.UnitModel;

import java.util.function.Supplier;

import static frc.robot.Constants.SHOOTER.TICKS_PER_REVOLUTION;
import static frc.robot.Constants.SHOOTER.getConfiguration;
import static frc.robot.Ports.Shooter.AUX_MOTOR;
import static frc.robot.Ports.Shooter.MAIN_MOTOR;


public class Shooter extends SubsystemBase {
    private Supplier<Superstructure.State.StateName> pipelineState;
    private static Shooter INSTANCE;
    private final UnitModel unitModel = new UnitModel(TICKS_PER_REVOLUTION);
    private final WPI_TalonFX mainMotor = new WPI_TalonFX(MAIN_MOTOR);
    private final WPI_TalonFX auxMotor = new WPI_TalonFX(AUX_MOTOR);

    private Shooter() {
        configureMotor();
    }

    public static Shooter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Shooter();
        }

        return INSTANCE;
    }
    private void pipelineState (Supplier<Superstructure.State.StateName> pipelineState){
        this.pipelineState = pipelineState;
    }

    public double getPower() {
        return mainMotor.get();
    }

    public void setPower(double power) {
        mainMotor.set(power);
    }

    public double getVelocity() {
        System.out.println(unitModel.toVelocity(mainMotor.getSelectedSensorVelocity()));
        return unitModel.toVelocity(mainMotor.getSelectedSensorVelocity());
    }

    public void setVelocity(double velocity) {
        mainMotor.set(ControlMode.Velocity, unitModel.toTicks100ms(velocity) / 60.0);
    }

    public double returnSpeedForDistance() {
        double SpeedForDistance = Constants.SHOOTER.SpeedForDistance;
        return SpeedForDistance;
    }

    public double returnSpeedForEnemysBalls() {
        return 88;
    }

    private void configureMotor() {
        mainMotor.configFactoryDefault();
        mainMotor.configAllSettings(getConfiguration());
        mainMotor.setInverted(Ports.Shooter.INVERSION_TYPE);
        mainMotor.setNeutralMode(NeutralMode.Coast);

        var currentLimit = new SupplyCurrentLimitConfiguration(true, 45, 5, 0.1);
        mainMotor.configSupplyCurrentLimit(currentLimit);
        mainMotor.configVoltageCompSaturation(Constants.NOMINAL_VOLTAGE);
        mainMotor.enableVoltageCompensation(true);
        mainMotor.config_IntegralZone(0, 0);

        auxMotor.configFactoryDefault();
        auxMotor.follow(mainMotor);
        auxMotor.configAllSettings(getConfiguration());
        auxMotor.configSupplyCurrentLimit(currentLimit);
        auxMotor.setNeutralMode(NeutralMode.Coast);
        auxMotor.setInverted(TalonFXInvertType.Clockwise);
        auxMotor.configVoltageCompSaturation(Constants.NOMINAL_VOLTAGE);
        auxMotor.enableVoltageCompensation(true);
        auxMotor.config_IntegralZone(0, 0);
    }

    @Override
    public void periodic() {

        mainMotor.config_kP(0, Constants.SHOOTER.kP);
        mainMotor.config_kI(0, Constants.SHOOTER.kI);
        mainMotor.config_kD(0, Constants.SHOOTER.kD);
        mainMotor.config_kF(0, Constants.SHOOTER.kF);

        switch (pipelineState.get()) {

            case Idle:
                setPower(0);
                break;

            case WARMUP:
                setVelocity(returnSpeedForDistance());
                break;

            case FEED_AND_CONVEY:
                setPower(0);
                break;

            case CONVEY_AND_SHOOT:
                setVelocity(returnSpeedForDistance());
                break;

            case REVERSE_PIPELINE:
                setPower(0);
                break;

            default:
                throw new IllegalStateException("Unknown State " + Superstructure.State.StateName.Idle);
        }
    }
}
