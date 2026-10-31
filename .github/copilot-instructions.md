# Copilot Instructions

This is a WPILib command-based Java FRC robot project (Trident Robotics, "Project 4 – Clock")

## Architecture

- Use the command-based framework (`edu.wpi.first.wpilibj2.command`). Keep `Robot.java` minimal: it only runs the scheduler.
- Declare subsystems, controllers and button bindings in `RobotContainer.java`; put bindings in `configureBindings()`.
- Subsystems live in `src/main/java/frc/robot/subsystems/`. Expose actions as `Command` factory methods (e.g. `runOnce`, `run`, `startEnd`) rather than public setters where practical.
- Standalone commands and autonomous routines live in `src/main/java/frc/robot/commands/`.
- Driver input uses `CommandXboxController` on port `OperatorConstants.kDriverControllerPort`.

## Constants

- All numeric/boolean constants go in `Constants.java` (or a dedicated `*Constants` class), grouped in a static inner class per subsystem, as `public static final`.
- No functional code in constants classes.

## Style

- Follow the WPILib template conventions: `m_` prefix for member fields, `k` prefix for constants, 2-space indentation.
- Keep the WPILib license header on source files.
- Add Javadoc to public classes and methods.
- Don't edit `Main.java`.

## Build and test

- Build with `./gradlew build`; simulate with `./gradlew simulateJava`; deploy with `./gradlew deploy`.
- Prefer verifying in simulation before suggesting code that targets hardware.

## Safety

- Never run motors without a command that stops them when it ends or is interrupted (use `startEnd` or an `end()` that stops output).
- Keep motor speeds and current limits in constants, and keep them conservative by default.
