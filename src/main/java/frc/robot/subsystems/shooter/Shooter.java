package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.UnitModel;
import frc.robot.utils.Utils;
import webapp.FireLog;

import static frc.robot.Constants.Shooter.*;
import static frc.robot.Ports.Shooter.*;


public class Shooter extends SubsystemBase {
    private static Shooter INSTANCE;
    private final UnitModel unitModel = new UnitModel(TICKS_PER_REVOLUTION);
    private static final WPI_TalonFX mainMotor = new WPI_TalonFX(MAIN_MOTOR);
    private final WPI_TalonFX auxMotor = new WPI_TalonFX(AUX_MOTOR);

    public static void setPower(double power) {
        mainMotor.set(power);
    }

    public static double getPower() {
        return mainMotor.get();
    }

    public static double returnSpeedForDistance(){
        double SpeedForDistance = Constants.SHOOTER.SpeedForDistance;
        return SpeedForDistance;
    }
}
