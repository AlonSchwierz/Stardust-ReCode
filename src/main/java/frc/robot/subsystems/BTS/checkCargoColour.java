package frc.robot.subsystems.BTS;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Conveyor.Conveyor;

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
