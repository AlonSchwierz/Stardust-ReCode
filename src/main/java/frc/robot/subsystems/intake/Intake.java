package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.util.datalog.BooleanLogEntry;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Ports;

public class Intake extends SubsystemBase {

    private static Intake INSTANCE;

    private final WPI_TalonFX motor = new WPI_TalonFX(Ports.Intake.MOTOR);

//    private final Solenoid retractor = new Solenoid(PneumaticsModuleType.CTREPCM, Ports.Intake.SOLENOID);

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

    public void openREEEtractor(){
//        retractor.set(true);
    }

    public void closeREEEtractor(){
//        retractor.set(false);
    }

}
