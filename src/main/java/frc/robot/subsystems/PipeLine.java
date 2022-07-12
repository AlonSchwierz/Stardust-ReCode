package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Conveyor.Conveyor;
import frc.robot.subsystems.Flap.Flap;
import frc.robot.subsystems.Hood.Hood;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

public class PipeLine extends CommandBase {
    private Intake intake = Intake.getInstance();
    private Conveyor conveyor = Conveyor.getInstance();
    private Shooter shooter = Shooter.getInstance();
    private Flap flap = Flap.getInstance();
    private Hood hood = Hood.getInstance();
    private String Case = "default";
    public PipeLine() {
        addRequirements(flap, conveyor, shooter, intake, hood);
    }



    @Override
    public void execute() {
        public void OperateCase (String Case){
            switch (Case) {
                case "aim":
                    flap.ShallNotPass();
                    shooter.setVelocity(3500);
                    conveyor.setPower(-0.05);
                    hood.setState(); // ...
                    break;
                case "shoot":

                    shooter.setPower(Shooter.returnSpeedForDistance());
                    break;
                case "feed":
                    intake.openREEEtractor();
                    intake.setPower(8);
                    intake.closeREEEtractor();
                case "convey":
                    conveyor.setPower(8);
                    break;
                case "shoot":

            }
        }
    }
}


}
