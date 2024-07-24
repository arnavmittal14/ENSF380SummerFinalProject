package WeatherPanel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherFetcher {
    private static final String API_KEY = "YOUR_API_KEY";

    public static String fetchWeather(String cityCode) {
        String result = "";
        try {
            String urlString = "http://api.openweathermap.org/data/2.5/weather?id=" + cityCode + "&appid=" + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            result = content.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
