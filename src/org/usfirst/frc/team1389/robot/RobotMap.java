package org.usfirst.frc.team1389.robot;

import com.team1389.hardware.registry.port_types.CAN;
import com.team1389.hardware.registry.port_types.DIO;
import com.team1389.hardware.registry.port_types.PCM;
import com.team1389.hardware.registry.port_types.PWM;
import com.team1389.hardware.registry.port_types.SPIPort;

import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * <p>
 * <b>Conventions</b>: <br>
 * For I/O ports, the naming convention is <em>type_ALL_CAPS_IDENTIFIER</em>.
 * for example, a talon port might be named can_LEFT_MOTOR_A. Possible port
 * types and identifiers are CAN (can), Analog (anlg), PWM (pwm), USB (usb), PCM
 * (pcm), DIO (dio), etc
 * <p>
 * Inputs and Outputs may be inverted. The inversions in this map should only
 * relate to the physical configuration of the robot. A positive value should
 * cause the output to move in the most logical direction (I.e, the drive motors
 * should move forward with positive voltage values) <br>
 * the convention for inversion constants is
 * <em>inv_ASSOCIATED_IO_IDENTIFIER</em> for outputs and
 * <em>sinv_ASSOCIATED_IO_IDENTIFIER</em> for inputs.
 */

public class RobotMap
{
	// Drivetrain motors and inversions
	protected final CAN can_LEFT_FRONT_MOTOR = new CAN(3);
	protected final boolean inv_LEFT_FRONT_MOTOR = true;

	protected final CAN can_LEFT_REAR_MOTOR = new CAN(5);
	protected final boolean inv_LEFT_REAR_MOTOR = true;

	protected final CAN can_RIGHT_FRONT_MOTOR = new CAN(6);
	protected final boolean inv_RIGHT_FRONT_MOTOR = false;

	protected final CAN can_RIGHT_REAR_MOTOR = new CAN(4);
	protected final boolean inv_RIGHT_REAR_MOTOR = false;

	// Drive Train pneumatics

	protected final int mod_DRIVE_TRAIN_PCM = 1;

	protected final PCM pcm_FRONT_LEFT_PISTON_A = new PCM(7);
	protected final PCM pcm_FRONT_LEFT_PISTON_B = new PCM(0);

	protected final PCM pcm_FRONT_RIGHT_PISTON_A = new PCM(4);
	protected final PCM pcm_FRONT_RIGHT_PISTON_B = new PCM(3);

	protected final PCM pcm_REAR_LEFT_PISTON_A = new PCM(6);
	protected final PCM pcm_REAR_LEFT_PISTON_B = new PCM(1);

	protected final PCM pcm_REAR_RIGHT_PISTON_A = new PCM(5);
	protected final PCM pcm_REAR_RIGHT_PISTON_B = new PCM(2);

	// Hopper pneumatics

	protected final int mod_HOPPER_PCM = 2;

	protected final PCM pcm_DUMPER_PISTON_RIGHT_A = new PCM(3);
	protected final PCM pcm_DUMPER_PISTON_RIGHT_B = new PCM(4);

	protected final PCM pcm_DUMPER_PISTON_LEFT_A = new PCM(1);
	protected final PCM pcm_DUMPER_PISTON_LEFT_B = new PCM(6);

	// Arm motors, inversions, and sensors
	protected final CAN can_ARM_POSITION_MOTOR = new CAN(7);
	protected final boolean inv_ARM_POSITION_MOTOR = true;
	protected final boolean sinv_ARM_POSITION_MOTOR = false;

	protected final PWM pwm_GEAR_INTAKE_MOTOR = new PWM(1);
	protected final boolean inv_GEAR_INTAKE_MOTOR = false;

	protected final DIO dio_GEAR_BEAM_BREAK = new DIO(0);

	// Climber motors and inversions
	protected final PWM pwm_CLIMBER_MOTOR_A = new PWM(0);
	protected final boolean inv_CLIMBER_MOTOR_A = false;

	protected final PWM pwm_CLIMBER_MOTOR_B = new PWM(5);
	protected final boolean inv_CLIMBER_MOTOR_B = true;

	protected final PWM pwm_CLIMBER_MOTOR_C = new PWM(4);
	protected final boolean inv_CLIMBER_MOTOR_C = false;

	// Miscellaneous

	protected final CAN can_LIGHT_CONTROLLER = new CAN(8);

	protected final SPIPort spi_GYRO = new SPIPort(SPI.Port.kOnboardCS0);
}
