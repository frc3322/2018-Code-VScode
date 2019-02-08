package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Robot;

import static frc.robot.Robot.drivetrain;
import static frc.robot.Robot.oi;

public class HallRotate extends Command {
    

    

    public HallRotate() {
        requires(drivetrain);

    }

    @Override
    protected void initialize() {
        var initial_angle = drivetrain.navx.getAngle();
    }

    @Override
    protected void execute() {
        if (drivetrain.navx.getAngle() < initial_angle + 180) {
            drivetrain.TurnToAngle();
        }else{
            drivetrain.stop();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
