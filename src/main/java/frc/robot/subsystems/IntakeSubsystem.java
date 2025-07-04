package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

 
  private final SparkMax motorIntake = new SparkMax(2, MotorType.kBrushless);
  private final DigitalInput buttonLimit = new DigitalInput(0);


 
  public static boolean isAtShootPosition = false;

  public IntakeSubsystem() {

  }

  
  public void runIntake() {
    motorIntake.set(0.1);
  }

  
  public void stopIntake() {
    motorIntake.set(0);
  }

  
  public boolean isLimitReached() {
    return buttonLimit.get();
  }


  public static boolean isAtShootPosition() {
    return isAtShootPosition;
  }

  @Override
  public void periodic() {

    SmartDashboard.putBoolean("Limit Switch", buttonLimit.get());
   
    if (buttonLimit.get()) {
      isAtShootPosition = true;
    }
  }
}