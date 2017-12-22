package org.usfirst.frc.team1389.operation;

import org.usfirst.frc.team1389.DebugDash;
import org.usfirst.frc.team1389.robot.RobotConstants;
import org.usfirst.frc.team1389.robot.RobotSoftware;
import org.usfirst.frc.team1389.systems.ClimberSystem;
import org.usfirst.frc.team1389.systems.HopperSystem;
import org.usfirst.frc.team1389.systems.TeleopGearIntakeSystem;

import com.team1389.configuration.PIDConstants;
import com.team1389.control.SmoothSetController;
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
	 * https://frcevents2.docs.apiary.io/# to figure out how we're going to do
	 * that next season
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
		TeleopGearIntakeSystem gearIntake = setUpGearIntakeSystem();
		DebugDash.getInstance().watch(gearIntake);
		manager = new SystemManager(drive, climb, hopper, gearIntake);
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

	/*
	 * RangeOut<Percent> intakeVoltage, RangeOut<Percent> armVoltage,
	 * SmoothSetController armPositionPID, RangeIn<Percent> armAxis, DigitalIn
	 * intakeButton, DigitalIn outtakeButton, DigitalIn carryButton, DigitalIn
	 * allignButton, DigitalIn placeButton, DigitalIn manualButton, DigitalIn
	 * beamBreak
	 */
	private TeleopGearIntakeSystem setUpGearIntakeSystem()
	{
		// kinda want to practice tuning PID
//idea: define what each button is supposed to be on so coders dont have to trace back to here
		return new TeleopGearIntakeSystem(robot.intakeVoltage, robot.armVoltage,
				new SmoothSetController(new PIDConstants(.03, 0, .0), 800, 800, 500, robot.armAngle, robot.armVel,
						robot.armVoltage),
				controls.leftStickYAxis(), controls.aButton(), controls.rightBumper(), controls.yButton(),
				controls.bButton(), controls.xButton(), controls.startButton(), robot.gearBeamBreak);
	}

	public void update()
	{
		manager.update();
	}
}
