import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;
import java.time.ZonedDateTime;

/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Nick Efford & Saif Elzegheiby
 */
public class Point {
  // Constants useful for bounds checking, etc

  private static final double MIN_LONGITUDE = -180.0;
  private static final double MAX_LONGITUDE = 180.0;
  private static final double MIN_LATITUDE = -90.0;
  private static final double MAX_LATITUDE = 90.0;
  private static final double MEAN_EARTH_RADIUS = 6.371009e+6;

  // TODO: Create a stub for the constructor

  private ZonedDateTime timestamp;
  private double longitude;
  private double latitude;
  private double elevation;

  public Point(ZonedDateTime time, double Longitude, double Latitude, double Elevation) {
    if (Longitude < MIN_LONGITUDE || Longitude > MAX_LONGITUDE) {
      throw new GPSException("Longitude out of bounds");
    }
    if (Latitude < MIN_LATITUDE || Latitude > MAX_LATITUDE) {
      throw new GPSException("Latitude out of bounds");
    }

    this.timestamp = time;
    this.longitude = Longitude;
    this.latitude = Latitude;
    this.elevation = Elevation;
  }

  // TODO: Create a stub for getTime()
  public ZonedDateTime getTime() {
    return timestamp;
  }

  // TODO: Create a stub for getLatitude()
  public double getLatitude() {
    return latitude;
  }

  // TODO: Create a stub for getLongitude()
  public double getLongitude() {
    return longitude;
  }

  // TODO: Create a stub for getElevation()
  public double getElevation() {
    return elevation;
  }

  // TODO: Create a stub for toString()
  public String toString() {
    return String.format("(%.5f, %.5f), %.1f m", longitude, latitude, elevation);
  }

  // IMPORTANT: Do not alter anything beneath this comment!

  /**
   * Computes the great-circle distance or orthodromic distance between
   * two points on a spherical surface, using Vincenty's formula.
   *
   * @param p First point
   * @param q Second point
   * @return Distance between the points, in metres
   */
  public static double greatCircleDistance(Point p, Point q) {
    double phi1 = toRadians(p.getLatitude());
    double phi2 = toRadians(q.getLatitude());

    double lambda1 = toRadians(p.getLongitude());
    double lambda2 = toRadians(q.getLongitude());
    double delta = abs(lambda1 - lambda2);

    double firstTerm = cos(phi2) * sin(delta);
    double secondTerm = cos(phi1) * sin(phi2) - sin(phi1) * cos(phi2) * cos(delta);
    double top = sqrt(firstTerm * firstTerm + secondTerm * secondTerm);

    double bottom = sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(delta);

    return MEAN_EARTH_RADIUS * atan2(top, bottom);
  }
}
