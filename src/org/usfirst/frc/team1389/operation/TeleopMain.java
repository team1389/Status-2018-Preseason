package org.usfirst.frc.team1389.operation;

import org.usfirst.frc.team1389.robot.RobotSoftware;
import org.usfirst.frc.team1389.systems.ClimberSystem;
import org.usfirst.frc.team1389.systems.HopperSystem;

import com.team1389.hardware.controls.ControlBoard;
import com.team1389.system.SystemManager;
import com.team1389.system.drive.OctoMecanumSystem;

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
		controls = ControlBoard.getInstance();
		OctoMecanumSystem drive = setUpDrive();
		ClimberSystem climb = setUpClimber();
		HopperSystem hopper = setUpHopper();
		manager = new SystemManager(drive, climb, hopper);
	}

	private OctoMecanumSystem setUpDrive()
	{
		return new OctoMecanumSystem(robot.voltageDrive, robot.pistons, robot.gyroInput, controls.driveXAxis(),
				controls.driveYAxis(), controls.driveYaw(), controls.driveTrim(), controls.driveModeBtn(),
				controls.driveModifierBtn());

	}

	private ClimberSystem setUpClimber()
	{
		return new ClimberSystem(controls.leftTrigger(), robot.climberVoltage);
	}

	private HopperSystem setUpHopper() 
	{
		return new HopperSystem(controls.upDPad(),
				robot.dumperLPiston.getDigitalOut().addFollowers(robot.dumperRPiston.getDigitalOut()));
	}
	public void update()
	{
		manager.update();
	}
}
