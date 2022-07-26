package frc.robot.subsystems.Conveyor;

import edu.wpi.first.wpilibj.DigitalInput;

public class BeamBreaker {
    private final DigitalInput beam;
    private boolean hadObjectLastLoop = false;
    private boolean hasObject;

    public BeamBreaker(int beamBreakerPort) {
        this.beam = new DigitalInput(beamBreakerPort);
    }

    public boolean get() {
        return beam.get();
    }

    public boolean hasObject() {
        return !beam.get();
    }

    public void updateBeamBreaker() {
        hadObjectLastLoop = hasObject;
        hasObject = hasObject();
    }

    public boolean hasChange() {
        return hasObject != hadObjectLastLoop;
    }

}
