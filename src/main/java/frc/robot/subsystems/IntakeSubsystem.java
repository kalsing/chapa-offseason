package frc.robot.subsystems;


import static edu.wpi.first.units.Units.RPM;

import java.nio.file.attribute.PosixFileAttributeView;

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


  double kP1 = 0.00002;
  double kI1 = 0.000001;
  double kD1 = 0.0;

  
  public boolean atTargetRPM = false;
  public boolean IsLimitReached = false;
  public boolean isAtIdealCollectState = false;
  
  public IntakeSubsystem() {
   this.config.closedLoop
   .p(kP1, ClosedLoopSlot.kSlot1)
   .i(kI1, ClosedLoopSlot.kSlot1)
   .d(kD1, ClosedLoopSlot.kSlot1);

   
   this.motorIntake.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
  }

  
  public void runIntake() {
    motorIntake.getClosedLoopController().setReference(1800, SparkBase.ControlType.kVelocity, ClosedLoopSlot.kSlot1);
  }

  public void stopIntake() {
    motorIntake.set(0);
  }

  
  public void setIsLimitReached() {
    double POSIntake = motorIntake.getEncoder().getPosition();
    IsLimitReached  = POSIntake >= 95 && POSIntake <=105;
  }
  public boolean getIsLimitReached(){
    return IsLimitReached;
    }

    public void setIsAtIdealCollectState(){
    isAtIdealCollectState = getAtTargetRPM() && getIsLimitReached();
    }

    public boolean getIsAtIdealCollectState(){
      return isAtIdealCollectState;
    }


  public void setAtTargetRPM(){
    double RPMIntake = encoderIntake.getVelocity();
    atTargetRPM  = RPMIntake >= 1700 && RPMIntake <=1900;
  }

  public boolean getAtTargetRPM(){
  return atTargetRPM;
  }

  

  public void resetEncoder() {
  encoderIntake.setPosition(0);
  }


  @Override
  public void periodic() {
    setAtTargetRPM();
    setIsAtIdealCollectState();
    setIsLimitReached();
    SmartDashboard.putNumber("CurrentRPM", encoderIntake.getVelocity());
    SmartDashboard.putNumber("position", encoderIntake.getPosition());
    SmartDashboard.putBoolean("is at position", IsLimitReached);
    SmartDashboard.putBoolean("ideal?", isAtIdealCollectState);
    SmartDashboard.putBoolean("Target RPM?", atTargetRPM);
    }
  }
