package frc.robot.subsystems.Conveyor;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.subsystems.UnitModel;

import java.util.ArrayDeque;
import java.util.Deque;

import static frc.robot.Ports.Conveyor.IS_COMPENSATING_VOLTAGE;
import static frc.robot.Ports.Conveyor.MOTOR_INVERSION;

public class Conveyor extends SubsystemBase {
    private static final WPI_TalonFX motor = new WPI_TalonFX(Ports.Conveyor.MOTOR);
    private static final BeamBreaker postFlapBeam = new BeamBreaker(Ports.Conveyor.postFlapBeam);
    private static final BeamBreaker preFlapBeam = new BeamBreaker(Ports.Conveyor.preFlapBeam);
    private static Conveyor INSTANCE = null;
    private final Deque<DriverStation.Alliance> cargoPositions = new ArrayDeque<>();
    private final UnitModel unitModel = new UnitModel(Constants.Conveyor.TICKS_PER_UNIT);

    private final ColorSensor colorSensor = new ColorSensor(I2C.Port.kMXP);

    private Conveyor() {
        motor.setInverted(MOTOR_INVERSION);
        motor.enableVoltageCompensation(IS_COMPENSATING_VOLTAGE);
        motor.configVoltageCompSaturation(Constants.NOMINAL_VOLTAGE);

    }

    public static Conveyor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Conveyor();
        }
        return INSTANCE;
    }

    public double getPower() {
        return motor.get();
    }

    public void setPower(double power) {
        motor.set(power);
    }

    public boolean IsCargoInFrontOfPre() {
        System.out.println(preFlapBeam.get());
        return preFlapBeam.get();
    }

    public boolean IsCargoInFrontOfPost() {
        System.out.println(postFlapBeam.get());
        return postFlapBeam.get();

    }

    public String getColor() {
        return String.valueOf(colorSensor.getColor());
    }


    @Override
    public void periodic() {
        postFlapBeam.updateBeamBreaker();
        preFlapBeam.updateBeamBreaker();
        colorSensor.updateColorSensor();
    }
}
