package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.Flap.Flap;
import frc.robot.subsystems.Hood.Hood;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

public class Superstructure extends SubsystemBase {
    private final Intake intake = Intake.getInstance();
    private final Conveyor conveyor = Conveyor.getInstance();
    private final Shooter shooter = Shooter.getInstance();
    private final Flap flap = Flap.getInstance();
    private final Hood hood = Hood.getInstance();
    State idle = new State(State.stateName.idle, State.stateName.feedAndConvey, State.stateName.warmUp, State.stateName.reversePipeLine, null);
    State feedAndConvey = new State(State.stateName.feedAndConvey, State.stateName.idle, State.stateName.warmUp, null, null);
    State warmUp = new State(State.stateName.warmUp, State.stateName.idle, State.stateName.feedAndConvey, State.stateName.reversePipeLine, State.stateName.conveyAndShoot);
    State conveyAndShoot = new State(State.stateName.conveyAndShoot, State.stateName.idle, State.stateName.warmUp, State.stateName.reversePipeLine, null);
    State reversePipeLine = new State(State.stateName.reversePipeLine, State.stateName.idle, State.stateName.warmUp, null, null);


    public void idle() {
        intake.setPower(0);
        conveyor.setPower(0);
        shooter.setPower(0);
    }

    public void feed() {
        intake.openREEEtractor();
        intake.setPower(0.5);
    }

    public void convey() {
        flap.ShallNotPass();
        conveyor.setPower(0.5);
    }

    public void shoot() {
        flap.ShallPass();
        shooter.setPower(0.5);
    }


    public void feedAndConvey() {
        shooter.setPower(0);
        intake.openREEEtractor();
        flap.ShallNotPass();
        intake.setPower(0.5);
        conveyor.setPower(0.5);
    }

    public void warmUp() {
        intake.setPower(0);
        conveyor.setPower(0);
        flap.ShallNotPass();
        hood.changeAngleForDistance();
        shooter.setVelocity(shooter.returnSpeedForDistance());
    }

    public static class State {

        enum stateName{
            idle,
            feedAndConvey,
            conveyAndShoot,
            warmUp,
            reversePipeLine
        }

        stateName thisStateName;
        stateName nextAvliableState1;
        stateName nextAvliableState2;
        stateName nextAvliableState3;
        stateName nextAvliableState4;


        public State(stateName thisStateName , stateName nextAvliableState1, stateName nextAvliableState2, stateName nextAvliableState3, stateName nextAvliableState4) {
            this.thisStateName = thisStateName;
            this.nextAvliableState1 = nextAvliableState1;
            this.nextAvliableState2 = nextAvliableState2;
            this.nextAvliableState3 = nextAvliableState3;
            this.nextAvliableState4 = nextAvliableState4;

        }
    public boolean isStateAveliable (stateName wantedState){
            if(wantedState == this.nextAvliableState1 || wantedState == this.nextAvliableState2 || wantedState == this.nextAvliableState3 || wantedState == this.nextAvliableState4){
                return true;
            }
            else {
                return false;
            }
    }

    }
}





