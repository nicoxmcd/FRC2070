/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Ridgefield Robotics. All Rights Reserved. */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
// import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private WPI_TalonSRX _left = new WPI_TalonSRX(0);
  private WPI_TalonSRX _right = new WPI_TalonSRX(1);
  private DifferentialDrive robotDrive = new DifferentialDrive(_left, _right);
  private final Joystick stick = new Joystick(0);
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
    if (timer.get() < 2.0) {
      robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
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
    // double stick_left = stick.getRawAxis(0);
    // double stick_right = stick.getRawAxis(1); 
    // _right.set(ControlMode.PercentOutput, stick);
    // _left.set(ControlMode.PercentOutput, stick);
    robotDrive.arcadeDrive(stick.getY(), stick.getX());
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
//   ,',': :: : ,',' : :: :Max          Luke
//  '.'_ : :; : : :  : :; :Fatima       Vince
// :____;`.__.' :_:  `.__.'Lizbeth      Yanako