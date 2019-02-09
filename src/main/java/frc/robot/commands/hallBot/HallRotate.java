package frc.robot.commands.hallBot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Robot;

import static frc.robot.Robot.drivetrain;
import static frc.robot.Robot.oi;

public class HallRotate extends Command {
    public double initialAngle;

    

    public HallRotate() {
        requires(drivetrain);

    }

    @Override
    protected void initialize() {
        initialAngle = drivetrain.navx.getAngle();
    }

    @Override
    protected void execute() {
        while (drivetrain.navx.getAngle() < initialAngle + 170) {
            drivetrain.drive(0,0.5);
        }
        drivetrain.stop();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}