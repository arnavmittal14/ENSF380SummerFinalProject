package tests;

import org.junit.Before;
import org.junit.Test;

import WeatherPanel.WeatherPanel;

import javax.swing.*;
import static org.junit.Assert.*;

public class WeatherPanelTest {

    private WeatherPanel weatherPanel;

    @Before
    public void setUp() {
        // Initialize the WeatherPanel with a sample city
        weatherPanel = new WeatherPanel("Calgary");
    }

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
        assertEquals("text/html", weatherTextPane.getContentType());

        // Optionally, check if the JTextPane contains some content (it may be empty initially)
        String content = weatherTextPane.getText();
        assertTrue("Weather text pane should contain some content", content != null && !content.isEmpty());
    }
}
