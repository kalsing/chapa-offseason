/* 

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


public class ShootCommand extends Command {
  private final ShooterSubsystem shooter;

  public ShootCommand(ShooterSubsystem shooter) {
    this.shooter = shooter;
addRequirements(this.shooter);
  }


  @Override
  public void initialize() {}


  @Override
  public void execute() {
    if(IntakeSubsystem.isAtShootPosition){
      shooter.runShooter();
      }
      else if(!IntakeSubsystem.isAtShootPosition){
        shooter.hasShot();
      }
      else{
        shooter.stopShooter();
      }
    }
  

 
  @Override
  public void end(boolean interrupted) {
    shooter.stopShooter();
  }
    
  @Override
  public boolean isFinished() {
    return shooter.hasShot();
  }
}

*/