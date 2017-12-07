package org.usfirst.frc.team1389.robot.controls;

import com.team1389.hardware.inputs.controllers.LogitechExtreme3D;
import com.team1389.hardware.inputs.controllers.XBoxController;
import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.inputs.software.PercentIn;
import com.team1389.hardware.outputs.software.DigitalOut;
import com.team1389.util.bezier.BezierCurve;

/**
 * A basic framework for the robot controls. Like the RobotHardware, one
 * instance of the ControlBoard object is created upon startup, then other
 * methods request the singleton ControlBoard instance.
 * 
 * @author amind
 * @see ControlMap
 */
public class ControlBoard
{
	private static ControlBoard mInstance = new ControlBoard();
	public static final double turnSensitivity = 1.0;
	public static final double spinSensitivity = 1.0;

	public static ControlBoard getInstance()
	{
		return mInstance;
	}

	private ControlBoard()
	{
	}

	private final LogitechExtreme3D driveController = new LogitechExtreme3D(0);
	private final XBoxController manipController = new XBoxController(1); 

	public PercentIn driveYAxis()
	{
		return driveController.yAxis().applyDeadband(.075).invert();
	}

	public PercentIn driveXAxis()
	{
		return driveController.xAxis().applyDeadband(.075);
	}

	public PercentIn driveYaw()
	{
		return driveController.yaw().applyDeadband(.075);
	}

	public PercentIn driveTrim()
	{
		return driveController.throttle();
	}

	public DigitalIn driveModeBtn()
	{
		return driveController.thumbButton().latched();
	}

	public DigitalIn driveModifierBtn()
	{
		return driveController.trigger();
	}

	public DigitalIn intakeGearBtn()
	{
		return manipController.aButton().latched();
	}

	public DigitalIn stowArmBtn()
	{
		return manipController.yButton().latched();
	}

	public DigitalIn placeGearBtn()
	{
		return manipController.xButton().latched();
	}

	public DigitalIn prepareArmBtn()
	{
		return manipController.bButton().latched();
	}

	public PercentIn climberThrottle()
	{
		return manipController.leftTrigger();
	}

	public PercentIn armAngleAxis()
	{
		BezierCurve curve = new BezierCurve(.58, .13, .42, .89);
		return manipController.leftStick.yAxis().applyBezier(curve).scale(.5).invert();
	}

	public DigitalIn ballIntakeBtn()
	{
		return manipController.rightBumper();
	}

	public DigitalOut gearRumble()
	{
		return new DigitalOut(b -> manipController.setRumble(b ? 1.0 : 0.0));
	}

	public DigitalIn resetHopperBtn()
	{
		return manipController.downArrow();
	}

	public DigitalIn zeroAutomaticBtn()
	{
		return manipController.backButton().latched();
	}

	public DigitalIn dumpHopperBtn()
	{
		return manipController.upArrow();
	}

	public PercentIn outtakeAxis()
	{
		return manipController.rightTrigger();
	}

	public DigitalIn armManualTrigger()
	{
		return manipController.startButton().latched();
	}

}
