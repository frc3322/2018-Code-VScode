package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Elevator;

import static frc.robot.Robot.elevator;

public class ElevatorToScale extends Command {
    public ElevatorToScale() {
        requires(elevator);
        setTimeout(7);
    }

    @Override
    protected void initialize() {
        elevator.goToPosInit(Elevator.SCALE);
    }

    @Override
    protected void execute() {
        elevator.goToPos();
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut() || elevator.isAtScale();
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
