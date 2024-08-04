package TrainInfoPanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code CSVReader} class provides functionality to read and parse CSV files containing station data.
 * <p>
 * This class reads a CSV file where each line represents a station with its details and converts
 * each line into a {@link Station} object. The resulting list of {@code Station} objects is returned.
 */
public class CSVReader {

    /**
     * Reads station data from a CSV file and converts it into a list of {@code Station} objects.
     * <p>
     * The CSV file is expected to have the following columns in order: [stationName, stationCode, xCoordinate, yCoordinate].
     * The first line of the file is assumed to be a header and is skipped.
     *
     * @param filePath the path to the CSV file containing station data
     * @return a list of {@code Station} objects representing the stations read from the CSV file
     */
    public List<Station> readStations(String filePath) {
        List<Station> stations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String stationName = values[4];
                String stationCode = values[3];
                double xCoordinate = Double.parseDouble(values[5]);
                double yCoordinate = Double.parseDouble(values[6]);
                Station station = new Station(stationName, stationCode, xCoordinate, yCoordinate);
                stations.add(station);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stations;
    }
}
