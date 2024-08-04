package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import NewsPanel.NewsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit test for the {@link NewsPanel} class.
 * This test class verifies the correct initialization and configuration of the
 * {@link NewsPanel} component, including its appearance and layout.
 */
public class NewsPanelTest {

    private NewsPanel newsPanel;

    /**
     * Sets up the test environment by initializing a {@link NewsPanel} with a
     * sample list of headlines.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the NewsPanel with a sample list of headlines
        newsPanel = new NewsPanel(Arrays.asList(
            "Breaking News: Major Event!",
            "Sports Update: Team Wins!",
            "Weather Alert: Severe Storms!"
        ));
    }

    /**
     * Tests the initialization of the {@link NewsPanel}.
     * <p>
     * This test verifies that the NewsPanel is properly initialized, including
     * checking the presence and configuration of the news label component, as
     * well as the preferred size of the panel.
     * </p>
     */
    @Test
    public void testInitialization() {
        // Check if the NewsPanel is not null
        assertNotNull(newsPanel, "NewsPanel should be initialized");

        // Check if the newsLabel component is correctly set up
        JLabel newsLabel = findComponentOfType(JLabel.class);
        assertNotNull(newsLabel, "NewsLabel should be initialized");
        assertEquals("Arial", newsLabel.getFont().getName(), "Font name should be Arial");
        assertEquals(Color.WHITE, newsLabel.getForeground(), "Font color should be white");

        // Check the preferred size of the NewsPanel
        Dimension preferredSize = newsPanel.getPreferredSize();
        assertEquals(new Dimension(800, 100), preferredSize, "Preferred size should be 800x100");
    }

    /**
     * Finds and returns the first component of the specified type within the
     * {@link NewsPanel}.
     * 
     * @param componentClass the class of the component to find
     * @param <T> the type of the component
     * @return the component of the specified type, or null if not found
     */
    private <T extends Component> T findComponentOfType(Class<T> componentClass) {
        for (Component component : newsPanel.getComponents()) {
            if (componentClass.isInstance(component)) {
                return componentClass.cast(component);
            }
        }
        return null;
    }
}
