package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Arms;

import static frc.robot.Robot.arms;


public class CloseArms extends Command {
    public CloseArms() {
        requires(arms);
    }

    @Override
    protected void execute() {
        arms.close();
    }

    @Override
    protected boolean isFinished() {
        return arms.haveReached(Arms.POS_CLOSED);
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
