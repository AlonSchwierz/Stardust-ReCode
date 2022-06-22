package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

public class PipeLine extends SubsystemBase {

    public static void OperateCase(String Case) {
        switch (Case) {
            case "aim":
                break;
            case "shoot":
                Shooter.setPower(Shooter.returnSpeedForDistance());
                break;
            case "take":
                Intake.openREEEtractor();
                Intake.setPower(8);
                Intake.closeREEEtractor();
            case "convey":
                Conveyor.
        }
    }

}
