# Clock – Project Instructions

A WPILib command-based Java robot project (FRC team **4296**, season **2026**). The project lives in the [Clock/](Clock/) folder. At the moment it is the stock WPILib command-based template: the structure is in place, but no clock-specific logic has been written yet (see [Where to add your code](#where-to-add-your-code)).

## Requirements

- **WPILib 2026 installation** (includes the matching JDK 17, VS Code, and GradleRIO tooling)
- Java 17 (`sourceCompatibility`/`targetCompatibility` in [build.gradle](Clock/build.gradle))
- Gradle 8.11 – supplied by the wrapper, no separate install needed
- For deploying: a roboRIO reachable over USB, Ethernet, or the robot network
- For driving: an Xbox-compatible controller on USB port 0 of the Driver Station

## Opening the project

1. Install WPILib 2026 and launch its bundled VS Code.
2. Open the **`Clock`** folder (not the repo root) so that `build.gradle` is at the workspace root.
3. Let the Java extension finish importing the Gradle project.

## Build, simulate, deploy

Run these from the `Clock` folder. In WPILib VS Code you can also press `Ctrl+Shift+P` and search for `WPILib: ...`.

| Goal | Command (Windows) | WPILib VS Code command |
| --- | --- | --- |
| Build | `.\gradlew build` | `WPILib: Build Robot Code` |
| Simulate on desktop | `.\gradlew simulateJava` | `WPILib: Simulate Robot Code` |
| Deploy to roboRIO | `.\gradlew deploy` | `WPILib: Deploy Robot Code` |
| Run unit tests | `.\gradlew test` | – |
| Clean outputs | `.\gradlew clean` | – |

Notes:

- The team number (4296) is read from [.wpilib/wpilib_preferences.json](Clock/.wpilib/wpilib_preferences.json). Change it there if needed.
- Simulation opens the WPILib Sim GUI and the simulated Driver Station (enabled in `build.gradle`).
- Everything under [src/main/deploy/](Clock/src/main/deploy/) is copied to `/home/lvuser/deploy` on the roboRIO.
- The first build needs internet access to download GradleRIO dependencies; afterward it works offline.

## Running on the robot

1. Power the robot and connect your laptop to it.
2. Run **Deploy** (above). The code restarts on the roboRIO when it finishes.
3. Open the FRC Driver Station, confirm *Communications* and *Robot Code* indicators are green, and plug in the controller.
4. Choose a mode (Teleoperated, Autonomous, Test) and press **Enable**.

## Project layout

```
Clock/
├── build.gradle                  Build, deploy, and dependency configuration
├── settings.gradle               Gradle settings
├── vendordeps/                   Vendor libraries (currently WPILibNewCommands only)
├── .wpilib/wpilib_preferences.json   Team number, language, year
└── src/main/java/frc/robot/
    ├── Main.java                 Entry point – do not modify
    ├── Robot.java                Lifecycle (init/periodic per mode); runs the command scheduler
    ├── RobotContainer.java       Declares subsystems, controllers, and trigger bindings
    ├── Constants.java            Robot-wide constants (e.g. controller port = 0)
    ├── commands/
    │   ├── Autos.java            Autonomous command factories
    │   └── ExampleCommand.java   Placeholder command
    └── subsystems/
        └── ExampleSubsystem.java Placeholder subsystem
```

## Current behavior

- **Driver controller:** `CommandXboxController` on port 0. Holding **B** runs `ExampleSubsystem.exampleMethodCommand()` (currently a no-op).
- **Autonomous:** `Autos.exampleAuto(...)` is returned by `RobotContainer.getAutonomousCommand()`.
- **Triggers:** `ExampleCommand` is scheduled when `ExampleSubsystem.exampleCondition()` becomes true (it always returns `false` today).

## Where to add your code

1. **New hardware or mechanism** → create a class in `subsystems/` extending `SubsystemBase`, and put motor/sensor setup and command factories in it. Replace or rename `ExampleSubsystem`.
2. **Behavior** → write commands as factory methods on the subsystem, or as classes in `commands/`.
3. **Wire-up** → instantiate the subsystem in `RobotContainer` and bind controller buttons in `configureBindings()`.
4. **Numbers** (ports, CAN IDs, speeds) → add them to `Constants.java` instead of hard-coding.
5. **Autonomous** → build the routine in `Autos.java` and return it from `getAutonomousCommand()`.
6. **Vendor libraries** (e.g. CTRE, REV) → install via `WPILib: Manage Vendor Libraries`, which adds a JSON file to `vendordeps/`.

## Troubleshooting

- **Gradle can't resolve `edu.wpi.first.GradleRIO`:** check internet access and that WPILib 2026 is installed; the plugin version is `2026.2.1`.
- **"Team number not found":** verify `teamNumber` in `wpilib_preferences.json`.
- **Deploy can't find the roboRIO:** confirm the connection, that the roboRIO image/firmware matches the 2026 season, and that the Driver Station shows communications.
- **Stale build errors:** run `.\gradlew clean build`.

## Repository notes

- Build output (`Clock/build/`, `Clock/bin/`, `Clock/.gradle/`) should not be committed; see [Clock/.gitignore](Clock/.gitignore).
- WPILib code is covered by the license in [Clock/WPILib-License.md](Clock/WPILib-License.md).
