package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TrainInfoPanel.Station;

import static org.junit.jupiter.api.Assertions.*;

public class StationTest {

    private Station station;

    @BeforeEach
    public void setUp() {
        // Initialize the Station with sample data
        station = new Station("Central Station", "CS001", 12.34, 56.78);
    }

    @Test
    public void testGetName() {
        assertEquals("Central Station", station.getName(), "Station name should be 'Central Station'");
    }

    @Test
    public void testGetCode() {
        assertEquals("CS001", station.getCode(), "Station code should be 'CS001'");
    }

    @Test
    public void testGetXCoordinate() {
        assertEquals(12.34, station.getXCoordinate(), "X coordinate should be 12.34");
    }

    @Test
    public void testGetYCoordinate() {
        assertEquals(56.78, station.getYCoordinate(), "Y coordinate should be 56.78");
    }

    @Test
    public void testToString() {
        assertEquals("CS001", station.toString(), "toString should return the station code 'CS001'");
    }
}
