package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class ArmsToParallel extends TimedCommand {
    public ArmsToParallel() {
        super(1);
        requires(Robot.arms);
    }

    @Override
    protected void initialize() {
        Robot.arms.lowerArms();
    }

    @Override
    protected void end() { Robot.arms.stop(); }
}
