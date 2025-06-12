package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RobotContainer {
  
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();


  private final Joystick joystick = new Joystick(0);
  private final XboxController controller = new XboxController(0); 


  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    new Trigger(controller::getAButton)
    .onTrue(Commands.sequence(
    Commands.run(() -> intakeSubsystem.runIntake(), intakeSubsystem)
    .until(() -> intakeSubsystem.isLimitReached())
    .andThen(() -> intakeSubsystem.stopIntake()),
    
   Commands.run(() -> shooterSubsystem.runShooter(), shooterSubsystem),

   Commands.waitSeconds(1),
   new InstantCommand(() -> shooterSubsystem.stopShooter())));

   new Trigger(controller::getAButton)
   .onTrue(new InstantCommand(() -> {
    shooterSubsystem.stopShooter();
    intakeSubsystem.stopIntake();
   }));





  }
}