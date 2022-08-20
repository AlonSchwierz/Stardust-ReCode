package frc.robot.subsystems;

public class State {
    enum stateName{
        idle,
        feedAndConvey,
        conveyAndShoot,
        warmUp,
        reversePipeLine
    }
    stateName nextAvliableState1;
    stateName nextAvliableState2;
    stateName nextAvliableState3;
    stateName nextAvliableState4;


    public State(stateName nextAvliableState1, stateName nextAvliableState2, stateName nextAvliableState3, stateName nextAvliableState4) {
        this.nextAvliableState1 = nextAvliableState1;
        this.nextAvliableState2 = nextAvliableState2;
        this.nextAvliableState3 = nextAvliableState3;
        this.nextAvliableState4 = nextAvliableState4;

    }
}
