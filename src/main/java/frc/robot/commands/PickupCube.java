package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class PickupCube extends CommandGroup {

    public PickupCube() {
        addParallel(new LiftArms(), .75);
        addParallel(new IntakeCube(), .75);
    }
}
