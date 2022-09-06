package frc.robot.subsystems.Flap;

import edu.wpi.first.util.datalog.BooleanLogEntry;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Ports.Flap.SOLENOID;

public class Flap extends SubsystemBase {
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

    public void ShallPass() {
        flap.set(true);
    }

    public void ShallNotPass() {
        flap.set(false);
    }

    public void ShallToggle() {
        flap.toggle();
    }
}
