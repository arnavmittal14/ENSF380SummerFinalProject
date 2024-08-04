package tests;

import org.junit.Before;
import org.junit.Test;

import AdvertisementPanel.AdvertisementPanel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Unit test class for {@code AdvertisementPanel}.
 * <p>
 * This class contains tests to verify the correct initialization and configuration of the {@code AdvertisementPanel}.
 */
public class AdvertisementPanelTest {

    /** The {@code AdvertisementPanel} instance to be tested. */
    private AdvertisementPanel advertisementPanel;

    /**
     * Sets up the test environment by initializing a new {@code AdvertisementPanel} instance.
     * <p>
     * Introduces a delay of 2 seconds to ensure that the panel is fully initialized before running tests.
     */
    @Before
    public void setUp() {
        advertisementPanel = new AdvertisementPanel();
        
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Tests the initialization of the {@code AdvertisementPanel}.
     * <p>
     * Verifies that the {@code AdvertisementPanel} instance is not {@code null}, 
     * that it uses a {@code BorderLayout}, and that its preferred size matches the expected dimensions.
     */
    @Test
    public void testInitialization() {
        // Check if the panel is initialized
        assertNotNull("AdvertisementPanel should be initialized", advertisementPanel);

        // Check if the panel uses BorderLayout
        assertTrue("Panel should use BorderLayout", advertisementPanel.getLayout() instanceof BorderLayout);

        // Check if the preferred size is set correctly
        try {
            Field imageWidthField = AdvertisementPanel.class.getDeclaredField("IMAGE_WIDTH");
            imageWidthField.setAccessible(true);
            int imageWidth = (int) imageWidthField.get(null);

            Field imageHeightField = AdvertisementPanel.class.getDeclaredField("IMAGE_HEIGHT");
            imageHeightField.setAccessible(true);
            int imageHeight = (int) imageHeightField.get(null);

            Dimension preferredSize = advertisementPanel.getPreferredSize();
            assertEquals("Panel width should be " + imageWidth, imageWidth, preferredSize.width);
            assertEquals("Panel height should be " + imageHeight, imageHeight, preferredSize.height);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to access fields via reflection: " + e.getMessage());
        }
    }
}
