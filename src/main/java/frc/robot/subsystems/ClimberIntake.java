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
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.LinearSystemSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberIntake extends SubsystemBase {
   
   SingleJointedArmSim motorClimberIntake = new SingleJointedArmSim(null, 2, 4, 2, 5, 4, true, 0, null);

   double encoderClimberIntake = motorClimberIntake.getAngleRads();
   private final DigitalInput IntakeCage = new DigitalInput(0);
   double kP = 0.0004;
   double kI = 0.0000006;
   double kD = 0.0;
   SparkMaxConfig config = new SparkMaxConfig(); 
   boolean atTargetRPM = false;
   boolean atIdealCondition = false;

   public ClimberIntake(){
}

   public void runClimberIntake() {  
      motorClimberIntake.setInputVoltage(12);
  }
   public void stopClimberIntake(){
      motorClimberIntake.setInputVoltage(12);
   }

public void simulationPeriodic(){
   setIsAtIdealCondition();
   SmartDashboard.putNumber("Current RPM", motorClimberIntake.getAngleRads());
   SmartDashboard.putBoolean("Target RPM Reached?", atTargetRPM);
   SmartDashboard.putBoolean("Ideal Condition", atIdealCondition);
   SmartDashboard.putBoolean("Digital Input", getIntakeCage());
}


  public boolean getAtTargetRPM(){
      return this.atTargetRPM;
  }

  public boolean getIntakeCage(){
   return IntakeCage.get();
  }

  public void setIsAtIdealCondition(){
   atIdealCondition = IntakeCage.get() && getAtTargetRPM();
  }

  public boolean getIsAtIdealCondition(){
   return atIdealCondition;
  }
   





}






