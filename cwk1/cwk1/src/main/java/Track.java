import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.time.ZonedDateTime;


/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Saif Elzegheiby
 */

public class Track {
  private List<Point> points;

  // TODO: Create a stub for the constructor
  public Track(String filePath) throws IOException {
    this.points = new ArrayList<>();
    readFile(filePath);
}

  // TODO: Create a stub for readFile()
  public void readFile(String filePath) {
    try {
        Scanner myScanner = new Scanner(new File(filePath));
        while (myScanner.hasNextLine()) {
            String item = myScanner.nextLine();
            String[] data = item.split(",");
            if (data.length != 4) {
                throw new IllegalArgumentException("Line does not contain the correct number of values.");
            }
            ZonedDateTime timestamp = ZonedDateTime.parse(data[0]);
            double longitude = Double.parseDouble(data[1]);
            double latitude = Double.parseDouble(data[2]);
            double elevation = Double.parseDouble(data[3]);
            Point point = new Point(timestamp, longitude, latitude, elevation);
            points.add(point);
        }
        myScanner.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error has occurred.");
        e.printStackTrace();
    }
}


  // TODO: Create a stub for add()
  public void add(Point point) {
    this.points.add(point);
    
}

  // TODO: Create a stub for get()
  public Point get(int index) {
    if (index < 0 || index >= points.size()) {
      throw new IndexOutOfBoundsException("Index out of bounds");
  }
  return points.get(index);
}

  // TODO: Create a stub for size()
  public int size() {
    return points.size();
}

  // TODO: Create a stub for lowestPoint()
public Point lowestPoint() {
  return null;
}
  // TODO: Create a stub for highestPoint()
  public Point highestPoint() {
    return null;
  }

  // TODO: Create a stub for totalDistance()
  public double totalDistance() {
    return 0;
  }

  // TODO: Create a stub for averageSpeed()
  public double averageSpeed() {
    return 0;
  }
}