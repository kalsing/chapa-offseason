package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import frc.robot.subsystems.ClimberMaster;
import frc.robot.subsystems.ClimberIntake;

public class RobotContainer {

    private final ClimberMaster climberMaster = new ClimberMaster();
    private final ClimberIntake climberIntake = new ClimberIntake();

    private final Joystick joystick = new Joystick(0);
    private final XboxController controller = new XboxController(1);

    public RobotContainer() {
        configureBindings();
    }
    private void configureBindings() {
        new Trigger(controller::getAButton)
            .onTrue(Commands.sequence(
                Commands.run(() -> climberMaster.runFirstStageClimb(), climberMaster)
                    .until(() -> climberMaster.getIsAtFirstStageTarget()),
                Commands.runOnce(() -> climberMaster.stopClimber(), climberMaster),
                Commands.print("terminou o 1"),
    
                Commands.run(() -> climberIntake.runClimberIntake(), climberIntake)
                    .until(() -> climberIntake.getIsAtIdealCondition()),
                Commands.runOnce(() -> climberIntake.stopClimberIntake(), climberIntake),
                Commands.print("terminou o 2"),
    
                Commands.run(() -> climberMaster.runSecondStageClimb(), climberMaster)
                    .until(() -> climberMaster.getIsAtSecondStageTarget()),
                Commands.runOnce(() -> climberMaster.stopClimber(), climberMaster),
                Commands.print("terminou o 3")
            ));
    
        new Trigger(controller::getBButton)
            .onTrue(new InstantCommand(() -> {
                CommandScheduler.getInstance().cancelAll();
                climberIntake.stopClimberIntake();
                climberMaster.stopClimber();
            }));

        new Trigger(controller::getXButton)
            .onTrue(new SequentialCommandGroup(new InstantCommand(() -> climberIntake.resetEncoder()), 
                new InstantCommand(() -> climberMaster.resetEncoder(), climberMaster)));
    }
}
