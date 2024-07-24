import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create database and table
        AdDatabaseHelper.createDatabaseAndTable();

        // Scrape ads and insert them into the database
        List<Advertisement> ads = AdScraper.scrapeAds();
        AdDatabaseHelper.insertAds(ads);

        // Run the GUI application
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Subway Screen Project");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            AdvertisementPanel adPanel = new AdvertisementPanel();
            WeatherPanel weatherPanel = new WeatherPanel();
            NewsPanel newsPanel = new NewsPanel();
            TrainInfoPanel trainInfoPanel = new TrainInfoPanel();

            frame.add(adPanel, BorderLayout.CENTER);
            frame.add(weatherPanel, BorderLayout.NORTH);
            frame.add(newsPanel, BorderLayout.SOUTH);
            frame.add(trainInfoPanel, BorderLayout.EAST);

            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}
