package org.usfirst.frc.team1389.systems;

import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.inputs.software.RangeIn;
import com.team1389.hardware.outputs.software.RangeOut;
import com.team1389.hardware.value_types.Percent;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;

//will later extend GearIntakeSystem, which contains all the commands
public class TeleopGearIntakeSystem extends Subsystem
{
	RangeOut<Percent> intakeVoltage, armVoltage;
	RangeIn<Percent> armAxis;
	DigitalIn intakeButton;
	DigitalIn outtakeButton;
	boolean intakeRunning;
	boolean outtakeRunning;
	public TeleopGearIntakeSystem(RangeOut<Percent> intakeVoltage, RangeOut<Percent> armVoltage,
			RangeIn<Percent> armAxis, DigitalIn intakeButton, DigitalIn outtakeButton)
	{
		this.intakeVoltage = intakeVoltage;
		this.armVoltage = armVoltage;
		this.armAxis = armAxis;
		this.intakeButton = intakeButton;
		this.outtakeButton = outtakeButton;
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
		armVoltage.set(armAxis.get());
		intakeRunning = intakeButton.get()^intakeRunning;
		outtakeRunning = outtakeButton.get()^ outtakeRunning;
		if(!intakeRunning)
		{
			intakeVoltage.set((outtakeRunning)? -1:0);
		}
		else
		{
			intakeVoltage.set(1);
		}
	}

}
