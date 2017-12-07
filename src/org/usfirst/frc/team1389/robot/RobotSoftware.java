package org.usfirst.frc.team1389.robot;

import java.util.function.Function;

import com.team1389.hardware.inputs.software.AngleIn;
import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.inputs.software.PositionEncoderIn;
import com.team1389.hardware.inputs.software.RangeIn;
import com.team1389.hardware.outputs.software.DigitalOut;
import com.team1389.hardware.outputs.software.PercentOut;
import com.team1389.hardware.value_types.Percent;
import com.team1389.hardware.value_types.Position;
import com.team1389.hardware.value_types.Speed;
import com.team1389.hardware.value_types.Value;
import com.team1389.system.drive.FourDriveOut;

public class RobotSoftware extends RobotHardware
{
	private static RobotSoftware INSTANCE = new RobotSoftware();
	public AngleIn<Position> gyroInput;
	public DigitalOut pistons;
	public FourDriveOut<Percent> voltageDrive;
	public FourDriveOut<Percent> compensatedDrive;
	public AngleIn<Position> armAngle;
	public AngleIn<Position> armAngleNoOffset;
	public AngleIn<Speed> armVel;
	public RangeIn<Value> gearIntakeCurrent;
	public RangeIn<Position> flPos, frPos;
	public DigitalIn timeRunning;
	public RangeIn<Value> flCurrent, frCurrent, blCurrent, brCurrent;
	public RangeIn<Value> armCurrent;
	public DigitalIn gearBeamBreak;
	public PercentOut climberVoltage;

	public static RobotSoftware getInstance()
	{
		return INSTANCE;
	}

	public RobotSoftware()
	{
		voltageDrive = new FourDriveOut<>(frontLeft.getVoltageOutput(), frontRight.getVoltageOutput(),
				rearLeft.getVoltageOutput(), rearRight.getVoltageOutput());
		compensatedDrive = new FourDriveOut<>(frontLeft.getCompensatedVoltageOut(),
				frontRight.getCompensatedVoltageOut(), rearLeft.getCompensatedVoltageOut(),
				rearRight.getCompensatedVoltageOut());
		
		gyroInput = gyro.getAngleInput();

		pistons = driveFLPiston.getDigitalOut().addFollowers(driveFRPiston.getDigitalOut(), driveBLPiston.getDigitalOut(),
				driveBRPiston.getDigitalOut());

		armAngleNoOffset = armPositioner.getAbsoluteIn().mapToAngle(Position.class).invert()
				.scale(RobotConstants.armSprocketRatio);
		
		armAngle = armAngleNoOffset.copy().offset(-RobotConstants.armOffset);
		armVel = armPositioner.getSpeedInput().scale(RobotConstants.armSprocketRatio).mapToAngle(Speed.class);

		gearIntakeCurrent = pdp.getCurrentIn(13);
		gearBeamBreak = gearSensor.getSwitchInput();
		flCurrent = frontLeft.getCurrentIn();
		frCurrent = frontRight.getCurrentIn();
		blCurrent = rearLeft.getCurrentIn();
		brCurrent = rearRight.getCurrentIn();
		armCurrent = armPositioner.getCurrentIn();

		climberVoltage = climberA.getVoltageOutput().addFollowers(climberB.getVoltageOutput())
				.addFollowers(climberC.getVoltageOutput());
	}

}

