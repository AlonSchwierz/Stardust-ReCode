package frc.robot.subsystems.helicopter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.subsystems.UnitModel;

public class Helicopter extends SubsystemBase {

    private static Helicopter INSTANCE = null;

    private final WPI_TalonFX mainMotor = new WPI_TalonFX(Ports.Helicopter.MAIN);
    private final WPI_TalonFX auxMotor = new WPI_TalonFX(Ports.Helicopter.AUX);
    private final UnitModel unitModelPosition = new UnitModel(Constants.Helicopter.TICKS_PER_RAD);
    private final UnitModel unitModelPositionAbsolute = new UnitModel(Constants.Helicopter.TICKS_PER_RAD_ABSOLUTE_ENCODER);
    private final DutyCycleEncoder dutyCycleEncoder = new DutyCycleEncoder(Ports.Helicopter.ENCODER);


    private final DoubleLogEntry power;
    private final DoubleLogEntry voltage;

    private Helicopter() {
        mainMotor.setSensorPhase(Ports.Helicopter.SENSOR_PHASE);
        /*
         Set the right motor on Brake mode.
         */
        mainMotor.setNeutralMode(NeutralMode.Brake);
        auxMotor.setNeutralMode(NeutralMode.Brake);

        /*
         Setting the motor to go clockwise.
         */
        mainMotor.setInverted(Ports.Helicopter.IS_MAIN_INVERTED);

        /*
         config PID velocity for main motor.
         */
        mainMotor.configMotionCruiseVelocity(Constants.Helicopter.CRUISE_VELOCITY);
        mainMotor.configMotionAcceleration(Constants.Helicopter.MAXIMAL_ACCELERATION);
//        configPID();


        auxMotor.follow(mainMotor);

        mainMotor.enableVoltageCompensation(Constants.Helicopter.VOLTAGE_COMPENSATION);

        mainMotor.configVoltageCompSaturation(Constants.NOMINAL_VOLTAGE);

        auxMotor.enableVoltageCompensation(Constants.Helicopter.VOLTAGE_COMPENSATION);

        auxMotor.configVoltageCompSaturation(Constants.NOMINAL_VOLTAGE);

        /*
         Set the aux motor on Brake mode.
         */
        auxMotor.setNeutralMode(NeutralMode.Brake);

        /*
         Setting the motor to go clockwise.
         */
        auxMotor.setInverted(Ports.Helicopter.IS_AUX_INVERTED);

        DataLog log = DataLogManager.getLog();
        power = new DoubleLogEntry(log, "/helicopter/power");
        voltage = new DoubleLogEntry(log, "/helicopter/voltage");
    }

    public static Helicopter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Helicopter();
        }
        return INSTANCE;
    }

    public double getvelocity() {
        return unitModelPosition.toVelocity(mainMotor.getSelectedSensorVelocity());
    }

    public void setVelocity(double velocity) {
        mainMotor.set(ControlMode.Velocity, unitModelPosition.toTicks100ms(velocity));
    }

    public void vroomVroom(double power) {
        mainMotor.set(ControlMode.PercentOutput, power);
    }

    public double getAbsulutePosition() {
        return Math.IEEEremainder(unitModelPositionAbsolute.toUnits(dutyCycleEncoder.get() - Constants.Helicopter.ZERO_POSITION), 2 * Math.PI);
    }

    public void setAbsulutePosition(Rotation2d position) {
        var currentPosition = new Rotation2d(getAbsulutePosition());
        var error = position.minus(currentPosition);
        mainMotor.set(ControlMode.Position, unitModelPosition.toTicks(error.getRadians()) + mainMotor.getSelectedSensorPosition());
    }

    public Rotation2d getPosition() {
        return new Rotation2d(Math.IEEEremainder(unitModelPosition.toUnits(mainMotor.getSelectedSensorPosition(0)), Math.PI * 2));
    }

    public void setposition(Rotation2d position) {
        var currentPosition = getPosition();
        var error = position.minus(currentPosition);
        Rotation2d minMove = new Rotation2d(Math.IEEEremainder(unitModelPosition.toTicks(error.getRadians()), Math.PI * 2));
    }

    public void stop() {
        mainMotor.stopMotor();
    }

    @Override
    public void periodic() {
        power.append(mainMotor.get());
        voltage.append(mainMotor.getMotorOutputVoltage());
    }


}
