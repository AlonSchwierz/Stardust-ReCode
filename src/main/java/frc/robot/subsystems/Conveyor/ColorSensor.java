package frc.robot.subsystems.Conveyor;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;

public class ColorSensor extends ColorSensorV3 {
    /**
     * Constructs a ColorSensor.
     *
     * @param port The I2C port the color sensor is attached to
     */
    public ColorSensor(I2C.Port port) {
        super(port);
    }
}
