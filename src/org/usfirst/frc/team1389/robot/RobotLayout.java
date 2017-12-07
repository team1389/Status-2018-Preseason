package org.usfirst.frc.team1389.robot;

import com.team1389.hardware.inputs.hardware.PDPHardware;
import com.team1389.hardware.inputs.hardware.SpartanGyro;
import com.team1389.hardware.inputs.hardware.SwitchHardware;
import com.team1389.hardware.outputs.hardware.CANLightHardware;
import com.team1389.hardware.outputs.hardware.CANTalonHardware;
import com.team1389.hardware.outputs.hardware.DoubleSolenoidHardware;
import com.team1389.hardware.outputs.hardware.VictorHardware;
import com.team1389.hardware.registry.Registry;

public class RobotLayout extends RobotMap
{
	public Registry registry;
	public CANTalonHardware frontLeft, frontRight, rearLeft, rearRight, armPositioner;
	public VictorHardware climberA, climberB, climberC, gearIntake;
	public PDPHardware pdp;
	public DoubleSolenoidHardware driveFLPiston, driveBLPiston, driveFRPiston, driveBRPiston;
	public CANLightHardware light;
	public SpartanGyro gyro;
	public SwitchHardware gearSensor;

}
