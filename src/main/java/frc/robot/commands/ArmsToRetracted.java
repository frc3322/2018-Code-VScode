package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Arms;

import static frc.robot.Robot.arms;

public class ArmsToRetracted extends Command {
    public ArmsToRetracted() {
    }

    @Override
    protected void initialize() {
        arms.goToRotationInit(Arms.POS_RETRACTED);
    }

    @Override
    protected void execute() {
        arms.goToRotation();
    }

    @Override
    protected boolean isFinished() {
        return arms.haveReached(Arms.POS_RETRACTED);
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
