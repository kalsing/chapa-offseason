package frc.robot.subsystems;


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
  double kP = 0.0;
  double kI = 0.0;
  double kD = 0.0;

  public boolean isAtIdealCollectState = false;

  public IntakeSubsystem() {
   this.config.closedLoop.pid(kP, kI, kD);
   this.motorIntake.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
  }

  
  public void runIntake() {
    motorIntake.getClosedLoopController().setReference(1800, SparkBase.ControlType.kPosition, ClosedLoopSlot.kSlot0);


  }

  
  public void stopIntake() {
    motorIntake.set(0);
  }

  
  public boolean isLimitReached() {
    return buttonLimit.get();
  }


  public static boolean isAtIdealCollectState() {
    return isAtIdealCollectState();
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
