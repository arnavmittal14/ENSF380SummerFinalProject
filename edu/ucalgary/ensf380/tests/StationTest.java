package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TrainInfoPanel.Station;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the {@link Station} class.
 * This test class verifies the correct functionality of the {@link Station} class methods,
 * including getters and the {@code toString()} method.
 */
public class StationTest {

    private Station station;

    /**
     * Sets up the test environment by initializing a {@link Station} object with
     * sample data before each test.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the Station with sample data
        station = new Station("Central Station", "CS001", 12.34, 56.78);
    }

    /**
     * Tests the {@link Station#getName()} method.
     * <p>
     * This test verifies that the name of the station is correctly returned.
     * </p>
     */
    @Test
    public void testGetName() {
        assertEquals("Central Station", station.getName(), "Station name should be 'Central Station'");
    }

    /**
     * Tests the {@link Station#getCode()} method.
     * <p>
     * This test verifies that the code of the station is correctly returned.
     * </p>
     */
    @Test
    public void testGetCode() {
        assertEquals("CS001", station.getCode(), "Station code should be 'CS001'");
    }

    /**
     * Tests the {@link Station#getXCoordinate()} method.
     * <p>
     * This test verifies that the X coordinate of the station is correctly returned.
     * </p>
     */
    @Test
    public void testGetXCoordinate() {
        assertEquals(12.34, station.getXCoordinate(), "X coordinate should be 12.34");
    }

    /**
     * Tests the {@link Station#getYCoordinate()} method.
     * <p>
     * This test verifies that the Y coordinate of the station is correctly returned.
     * </p>
     */
    @Test
    public void testGetYCoordinate() {
        assertEquals(56.78, station.getYCoordinate(), "Y coordinate should be 56.78");
    }

    /**
     * Tests the {@link Station#toString()} method.
     * <p>
     * This test verifies that the {@code toString()} method returns the correct station code.
     * </p>
     */
    @Test
    public void testToString() {
        assertEquals("CS001", station.toString(), "toString should return the station code 'CS001'");
    }
}
