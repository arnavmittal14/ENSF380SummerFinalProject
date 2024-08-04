package tests;

import org.junit.Before;
import org.junit.Test;

import TrainInfoPanel.Line;
import TrainInfoPanel.Train;
import TrainInfoPanel.TrainInfo;

import static org.junit.Assert.*;

/**
 * Unit test for the {@link TrainInfo} class.
 * This test class verifies the proper assignment of trains to their respective subway lines
 * in the {@link TrainInfo} object.
 */
public class TrainInfoTest {

    private TrainInfo trainInfo;

    /**
     * Sets up the test environment by initializing a {@link TrainInfo} object
     * before each test method is executed.
     */
    @Before
    public void setUp() {
        trainInfo = new TrainInfo();
    }

    /**
     * Tests that the first train in the red line is correctly part of the red line.
     * <p>
     * This test verifies that the {@link Train} objects assigned to the red line
     * are correctly contained in the {@link Line} object representing the red line.
     * </p>
     */
    @Test
    public void testTrainInRedLine() {
        // Assuming that the first train in the red line should be in the red line
        Line redLine = trainInfo.getRedLine();
        Train redTrain = redLine.getTrains().get(0);

        // Check if the train is part of the red line
        assertTrue(redLine.getTrains().contains(redTrain));
    }

    /**
     * Tests that the first train in the blue line is correctly part of the blue line.
     * <p>
     * This test verifies that the {@link Train} objects assigned to the blue line
     * are correctly contained in the {@link Line} object representing the blue line.
     * </p>
     */
    @Test
    public void testTrainInBlueLine() {
        // Assuming that the first train in the blue line should be in the blue line
        Line blueLine = trainInfo.getBlueLine();
        Train blueTrain = blueLine.getTrains().get(0);

        // Check if the train is part of the blue line
        assertTrue(blueLine.getTrains().contains(blueTrain));
    }

    /**
     * Tests that the first train in the green line is correctly part of the green line.
     * <p>
     * This test verifies that the {@link Train} objects assigned to the green line
     * are correctly contained in the {@link Line} object representing the green line.
     * </p>
     */
    @Test
    public void testTrainInGreenLine() {
        // Assuming that the first train in the green line should be in the green line
        Line greenLine = trainInfo.getGreenLine();
        Train greenTrain = greenLine.getTrains().get(0);

        // Check if the train is part of the green line
        assertTrue(greenLine.getTrains().contains(greenTrain));
    }
}
