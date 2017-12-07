package org.usfirst.frc.team1389.systems;

import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.outputs.software.DigitalOut;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;

/*
 * need one button for toggling hopper, one stream representing both solenoids
 * probably want states so that auto can figure out what to do 
 * Question: Do we want related commands in here, or in another AutoCommands class?
 * In terms of ease of use during competition, having all commands in AutoCommands class seems nice, but would be a super bloated class
 * for now, going to have command(s) in here, can change this later
 * does true on a digitalOut off a Solenoid mean extended or retracted? For now, working off assumption that true = extended
 */
public class HopperSystem extends Subsystem
{
	DigitalIn toggleHopper;
	DigitalOut solenoids;
	private HopperState state;

	/**
	 * 
	 * @param toggleHopper assumed to be latched
	 * @param solenoids all streams representing hopper pistons
	 */
	public HopperSystem(DigitalIn toggleHopper, DigitalOut solenoids)
	{
		this.toggleHopper = toggleHopper;
		this.solenoids = solenoids;
	}

	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> stem)
	{
		return stem.put(toggleHopper.getWatchable("hopper button"));
	}

	@Override
	public String getName()
	{
		return "Hopper";
	}

	/**
	 * assumes hopper starts down
	 */
	@Override
	public void init()
	{
		state = HopperState.DOWN;
		
	}

	@Override
	public void update()
	{
		
		solenoids.set(state.extended);

	}

	/**
	 * represents State, all changes to Piston orientation should be done
	 * through HopperState
	 * 
	 * @author Quunii
	 *
	 */
	private enum HopperState
	{
		UP(false), DOWN(true);
		private boolean extended;

		private HopperState(boolean extended)
		{
			this.extended = extended;
		}
	}
}
