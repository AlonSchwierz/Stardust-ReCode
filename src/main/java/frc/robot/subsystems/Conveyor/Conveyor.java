package frc.robot.subsystems.Conveyor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.subsystems.Superstructure;

import java.util.function.Supplier;

public class Conveyor extends SubsystemBase {
    private Supplier<Superstructure.State.StateName> pipelineState;
    private static Conveyor INSTANCE = null;
    private final WPI_TalonFX motorFromIntake;
    private final WPI_TalonFX motorToShooter;
    private final DigitalInput preFlapBeamBreaker;
    private final DigitalInput postFlapBeamBreaker;
    private final ColorSensorV3 colorSensor;
    private final ColorMatch colorMatch;
    private Color currentColorSensed = Constants.Conveyor.NONE;
    private Color lastColorSensed;

    private void pipelineState(Supplier<Superstructure.State.StateName> pipelineState) {
        this.pipelineState = pipelineState;
    }

    private Conveyor() {

        motorFromIntake = new WPI_TalonFX(Ports.Conveyor.MOTOR_FROM_INTAKE);
        motorFromIntake.setInverted(TalonFXInvertType.CounterClockwise);
        motorFromIntake.setNeutralMode(NeutralMode.Brake);

        motorToShooter = new WPI_TalonFX(Ports.Conveyor.MOTOR_TO_SHOOTER);
        motorToShooter.setInverted(TalonFXInvertType.CounterClockwise);
        motorToShooter.setNeutralMode(NeutralMode.Brake);

        preFlapBeamBreaker = new DigitalInput(Ports.Conveyor.PRE_FLAP_BEAM);
        postFlapBeamBreaker = new DigitalInput(Ports.Conveyor.POST_FLAP_BEAM);
        colorSensor = new ColorSensorV3(Ports.Conveyor.COLOR_SENSOR);
        colorMatch = new ColorMatch();

        colorMatch.addColorMatch(Constants.Conveyor.BLUE);
        colorMatch.addColorMatch(Constants.Conveyor.RED);
        colorMatch.addColorMatch(Constants.Conveyor.NONE);
    }

    public static Conveyor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Conveyor();
        }
        return INSTANCE;
    }

    public boolean preFlapBeamSeesObject() {
        return preFlapBeamBreaker.get();
    }

    public boolean postFlapBeamSeesObject() {
        return postFlapBeamBreaker.get();
    }

    public boolean newObjectSensed() {
        return !lastColorSensed.equals(currentColorSensed) && lastColorSensed.equals(Constants.Conveyor.NONE);
    }

    public boolean oldObjectExited() {
        return !lastColorSensed.equals(currentColorSensed) && !lastColorSensed.equals(Constants.Conveyor.NONE);
    }

    public Color getColor() {
        if (colorSensor.getProximity() < 100) {
            return Constants.Conveyor.NONE;
        }
        ColorMatchResult matchResult = colorMatch.matchColor(colorSensor.getColor());
        return matchResult.color;
    }

    public Color getLastColor() {
        return lastColorSensed;
    }

    public Color allianceToColor(DriverStation.Alliance alliance) {
        if (alliance.equals(DriverStation.Alliance.Blue)) {
            return Constants.Conveyor.BLUE;
        } else if (alliance.equals(DriverStation.Alliance.Red)) {
            return Constants.Conveyor.RED;
        }
        return Constants.Conveyor.NONE;
    }

    public void feedFromIntake(double power) {
        motorFromIntake.set(ControlMode.PercentOutput, power);
    }

    public void feedToShooter(double power) {
        motorToShooter.set(ControlMode.PercentOutput, power);
    }

    public MotorsState getPower() {
        return new MotorsState(motorFromIntake.get(), motorToShooter.get());
    }


    @Override
    public void periodic() {
        lastColorSensed = currentColorSensed;
        currentColorSensed = getColor();
        switch (pipelineState.get()) {

            case Idle:
            case WARMUP:
                feedToShooter(0);
                feedToShooter(0);
                break;
            case FEED_AND_CONVEY:
                feedFromIntake(0.5);
                feedToShooter(0);
                break;
            case CONVEY_AND_SHOOT:
                feedFromIntake(0);
                feedToShooter(0.5);
                break;
            case REVERSE_PIPELINE:
                feedToShooter(-0.5);
                feedFromIntake(-0.5);
                break;
            default:
                throw new IllegalStateException("Unknown State " + Superstructure.State.StateName.Idle);
        }
    }

    public static class MotorsState {
        public double motorFromIntakePower;
        public double motorToShooterPower;

        public MotorsState(double motorFromIntakePower, double motorToShooterPower) {
            this.motorFromIntakePower = motorFromIntakePower;
            this.motorToShooterPower = motorToShooterPower;
        }

        public double[] toArray() {
            return new double[]{motorFromIntakePower, motorToShooterPower};
        }
    }
}