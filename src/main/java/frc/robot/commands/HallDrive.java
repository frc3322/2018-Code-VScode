package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

import static frc.robot.Robot.drivetrain;
import static frc.robot.Robot.oi;

public class HallDrive extends Command {

    

    public HallDrive() {
        requires(drivetrain);

    }

    @Override
    protected void initialize() {
       
    }

    @Override
    protected void execute() {
        drivetrain.drive(-1,-1);

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        drivetrain.drive(0,0);
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
