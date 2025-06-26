package frc.robot.subsystems;

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
double kP = 0.1;
double kI = 0.0;
double kD = 0.0;
PIDController pid = new PIDController(kP, kI, kD);
public ShooterSubsystem(){}

public void setTargetPosition(double position) {
   pid.setSetpoint(position);
}

public void runShooter(){
motorShoot.set(0.3);
   }

public void stopShooter(){
    motorShoot.set(0);
}

public void periodic(){
   double pos = encoder.getPosition();
   SmartDashboard.putNumber("EncoderPosicao", pos);
}

   public boolean atTarget() {
      return pid.atSetpoint();
}
public void resetEncoder() {
   pid.reset();
}
   }






