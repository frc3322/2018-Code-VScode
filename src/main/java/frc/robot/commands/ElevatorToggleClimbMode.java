package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

import static frc.robot.Robot.elevator;


public class ElevatorToggleClimbMode extends InstantCommand {

    @Override
    protected void execute() {
        elevator.toggleClimbMode();
    }
}
