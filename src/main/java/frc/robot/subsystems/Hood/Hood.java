package frc.robot.subsystems.Hood;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.subsystems.Superstructure;
import frc.robot.subsystems.UnitModel;
import frc.robot.utils.Utils;

import java.util.function.Supplier;

public class Hood extends SubsystemBase {
    private Supplier<Superstructure.State.StateName> pipelineState;
    private static Hood INSTANCE = null;
    private final WPI_TalonFX motor;
    private final DutyCycleEncoder encoder;

    private final UnitModel unitModelPosition = new UnitModel(Constants.Hood.TICKS_PER_DEGREE);
    private final UnitModel unitModelPositionAbsolute = new UnitModel(Constants.Hood.TICKS_PER_RAD_ABSOLUTE_ENCODER);
    private double setpoint;

    private void pipelineState(Supplier<Superstructure.State.StateName> pipelineState) {
        this.pipelineState = pipelineState;
    }

    private Hood() {
        motor = new WPI_TalonFX(Ports.Hood.MOTOR);
        motor.setNeutralMode(NeutralMode.Brake);

        encoder = new DutyCycleEncoder(8);
        motor.setInverted(TalonFXInvertType.Clockwise);
        motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, Constants.TALON_TIMEOUT);
        motor.setSelectedSensorPosition(encoder.get() * 2048 - Constants.Hood.ZERO_POSITION);

//        configSoftLimits(true);

        motor.configMotionCruiseVelocity(unitModelPosition.toTicks100ms(Constants.Hood.MAX_VELOCITY));
        motor.configMotionAcceleration(unitModelPosition.toTicks100ms(Constants.Hood.MAX_ACCELERATION));
    }

    public static Hood getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Hood();
        }
        return INSTANCE;
    }

    public double getAngle() {
        return Math.IEEEremainder(unitModelPosition.toUnits(motor.getSelectedSensorPosition()), 360.0);
    }

    public void setAngle(double angle) {
        motor.set(ControlMode.MotionMagic, unitModelPosition.toTicks(angle));
        setpoint = angle;
    }

    public void setPower(double output) {
        motor.set(ControlMode.PercentOutput, output);
    }

    public double getVelocity() {
        return unitModelPosition.toVelocity(motor.getSelectedSensorVelocity());
    }

    public void stop() {
        motor.stopMotor();
    }

    public boolean atSetpoint(double tolerance) {
        return Utils.deadband(getAngle() - setpoint, tolerance) == 0;
    }


    public void configSoftLimits(boolean enable) {
        motor.configReverseSoftLimitEnable(enable);
        motor.configReverseSoftLimitThreshold(Constants.Hood.BOTTOM_SOFT_LIMIT);
        motor.configForwardSoftLimitEnable(enable);
        motor.configForwardSoftLimitThreshold(Constants.Hood.TOP_SOFT_LIMIT);
    }

    @Override
    public void periodic() {
        //   updatePID();
        switch (pipelineState.get()) {


            case Idle:
            case WARMUP:
            case FEED_AND_CONVEY:
            case CONVEY_AND_SHOOT:
            case REVERSE_PIPELINE:
                setAngle(8);
                break;

            default:
                throw new IllegalStateException("Unknown State " + Superstructure.State.StateName.Idle);
        }
    }
}
