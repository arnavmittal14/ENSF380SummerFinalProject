package tests;

import org.junit.Before;
import org.junit.Test;

import WeatherPanel.WeatherPanel;

import javax.swing.*;
import static org.junit.Assert.*;

/**
 * Unit test for the {@link WeatherPanel} class.
 * This test class verifies the initialization and configuration of components within the {@link WeatherPanel}.
 */
public class WeatherPanelTest {

    private WeatherPanel weatherPanel;

    /**
     * Sets up the test environment by initializing a {@link WeatherPanel} with a sample city
     * before each test method is executed.
     */
    @Before
    public void setUp() {
        // Initialize the WeatherPanel with a sample city
        weatherPanel = new WeatherPanel("Calgary");
    }

    /**
     * Tests the initialization and configuration of the {@link JTextPane} within a {@link JScrollPane}
     * in the {@link WeatherPanel}.
     * <p>
     * This test verifies that:
     * <ul>
     * <li>The {@link JScrollPane} component is properly initialized.</li>
     * <li>The {@link JTextPane} contained within the {@link JScrollPane} is correctly initialized.</li>
     * <li>The {@link JTextPane} is set to use the "text/html" content type.</li>
     * <li>The {@link JTextPane} contains some content (it may be empty initially).</li>
     * </ul>
     * </p>
     */
    @Test
    public void testWeatherTextPaneInitialization() {
        // Fetch the JScrollPane component
        JScrollPane scrollPane = (JScrollPane) weatherPanel.getComponent(1);
        
        // Ensure the JScrollPane is not null
        assertNotNull("JScrollPane should be initialized", scrollPane);

        // Fetch the JTextPane from the JScrollPane
        JTextPane weatherTextPane = (JTextPane) scrollPane.getViewport().getView();
        
        // Check if the JTextPane is not null
        assertNotNull("Weather text pane should be initialized", weatherTextPane);

        // Check if the JTextPane content type is set to "text/html"
        assertEquals("Content type should be 'text/html'", "text/html", weatherTextPane.getContentType());

        // Optionally, check if the JTextPane contains some content (it may be empty initially)
        String content = weatherTextPane.getText();
        assertTrue("Weather text pane should contain some content", content != null && !content.isEmpty());
    }
}
