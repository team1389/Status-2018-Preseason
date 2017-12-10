package org.usfirst.frc.team1389.systems;

import com.team1389.hardware.inputs.software.RangeIn;
import com.team1389.hardware.outputs.software.RangeOut;
import com.team1389.hardware.value_types.Percent;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;

public class ClimberSystem extends Subsystem
{
	RangeIn<Percent> inputAxis;
	RangeOut<Percent> climberMotors;
	/**
	 * 
	 * @param inputAxis controls voltage set at a 1:1 ratio
	 * @param climberMotors all motors on climber (strung together using followers)
	 */
	public ClimberSystem(RangeIn<Percent> inputAxis, RangeOut<Percent> climberMotors)
	{
		this.inputAxis = inputAxis;
		this.climberMotors = climberMotors;
	}

	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> stem)
	{
		return stem.put(inputAxis.getWatchable("climber input axis"), climberMotors.getWatchable("climber motors"));
	}

	@Override
	public String getName()
	{
		return "Climber";
	}

	@Override
	public void init()
	{	
	}

	@Override
	public void update()
	{
		climberMotors.set(inputAxis.get());
	}

}
