package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

public class Ports {
    public static class Controls{
public static final int XBOX = 0;
    }

    public static class Hood {
        public static final int SOLENOID = 0;
    }

    public static class Helicopter {
        public static final int MAIN = 41;
        public static final int AUX = 42;
        public static final int ENCODER = 2;

        public static final boolean SENSOR_PHASE = true;

        public static final TalonFXInvertType IS_AUX_INVERTED = TalonFXInvertType.Clockwise;
        public static final TalonFXInvertType IS_MAIN_INVERTED = TalonFXInvertType.Clockwise;
    }

    public static class Intake {

        public static final int MOTOR = 11;

        public static final int Solenoid = 1;

        public static final boolean isMotorInverted = true;

    }
    public static class Conveyor{
        public  static  final int MOTOR = 21;



        public static final int preFlapBeam = 7;

        public static final int postFlapBeam = 6;


        public static final TalonFXInvertType MOTOR_INVERSION = TalonFXInvertType.Clockwise;

        public static final boolean IS_COMPENSATING_VOLTAGE = true;


    }
    public static class Shooter{
        public static final int MAIN_MOTOR = 31;
        public static final int AUX_MOTOR = 32;
        public static final TalonFXInvertType INVERSION_TYPE = TalonFXInvertType.CounterClockwise;


    }

    public static class Flap{
        public static final int SOLENOID = 7;
    }
}
