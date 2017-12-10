package org.usfirst.frc.team1389.operation;

import org.usfirst.frc.team1389.robot.RobotSoftware;
import org.usfirst.frc.team1389.robot.controls.ControlBoard;
 

import com.team1389.system.SystemManager;

public class TeleopMain
{
	RobotSoftware robot;
	SystemManager manager;
	ControlBoard controls;

	/**
	 * 
	 * Notice no DigitalIn to represent time TODO use
	 * https://frcevents2.docs.apiary.io/# to figure out how we're going to do that
	 * next season
	 * 
	 */

	public TeleopMain(RobotSoftware robot)
	{
		this.robot = robot;
	}

	public void init()
	{
		OctoMecanumSystem drive = setUpDirve();
	}
	
	private OctoMecanumSystem setUpDrive() {
		
	}
}
