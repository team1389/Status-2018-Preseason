package org.usfirst.frc.team1389.systems;

import com.team1389.control.SmoothSetController;
import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.inputs.software.RangeIn;
import com.team1389.hardware.outputs.software.RangeOut;
import com.team1389.hardware.value_types.Percent;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;

public class TeleopGearIntakeSystem extends GearIntakeSystem
{
	RangeOut<Percent> intakeVoltage, armVoltage;
	RangeIn<Percent> armAxis;
	DigitalIn intakeButton;
	DigitalIn outtakeButton;
	DigitalIn carryButton;
	DigitalIn allignButton;
	DigitalIn placeButton;
	DigitalIn manualButton;
	SmoothSetController armPositionPID;

	boolean intakeRunning;
	boolean outtakeRunning;
	boolean manualMode;

	public TeleopGearIntakeSystem(RangeOut<Percent> intakeVoltage, RangeOut<Percent> armVoltage,
			SmoothSetController armPositionPID, RangeIn<Percent> armAxis, DigitalIn intakeButton,
			DigitalIn outtakeButton, DigitalIn carryButton, DigitalIn allignButton, DigitalIn placeButton,
			DigitalIn manualButton)
	{
		super(intakeVoltage, armVoltage, armPositionPID);

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
		return stem.put(intakeVoltage.getWatchable("intake Voltage Set"), armVoltage.getWatchable("arm Voltage Set"),
				armAxis.getWatchable("arm axis"), intakeButton.getWatchable("intake Button"),
				outtakeButton.getWatchable("outtake button"));

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
			updateManual();
		} else
		{
			updateAuto();
		}
	}

	private void updateManual()
	{
		armVoltage.set(armAxis.get());
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
			moveArm(State.Intaking);
		}
	}

}
