package org.usfirst.frc.team1389.robot;

import edu.wpi.first.wpilibj.Preferences;

public class RobotConstants
{
	public static final double INCHES_TO_METERS = .0254;
	// was 8
	public static final double WheelDiameter = 4; // in

	public static final double armOffset = Preferences.getInstance().getDouble("offset", 0.0);
	public static final double armSprocketRatio = 12.0 / 22.0;

	/**
	 * constants for odometry calculations
	 */
	public static final double TrackWidth = 22; // in
	public static final double TrackLength = 23;
	public static final double TrackScrub = 1;

	/**
	 * constants for motion profiling
	 */
	public static final double MaxVelocity = 203.4; // in/s;
	public static final double MaxAcceleration = 100; // ft/s^2
	public static final double MaxDeceleration = 100; // ft/s^2

}
