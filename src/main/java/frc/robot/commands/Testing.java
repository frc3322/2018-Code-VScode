/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.*;

/**
 * Add your docs here.
 */
public class Testing extends Command {

    public Testing() {
        requires(Robot.arms);
    }

    protected void execute() {
    //    Robot.arms.sparkTest(1);
    }

    

    @Override
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    //    Robot.arms.sparkTest(0);
    }

    protected void interrupted() {
        super.interrupted();
    }
}