// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class ClockConstants {
    /** CAN ID of the clock motor's Talon FX. */
    public static final int kMotorCanId = 2;

    /**
     * Motor rotations per step. 0.25 is 90 degrees of the motor shaft (before any gearbox); the
     * sign sets the direction.
     */
    public static final double kStepRotations = -0.25;

    /**
     * Motor rotations per second-hand tick: 6 degrees, so a full turn takes 60 seconds. The sign
     * sets the direction.
     */
    public static final double kSecondHandStepRotations = -1.0 / 60.0;

    /** Seconds to wait between steps. */
    public static final double kPauseSeconds = 15.0;

    /** Position error, in motor rotations, under which a step counts as finished. */
    public static final double kToleranceRotations = 0.01;

    /** Longest a step may take before the pause starts anyway, in case it never settles. */
    public static final double kStepTimeoutSeconds = 2.0;

    /** Fastest the motor turns during a step, in rotations per second. Lower is slower. */
    public static final double kCruiseRotationsPerSecond = 0.5;

    /** How quickly the motor speeds up and slows down, in rotations per second squared. */
    public static final double kAccelRotationsPerSecondSq = 1.0;

    /** Position loop proportional gain, in volts per rotation of error. Tune on the robot. */
    public static final double kP = 24.0;
  }
}
