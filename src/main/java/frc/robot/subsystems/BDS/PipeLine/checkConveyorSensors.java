package frc.robot.subsystems.BDS.PipeLine;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Conveyor.Conveyor;

public class checkConveyorSensors extends ParallelCommandGroup {
    public checkConveyorSensors(Conveyor conveyor) {
        addRequirements(conveyor);
        addCommands(
                // this BIT is currently built in robotPeriodic()
        );
    }

}
