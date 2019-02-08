package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Robot;
import frc.robot.commands.HallRotate;

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
        if (Robot.dist <= 135) {
            new HallRotate();
        } else {
            drivetrain.drive(-0.3,0);
        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        drivetrain.stop();
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
//closest the robot should get is 135 on the sensor