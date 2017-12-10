package org.usfirst.frc.team1389.systems;

import com.team1389.command_framework.CommandUtil;
import com.team1389.command_framework.command_base.Command;
import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.outputs.software.DigitalOut;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;


public class HopperSystem extends Subsystem
{
	DigitalIn toggleHopper;
	DigitalOut solenoids;
	private HopperState state;

	/**
	 * 
	 * @param toggleHopper
	 *            assumed to be latched
	 * @param solenoids
	 *            all streams representing hopper pistons
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
		state.retracted = ((toggleHopper.get() ^ state.retracted) ? true : false);
		solenoids.set(state.retracted);
	}

	/**
	 * represents State, all changes to Piston orientation are done through
	 * HopperState. Note that stored boolean is whether the pistons are
	 * retracted.
	 * 
	 * @author Quunii
	 *
	 */
	private enum HopperState
	{
		UP(true), DOWN(false);
		private boolean retracted;

		private HopperState(boolean retracted)
		{
			this.retracted = retracted;
		}
	}

	// TODO: Decide whether to keep these commands seperate or not, as they
	// could be one command
	/**
	 * 
	 * @return Command that retracts pistons and dumps
	 */
	public Command getDumpCommand()
	{
		return CommandUtil.createCommand(() ->
		{
			state.retracted = true;
			solenoids.set(state.retracted);
		});
	}

	/**
	 * 
	 * @return Command that extends pistons and resets hopper to allow taking in
	 *         balls
	 */
	public Command getResetHopperCommand()
	{
		return CommandUtil.createCommand(() ->
		{
			state.retracted = false;
			solenoids.set(state.retracted);
		});
	}
}
