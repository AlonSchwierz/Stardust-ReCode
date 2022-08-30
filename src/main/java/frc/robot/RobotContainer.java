// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.Flap.Flap;
import frc.robot.subsystems.Hood.Hood;
import frc.robot.subsystems.PipeLine;
import frc.robot.subsystems.Superstructure;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    private final Intake intake = Intake.getInstance();
    private final Conveyor conveyor = Conveyor.getInstance();
    private final Shooter shooter = Shooter.getInstance();
    private final Flap flap = Flap.getInstance();
    private final Hood hood = Hood.getInstance();

    private final XboxController xbox = new XboxController(Ports.Controls.XBOX);
    private final JoystickButton a = new JoystickButton(xbox, XboxController.Button.kA.value);
    private final JoystickButton b = new JoystickButton(xbox, XboxController.Button.kB.value);
    private final JoystickButton x = new JoystickButton(xbox, XboxController.Button.kX.value);
    private final JoystickButton y = new JoystickButton(xbox, XboxController.Button.kY.value);
    private final JoystickButton rb = new JoystickButton(xbox, XboxController.Button.kRightBumper.value);
    private Superstructure.State currentState = new Superstructure.State(null, null, null, null, null);
    private Superstructure.State idle = new Superstructure.State(Superstructure.State.stateName.Idle, Superstructure.State.stateName.FEED_AND_CONVEY, Superstructure.State.stateName.WARMUP, Superstructure.State.stateName.REVERSE_PIPELINE, null);
    private Superstructure.State feedAndConvey = new Superstructure.State(Superstructure.State.stateName.FEED_AND_CONVEY, Superstructure.State.stateName.Idle, Superstructure.State.stateName.WARMUP, null, null);
    private Superstructure.State warmUp = new Superstructure.State(Superstructure.State.stateName.WARMUP, Superstructure.State.stateName.Idle, Superstructure.State.stateName.FEED_AND_CONVEY, Superstructure.State.stateName.REVERSE_PIPELINE, Superstructure.State.stateName.CONVEY_AND_SHOOT);
    private Superstructure.State conveyAndShoot = new Superstructure.State(Superstructure.State.stateName.CONVEY_AND_SHOOT, Superstructure.State.stateName.Idle, Superstructure.State.stateName.WARMUP, Superstructure.State.stateName.REVERSE_PIPELINE, null);
    private Superstructure.State reversePipeLine = new Superstructure.State(Superstructure.State.stateName.REVERSE_PIPELINE, Superstructure.State.stateName.Idle, Superstructure.State.stateName.WARMUP, null, null);


    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
    }


    private void configureButtonBindings() {
        // See https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
    }

    /**
     * a - feedAndConvey
     * b - warmUp
     * x - conveyAndShoot
     * y - reversePipeLine
     */
    private Superstructure.State.stateName getPipelineState() {
        if (a.get() && currentState.isStateAveliable(Superstructure.State.stateName.FEED_AND_CONVEY)) {
            currentState = feedAndConvey;
            return Superstructure.State.stateName.FEED_AND_CONVEY;
        }

        if (Math.abs(shooter.returnSpeedForDistance() - shooter.getVelocity()) < 50 && currentState.isStateAveliable(Superstructure.State.stateName.CONVEY_AND_SHOOT)) {
            currentState = conveyAndShoot;
            return Superstructure.State.stateName.CONVEY_AND_SHOOT;



        }
        if (x.get() && currentState.isStateAveliable(Superstructure.State.stateName.WARMUP)) {
            currentState = warmUp;
            return Superstructure.State.stateName.WARMUP;
        }

        if (y.get() && currentState.isStateAveliable(Superstructure.State.stateName.REVERSE_PIPELINE)) {
            currentState = reversePipeLine;
            return Superstructure.State.stateName.REVERSE_PIPELINE;
        } else {
            currentState = idle;
            return Superstructure.State.stateName.Idle;
        }

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null;
    }
}
