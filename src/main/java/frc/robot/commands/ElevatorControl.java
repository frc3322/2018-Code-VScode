package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

import static frc.robot.Robot.elevator;
import static frc.robot.Robot.oi;


public class ElevatorControl extends Command {

    private final int UP_AXIS;
    private final int DOWN_AXIS;

    private boolean holding = false;

    public ElevatorControl() {
        // Use requires() here to declare subsystem dependencies
        requires(elevator);

        this.UP_AXIS = RobotMap.XBOX.TRIGGER_L_AXIS;
        this.DOWN_AXIS = RobotMap.XBOX.TRIGGER_R_AXIS;
    }

    @Override
    protected void execute() {
        double moveInput = oi.aboveChassisStick.getRawAxis(UP_AXIS) - oi.aboveChassisStick.getRawAxis(DOWN_AXIS) * elevator.downSpeedModifier;

       if ((moveInput < .1) && (moveInput > -0.1)) {
            if (!holding) {
                holding = true;
                elevator.goToPosInit(elevator.getHeight());
            }


            elevator.goToPos();
        } else {
            holding = false;

            elevator.move(moveInput);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
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
