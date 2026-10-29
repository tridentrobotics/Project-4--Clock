package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorConstants;

public class MotorSubsystem extends SubsystemBase {
  private final WPI_TalonSRX m_motor = new WPI_TalonSRX(MotorConstants.kMotorCanId);

  public void spin() {
    m_motor.set(ControlMode.PercentOutput, MotorConstants.kMotorSpeed);
  }

  public void stop() {
    m_motor.set(ControlMode.PercentOutput, 0);
  }

  /** Spins the motor while the command is scheduled, stopping when it ends. */
  public Command spinCommand() {
    return startEnd(this::spin, this::stop);
  }
}
