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
import com.ctre.phoenix.motorcontrol.ControlMode;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.wpilibj.buttons.*;



public class Robot extends TimedRobot {
  CameraServer server;
  //Intake and Generator Initialization
  private TalonSRX _intake_powercell = new TalonSRX(0);
  private TalonSRX _generator_lift = new TalonSRX(1);
  private TalonSRX _intake_lift = new TalonSRX(2);
  //Drive Motor Initialization
  private TalonSRX _right = new TalonSRX(3);
  private TalonSRX _left = new TalonSRX(4);
  //Drivetrain Initialization
  // private DifferentialDrive robotDrive = new DifferentialDrive(_left, _right);
  //Joystick
  private final Joystick stick = new Joystick(0);
  //Timer Initialization
  private final Timer timer = new Timer();
  //Marker Initialization :)
  // public final String marker = "up";

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
    // if (timer.get() < 5.0) {
    //   robotDrive.tankDrive(0.6, 0.6); // drive forwards half speed
    // } else if (timer.get() < 5.5 ) {
    //   robotDrive.tankDrive(0.6, -0.6);
    // } else if (timer.get() < 7.0 ) {
    //   robotDrive.tankDrive(0.6, 0.6);
    // } else {
    //   robotDrive.stopMotor(); // stop robot
    // }
  }

  /** 
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
    _right.setInverted(false);
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {

    /**Utilizes values initialized in class Robot
    This command is the tankDrive, using the two TalonSRX motorcontrollers connected to the drive motors.**/
    //  robotDrive.tankDrive((stick.getRawAxis(1)*(-0.62)), (stick.getRawAxis(5))*(-0.62));
    _left.set(ControlMode.PercentOutput, stick.getRawAxis(1));
    _right.set(ControlMode.PercentOutput, (stick.getRawAxis(5)*-1));
     /**This command controls the lift:
     hold down the y button, there is a 4 second delay in going up and down.
     Remember to reset after every match.**/



     if (stick.getRawButtonPressed(4)){
      _generator_lift.set(ControlMode.PercentOutput, 0.6);
     }
     
     //To reset lift after matches
    if (stick.getRawButtonPressed(1) && stick.getRawButtonPressed(4)){
      _generator_lift.set(ControlMode.PercentOutput, -0.6);
    }
    //Set intake lift
     if (stick.getRawButtonPressed(3)){
      _intake_lift.set(ControlMode.PercentOutput, 0.6);
     }

     if (stick.getRawButtonPressed(2)){
      _intake_lift.set(ControlMode.PercentOutput, -0.7);
     } 
    //Set intake motor speed
     if (stick.getRawButtonPressed(5)){
      _intake_powercell.set(ControlMode.PercentOutput, 0.6);
     }

     if (stick.getRawButtonPressed(6)){
      _intake_powercell.set(ControlMode.PercentOutput, 0.6);
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