package frc.robot.commands.testing;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

import static frc.robot.Robot.drivetrain;
import frc.robot.commands.auton.TurnToAngle;

public class GoStraight extends Command{

    private double straightAngle;
    private final double desiredAngle;
    private final double turnAngle;

    public GoStraight(){
        requires(drivetrain);

        double degrees = 180;
        
        turnAngle = degrees;
        double initialAngle = drivetrain.navx.getAngle();
        desiredAngle = initialAngle + degrees;
    }

    @Override
    protected void initialize() {
        drivetrain.driveAngleInit(desiredAngle);
    }

    @Override
    protected void execute() {
        /*if(distL > 7 && distR > 7){
            straightAngle = drivetrain.navx.getAngle();
            drivetrain.driveAngleInit(straightAngle);
            drivetrain.drive(1, 0);
        } else {
            drivetrain.drive(0, turnAngle);
        }

          */ 
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }



}