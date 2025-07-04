package frc.robot.subsystems;

import static edu.wpi.first.units.Units.RPM;

import java.util.Currency;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Logging.EnhancedLoggers.CustomDoubleLogger;

public class ShooterSubsystem extends SubsystemBase {
   
   SparkMax motorShoot = new SparkMax(1, MotorType.kBrushless);
RelativeEncoder encoder = motorShoot.getEncoder();
double kP = 0.0005;
double kI = 0.000001;
double kD = 0.0;
SparkMaxConfig config = new SparkMaxConfig(); 
boolean atTargetPosition = false;
CustomDoubleLogger velocity;
CustomDoubleLogger targetVelocity;

public ShooterSubsystem(){
   this.config.closedLoop.pid(kP, kI, kD);
   this.motorShoot.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
   this.targetVelocity = new CustomDoubleLogger("Target Velocity");
   this.velocity = new CustomDoubleLogger("Current Velocity");
}

   public void runShooter() {  
      motorShoot.getClosedLoopController().setReference(3000, SparkBase.ControlType.kVelocity, ClosedLoopSlot.kSlot0);
  }
   public void stopShooter(){
    motorShoot.set(0);
   }

public void periodic(){
   double pos = encoder.getPosition();
   double RPM = encoder.getVelocity();
   this.setAtTargetPosition();
   this.velocity.append(RPM);
   this.targetVelocity.append(3000);
   SmartDashboard.putNumber("EncoderPosicao", pos);
   SmartDashboard.putNumber("RPM", RPM);
   SmartDashboard.putBoolean("RPM At target?", this.atTargetPosition);
}

   public void setAtTargetPosition(){
   this.atTargetPosition = encoder.getVelocity() >= 2500 && encoder.getVelocity() <= 3500;
  }

  public boolean getAtTargetPosition(){
   return this.atTargetPosition;
  }
   


public void resetEncoder() {
   encoder.setPosition(0);
}


}






