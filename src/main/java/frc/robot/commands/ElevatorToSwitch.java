package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Elevator;

import static frc.robot.Robot.elevator;

public class ElevatorToSwitch extends Command {
    public ElevatorToSwitch() {
        requires(elevator);
        setTimeout(4);
    }

    @Override
    protected void initialize() {
        elevator.goToPosInit(Elevator.SWITCH);
    }

    @Override
    protected void execute() {
        elevator.goToPos();
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut() || elevator.isAtSwitch();
    }

    @Override
    protected void end() {
        elevator.stop();
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
