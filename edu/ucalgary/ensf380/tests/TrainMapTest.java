package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TrainInfoPanel.TrainMap;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the {@link TrainMap} class.
 * This test class verifies the proper initialization of the {@link TrainMap} component.
 */
public class TrainMapTest {

    private TrainMap trainMap;

    /**
     * Sets up the test environment by initializing a {@link TrainMap} object
     * with a sample train ID before each test method is executed.
     */
    @BeforeEach
    public void setUp() {
        trainMap = new TrainMap(1); // Initialize with a trainId
    }

    /**
     * Tests that the {@link TrainMap} component is properly initialized.
     * <p>
     * This test verifies that the {@link TrainMap} object is not null after
     * initialization, ensuring that the component is created successfully.
     * </p>
     */
    @Test
    public void testInitialization() {
        assertNotNull(trainMap, "TrainMap should be initialized");
    }
}
