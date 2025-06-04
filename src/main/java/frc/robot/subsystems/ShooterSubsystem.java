package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
 
    

SparkMax motorShoot = new SparkMax(1, MotorType.kBrushless);
private static boolean hasShot = false;

public ShooterSubsystem(){}


public void runShooter(){
motorShoot.set(0.5);
   }

public void stopShooter(){
    motorShoot.set(0);
}

   public boolean hasShot(){
    return hasShot = true;
   }

   public void resetShot() {
    hasShot = false;
   }

}




