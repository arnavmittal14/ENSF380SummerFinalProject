package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import NewsPanel.NewsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NewsPanelTest {

    private NewsPanel newsPanel;

    @BeforeEach
    public void setUp() {
        // Initialize the NewsPanel with a sample list of headlines
        newsPanel = new NewsPanel(Arrays.asList(
            "Breaking News: Major Event!",
            "Sports Update: Team Wins!",
            "Weather Alert: Severe Storms!"
        ));
    }

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

    private <T extends Component> T findComponentOfType(Class<T> componentClass) {
        for (Component component : newsPanel.getComponents()) {
            if (componentClass.isInstance(component)) {
                return componentClass.cast(component);
            }
        }
        return null;
    }
}
