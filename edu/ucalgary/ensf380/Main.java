import javax.swing.*;

import AdvertisementPanel.AdDatabaseHelper;
import AdvertisementPanel.AdScraper;
import AdvertisementPanel.Advertisement;
import AdvertisementPanel.AdvertisementPanel;
import NewsPanel.NewsPanel;
import TrainInfoPanel.TrainInfoPanel;
import WeatherPanel.WeatherPanel;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String NEWS_KEYWORDS = "Calgary"; // Replace with actual command line argument if needed

    public static void main(String[] args) {
    	   Scanner scanner = new Scanner(System.in);
           System.out.print("Enter the city name: ");
           String city = scanner.nextLine();
           scanner.close();

    	
        // Create database and table
//        AdDatabaseHelper.createDatabaseAndTable();

        // Scrape ads and insert them into the database
//        List<Advertisement> ads = AdScraper.scrapeAds();
//        AdDatabaseHelper.insertAds(ads);

        // Fetch news in a separate thread to avoid blocking the UI
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Subway Screen Project");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            AdvertisementPanel adPanel = new AdvertisementPanel();
            WeatherPanel weatherPanel = new WeatherPanel(city);
           // NewsPanel newsPanel = new NewsPanel();
            TrainInfoPanel trainInfoPanel = new TrainInfoPanel();

            frame.add(adPanel, BorderLayout.CENTER);
            frame.add(weatherPanel, BorderLayout.NORTH);
            // frame.add(newsPanel, BorderLayout.SOUTH);
            frame.add(trainInfoPanel, BorderLayout.EAST);

            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}
