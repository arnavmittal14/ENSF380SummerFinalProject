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

/**
 * The Main class sets up the GUI for the subway screen application, integrating various panels such as 
 * weather, news, advertisements, and train information. It handles user input for selecting a train and
 * city for the weather report, and manages the display of different panels using timers.
 */
public class Main {
    private static final int ADVERTISEMENT_DISPLAY_TIME = 10000; 
    private static final int TRAIN_MAP_DISPLAY_TIME = 5000; 

    /**
     * The main method initializes the subway screen application.
     * 
     * @param args Command line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        TrainInfo trainInfo = new TrainInfo();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter train ID (1-4 for Red Line, 5-8 for Blue Line, 9-12 for Green Line) to select a train: ");
        int trainId = scanner.nextInt() - 1;
        Train selectedTrain = trainInfo.getTrainById(trainId);
        System.out.print("Enter the city for the weather report and news: ");
        String city = scanner.next();
        System.out.print("Enter the topic of news to search: ");
        String NEWS_KEYWORDS = scanner.next();
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

        JPanel rightPanel = new JPanel(new GridBagLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel1 = new JPanel(new BorderLayout());
        JPanel bottomPanel2 = new JPanel(new BorderLayout());

        GridBagConstraints rightGbc = new GridBagConstraints();
        rightGbc.fill = GridBagConstraints.BOTH;
        rightGbc.weightx = 1.0;
        rightGbc.weighty = 0.5;

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.25;
        gbc.weighty = 0.8;
        mainPanel.add(rightPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.75;
        gbc.weighty = 0.8;
        mainPanel.add(leftPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.05;
        mainPanel.add(bottomPanel1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.15;
        mainPanel.add(bottomPanel2, gbc);
        
        WeatherPanel weatherPanel = new WeatherPanel(city);
        rightPanel.add(weatherPanel, rightGbc);

        SwingUtilities.invokeLater(() -> {
            try {
                List<String> newsHeadlines = NewsFetcher.fetchNews(city);
                NewsPanel newsPanel = new NewsPanel(newsHeadlines);

                JPanel newsWrapperPanel = new JPanel(new BorderLayout());
                newsWrapperPanel.add(newsPanel, BorderLayout.CENTER);
                newsWrapperPanel.setPreferredSize(new Dimension(400, 100));

                bottomPanel1.add(newsWrapperPanel, BorderLayout.CENTER);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        CSVReader csvReader = new CSVReader();
        List<Station> stations = csvReader.readStations("edu/ucalgary/ensf380/assets/Map.csv");

        TrainRoute trainRoute = new TrainRoute(stations);
        TrainInfoPanel trainInfoPanel = new TrainInfoPanel();

        JPanel trainInfoWrapperPanel = new JPanel(new BorderLayout());
        trainInfoWrapperPanel.add(trainInfoPanel, BorderLayout.CENTER);
        trainInfoWrapperPanel.setPreferredSize(new Dimension(800, 200));

        bottomPanel2.add(trainInfoWrapperPanel, BorderLayout.CENTER);

        AdvertisementPanel advertisementPanel = new AdvertisementPanel();
        TrainMap trainMap = new TrainMap(trainId);

        JPanel adWrapperPanel = new JPanel(new BorderLayout());
        adWrapperPanel.add(advertisementPanel, BorderLayout.CENTER);
        adWrapperPanel.setPreferredSize(new Dimension(1500, 700));

        leftPanel.add(adWrapperPanel, BorderLayout.CENTER);

        Timer adToMapTimer = new Timer(ADVERTISEMENT_DISPLAY_TIME, e -> {
            leftPanel.remove(adWrapperPanel);
            leftPanel.add(trainMap, BorderLayout.CENTER);
            leftPanel.revalidate();
            leftPanel.repaint();

            Timer mapToAdTimer = new Timer(TRAIN_MAP_DISPLAY_TIME, event -> {
                leftPanel.remove(trainMap);
                leftPanel.add(adWrapperPanel, BorderLayout.CENTER);
                leftPanel.revalidate();
                leftPanel.repaint();
            });
            mapToAdTimer.setRepeats(false);
            mapToAdTimer.start();
        });
        adToMapTimer.start();
        adToMapTimer.setRepeats(true);

        frame.add(mainPanel);
        frame.setVisible(true);

        while (true) {
            trainRoute.moveToNextStation();
            trainInfoPanel.updatePanel(trainRoute, trainId);

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
