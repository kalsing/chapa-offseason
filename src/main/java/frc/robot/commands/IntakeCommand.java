

/* 
package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {
  private final IntakeSubsystem intake;
  private final XboxController controller;
  private boolean isAtShootPosition = false;

  public IntakeCommand(IntakeSubsystem intake, XboxController controller) {
    this.intake = intake;
    this.controller = controller;
    addRequirements(intake);
  }

  @Override
  public void execute() {
    if (controller.getAButton()) {
      intake.runIntake();
    } else if (intake.isLimitReached()) {
      isAtShootPosition = true;
      intake.stopIntake();
    } else {
      intake.stopIntake();
    }
  }

  @Override
  public void end(boolean interrupted) {
    intake.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return isAtShootPosition;
  }
}

*/