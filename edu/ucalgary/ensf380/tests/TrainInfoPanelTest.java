package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TrainInfoPanel.TrainInfoPanel;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TrainInfoPanelTest {
    private TrainInfoPanel trainInfoPanel;

    @BeforeEach
    public void setUp() {
        trainInfoPanel = new TrainInfoPanel();
    }

    @Test
    public void testInitialComponents() {
        // Check that the directionLabel is initialized correctly
        JPanel headerPanel = (JPanel) trainInfoPanel.getComponent(0);
        assertNotNull(headerPanel, "Header panel should be initialized");

        JLabel directionLabel = (JLabel) headerPanel.getComponent(0);
        assertNotNull(directionLabel, "Direction label should be initialized");
        assertEquals("Direction: ", directionLabel.getText(), "Initial direction label text should be 'Direction: '");

        // Check that the stationsPanel is initialized correctly
        JPanel stationsPanel = (JPanel) headerPanel.getComponent(1);
        assertNotNull(stationsPanel, "Stations panel should be initialized");
        assertEquals(new Dimension(1000, 250), stationsPanel.getPreferredSize(), "Stations panel size should be 1000x250");
        assertEquals(0, stationsPanel.getComponentCount(), "Stations panel should initially have no components");

        // Check that currentStationIndicator is initialized correctly
        JLabel currentStationIndicator = (JLabel) trainInfoPanel.getComponent(1);
        assertNotNull(currentStationIndicator, "Current station indicator should be initialized");
        assertEquals("", currentStationIndicator.getText(), "Initial current station indicator text should be empty");
    }
}
