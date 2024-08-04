package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TrainInfoPanel.TrainMap;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TrainMapTest {

    private TrainMap trainMap;

    @BeforeEach
    public void setUp() {
        trainMap = new TrainMap(1); // Initialize with a trainId
    }

    @Test
    public void testInitialization() {
        assertNotNull(trainMap, "TrainMap should be initialized");
    }
}
