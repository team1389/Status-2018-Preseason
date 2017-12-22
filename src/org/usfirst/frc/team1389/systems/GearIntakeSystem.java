package org.usfirst.frc.team1389.systems;

import com.team1389.control.PIDController;
import com.team1389.control.SmoothSetController;
import com.team1389.control.SynchronousPIDController;
import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.outputs.software.RangeOut;
import com.team1389.hardware.value_types.Percent;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;
import com.team1389.watch.info.EnumInfo;

public class GearIntakeSystem extends Subsystem
{

	private final double PLACING = 35;
	private final double ALLIGNING = 45;
	private final double CARRYING = 100;
	private final double INTAKING = -37;
	private State currentState;
	DigitalIn beamBreak;
	RangeOut<Percent> intakeVoltage, armVoltage;
	SmoothSetController pid;

	public GearIntakeSystem(RangeOut<Percent> intakeVoltage, RangeOut<Percent> armVoltage, DigitalIn beamBreak,
			SmoothSetController armPositionPID)
	{

		this.intakeVoltage = intakeVoltage;
		this.armVoltage = armVoltage;
		this.beamBreak = beamBreak;
		pid= armPositionPID;

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
			pid.setSetpoint(INTAKING);
		

			currentState = State.Intaking;
			break;
		case Alligning:
			pid.setSetpoint(ALLIGNING);

			currentState = State.Alligning;
			break;
		case Placing:
			pid.setSetpoint(PLACING);
			currentState = State.Placing;
			break;
		case Carrying:
			pid.setSetpoint(CARRYING);
			currentState = State.Carrying;
			break;
		}
	}

	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> stem)
	{
		// not really sure if this is how EnumInfo should be set up, test to
		// make sure
		return stem.put(new EnumInfo("State", () -> currentState), beamBreak.getWatchable("sensor_beam-break"));
	}

	@Override
	public String getName()
	{
		return "GearIntakeSystem";
	}

	@Override
	public void init()
	{
		pid.enable();
	}

	//keep in mind all of this gets overwrote in teleopGearIntake
	@Override
	public void update()
	{

		if (currentState == State.Intaking && !beamBreak.get())
		{
			intakeVoltage.set(1);
		}
		// Tolerance may be too low, will be tuneduntil its realistic
		// only outtaking when beamBreak is procced may mean not enough distance
		// on the gear
		else if (currentState == State.Placing && beamBreak.get())
		{
			intakeVoltage.set(-1);
		} else
		{
			intakeVoltage.set(0);
		}

	}

}
