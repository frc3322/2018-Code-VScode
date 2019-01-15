package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import static frc.robot.Robot.drivetrain;


public class ShiftHigh extends InstantCommand {
    @Override
    protected void execute() {
        drivetrain.shiftHigh();
    }
}
