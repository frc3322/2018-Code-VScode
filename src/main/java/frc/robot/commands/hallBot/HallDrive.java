package frc.robot.commands.hallBot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Robot;
import frc.robot.commands.hallBot.HallRotate;

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
        Robot.dist = Robot.ultra.getValue();
        while (Robot.dist >= 155) {
            drivetrain.drive(-0.7,0);
            Robot.dist = Robot.ultra.getValue();
        }
        drivetrain.stop();

    }

    @Override
    protected boolean isFinished() {
        return true;
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