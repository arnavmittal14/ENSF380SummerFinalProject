import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.io.IOException;

import NewsPanel.NewsPanel;
import NewsPanel.NewsFetcher;

public class Main {
    private static final String NEWS_KEYWORDS = "Calgary"; // Replace with actual command line argument if needed

    public static void main(String[] args) {
        JFrame frame = new JFrame("Subway Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Fetch news in a separate thread to avoid blocking the UI
        SwingUtilities.invokeLater(() -> {
            try {
                List<String> newsHeadlines = NewsFetcher.fetchNews(NEWS_KEYWORDS);
                NewsPanel newsPanel = new NewsPanel(newsHeadlines);
                mainPanel.add(newsPanel, BorderLayout.SOUTH); // Place the news section at the bottom
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Add other components (advertisements, time/weather, train info, etc.)
        // mainPanel.add(otherComponents);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
