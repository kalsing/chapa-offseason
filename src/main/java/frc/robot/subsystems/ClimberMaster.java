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

  public class ClimberMaster extends SubsystemBase {

 
  private final SparkMax motorClimber = new SparkMax(2, MotorType.kBrushless);
  RelativeEncoder encoderIntake = motorClimber.getEncoder();
  SparkMaxConfig config = new SparkMaxConfig();


  double kP1 = 0.00002;
  double kI1 = 0.000001;
  double kD1 = 0.0;

  public boolean IsAtFirstStageTarget = false;
  public boolean IsAtSecondStageTarget = false;
  
  public ClimberMaster() {
   this.config.closedLoop
   .p(kP1, ClosedLoopSlot.kSlot1)
   .i(kI1, ClosedLoopSlot.kSlot1)
   .d(kD1, ClosedLoopSlot.kSlot1);
   
   this.motorClimber.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
  }

  
  public void runFirstStageClimb() {
    motorClimber.getClosedLoopController().setReference(90, SparkBase.ControlType.kPosition, ClosedLoopSlot.kSlot1);
  }

  public void runSecondStageClimb() {
    motorClimber.getClosedLoopController().setReference(180, SparkBase.ControlType.kPosition, ClosedLoopSlot.kSlot1);
  }

  public void setIsAtFirstStageTarget() {
    double POSIntake = motorClimber.getEncoder().getPosition();
    IsAtFirstStageTarget  = POSIntake >= 89 && POSIntake <=91;
  }
  public boolean getIsAtFirstStageTarget(){
    return IsAtFirstStageTarget;
    }

    public void setIsAtSecondStageTarget() {
      double POSIntake = motorClimber.getEncoder().getPosition();
      IsAtSecondStageTarget  = POSIntake >= 179 && POSIntake <=181;
    }
    public boolean getIsAtSecondStageTarget(){
      return IsAtFirstStageTarget;
      }

  public void stopClimber() {
    motorClimber.set(0);
  }

  public void resetEncoder() {
  encoderIntake.setPosition(0);
  }


  @Override
  public void periodic() {
    setIsAtFirstStageTarget();
    setIsAtSecondStageTarget();
    SmartDashboard.putNumber("CurrentRPM", encoderIntake.getVelocity());
    SmartDashboard.putNumber("position", encoderIntake.getPosition());
    SmartDashboard.putBoolean("1st Stage?", IsAtFirstStageTarget);
    SmartDashboard.putBoolean("2nd Stage?", IsAtSecondStageTarget);
    }
  }
