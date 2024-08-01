import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

import NewsPanel.NewsPanel;
import NewsPanel.NewsFetcher;
import WeatherPanel.WeatherPanel;

public class Main {
    private static final String NEWS_KEYWORDS = "Calgary"; // Replace with actual command line argument if needed

    public static void main(String[] args) {
        JFrame frame = new JFrame("Subway Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        
       // Scanner scanner = new Scanner(System.in);
        //System.out.print("Enter the city name for weather: ");
       // String city = scanner.nextLine();
      //  scanner.close();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Fetch news in a separate thread to avoid blocking the UI
        SwingUtilities.invokeLater(() -> {
            try {
                List<String> newsHeadlines = NewsFetcher.fetchNews(NEWS_KEYWORDS);
                NewsPanel newsPanel = new NewsPanel(newsHeadlines);
                WeatherPanel weatherPanel = new WeatherPanel("Calgary");
                mainPanel.add(weatherPanel, BorderLayout.WEST);
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
