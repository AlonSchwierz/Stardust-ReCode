package frc.robot.subsystems.BDS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Conveyor.Conveyor;

public class checkCargoColour extends ParallelCommandGroup {
    public checkCargoColour( Conveyor conveyor){
        addRequirements(conveyor);
addCommands(

);
    }

    }
