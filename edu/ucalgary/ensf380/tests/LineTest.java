package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TrainInfoPanel.Line;
import TrainInfoPanel.Station;
import TrainInfoPanel.Train;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the {@link Line} class.
 * This test class verifies the functionality of the {@link Line} class, including
 * adding stations and trains, and retrieving all lines.
 */
public class LineTest {

    private Line line;
    private Station station1;
    private Station station2;
    private Train train1;
    private Train train2;

    /**
     * Sets up the test environment by initializing a {@link Line} instance
     * and adding sample {@link Station} and {@link Train} objects.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the Line and add it to the static list
        line = new Line();

        // Create sample stations
        station1 = new Station("Station 1", "001", 0.0, 0.0);
        station2 = new Station("Station 2", "002", 1.0, 1.0);

        // Create sample trains with proper IDs and directions
        train1 = new Train(1, 'F', 1); // Train ID 1, Direction Forward, Station 1
        train2 = new Train(2, 'B', 2); // Train ID 2, Direction Backward, Station 2

        // Add stations and trains to the line
        line.addStation(station1);
        line.addStation(station2);
        line.addTrain(train1);
        line.addTrain(train2);
    }

    /**
     * Tests that stations are correctly added to the {@link Line}.
     * Asserts that the line contains the expected number of stations
     * and that specific stations are present.
     */
    @Test
    public void testAddStation() {
        assertEquals(2, line.getStations().size(), "Line should have 2 stations");
        assertTrue(line.getStations().contains(station1), "Line should contain Station 1");
        assertTrue(line.getStations().contains(station2), "Line should contain Station 2");
    }

    /**
     * Tests that trains are correctly added to the {@link Line}.
     * Asserts that the line contains the expected number of trains
     * and that specific trains are present.
     */
    @Test
    public void testAddTrain() {
        assertEquals(2, line.getTrains().size(), "Line should have 2 trains");
        assertTrue(line.getTrains().contains(train1), "Line should contain Train 1");
        assertTrue(line.getTrains().contains(train2), "Line should contain Train 2");
    }

    /**
     * Tests that the static method {@link Line#getAllLines()} returns
     * all lines including the current instance.
     * Asserts that the list of all lines contains the line instance created in setup.
     */
    @Test
    public void testGetAllLines() {
        // Ensure that the static method returns all lines including the current one
        assertTrue(Line.getAllLines().contains(line), "All lines should contain the current line");
    }
}
