package frc.robot.Logging.EnhancedLoggers;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Logging.ChassisSpeedsLogEntry;

public class CustomChassisSpeedsLogger extends ChassisSpeedsLogEntry {

  private static boolean isFmsMatch;

  private String name;

  private ChassisSpeeds loggedValue;

  public CustomChassisSpeedsLogger(String name) {
    super(DataLogManager.getLog(), name);
    this.name = name;
    CustomChassisSpeedsLogger.isFmsMatch = DriverStation.getMatchNumber() > 0;
    this.loggedValue = new ChassisSpeeds(1, 1, 1); // Set to something different than default for initial logging
    this.append(new ChassisSpeeds());
  }

  @Override
  public void append(ChassisSpeeds chassisSpeeds) {
    if (DriverStation.isEnabled() && !chassisSpeeds.equals(this.loggedValue)) {
      this.loggedValue = chassisSpeeds;
      super.append(chassisSpeeds);
      if (!CustomChassisSpeedsLogger.isFmsMatch) {
        double[] data = new double[3];
        data[0] = chassisSpeeds.vxMetersPerSecond;
        data[1] = chassisSpeeds.vyMetersPerSecond;
        data[2] = chassisSpeeds.omegaRadiansPerSecond;
        SmartDashboard.putNumberArray(this.name, data);
      }
    }
  }

}
