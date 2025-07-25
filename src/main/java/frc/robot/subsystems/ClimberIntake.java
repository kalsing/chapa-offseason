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

public class ClimberIntake extends SubsystemBase {
   
   SparkMax motorClimberIntake = new SparkMax(1, MotorType.kBrushless);
   RelativeEncoder encoderClimberIntake = motorClimberIntake.getEncoder();
   private final DigitalInput IntakeCage = new DigitalInput(0);
   double kP = 0.0004;
   double kI = 0.0000001;
   double kD = 0.0;
   SparkMaxConfig config = new SparkMaxConfig(); 
   boolean atTargetRPM = false;

   public ClimberIntake(){
   this.config.closedLoop.pid(kP, kI, kD);
   this.motorClimberIntake.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
}

   public void runClimberIntake() {  
      motorClimberIntake.getClosedLoopController().setReference(3000, SparkBase.ControlType.kVelocity, ClosedLoopSlot.kSlot0);
  }
   public void stopClimberIntake(){
      motorClimberIntake.set(0);
   }

public void periodic(){
   setAtTargetRPM();
   SmartDashboard.putNumber("Current RPM", encoderClimberIntake.getVelocity());
   SmartDashboard.putBoolean("Target RPM Reached?", atTargetRPM);
}

   public void setAtTargetRPM(){
      this.atTargetRPM = encoderClimberIntake.getVelocity() >= 2900 && encoderClimberIntake.getVelocity() <= 3100;
  }

  public boolean getAtTargetRPM(){
      return this.atTargetRPM;
  }

  public boolean getIntakeCage(){
   return IntakeCage.get();
  }
   


public void resetEncoder() {
   encoderClimberIntake.setPosition(0);
}


}






