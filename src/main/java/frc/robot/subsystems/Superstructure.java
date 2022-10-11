package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.Hood.Hood;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

import java.util.ArrayList;
import java.util.Arrays;

public class Superstructure extends SubsystemBase {
    private final Intake intake = Intake.getInstance();
    private final Conveyor conveyor = Conveyor.getInstance();
    private final Shooter shooter = Shooter.getInstance();
    private final Hood hood = Hood.getInstance();



    public static class State {

        StateName thisStateName;
        ArrayList<StateName> nextAvailableStates;

        public State(StateName thisStateName, StateName... nextAvailableStates) {
            this.thisStateName = thisStateName;
            this.nextAvailableStates = new ArrayList<>(Arrays.asList(nextAvailableStates));
        }
        public State(StateName thisStateName, ArrayList<StateName> nextAvailableStates) {
            this.thisStateName = thisStateName;
            this.nextAvailableStates = nextAvailableStates;
        }

        public boolean isStateAveliable(StateName wantedState) {
            return nextAvailableStates.contains(wantedState);
        }

        public enum StateName {
            FEED_AND_CONVEY, CONVEY_AND_SHOOT, Idle, REVERSE_PIPELINE, WARMUP

        }

    }
}





