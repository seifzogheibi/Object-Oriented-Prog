import java.io.FileNotFoundException;

/**
 * Program to provide information on a GPS track stored in a file.
 *
 * @author Saif Elzegheiby
 */
public class TrackInfo {

  public static void main(String[] args) throws FileNotFoundException {

    // TODO: Implementation TrackInfo application here
    if (args.length == 0) {
      System.err.println("No filename given.");
    }

    Track mytrack = new Track();
    mytrack.readFile(args[0]);

    System.out.println(mytrack.size() + " points in track");
    System.out.println("Lowest point is " + mytrack.lowestPoint());
    System.out.println("Highest point is " + mytrack.highestPoint());
    System.out.printf("Total distance = %.3f km\n", mytrack.totalDistance() / 1000);
    System.out.printf("Average speed = %.3f m/s\n", mytrack.averageSpeed());

  }
}
