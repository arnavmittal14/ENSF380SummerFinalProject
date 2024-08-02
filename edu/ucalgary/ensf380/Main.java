import edu.ucalgary.ensf380.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

import NewsPanel.NewsPanel;
import NewsPanel.NewsFetcher;
import WeatherPanel.WeatherPanel;
import TrainInfoPanel.TrainInfoPanel;
import AdvertisementPanel.AdvertisementPanel;

public class Main {
    private static final String NEWS_KEYWORDS = "Calgary"; // Replace with actual command line argument if needed

    public static void main(String[] args) {
        JFrame frame = new JFrame("Subway Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        // Fetch news in a separate thread to avoid blocking the UI
        SwingUtilities.invokeLater(() -> {
            try {
                List<String> newsHeadlines = NewsFetcher.fetchNews(NEWS_KEYWORDS);
                NewsPanel newsPanel = new NewsPanel(newsHeadlines);
                WeatherPanel weatherPanel = new WeatherPanel("Calgary"); // make city a command line argument
                TrainInfoPanel trainInfoPanel = new TrainInfoPanel();
                AdvertisementPanel advertisementPanel = new AdvertisementPanel();
                
                mainPanel.add(weatherPanel, BorderLayout.EAST);
                mainPanel.add(newsPanel, BorderLayout.SOUTH); // Place the news section at the bottom
                mainPanel.add(trainInfoPanel, BorderLayout.CENTER); // Add TrainInfoPanel to the center
                mainPanel.add(advertisementPanel, BorderLayout.WEST); // Add AdvertisementPanel to the right
                
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
