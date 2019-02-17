/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.I2C;
import frc.robot.commands.auton.Auton;
import frc.robot.subsystems.Arms;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intakes;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
// If you rename or move this class, update the build.properties file in the project root
public class Robot extends TimedRobot 
{

    public static final Drivetrain drivetrain = new Drivetrain();
    public static final Elevator elevator = new Elevator();
    public static final Arms arms = new Arms();
    public static final Intakes intakes = new Intakes();
    public static final AnalogInput ultra = new AnalogInput(RobotMap.ALOG.ULTRASONIC);
    public static double dist;

    
    public static OI oi;

    public static String gameData;
    
    private Command autonomousCommand;
    private SendableChooser<Auton.Position> startChooser = new SendableChooser<>();
    private SendableChooser<Auton.Objective> objectiveChooser = new SendableChooser<>();
    private SendableChooser<Auton.Priority> priorityChooser = new SendableChooser<>();

    private static final String kRainbow = "Rainbow";
    private static final String kRedToBlue = "RedToBlue";
    private static final String kGlow = "Glow";
    //private static final String kPurple = "Purple";
    private String m_autoSelected;
    private final SendableChooser<String> m_chooser = new SendableChooser<>();
    public static final I2C Arduino = new I2C(I2C.Port.kOnboard,4);
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() 
    {
        CameraServer.getInstance().startAutomaticCapture();

        oi = new OI();

        // Create sendable choosers for auton selection
        startChooser.addDefault("Left", Auton.Position.LEFT);
        startChooser.addObject("Middle", Auton.Position.MIDDLE);
        startChooser.addObject("Right", Auton.Position.RIGHT);

        objectiveChooser.addObject("Switch", Auton.Objective.SWITCH);
        objectiveChooser.addObject("Scale", Auton.Objective.SCALE);
        objectiveChooser.addDefault("Auto line", Auton.Objective.SCALE);
        objectiveChooser.addObject("None (do nothing)", Auton.Objective.SCALE);

        priorityChooser.addDefault("Safe (stay on start side)", Auton.Priority.SAFE);
        priorityChooser.addObject("Prefer (stay on start side, switch and scale)", Auton.Priority.PREFER);
        priorityChooser.addObject("Force (both sides)", Auton.Priority.FORCE);

        SmartDashboard.putData("Start pos", startChooser);
        SmartDashboard.putData("Auton action", objectiveChooser);
        SmartDashboard.putData("Objective priority", priorityChooser);

        ultra.setAverageBits(8);

        m_chooser.setDefaultOption("Rainbow", kRainbow);
        m_chooser.addOption("RedToBlue", kRedToBlue);
        m_chooser.addOption("Glow", kGlow);
        //m_chooser.addOption("Purple", kPurple);
        SmartDashboard.putData("LEDs", m_chooser);
    }

    @Override
    public void robotPeriodic() {
        SmartDashboard.putNumber("NavX Angle", drivetrain.navx.getAngle());
        dist = ultra.getValue();
        SmartDashboard.putNumber("Left ticks", drivetrain.getLeftTicks());
        SmartDashboard.putNumber("Right ticks", drivetrain.getRightTicks());
        SmartDashboard.putNumber("Left distance", drivetrain.getLeftDisplacement());
        SmartDashboard.putNumber("Right distance", drivetrain.getRightDisplacement());
        SmartDashboard.putNumber("Distance", drivetrain.getRobotDisplacement());
        SmartDashboard.putNumber("Velocity", drivetrain.getRobotVelocity());
        SmartDashboard.putNumber("Acceleration", drivetrain.getAcceleration());
        SmartDashboard.putNumber("Current angle", drivetrain.navx.getAngle());
        SmartDashboard.putNumber("Elevator height", elevator.getHeight());
        //SmartDashboard.putNumber("Right Intake Current", intakes.getRightIntakeCurrent());
        SmartDashboard.putNumber("Left Intake Current", intakes.getLeftIntakeCurrent());
        //SmartDashboard.putNumber("Arms Current", arms.getArmCurrent());
        //SmartDashboard.putNumber("SPARK Encoder 0", arms.getSparkEncoder0());
        //SmartDashboard.putNumber("SPARK Encoder 1", arms.getSparkEncoder1());
        //SmartDashboard.putNumber("SPARK Encoder 2", arms.getSparkEncoder2());
        //SmartDashboard.putNumber("SPARK Encoder 3", arms.getSparkEncoder3());
        SmartDashboard.putNumber(" ultrasonic distance", dist);
        //LEDMode("Enabled ");
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() 
    {

    }

    @Override
    public void disabledPeriodic() 
    {
        Scheduler.getInstance().run();

        updateAutonData();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
     @Override
    public void autonomousInit() 
    {
        updateAutonData();
        drivetrain.resetPositioning();
        elevator.resetEncoder();
        arms.resetPosition();


        autonomousCommand = new Auton(startChooser.getSelected(), objectiveChooser.getSelected(), priorityChooser.getSelected());
        autonomousCommand.start();

        m_autoSelected = m_chooser.getSelected();
        //m_autoSelected = SmartDashboard.getString("Choose Color", "");
        System.out.println("Choose Color" + m_autoSelected);
          switch (m_autoSelected) {
            case kRainbow:
                ledMode("B");
                break;
            case kRedToBlue:
                ledMode("R");
                break;
            case kGlow:
                ledMode("G");
                break;
            /*case kPurple:
                ledMode("P");
                break;*/        
            default:
                // Put default auto code here
                break;
          }
        }
    
    
    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() 
    {
        drivetrain.resetPositioning();

        

        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() 
    {
        Scheduler.getInstance().run();

        boolean on = false;
        System.out.println("Starting OperatorControl");

    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic()
    {
        elevator.goToPos();
    }

    public void testInit() {
        elevator.goToPosInit(Elevator.SWITCH);
    }

    private void updateAutonData() {
        String newGameData = DriverStation.getInstance().getGameSpecificMessage();

        if (newGameData.length() == 3) {
            gameData = newGameData;
        }
    }

    public static void ledMode(String mode) {
        SmartDashboard.putString("ledMode", mode);
        char[] CharArray = mode.toCharArray();
        byte[] WriteData = new byte[CharArray.length];
        for (int i = 0; i < CharArray.length; i++) {
            WriteData[i] = (byte) CharArray[i];
        }
        Arduino.writeBulk(WriteData, WriteData.length);
        //Arduino.transaction(WriteData, WriteData.length, null, 0);
    }
}
