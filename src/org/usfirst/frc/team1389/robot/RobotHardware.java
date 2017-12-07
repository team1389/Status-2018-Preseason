package org.usfirst.frc.team1389.robot;

import com.team1389.hardware.inputs.hardware.PDPHardware;
import com.team1389.hardware.inputs.hardware.SpartanGyro;
import com.team1389.hardware.inputs.hardware.SwitchHardware;
import com.team1389.hardware.outputs.hardware.CANLightHardware;
import com.team1389.hardware.outputs.hardware.CANTalonHardware;
import com.team1389.hardware.outputs.hardware.DoubleSolenoidHardware;
import com.team1389.hardware.outputs.hardware.VictorHardware;
import com.team1389.hardware.registry.Registry;
import com.team1389.hardware.registry.port_types.CAN;

public class RobotHardware extends RobotLayout
{
	protected RobotHardware()
	{
		registry = new Registry();
		pdp = new PDPHardware(new CAN(0), registry);
		gyro = new SpartanGyro(spi_GYRO, registry);
		light = new CANLightHardware(can_LIGHT_CONTROLLER, registry);
		initDrivetrain();
		initArm();
		initHopper();
		initClimber();
	}

	private void initDrivetrain()
	{
		// motors
		frontLeft = new CANTalonHardware(inv_LEFT_FRONT_MOTOR, can_LEFT_FRONT_MOTOR, registry);
		frontRight = new CANTalonHardware(inv_RIGHT_FRONT_MOTOR, can_RIGHT_FRONT_MOTOR, registry);
		rearLeft = new CANTalonHardware(inv_LEFT_REAR_MOTOR, can_LEFT_REAR_MOTOR, registry);
		rearRight = new CANTalonHardware(inv_RIGHT_REAR_MOTOR, can_RIGHT_REAR_MOTOR, registry);

		// pneumatics
		driveFLPiston = new DoubleSolenoidHardware(pcm_FRONT_LEFT_PISTON_A, pcm_FRONT_LEFT_PISTON_B, registry);
		driveFRPiston = new DoubleSolenoidHardware(pcm_FRONT_RIGHT_PISTON_A, pcm_FRONT_RIGHT_PISTON_B, registry);
		driveBLPiston = new DoubleSolenoidHardware(pcm_REAR_LEFT_PISTON_A, pcm_REAR_LEFT_PISTON_B, registry);
		driveBRPiston = new DoubleSolenoidHardware(pcm_REAR_RIGHT_PISTON_A, pcm_REAR_RIGHT_PISTON_B, registry);

	}

	private void initArm()
	{
		armPositioner = new CANTalonHardware(inv_ARM_POSITION_MOTOR, sinv_ARM_POSITION_MOTOR, can_ARM_POSITION_MOTOR,
				registry);
		gearIntake = new VictorHardware(inv_GEAR_INTAKE_MOTOR, pwm_GEAR_INTAKE_MOTOR, registry);
		gearSensor = new SwitchHardware(dio_GEAR_BEAM_BREAK, registry);

	}
	
	private void initHopper()
	{
			dumperRPiston = new DoubleSolenoidHardware(mod_HOPPER_PCM, pcm_DUMPER_PISTON_RIGHT_A,
					pcm_DUMPER_PISTON_RIGHT_B, registry);
			dumperLPiston = new DoubleSolenoidHardware(mod_HOPPER_PCM, pcm_DUMPER_PISTON_LEFT_A,
					pcm_DUMPER_PISTON_LEFT_B, registry);
		
	}

	private void initClimber()
	{
		climberA = new VictorHardware(inv_CLIMBER_MOTOR_A, pwm_CLIMBER_MOTOR_A, registry);
		climberB = new VictorHardware(inv_CLIMBER_MOTOR_B, pwm_CLIMBER_MOTOR_B, registry);
		climberC = new VictorHardware(inv_CLIMBER_MOTOR_C, pwm_CLIMBER_MOTOR_C, registry);
	}
}
