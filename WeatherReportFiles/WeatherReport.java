package edu.ucalgary.ensf380;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherReport {

    public static String getWeatherReport(String city) throws Exception {
        String baseUrl = "https://wttr.in/";
        String format = URLEncoder.encode("%C %t %w %m %p %P", StandardCharsets.UTF_8.toString());
        String urlString = baseUrl + city + "?format=" + format;
        String rawData = downloadWeatherData(urlString);
        String formattedData = formatWeatherData(rawData);
        String date = extractDate();
        return "Date: " + date + "\n" + formattedData;
    }

    private static String downloadWeatherData(String urlString) throws Exception {
        URI uri = new URI(urlString);
        URL url = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder content = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine).append("\n");
        }
        in.close();
        connection.disconnect();
        return content.toString();
    }

    private static String formatWeatherData(String weatherData) {
      
        String regex = "(.+?)\\s+([+-]?\\d+°C)\\s+([↖↗↘↙↑↓←→]\\d+km/h)\\s+([🌑🌒🌓🌔🌕🌖🌗🌘])\\s+(\\d+\\.\\d+mm)\\s+(\\d+hPa)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(weatherData);

        // Create a StringBuilder for the formatted data
        StringBuilder formattedData = new StringBuilder();

        // Match the pattern and extract the data
        if (matcher.find()) {
            formattedData.append("Weather Condition: ").append(matcher.group(1)).append(" ").append(getEmojiForWeather(matcher.group(1))).append("\n");
            formattedData.append("Temperature: ").append(matcher.group(2)).append("\n");
            formattedData.append("Wind Speed: ").append(matcher.group(3)).append("\n");
            formattedData.append("Moon Phase: ").append(matcher.group(4)).append("\n");
            formattedData.append("Precipitation: ").append(matcher.group(5)).append("\n");
            formattedData.append("Atmospheric Pressure: ").append(matcher.group(6)).append("\n");
        } else {
            formattedData.append("Weather data format is incorrect.");
        }

        return formattedData.toString().trim();
    }

    private static String getEmojiForWeather(String weatherDescription) {
        switch (weatherDescription.toLowerCase()) {
            case "partly":
            case "partly cloudy":
                return "⛅";
            case "cloudy":
                return "☁️";
            case "sunny":
                return "☀️";
            case "rain":
                return "🌧️";
            case "snow":
                return "❄️";
            default:
                return "";
        }
    }

    private static String extractDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE dd MMM");
        return today.format(dateFormatter);
    }
}
