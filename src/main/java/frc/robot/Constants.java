// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import edu.wpi.first.wpilibj.util.Color;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int TALON_TIMEOUT = 10; // Waiting period for configurations [ms].

    public static final double LOOP_PERIOD = 0.02; // [s]
    public static final double NOMINAL_VOLTAGE = 12; // [volts]
    public static final double FIELD_WIDTH = 8.23; // Width of the field. [m]
    public static final double FIELD_LENGTH = 16.46; // Length of the field. [m]
    public static final double SIMULATION_LOOP_PERIOD = 0.02; // [s]

    public static final boolean ENABLE_VOLTAGE_COMPENSATION = true;
    public static final boolean ENABLE_CURRENT_LIMIT = true;


    public static class Helicopter {
        public static final double KP = 0.068;
        public static final double KI = 0;
        public static final double KD = 0;

        public static final double CRUISE_VELOCITY = 0; // [ticks/100ms]
        public static final double MAXIMAL_ACCELERATION = 0; // [ticks/100ms*sec]

        public static final double MAX_VELOCITY = Math.PI * 2 / 3; // [rad/s]

        public static final double GEAR_RATIO = 292.1;

        public static final double TICKS_PER_RAD = 2048 * GEAR_RATIO / (2 * Math.PI);
        public static final double TICKS_PER_RAD_ABSOLUTE_ENCODER = 1 / (2 * Math.PI);

        public static final double JOYSTICK_DEADBAND = 0.15; // [%]

        public static final double ARM_ENCODER_DIST_PER_PULSE = 2.0 * Math.PI / 2048;
        public static final double ARM_MASS = 5.0; // Kilograms
        public static final double ARM_LENGTH = 0.792; // [m]

        public static final double MAX_ANGLE = Math.toRadians(255); // [radians]
        public static final double MIN_ANGLE = Math.toRadians(-75); // [radians]

        public static final double STOP_HELICOPTER_TIMESTAMP = 149.5; // [s]

        public static final double ZERO_POSITION = -0.050759776268994417; // [ticks]

        public static final double POSITION_TOLERANCE = 0.015; // [radians]

        public static final double SECOND_RUNG = Math.toRadians(110.66); // [radians]
        public static final double THIRD_RUNG = 0; // [radians]
        public static final double RUNG_SEPARATION = 0; // [radians]

        public static final double BLUE_RUNG_YAW = 180;
        public static final double RED_RUNG_YAW = BLUE_RUNG_YAW - 180;

        public static final double OSCILLATION_DELTA_TIME = 0.2;

        public static final boolean VOLTAGE_COMPENSATION = true;
    }

    public static class SHOOTER {
        public static final double kP = 0.08;
        public static final double kI = 0;
        public static final double kD = 9;
        public static final double kF = 0.048;
        public static final double SpeedForDistance = 0;
        public static final double TICKS_PER_REVOLUTION = 2048;
        public static final TalonFXInvertType INVERSION_TYPE = TalonFXInvertType.CounterClockwise;
        public static double NEUTRAL_DEADBAND = 0.1;

        public static TalonFXConfiguration getConfiguration() {
            final TalonFXConfiguration configuration = new TalonFXConfiguration();
            configuration.neutralDeadband = NEUTRAL_DEADBAND;
            return configuration;
        }
    }

    public static class Conveyor {
        public static final Color BLUE = new Color(0.18, 0.385, 0.436);
        public static final Color RED = new Color(0.511, 0.346, 0.143);
        public static final Color NONE = new Color(0.31, 0.415, 0.275);
        public static final double TICKS_PER_UNIT = 0;
    }

    public static class Hood {
        public static final double DISTANCE_FOR_ANGLE = 0;
        public static final double DISTANCE_FROM_TARGET = 0;

        public static final double GEAR_RATIO = 106.88;
        public static final double TICKS_PER_DEGREE =0 ;
        public static final double TICKS_PER_RAD_ABSOLUTE_ENCODER = 0;
        public static final double ZERO_POSITION = 0;
        public static final double MAX_VELOCITY = 0;
        public static final double MAX_ACCELERATION = 0;
        public static final double TOP_SOFT_LIMIT = (0.86 * 2048 - ZERO_POSITION) * Constants.Hood.GEAR_RATIO;
        public static final double BOTTOM_SOFT_LIMIT = (0.75 * 2048 - ZERO_POSITION) * Constants.Hood.GEAR_RATIO;
    }

    public static class intake {
        public static final int MOTOR = 11;
        public static final int SOLENOID = 1;

        public static final boolean IS_MOTOR_INVERTED = true;
    }

    public static class State {
        enum stateName {
            idle,
            feedAndConvey,
            conveyAndShoot,
            warmUp,
            reversePipeLine
        }
    }

}
