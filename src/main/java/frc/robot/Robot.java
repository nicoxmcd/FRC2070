/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends TimedRobot {
  //Ignore this bit too under this
  // private Spark leftMotor = new Spark(0);
  // private Spark rightMotor = new Spark(1);

  //Here I am initializing our sparks motor controllers with our tank drive.
  private final DifferentialDrive robotDrive = new DifferentialDrive(new Spark(0), new Spark(1));
  //Initializing the joystick
  private Joystick stick = new Joystick(0);
  //Initializing the timer
  private final Timer timer = new Timer();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   * So at the beginning of the autonomous portion
   * We start the timer for our while function in AutonomousPeriodic
   */
  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   * This is where we tell the robot what to do and such for that period. 
   */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    if (timer.get() < 2.0) {
      robotDrive.arcadeDrive(0.2, 0.0); // drive forwards half speed
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
   * @my programmers: this is an important section, it's for Teleop, 
   * Basically the whole drive train is programmed here.
   */
  @Override
  public void teleopPeriodic() {
    /**Basically what I tried to do in this section was following a tutorial,
    *but then I realized it wasn't working
    *double speed = stick.getRawAxis(1) * 0.6;
    *double turn = stick.getRawAxis(0) * 0.6;

    *double left = speed + turn;
    *double right = speed - turn;

    *leftMotor.set(left);
    *rightMotor.set(-right); */
 
    //get information from the joysticks to plug into the drive's values, 
    //make robot go go
    robotDrive.arcadeDrive(stick.getY(), stick.getX());
  }

  /**
   * This function is called periodically during test mode.
   * @my programmers: To be quite honest, you don't need to put anything in this. 
   * 
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
//   ,',': :: : ,',' : :: :Max          Luke
//  '.'_ : :; : : :  : :; :Fatima       Vince
// :____;`.__.' :_:  `.__.'Lizbeth      Yonako