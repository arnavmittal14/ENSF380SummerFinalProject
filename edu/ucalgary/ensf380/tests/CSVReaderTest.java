package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TrainInfoPanel.CSVReader;
import TrainInfoPanel.Station;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CSVReaderTest {

    private CSVReader csvReader;
    private File tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        csvReader = new CSVReader();
        // Create a temporary CSV file for testing
        tempFile = File.createTempFile("stations", ".csv");
        try (FileWriter writer = new FileWriter(tempFile)) {
            // Write header
            writer.write("SomeHeader,SomeHeader,SomeHeader,StationCode,StationName,XCoordinate,YCoordinate\n");
            // Write sample data
            writer.write("Data,Data,Data,001,Central Station,123.45,678.90\n");
            writer.write("Data,Data,Data,002,West Station,234.56,789.01\n");
        }
    }

    @Test
    public void testReadStations() {
        List<Station> stations = csvReader.readStations(tempFile.getAbsolutePath());

        // Check if the list is not null and contains the correct number of stations
        assertNotNull(stations, "Stations list should not be null");
        assertEquals(2, stations.size(), "There should be 2 stations read from the CSV file");

        // Check if the first station is correctly read
        Station firstStation = stations.get(0);
        assertEquals("Central Station", firstStation.getName(), "First station name should be 'Central Station'");
        assertEquals("001", firstStation.getCode(), "First station code should be '001'");
        assertEquals(123.45, firstStation.getXCoordinate(), 0.01, "First station XCoordinate should be 123.45");
        assertEquals(678.90, firstStation.getYCoordinate(), 0.01, "First station YCoordinate should be 678.90");

        // Check if the second station is correctly read
        Station secondStation = stations.get(1);
        assertEquals("West Station", secondStation.getName(), "Second station name should be 'West Station'");
        assertEquals("002", secondStation.getCode(), "Second station code should be '002'");
        assertEquals(234.56, secondStation.getXCoordinate(), 0.01, "Second station XCoordinate should be 234.56");
        assertEquals(789.01, secondStation.getYCoordinate(), 0.01, "Second station YCoordinate should be 789.01");
    }
}
