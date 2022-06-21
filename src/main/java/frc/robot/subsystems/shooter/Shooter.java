package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.UnitModel;
import frc.robot.utils.Utils;
import webapp.FireLog;

import static frc.robot.Constants.Shooter.*;
import static frc.robot.Ports.Shooter.*;


public class Shooter extends SubsystemBase {
    private static Shooter INSTANCE;
    private final UnitModel unitModel = new UnitModel(TICKS_PER_REVOLUTION);
    private final WPI_TalonFX mainMotor = new WPI_TalonFX(MAIN_MOTOR);
    private final WPI_TalonFX auxMotor = new WPI_TalonFX(AUX_MOTOR);

    public void setPower(double power) {
        mainMotor.set(power);
    }

    public double getPower() {
        return mainMotor.get();
    }

    public double returnSpeedForDistance(){
        return 68;
    }
}
