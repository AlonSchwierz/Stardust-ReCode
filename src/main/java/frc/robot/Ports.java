package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

public class Ports {
    public static class Helicopter {
        public static final int MAIN = 41;
        public static final int AUX = 42;
        public static final int ENCODER = 2;

        public static final boolean SENSOR_PHASE = true;

        public static final TalonFXInvertType IS_AUX_INVERTED = TalonFXInvertType.Clockwise;
        public static final TalonFXInvertType IS_MAIN_INVERTED = TalonFXInvertType.Clockwise;
    }

    public static class Intake {

        public static final int MOTOR = 0;

        public static final int Solenoid = 0;

        public static Boolean isMotorInverted = null;

    }
    public static class Conveyor{
        public  static  final int MOTOR = 0;

        public static final int preFlap = 0;

        public static final int postFlap = 0;

        public static final int preFlapBeam = 0;

        public static final int postFlapBeam = 0;

        public static final Boolean isMotorInverted = null;
    }
}
