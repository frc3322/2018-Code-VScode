package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.Robot.intakes;


public class IntakeIdle extends Command {
    public IntakeIdle() {
        requires(intakes);
    }

    @Override
    protected void execute() {
        intakes.set(-.2);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        intakes.stop();
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
