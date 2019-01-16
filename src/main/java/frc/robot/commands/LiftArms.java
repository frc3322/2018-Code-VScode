package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Arms;

import static frc.robot.Robot.arms;


public class LiftArms extends Command {
    public LiftArms() {
        requires(arms);
    }

    @Override
    protected void execute() {
        arms.liftArms();
    }

    @Override
    protected boolean isFinished() {
        return arms.haveReachedPerpendicular();
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
