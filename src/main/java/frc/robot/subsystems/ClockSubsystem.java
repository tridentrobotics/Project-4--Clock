// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClockConstants;

/** Drives the clock motor in fixed-angle steps using the Talon FX's built-in encoder. */
public class ClockSubsystem extends SubsystemBase {
  private final TalonFX m_motor = new TalonFX(ClockConstants.kMotorCanId);
  private final MotionMagicVoltage m_positionRequest = new MotionMagicVoltage(0.0);

  /** Target position in motor rotations. Tracks the encoder while disabled. */
  private double m_targetRotations = 0.0;

  public ClockSubsystem() {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.Slot0.kP = ClockConstants.kP;
    config.MotionMagic.MotionMagicCruiseVelocity = ClockConstants.kCruiseRotationsPerSecond;
    config.MotionMagic.MotionMagicAcceleration = ClockConstants.kAccelRotationsPerSecondSq;
    config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    m_motor.getConfigurator().apply(config);
  }

  private double getRotations() {
    return m_motor.getPosition().getValueAsDouble();
  }

  private boolean atTarget() {
    return Math.abs(getRotations() - m_targetRotations) < ClockConstants.kToleranceRotations;
  }

  /**
   * Turns the motor {@link ClockConstants#kStepRotations}, waits {@link
   * ClockConstants#kPauseSeconds}, and repeats until the command is cancelled (e.g. the button is
   * released). The motor holds its position between and after steps.
   *
   * @return a command that steps the motor repeatedly
   */
  public Command stepCommand() {
    return Commands.repeatingSequence(
        runOnce(() -> m_targetRotations += ClockConstants.kStepRotations),
        Commands.waitUntil(this::atTarget).withTimeout(ClockConstants.kStepTimeoutSeconds),
        Commands.waitSeconds(ClockConstants.kPauseSeconds));
  }

  /**
   * Ticks the motor {@link ClockConstants#kSecondHandStepRotations} once per second, like a second
   * hand, until the command is cancelled. The first tick happens immediately.
   *
   * @return a command that ticks the motor once per second
   */
  public Command secondHandCommand() {
    Timer timer = new Timer();
    return startRun(
        () -> {
          timer.restart();
          m_targetRotations += ClockConstants.kSecondHandStepRotations;
        },
        () -> {
          // advanceIfElapsed keeps the leftover time, so ticks don't drift over a long hold.
          if (timer.advanceIfElapsed(1.0)) {
            m_targetRotations += ClockConstants.kSecondHandStepRotations;
          }
        });
  }

  @Override
  public void periodic() {
    // Only drive while enabled. While disabled, follow the encoder so the motor holds its current
    // position (rather than snapping back to an old target) when re-enabled.
    if (DriverStation.isEnabled()) {
      m_motor.setControl(m_positionRequest.withPosition(m_targetRotations));
    } else {
      m_targetRotations = getRotations();
      m_motor.stopMotor();
    }
  }
}
