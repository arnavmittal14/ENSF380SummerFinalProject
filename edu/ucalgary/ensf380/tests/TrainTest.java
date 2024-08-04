package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TrainInfoPanel.Line;
import TrainInfoPanel.Station;
import TrainInfoPanel.Train;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TrainTest {

    private Line redLine;
    private Train train;

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

    @Test
    public void testIncrementStation() {
        // Initial state
        assertEquals(1, train.getStationNo());
        assertEquals('F', train.getDirection());

        // Increment station
        train.incrementStation();
        assertEquals(2, train.getStationNo());
        assertEquals('F', train.getDirection());
    }

    @Test
    public void testDecrementStation() {
        // Move to the second station
        train.setStationNo(2);
        train.decrementStation();
        assertEquals(1, train.getStationNo());
        assertEquals('F', train.getDirection());

        // Move to the first station and decrement
        train.decrementStation();
        assertEquals(2, train.getStationNo());
        assertEquals('F', train.getDirection()); // Should change direction to 'F'
    }

    @Test
    public void testToString() {
        // Verify the toString method output
        String expected = "Train 1 : Station R1 Direction F";
        assertEquals(expected, train.toString());
    }
}
