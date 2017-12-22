package org.usfirst.frc.team1389.systems;

import com.team1389.control.SmoothSetController;
import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.inputs.software.RangeIn;
import com.team1389.hardware.outputs.software.RangeOut;
import com.team1389.hardware.value_types.Percent;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;
import com.team1389.watch.info.NumberInfo;

public class TeleopGearIntakeSystem extends GearIntakeSystem
{
	RangeIn<Percent> armAxis;
	DigitalIn intakeButton;
	DigitalIn outtakeButton;
	DigitalIn carryButton;
	DigitalIn allignButton;
	DigitalIn placeButton;
	DigitalIn manualButton;

	boolean intakeRunning;
	boolean outtakeRunning;
	boolean manualMode = false;

	public TeleopGearIntakeSystem(RangeOut<Percent> intakeVoltage, RangeOut<Percent> armVoltage,
			SmoothSetController armPositionPID, RangeIn<Percent> armAxis, DigitalIn intakeButton,
			DigitalIn outtakeButton, DigitalIn carryButton, DigitalIn allignButton, DigitalIn placeButton,
			DigitalIn manualButton, DigitalIn beamBreak)
	{
		super(intakeVoltage, armVoltage, beamBreak, armPositionPID);

		this.armAxis = armAxis;
		this.intakeButton = intakeButton;
		this.outtakeButton = outtakeButton;
		this.carryButton = carryButton;
		this.allignButton = allignButton;
		this.placeButton = placeButton;
		this.manualButton = manualButton;
	}

	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> stem)
	{
		return stem.put(intakeVoltage.getWatchable("intake voltage set"), armVoltage.getWatchable("arm Voltage Set"),
				pid.getSource().getWatchable("arm angle"), intakeButton.getWatchable("intake Button"),
				outtakeButton.getWatchable("outtake button"), 
				new NumberInfo(("setpoint"), () -> pid.getSetpoint()),
				new NumberInfo(("error"), () -> pid.getError()));

	}

	@Override
	public String getName()
	{
		return "TeleopGearIntake";
	}

	@Override
	public void init()
	{
		intakeRunning = false;
		outtakeRunning = false;
	}

	@Override
	public void update()
	{
		if (manualButton.get())
		{
			manualMode = true;
		}
		if (manualMode)
		{
			System.out.println("xxxxxxxxxxxxxxxxxxxxTeleopGearIntake: in manual modexxxxxxxxxxxxxxxxxxxxxxxxxxx");
			// TODO Fix update manual, crashes program
			// updateManual();
		} else
		{
			updateAuto();
			pid.update();
		}
	}

	private void updateManual()
	{
		armVoltage.set(1);
		intakeRunning = intakeButton.get() ^ intakeRunning;
		outtakeRunning = outtakeButton.get() ^ outtakeRunning;

		if (intakeRunning)
		{
			intakeVoltage.set(1);
		} else if (outtakeRunning)
		{
			intakeVoltage.set(-1);
		} else
		{
			intakeVoltage.set(0);
		}
	}

	private void updateAuto()
	{
		if (carryButton.get())
		{
	
			moveArm(State.Carrying);
		}
		if (allignButton.get())
		{
			moveArm(State.Alligning);
		}
		if (placeButton.get())
		{
			moveArm(State.Placing);
		}
		if (intakeButton.get())
		{
			System.out.println("aaaaaaaaaaaaaaaaaaaa");
			moveArm(State.Intaking);
		}
	}

}
