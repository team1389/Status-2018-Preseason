package org.usfirst.frc.team1389.systems;

import com.team1389.control.SmoothSetController;
import com.team1389.hardware.inputs.software.RangeIn;
import com.team1389.hardware.outputs.software.RangeOut;
import com.team1389.hardware.value_types.Percent;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;

public class GearIntakeSystem extends Subsystem
{

	private final double PLACING = 35;
	private final double ALLIGNING = 45;
	private final double CARRYING = 100;
	private final double INTAKING = -37;
	private State currentState;

	RangeOut<Percent> intakeVoltage, armVoltage;
	SmoothSetController armPositionPID;

	public GearIntakeSystem(RangeOut<Percent> intakeVoltage, RangeOut<Percent> armVoltage,
			SmoothSetController armPositionPID)
	{
		this.intakeVoltage = intakeVoltage;
		this.armVoltage = armVoltage;
		this.armPositionPID = armPositionPID;

	}

	public enum State
	{
		Intaking, Alligning, Placing, Carrying;
	}

	public void moveArm(State desiredState)
	{
		switch (desiredState)
		{
		case Intaking:
			armPositionPID.setSetpoint(INTAKING);
			currentState = State.Intaking;
			break;
		case Alligning:
			armPositionPID.setSetpoint(ALLIGNING);
			currentState = State.Alligning;
			break;
		case Placing:
			armPositionPID.setSetpoint(PLACING);
			currentState = State.Placing;
			break;
		case Carrying:
			armPositionPID.setSetpoint(CARRYING);
			currentState = State.Carrying;
			break;
		}
	}

	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> stem)
	{
		return null;
	}

	@Override
	public String getName()
	{
		return "GearIntakeSystem";
	}

	@Override
	public void init()
	{
		armPositionPID.enable();
	}

	@Override
	public void update()
	{
		armPositionPID.update();
	}

}
