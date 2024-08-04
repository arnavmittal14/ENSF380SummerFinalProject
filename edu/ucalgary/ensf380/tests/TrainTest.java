package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TrainInfoPanel.Line;
import TrainInfoPanel.Station;
import TrainInfoPanel.Train;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for the {@link Train} class.
 * This test class verifies the functionality of the {@link Train} class methods,
 * including incrementing and decrementing the station number, and checking the string representation of the train.
 */
public class TrainTest {

    private Line redLine;
    private Train train;

    /**
     * Sets up the test environment by initializing a {@link Line} with sample {@link Station} objects
     * and creating a {@link Train} object with sample data before each test method is executed.
     */
    @BeforeEach
    public void setUp() {
        // Create a sample line with stations
        redLine = new Line();
        for (int i = 0; i < 5; i++) {
            redLine.addStation(new Station("Station " + (i + 1), String.valueOf(i + 1), i, i));
        }

        // Initialize a train with sample data
        train = new Train(1, 'F', 1);
    }

    /**
     * Tests the {@link Train#incrementStation()} method to ensure it correctly increments the
     * station number while maintaining the current direction.
     * <p>
     * This test verifies that the station number increments by one and the direction remains unchanged.
     * </p>
     */
    @Test
    public void testIncrementStation() {
        // Initial state
        assertEquals(1, train.getStationNo(), "Initial station number should be 1");
        assertEquals('F', train.getDirection(), "Initial direction should be 'F'");

        // Increment station
        train.incrementStation();
        assertEquals(2, train.getStationNo(), "Station number should increment to 2");
        assertEquals('F', train.getDirection(), "Direction should remain 'F'");
    }

    /**
     * Tests the {@link Train#decrementStation()} method to ensure it correctly decrements the
     * station number while maintaining the current direction or changing it if necessary.
     * <p>
     * This test verifies that the station number decrements by one and the direction remains unchanged
     * or changes direction if the train moves past the starting point.
     * </p>
     */
    @Test
    public void testDecrementStation() {
        // Move to the second station
        train.setStationNo(2);
        train.decrementStation();
        assertEquals(1, train.getStationNo(), "Station number should decrement to 1");
        assertEquals('F', train.getDirection(), "Direction should remain 'F'");

        // Move to the first station and decrement
        train.decrementStation();
        assertEquals(2, train.getStationNo(), "Station number should be reset to 2");
        assertEquals('F', train.getDirection(), "Direction should remain 'F'");
    }

    /**
     * Tests the {@link Train#toString()} method to ensure it returns the correct string representation
     * of the train.
     * <p>
     * This test verifies that the toString method produces the expected format: "Train <trainId> : Station R<stationNo> Direction <direction>".
     * </p>
     */
    @Test
    public void testToString() {
        // Verify the toString method output
        String expected = "Train 1 : Station R1 Direction F";
        assertEquals(expected, train.toString(), "toString() should return the expected string representation of the train");
    }
}
