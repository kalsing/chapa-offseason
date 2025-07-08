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
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
   
   SparkMax motorShoot = new SparkMax(1, MotorType.kBrushless);
   RelativeEncoder encoderShooter = motorShoot.getEncoder();
   double kP = 0.0004;
   double kI = 0.0000001;
   double kD = 0.0;
   SparkMaxConfig config = new SparkMaxConfig(); 
   boolean atTargetPosition = false;

   public ShooterSubsystem(){
   this.config.closedLoop.pid(kP, kI, kD);
   this.motorShoot.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
}

   public void runShooter() {  
      motorShoot.getClosedLoopController().setReference(3000, SparkBase.ControlType.kVelocity, ClosedLoopSlot.kSlot0);
  }
   public void stopShooter(){
      motorShoot.set(0);
   }

public void periodic(){
   double pos = encoderShooter.getPosition();
   double RPM = encoderShooter.getVelocity();
   this.setAtTargetPosition();
   SmartDashboard.putNumber("EncoderPosicao", pos);
   SmartDashboard.putNumber("RPM", RPM);
   SmartDashboard.putBoolean("RPM At target?", this.atTargetPosition);
}

   public void setAtTargetPosition(){
      this.atTargetPosition = encoderShooter.getVelocity() >= 2950 && encoderShooter.getVelocity() <= 3500;
  }

  public boolean getAtTargetPosition(){
      return this.atTargetPosition;
  }
   


public void resetEncoder() {
   encoderShooter.setPosition(0);
}


}






