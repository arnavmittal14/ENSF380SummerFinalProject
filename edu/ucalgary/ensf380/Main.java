import edu.ucalgary.ensf380.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import AdvertisementPanel.AdvertisementPanel;
import NewsPanel.NewsPanel;
import NewsPanel.NewsFetcher;
import WeatherPanel.WeatherPanel;
import WeatherPanel.TimePanel;
import TrainInfoPanel.CSVReader;
import TrainInfoPanel.Line;
import TrainInfoPanel.Station;
import TrainInfoPanel.Train;
import TrainInfoPanel.TrainInfoPanel;
import TrainInfoPanel.TrainInfo;
import TrainInfoPanel.TrainRoute;
import TrainInfoPanel.TrainMap;

public class Main {
    private static final String NEWS_KEYWORDS = "Calgary"; // Replace with actual command line argument if needed
    private static final int ADVERTISEMENT_DISPLAY_TIME = 10000; // 10 seconds
    private static final int TRAIN_MAP_DISPLAY_TIME = 5000; // 5 seconds

    public static void main(String[] args) {
        TrainInfo trainInfo = new TrainInfo();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter train ID (0-3 for Red Line, 4-7 for Blue Line, 8-11 for Green Line) to select a train: ");
        int trainId = scanner.nextInt();
        Train selectedTrain = trainInfo.getTrainById(trainId);
        System.out.print("Enter the city for the weather report: ");
        String city = scanner.next();
        scanner.close();
        
        if (selectedTrain == null) {
            System.out.println("Invalid train ID. Exiting.");
            return;
        }


        JFrame frame = new JFrame("Subway Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // Create the panels
        JPanel rightPanel = new JPanel(new GridBagLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel1 = new JPanel(new BorderLayout());
        JPanel bottomPanel2 = new JPanel(new BorderLayout());

        
        GridBagConstraints rightGbc = new GridBagConstraints();
        rightGbc.fill = GridBagConstraints.BOTH;
        rightGbc.weightx = 1.0;
        rightGbc.weighty = 0.5;

//        rightPanel.add(timePanel, rightGbc);
        rightGbc.gridy = 1;

        // Add the right panel (50% height, 25% width)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.25;
        gbc.weighty = 0.8;
        mainPanel.add(rightPanel, gbc);

        // Add the left panel (50% height, 75% width)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.75;
        gbc.weighty = 0.8;
        mainPanel.add(leftPanel, gbc);

        // Add the bottom panel 1 (25% height, 100% width)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.05;
        mainPanel.add(bottomPanel1, gbc);

        // Add the bottom panel 2 (25% height, 100% width)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.15;
        mainPanel.add(bottomPanel2, gbc);
        
     // Create and add WeatherPanel and TimePanel to the rightPanel
        WeatherPanel weatherPanel = new WeatherPanel(city); // Make city a command line argument
        rightPanel.add(weatherPanel, rightGbc);

        // Fetch news in a separate thread to avoid blocking the UI
        SwingUtilities.invokeLater(() -> {
            try {
                List<String> newsHeadlines = NewsFetcher.fetchNews(NEWS_KEYWORDS);
                NewsPanel newsPanel = new NewsPanel(newsHeadlines);

                // Wrap the NewsPanel in a JPanel to control size
                JPanel newsWrapperPanel = new JPanel(new BorderLayout());
                newsWrapperPanel.add(newsPanel, BorderLayout.CENTER);
                newsWrapperPanel.setPreferredSize(new Dimension(400, 100)); // Set preferred size

                bottomPanel1.add(newsWrapperPanel, BorderLayout.CENTER); // Place the news section at the bottom
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Read stations from CSV file
        CSVReader csvReader = new CSVReader();
        List<Station> stations = csvReader.readStations("edu/ucalgary/ensf380/assets/Map.csv");

        // Create instances of TrainRoute and TrainInfoPanel
        TrainRoute trainRoute = new TrainRoute(stations);
        TrainInfoPanel trainInfoPanel = new TrainInfoPanel();

        // Wrap the TrainInfoPanel in a JPanel to control size
        JPanel trainInfoWrapperPanel = new JPanel(new BorderLayout());
        trainInfoWrapperPanel.add(trainInfoPanel, BorderLayout.CENTER);
        trainInfoWrapperPanel.setPreferredSize(new Dimension(800, 200)); // Set preferred size

        bottomPanel2.add(trainInfoWrapperPanel, BorderLayout.CENTER); // Add TrainInfoPanel to the bottom

        // Create and add AdvertisementPanel to the leftPanel
        AdvertisementPanel advertisementPanel = new AdvertisementPanel();
        TrainMap trainMap = new TrainMap(trainId);

        // Wrap the AdvertisementPanel in a JPanel to control size
        JPanel adWrapperPanel = new JPanel(new BorderLayout());
        adWrapperPanel.add(advertisementPanel, BorderLayout.CENTER);
        adWrapperPanel.setPreferredSize(new Dimension(1500, 700)); // Set preferred size

        leftPanel.add(adWrapperPanel, BorderLayout.CENTER); // Add AdvertisementPanel to the left panel

        // Timer to rotate between AdvertisementPanel and TrainMap
        Timer adToMapTimer = new Timer(ADVERTISEMENT_DISPLAY_TIME, e -> {
            leftPanel.remove(adWrapperPanel);
            leftPanel.add(trainMap, BorderLayout.CENTER);
            leftPanel.revalidate();
            leftPanel.repaint();

            // Timer to revert back to AdvertisementPanel after 5 seconds
            Timer mapToAdTimer = new Timer(TRAIN_MAP_DISPLAY_TIME, event -> {
                leftPanel.remove(trainMap);
                leftPanel.add(adWrapperPanel, BorderLayout.CENTER);
                leftPanel.revalidate();
                leftPanel.repaint();
            });
            mapToAdTimer.setRepeats(false); // Only execute once
            mapToAdTimer.start();
        });
        adToMapTimer.start();
        adToMapTimer.setRepeats(true); // Execute indefinitely

        frame.add(mainPanel);
        frame.setVisible(true);

        while (true) {
            // Move to the next station and update the UI
            trainRoute.moveToNextStation();
            trainInfoPanel.updatePanel(trainRoute, trainId);

            // Simulate train movement with a delay
            try {
                Thread.sleep(10000); // 5 seconds delay between stations
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
