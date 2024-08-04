package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TrainInfoPanel.Station;
import TrainInfoPanel.TrainRoute;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrainRouteTest {

    private TrainRoute trainRoute;
    private List<Station> stations;

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

    @Test
    public void testGetCurrentStation() {
        assertEquals("Station 1", trainRoute.getCurrentStation().getName(), "Initial current station should be 'Station 1'");
    }

    @Test
    public void testMoveToNextStation() {
        trainRoute.moveToNextStation();
        assertEquals("Station 2", trainRoute.getCurrentStation().getName(), "After moving, current station should be 'Station 2'");
        trainRoute.moveToNextStation();
        assertEquals("Station 3", trainRoute.getCurrentStation().getName(), "After moving again, current station should be 'Station 3'");
    }

    @Test
    public void testGetFutureStations() {
        List<Station> futureStations = trainRoute.getFutureStations(2);
        assertEquals(2, futureStations.size(), "There should be 2 future stations");
        assertEquals("Station 2", futureStations.get(0).getName(), "First future station should be 'Station 2'");
        assertEquals("Station 3", futureStations.get(1).getName(), "Second future station should be 'Station 3'");
    }

}
