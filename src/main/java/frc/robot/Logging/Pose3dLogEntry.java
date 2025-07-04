package frc.robot.Logging;

import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleArrayLogEntry;

public class Pose3dLogEntry {
  private DoubleArrayLogEntry baseLogger;

  public Pose3dLogEntry(DataLog log, String name) {
    this.baseLogger = new DoubleArrayLogEntry(log, name);
  }

  public void appendRadians(Pose3d pose) {
    double[] data = new double[4];
    data[0] = pose.getTranslation().getX();
    data[1] = pose.getTranslation().getY();
    data[2] = pose.getZ();
    data[3] = pose.getRotation().getAngle();
    this.baseLogger.append(data);
  }

  public void appendDegrees(Pose3d pose) {
    double[] data = new double[4];
    data[0] = pose.getTranslation().getX();
    data[1] = pose.getTranslation().getY();
    data[2] = pose.getZ();
    data[3] = Units.radiansToDegrees(pose.getRotation().getAngle());
    this.baseLogger.append(data);
  }
}
