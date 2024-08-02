package WeatherPanel;

import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherPanel extends VBox {
    private Label weatherLabel;

    public WeatherPanel(String city) {
        weatherLabel = new Label("Fetching weather data...");
        weatherLabel.setFont(new Font("Arial", 16));
        getChildren().add(weatherLabel);

        // Fetch weather data
        Platform.runLater(() -> {
            try {
                String weatherReport = getWeatherReport(city);
                weatherLabel.setText(formatForDisplay(weatherReport));
            } catch (Exception e) {
                weatherLabel.setText("Error fetching weather data: " + e.getMessage());
            }
        });
    }

    private String getWeatherReport(String city) throws Exception {
        String url = "http://wttr.in/" + city + "?format=%t+%c";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder content = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        return content.toString();
    }

    private String formatForDisplay(String weatherReport) {
        Pattern pattern = Pattern.compile("(\\d+°C)\\s+(.*)");
        Matcher matcher = pattern.matcher(weatherReport);
        
        if(matcher.find()) {
        	String temperature = matcher.group(1);
        	String condition = matcher.group(2);
        	return "Temperature: " + temperature + ", Condition: " + condition;
        } else {
        	return "Weather report format is unexpected.";
        }
    }
}
