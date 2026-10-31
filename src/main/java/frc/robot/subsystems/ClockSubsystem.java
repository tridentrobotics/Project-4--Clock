// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClockConstants;

/** Drives the clock motor, which can be toggled on and off. */
public class ClockSubsystem extends SubsystemBase {
  private final TalonFX m_motor = new TalonFX(ClockConstants.kMotorCanId);
  private boolean m_running = false;

  /**
   * Toggles the motor between running at {@link ClockConstants#kRunSpeed} and stopped.
   *
   * @return a command that flips the on/off state
   */
  public Command toggleCommand() {
    return runOnce(() -> m_running = !m_running);
  }

  @Override
  public void periodic() {
    // Only drive while enabled, and always stop when disabled. Turn the toggle off too so the
    // motor doesn't start spinning by itself on the next enable.
    if (DriverStation.isEnabled()) {
      m_motor.set(m_running ? ClockConstants.kRunSpeed : 0.0);
    } else {
      m_running = false;
      m_motor.stopMotor();
    }
  }
}
