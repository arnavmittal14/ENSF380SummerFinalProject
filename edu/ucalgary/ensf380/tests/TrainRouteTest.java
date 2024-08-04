package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TrainInfoPanel.Station;
import TrainInfoPanel.TrainRoute;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the {@link TrainRoute} class.
 * This test class verifies the functionality of the {@link TrainRoute} class, including methods
 * for retrieving the current station, moving to the next station, and getting future stations.
 */
public class TrainRouteTest {

    private TrainRoute trainRoute;
    private List<Station> stations;

    /**
     * Sets up the test environment by initializing a {@link TrainRoute} object
     * with a list of sample {@link Station} objects before each test method is executed.
     */
    @BeforeEach
    public void setUp() {
        // Set up a list of stations for testing
        stations = Arrays.asList(
            new Station("Station 1", "001", 0.0, 0.0),
            new Station("Station 2", "002", 1.0, 1.0),
            new Station("Station 3", "003", 2.0, 2.0),
            new Station("Station 4", "004", 3.0, 3.0)
        );
        trainRoute = new TrainRoute(stations);
    }

    /**
     * Tests the {@link TrainRoute#getCurrentStation()} method to ensure it returns the
     * correct current station.
     * <p>
     * This test verifies that the initial current station is 'Station 1'.
     * </p>
     */
    @Test
    public void testGetCurrentStation() {
        assertEquals("Station 1", trainRoute.getCurrentStation().getName(), "Initial current station should be 'Station 1'");
    }

    /**
     * Tests the {@link TrainRoute#moveToNextStation()} method to ensure it correctly updates
     * the current station when moving to the next station.
     * <p>
     * This test verifies that moving to the next station updates the current station as expected.
     * </p>
     */
    @Test
    public void testMoveToNextStation() {
        trainRoute.moveToNextStation();
        assertEquals("Station 2", trainRoute.getCurrentStation().getName(), "After moving, current station should be 'Station 2'");
        trainRoute.moveToNextStation();
        assertEquals("Station 3", trainRoute.getCurrentStation().getName(), "After moving again, current station should be 'Station 3'");
    }

    /**
     * Tests the {@link TrainRoute#getFutureStations(int)} method to ensure it returns the correct
     * number of future stations starting from the current station.
     * <p>
     * This test verifies that the method returns the correct future stations as specified.
     * </p>
     */
    @Test
    public void testGetFutureStations() {
        List<Station> futureStations = trainRoute.getFutureStations(2);
        assertEquals(2, futureStations.size(), "There should be 2 future stations");
        assertEquals("Station 2", futureStations.get(0).getName(), "First future station should be 'Station 2'");
        assertEquals("Station 3", futureStations.get(1).getName(), "Second future station should be 'Station 3'");
    }

}
