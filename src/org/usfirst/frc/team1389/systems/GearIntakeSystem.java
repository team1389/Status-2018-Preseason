package org.usfirst.frc.team1389.systems;

import com.team1389.hardware.inputs.software.RangeIn;
import com.team1389.hardware.outputs.software.RangeOut;
import com.team1389.hardware.value_types.Percent;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;

public class GearIntakeSystem extends Subsystem
{
	RangeOut<Percent> intakeVoltage, armVoltage;
	RangeIn<Percent> armAxis;

	boolean intakeRunning;
	boolean outtakeRunning;

	public GearIntakeSystem(RangeOut<Percent> intakeVoltage, RangeOut<Percent> armVoltage, RangeIn<Percent> armAxis)
	{
		this.intakeVoltage = intakeVoltage;
		this.armVoltage = armVoltage;
		this.armAxis = armAxis;

	}
	public enum State
	{
		Carrying, Intakeing, Placing, Alligning;
	}
	

	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> stem)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName()
	
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update()
	{
		// TODO Auto-generated method stub
		
	}

}
