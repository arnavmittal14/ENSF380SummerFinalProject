package tests;

import org.junit.Before;
import org.junit.Test;

import AdvertisementPanel.AdvertisementPanel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class AdvertisementPanelTest {

    private AdvertisementPanel advertisementPanel;

    @Before
    public void setUp() {
        advertisementPanel = new AdvertisementPanel();
        
        try {
            // Wait for the asynchronous image loading to complete
            Thread.sleep(2000); // Adjust as needed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void testInitialization() {
        assertNotNull("AdvertisementPanel should be initialized", advertisementPanel);

        // Check if the panel's layout is set to BorderLayout
        assertTrue("Panel should use BorderLayout", advertisementPanel.getLayout() instanceof BorderLayout);

        // Use reflection to access private static fields
        try {
            Field imageWidthField = AdvertisementPanel.class.getDeclaredField("IMAGE_WIDTH");
            imageWidthField.setAccessible(true);
            int imageWidth = (int) imageWidthField.get(null);

            Field imageHeightField = AdvertisementPanel.class.getDeclaredField("IMAGE_HEIGHT");
            imageHeightField.setAccessible(true);
            int imageHeight = (int) imageHeightField.get(null);

            // Check if the preferred size is set correctly
            Dimension preferredSize = advertisementPanel.getPreferredSize();
            assertEquals("Panel width should be " + imageWidth, imageWidth, preferredSize.width);
            assertEquals("Panel height should be " + imageHeight, imageHeight, preferredSize.height);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to access fields via reflection: " + e.getMessage());
        }
    }
}
