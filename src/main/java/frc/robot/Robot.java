/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Ridgefield Robotics. All Rights Reserved. */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.wpilibj.buttons.*;



public class Robot extends TimedRobot {
  CameraServer server;
  //Intake and Generator Initialization
  private WPI_TalonSRX _intake_powercell = new WPI_TalonSRX(0);
  private WPI_TalonSRX _generator_lift = new WPI_TalonSRX(1);
  private WPI_TalonSRX _intake_lift = new WPI_TalonSRX(2);
  //Drive Motor Initialization
  private WPI_TalonSRX _right = new WPI_TalonSRX(3);
  private WPI_TalonSRX _left = new WPI_TalonSRX(4);
  //Drivetrain Initialization
  private DifferentialDrive robotDrive = new DifferentialDrive(_left, _right);
  //Joystick
  private final Joystick stick = new Joystick(0);
  //Timer Initialization
  private final Timer timer = new Timer();
  //Marker Initialization :)
  public final String marker = "up";

/**
* This next function is run when the robot is first started up and should be
* used for any initialization code.
*/

  @Override
  public void robotInit() {
    server = CameraServer.getInstance();

    server.startAutomaticCapture(0);
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (timer.get() < 5.0) {
      robotDrive.tankDrive(0.6, 0.6); // drive forwards half speed
    } else if (timer.get() < 5.5 ) {
      robotDrive.tankDrive(0.6, -0.6);
    } else if (timer.get() < 7.0 ) {
      robotDrive.tankDrive(0.6, 0.6);
    } else {
      robotDrive.stopMotor(); // stop robot
    }
  }

  /** 
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {

    /**Utilizes values initialized in class Robot
    This command is the tankDrive, using the two TalonSRX motorcontrollers connected to the drive motors.**/
     robotDrive.tankDrive((stick.getRawAxis(1)*(-0.62)), (stick.getRawAxis(5))*(-0.62));

     /**This command controls the lift:
     hold down the y button, there is a 4 second delay in going up and down.
     Remember to reset after every match.**/
     if (stick.getRawButton(0)){
      _generator_lift.set(0.4);
     }
     //To reset lift after matches
    if (stick.getRawButton(0) && stick.getRawButton(1)){
      _generator_lift.set(-0.4);
    }
    //Set intake lift
     if (stick.getRawButton(1)){
      _intake_lift.set(0.4);
     }

     if (stick.getRawButton(2)){
      _intake_lift.set(-0.4);
     }
    //Set intake motor speed
     if (stick.getRawButton(6)){
      _intake_powercell.set(0.6);
     }

     if (stick.getRawButton(7)){
      _intake_lift.set(-0.6);
     }



     //button.whenHeld(New ExampleCommand());,
     //button.whenReleased(new ExampleCommand());

    // robotDrive.arcadeDrive(stick.getY(), stick.getX());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}

//    ___           ___           ___           ___     
//   /\  \         /\  \         /\  \         /\__\    
//   \:\  \       /::\  \       /::\  \       /::|  |   
//    \:\  \     /:/\:\  \     /:/\:\  \     /:|:|  |   
//    /::\  \   /::\~\:\  \   /::\~\:\  \   /:/|:|__|__ 
//   /:/\:\__\ /:/\:\ \:\__\ /:/\:\ \:\__\ /:/ |::::\__\
//  /:/  \/__/ \:\~\:\ \/__/ \/__\:\/:/  / \/__/~~/:/  /
// /:/  /       \:\ \:\__\        \::/  /        /:/  / 
// \/__/         \:\ \/__/        /:/  /        /:/  /  
//                \:\__\         /:/  /        /:/  /   
//                 \/__/         \/__/         \/__/    
// .---.  .--. .----. .--. Specifically the Programming Team:
// `--. :: ,. :`--  ;: ,. :Nicole       Grace
//   ,',': :: : ,',' : :: :Max          
//  '.'_ : :; : : :  : :; :       
// :____;`.__.' :_:  `.__.'       