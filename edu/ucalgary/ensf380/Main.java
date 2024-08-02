import edu.ucalgary.ensf380.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import AdvertisementPanel.AdvertisementPanel;
import NewsPanel.NewsPanel;
import NewsPanel.NewsFetcher;
import WeatherPanel.WeatherPanel;
import WeatherPanel.TimePanel;
import TrainInfoPanel.TrainInfoPanel;
import TrainInfoPanel.CSVReader;
import TrainInfoPanel.Station;
import TrainInfoPanel.TrainRoute;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    private static final String NEWS_KEYWORDS = "Calgary";

    @Override
    public void start(Stage primaryStage) {
        BorderPane mainPanel = new BorderPane();

        Scene scene = new Scene(mainPanel, 1000, 700);

        try {
            // Fetch news headlines
            List<String> newsHeadlines = NewsFetcher.fetchNews(NEWS_KEYWORDS);
            NewsPanel newsPanel = new NewsPanel(newsHeadlines);

            // Create weather and time panels
            WeatherPanel weatherPanel = new WeatherPanel("Calgary");
            TimePanel timePanel = new TimePanel();

            // Combine time and weather panels
            VBox topRightPanel = new VBox(10);
            topRightPanel.getChildren().addAll(timePanel, weatherPanel);
            topRightPanel.getStyleClass().add("vbox");
            topRightPanel.setPrefHeight(scene.getHeight() * 0.5);

            // Create an advertisement panel (placeholder)
            AdvertisementPanel advertisementPanel = new AdvertisementPanel();
            advertisementPanel.getStyleClass().add("advertisement");

            // Read stations from CSV and create train route and info panel
            CSVReader csvReader = new CSVReader();
            List<Station> stations = csvReader.readStations("edu/ucalgary/ensf380/assets/subway.csv");
            TrainRoute trainRoute = new TrainRoute(stations);
            TrainInfoPanel trainInfoPanel = new TrainInfoPanel();
            trainInfoPanel.updatePanel(trainRoute);

            // Layout arrangement
            BorderPane topPanel = new BorderPane();
            topPanel.setLeft(advertisementPanel);
            topPanel.setRight(topRightPanel);
            topPanel.setStyle("-fx-background-color: white; -fx-padding: 10;");

            mainPanel.setTop(topPanel); // Advertisement and weather/time panels at the top
            mainPanel.setCenter(newsPanel); // News panel in the center
            mainPanel.setBottom(trainInfoPanel); // Train info panel at the bottom

            // Load CSS
            scene.getStylesheets().add("./style.css");

        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Subway Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


