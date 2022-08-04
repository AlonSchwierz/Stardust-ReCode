package frc.robot.subsystems.BTS;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Ports;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.intake.Intake;

public class checkCargoColour extends ParallelCommandGroup {
    public checkCargoColour(frc.robot.subsystems.Conveyor.ColorSensor colorSensor, Conveyor conveyor){
        addRequirements(conveyor);
addCommands(


        new RunCommand(() -> conveyor.IsCargoInFrontOfPost()),
        new RunCommand(() -> conveyor.IsCargoInFrontOfPre()),
        new RunCommand(() -> colorSensor.getColor())
);
    }

    }
