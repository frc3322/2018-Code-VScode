package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.Robot.drivetrain;


public class AutoShiftDrivetrain extends Command {


    @Override
    protected void execute() {
        drivetrain.autoShift();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
