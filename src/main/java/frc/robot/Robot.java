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
    _left.setInverted(false);
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {

    /**Utilizes values initialized in class Robot
    This command is the tankDrive, using the two TalonSRX motorcontrollers connected to the drive motors.**/
    //  robotDrive.tankDrive((stick.getRawAxis(1)*(-0.62)), (stick.getRawAxis(5))*(-0.62));
    
    _left.set(ControlMode.PercentOutput, (stick.getRawAxis(1)*-0.6));
    _right.set(ControlMode.PercentOutput, (stick.getRawAxis(5)*0.6));

     /**This command controls the lift:
     hold down the y button, there is a 4 second delay in going up and down.
     Remember to reset after every match.**/
     if (stick.getRawButtonPressed(4)){
      _generator_lift.set(ControlMode.PercentOutput, 0.7);
     }

     if (stick.getRawButtonReleased(4)){
      _generator_lift.set(ControlMode.PercentOutput, 0);
     }
     
     //To reset lift after matches, y and a buttons must be held at the same time
    if (stick.getRawButtonPressed(1) && stick.getRawButtonPressed(4)){
      _generator_lift.set(ControlMode.PercentOutput, -0.7);
    }

    if (stick.getRawButtonReleased(1) && stick.getRawButtonReleased(4)){
      _generator_lift.set(ControlMode.PercentOutput, 0);
    }

    //Set intake lift down
    if (stick.getRawButtonPressed(3)){
      _intake_lift.set(ControlMode.PercentOutput, 0.7);
    }

    if (stick.getRawButtonReleased(3)){
      _intake_lift.set(ControlMode.PercentOutput, 0);
    }
    //Set intake lift up
    if (stick.getRawButtonPressed(2)){
      _intake_lift.set(ControlMode.PercentOutput, -0.7);
    } 

    if (stick.getRawButtonReleased(2)){
      _intake_lift.set(ControlMode.PercentOutput, 0);
     } 

    //Set intake motor speed forwards
    if (stick.getRawButtonPressed(5)){
      _intake_powercell.set(ControlMode.PercentOutput, 1);
    }

    if (stick.getRawButtonReleased(5)){
      _intake_powercell.set(ControlMode.PercentOutput, 0);
    }
    
    //Set intake motor speed backwards
    if (stick.getRawButtonPressed(6)){
      _intake_powercell.set(ControlMode.PercentOutput, -1);
    }

    if (stick.getRawButtonReleased(6)){
      _intake_powercell.set(ControlMode.PercentOutput, 0);
    }
  }

  /**
   * If you want to test code, put it in this function, run test code from build.gradle, then enable test mode on driver station. 
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