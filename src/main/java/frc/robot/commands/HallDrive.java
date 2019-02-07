package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Robot;

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
        if (dist < 135){
            drivetrain.drive(0,0.5);
        } else {
            drivetrain.drive(-0.5,0);
        }

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
//closest the robot should get is 135 on the sensor*/