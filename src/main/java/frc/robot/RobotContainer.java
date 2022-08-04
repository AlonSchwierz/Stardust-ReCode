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

    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
    }


    private void configureButtonBindings() {
        // See https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
    }


    private PipeLine.Cases getPipelineState() {
        if (a.get()) {
            return PipeLine.Cases.FEED_AND_CONVEY;
        }
        if (b.get()) {
            if (Math.abs(shooter.returnSpeedForDistance() - shooter.getVelocity()) < 50) {
                return PipeLine.Cases.CONVEY_AND_SHOOT;
            } else {
                return PipeLine.Cases.WARMUP;
            }

        }

        if (x.get()) {
            return PipeLine.Cases.REVERSE_PIPELINE;
        }
        else{
            return PipeLine.Cases.Idle;
        }

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return null;
    }
}
