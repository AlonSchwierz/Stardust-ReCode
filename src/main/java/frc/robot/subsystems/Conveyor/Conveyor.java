package frc.robot.subsystems.Conveyor;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.util.datalog.StringLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;
import frc.robot.subsystems.UnitModel;
import com.revrobotics.ColorSensorV3;


import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

import static frc.robot.Ports.Conveyor.IS_COMPENSATING_VOLTAGE;
import static frc.robot.Ports.Conveyor.MOTOR_INVERSION;

public class Conveyor extends SubsystemBase {
    private static Conveyor INSTANCE = null;

    private static final WPI_TalonFX motor = new WPI_TalonFX(Ports.Conveyor.MOTOR);

    private final Deque<DriverStation.Alliance> cargoPositions = new ArrayDeque<>();

    private static final BeamBreaker postFlapBeam = new BeamBreaker(Ports.Conveyor.postFlapBeam);

    private static final BeamBreaker preFlapBeam = new BeamBreaker(Ports.Conveyor.preFlapBeam);

    private final UnitModel unitModel = new UnitModel(Constants.Conveyor.TICKS_PER_UNIT);

    private final ColorSensor colorSensor = new ColorSensor(I2C.Port.kMXP);

    public static Conveyor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Conveyor();
        }
        return INSTANCE;
    }

    private Conveyor() {
        motor.setInverted(MOTOR_INVERSION);
        motor.enableVoltageCompensation(IS_COMPENSATING_VOLTAGE);
        motor.configVoltageCompSaturation(Constants.NOMINAL_VOLTAGE);

    }

    public  void setPower(double power) {
        motor.set(power);
    }

    public double getPower() {
        return motor.get();
    }

    public boolean IsCargoInFrontOfPre() {
        System.out.println(preFlapBeam.get());
        return preFlapBeam.get();
    }

    public boolean IsCargoInFrontOfPost() {
        System.out.println(postFlapBeam.get());
        return postFlapBeam.get();

    }
    public String getColor(){
        return String.valueOf(colorSensor.getColor());
    }



    @Override
    public void periodic() {
        postFlapBeam.updateBeamBreaker();
        preFlapBeam.updateBeamBreaker();
        colorSensor.updateColorSensor();
    }
}
