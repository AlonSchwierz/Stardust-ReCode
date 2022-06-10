package frc.robot;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

public class Ports {
    public static class Helicopter {
        public static final int MAIN = 41;
        public static final int AUX = 42;
        public static final int STOPPER = 6;
        public static final int ENCODER = 2;

        public static final boolean SENSOR_PHASE = true;

        public static final TalonFXInvertType IS_AUX_INVERTED = TalonFXInvertType.Clockwise;
        public static final TalonFXInvertType IS_MAIN_INVERTED = TalonFXInvertType.Clockwise;
    }
}
