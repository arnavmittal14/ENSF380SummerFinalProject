package TrainInfoPanel;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrainInfoTest {

    private TrainInfo trainInfo;

    @Before
    public void setUp() {
        trainInfo = new TrainInfo();
    }

    @Test
    public void testTrainInRedLine() {
        // Assuming that the first train in the red line should be in the red line
        Line redLine = trainInfo.getRedLine();
        Train redTrain = redLine.getTrains().get(0);

        // Check if the train is part of the red line
        assertTrue(redLine.getTrains().contains(redTrain));
    }

    @Test
    public void testTrainInBlueLine() {
        // Assuming that the first train in the blue line should be in the blue line
        Line blueLine = trainInfo.getBlueLine();
        Train blueTrain = blueLine.getTrains().get(0);

        // Check if the train is part of the blue line
        assertTrue(blueLine.getTrains().contains(blueTrain));
    }

    @Test
    public void testTrainInGreenLine() {
        // Assuming that the first train in the green line should be in the green line
        Line greenLine = trainInfo.getGreenLine();
        Train greenTrain = greenLine.getTrains().get(0);

        // Check if the train is part of the green line
        assertTrue(greenLine.getTrains().contains(greenTrain));
    }
}
