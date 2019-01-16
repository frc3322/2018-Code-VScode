package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Arms;

import static frc.robot.Robot.arms;


public class LowerArms extends Command {
    public LowerArms() {
        requires(arms);
    }

    @Override
    protected void execute() {
        arms.lowerArms();
    }

    @Override
    protected boolean isFinished() {
        return arms.haveReachedParallel();
    }

    @Override
    protected void end() {
        arms.stop();
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
