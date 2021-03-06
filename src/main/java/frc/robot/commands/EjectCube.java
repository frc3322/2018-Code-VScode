package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static frc.robot.Robot.arms;
import static frc.robot.Robot.intakes;


public class EjectCube extends Command {
    double speed = 0;

    public EjectCube() {
        requires(intakes);
    }

    public EjectCube(double speed) {
        this();
        this.speed = speed;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (speed == 0) {
            intakes.spinOutwards();
        } else {
            intakes.set(speed);
        }
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
