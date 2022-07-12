package frc.robot.subsystems.Hood;

import edu.wpi.first.util.datalog.BooleanLogEntry;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Ports;

public class Hood extends SubsystemBase {
    private static Hood INSTANCE;
    private final Solenoid angleChanger = new Solenoid(PneumaticsModuleType.CTREPCM, Ports.Hood.SOLENOID);
    private final BooleanLogEntry shortDistance;

    public static Hood getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Hood();
        }
        return INSTANCE;
    }

    private Hood() {
        DataLog log = DataLogManager.getLog();
        shortDistance = new BooleanLogEntry(log, "/hood/isOpen");
    }

    public void shallPass(){
        angleChanger.set(true);
    }

    public void shallNotPass(){
        angleChanger.set(false);
    }
}
