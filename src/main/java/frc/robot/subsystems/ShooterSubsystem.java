package frc.robot.subsystems;

import static edu.wpi.first.units.Units.RPM;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
   
SparkMax motorShoot = new SparkMax(1, MotorType.kBrushless);
RelativeEncoder encoder = motorShoot.getEncoder();
double targetRPM = 1000;
double currentRPM = encoder.getVelocity();
double kP = 0.1;
double kI = 0.0;
double kD = 0.0;
PIDController pid = new PIDController(kP, kI, kD);
public ShooterSubsystem(){
   pid.setSetpoint(targetRPM);
   double output = pid.calculate(currentRPM);
}

   public void runShooter() {
      double currentRPM = encoder.getVelocity();       // lê o RPM atual
      double output = pid.calculate(currentRPM);       // calcula a saída PID
      motorShoot.set(output);                          // aplica ao motor

  }
public void stopShooter(){
    motorShoot.set(0);
}

public void periodic(){
   double pos = encoder.getPosition();
   double RPM = encoder.getVelocity();
   SmartDashboard.putNumber("EncoderPosicao", pos);
   SmartDashboard.putNumber("RPM", RPM);
   SmartDashboard.putBoolean("RPM At target?", atTarget());
}

   public boolean atTarget() {
      return pid.atSetpoint();
  }
   


public void resetEncoder() {
   encoder.setPosition(0);
}
}






