package frc.robot.subsystems;


import static edu.wpi.first.units.Units.RPM;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

 
  private final SparkMax motorIntake = new SparkMax(2, MotorType.kBrushless);
  private final DigitalInput buttonLimit = new DigitalInput(0);
  RelativeEncoder encoderIntake = motorIntake.getEncoder();
  SparkMaxConfig config = new SparkMaxConfig();

  private double targetPosition;
  private double targetRPM;


  double kP = 0.0;
  double kI = 0.0;
  double kD = 0.0;

  double kP1 = 0.01;
  double kI1 = 0.0;
  double kD1 = 0.0;
  double RPMIntake = encoderIntake.getVelocity();
  double IntakePOS = encoderIntake.getPosition();


  public boolean isAtIdealCollectState = false;

  public IntakeSubsystem() {
   this.config.closedLoop
   .p(kP1, ClosedLoopSlot.kSlot1)
   .i(kI1, ClosedLoopSlot.kSlot1)
   .d(kD1, ClosedLoopSlot.kSlot1);

   
   this.motorIntake.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
  }

  
  public void runIntake() {
    targetRPM = 1800;
    motorIntake.getClosedLoopController().setReference(targetRPM, SparkBase.ControlType.kPosition, ClosedLoopSlot.kSlot1);
  }

  public void runDefault() {
    targetPosition = 0;
    motorIntake.getClosedLoopController().setReference(targetPosition, SparkBase.ControlType.kPosition, ClosedLoopSlot.kSlot1);
  }

  public void stopIntake() {
    motorIntake.set(0);
  }

  
  public boolean isLimitReached() {
    return buttonLimit.get();
  }

  public boolean isAtIdealCollectState() {
    if
      (RPMIntake > 1760 && RPMIntake <1840){
      return true;
    } else {
      return false;
  }
}
public void resetEncoder() {
  encoderIntake.setPosition(0);
}

  @Override
  public void periodic() {
    double RPMIntake = encoderIntake.getVelocity();
    double IntakePOS = encoderIntake.getPosition();

    SmartDashboard.putBoolean("Limit Switch", buttonLimit.get());
    SmartDashboard.putNumber("RPM Intake", RPMIntake);
    SmartDashboard.putNumber("Position Intake", IntakePOS);
    SmartDashboard.putBoolean("Pode coletar?", isAtIdealCollectState);
    }
  }
