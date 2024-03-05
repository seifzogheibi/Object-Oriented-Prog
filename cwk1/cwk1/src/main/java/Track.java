import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.time.ZonedDateTime;

/**
 * Represents a point in space and time, recorded by a GPS sensor.
 * 
 * @author: Saif Elzegheiby
 */

public class Track {
  private ArrayList<Point> points;

  // TODO: Create a stub for the constructor
  public Track() {
    this.points = new ArrayList<Point>();
  }

  public Track(String filename) throws FileNotFoundException, GPSException {
    this.points = new ArrayList<Point>();
    readFile(filename);
  }

  // TODO: Create a stub for readFile()
  public void readFile(String filePath) throws FileNotFoundException, GPSException {
    points.clear();
    Scanner myScanner = new Scanner(new File(filePath));
    String first = myScanner.nextLine();
    while (myScanner.hasNextLine()) {
      String item = myScanner.nextLine();
      String[] data = item.split(",");
      if (data.length != 4) {
        throw new GPSException("Line does not contain the correct number of values.");
      }
      try {
        ZonedDateTime timestamp = ZonedDateTime.parse(data[0]);
        double longitude = Double.parseDouble(data[1]);
        double latitude = Double.parseDouble(data[2]);
        double elevation = Double.parseDouble(data[3]);
        Point point = new Point(timestamp, longitude, latitude, elevation);
        this.points.add(point);
      } catch (DateTimeParseException e) {
        throw new GPSException("Timestamp format is incorrect.");
      }
    }
    myScanner.close();
  }

  // TODO: Create a stub for add()
  public void add(Point point) {
    this.points.add(point);
  }

  // TODO: Create a stub for get()
  public Point get(int index) {
    if (index < 0 || index >= points.size() || points.isEmpty()) {
      throw new GPSException("Bad points index.");
    }
    return points.get(index);
  }

  // TODO: Create a stub for size()
  public int size() {
    return points.size();
  }

  // TODO: Create a stub for lowestPoint()
  public Point lowestPoint() {
    if (this.points.isEmpty()) {
      throw new GPSException("Cannot determine the lowest point of an empty Track.");
    }

    Point lowestPoint = this.points.get(0);
    for (Point point : this.points) {
      if (point.getElevation() < lowestPoint.getElevation()) {
        lowestPoint = point;
      }
    }

    return lowestPoint;
  }

  // TODO: Create a stub for highestPoint()
  public Point highestPoint() {
    if (this.points.isEmpty()) {
      throw new GPSException("Cannot determine the highest point of an empty Track.");
    }

    Point highestPoint = this.points.get(0);
    for (Point point : this.points) {
      if (point.getElevation() > highestPoint.getElevation()) {
        highestPoint = point;
      }
    }
    return highestPoint;
  }

  // TODO: Create a stub for totalDistance()
  public double totalDistance() {
    if (this.points.size() < 2) {
      throw new GPSException("Not enough data to compute total distance.");
    }

    double totalDistance = 0.0;
    for (int i = 0; i < this.points.size() - 1; i++) {
      Point currentPoint = this.points.get(i);
      Point nextPoint = this.points.get(i + 1);
      totalDistance += Point.greatCircleDistance(currentPoint, nextPoint);
    }
    return totalDistance;
  }

  // TODO: Create a stub for averageSpeed()
  public double averageSpeed() {
    if (this.points.size() < 2) {
      throw new GPSException("Not enough data to compute average speed.");
    }

    double totalDistance = totalDistance();
    ZonedDateTime startTime = this.points.get(0).getTime();
    ZonedDateTime endTime = this.points.get(this.points.size() - 1).getTime();
    long totalTimeInSeconds = java.time.Duration.between(startTime, endTime).getSeconds();

    if (totalTimeInSeconds == 0) {
      throw new GPSException("Total time cannot be zero.");
    }
    return totalDistance / totalTimeInSeconds;
  }
}