package frc.robot.subsystems.BTS;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Ports;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.intake.Intake;

public class checkCargoColour extends ParallelCommandGroup {
    public checkCargoColour(ColorSensorV3 colorSensorV3, Conveyor conveyor){
addCommands(
        new InstantCommand(() -> conveyor.IsCargoInFrontOfPost()),
        new InstantCommand(() -> conveyor.IsCargoInFrontOfPre()),
        new InstantCommand(() -> colorSensorV3.getColor())
);
    }

    }
