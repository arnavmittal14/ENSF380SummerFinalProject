package TrainInfoPanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public List<Station> readStations(String filePath) {
        List<Station> stations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip header
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 8) {
                    String stationName = values[4];
                    double xCoordinate = Double.parseDouble(values[5]);
                    double yCoordinate = Double.parseDouble(values[6]);
                    Station station = new Station(stationName, xCoordinate, yCoordinate);
                    stations.add(station);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stations;
    }
}
