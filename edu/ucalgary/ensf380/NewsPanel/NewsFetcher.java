package NewsPanel;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code NewsFetcher} class is responsible for fetching news headlines from the NewsAPI.
 * It uses the Apache HttpClient library to send HTTP requests and parse JSON responses.
 * <p>
 * This class provides a method to retrieve a list of news headlines based on search keywords.
 */
public class NewsFetcher {
    private static final String API_URL = "https://newsapi.org/v2/everything";
    private static final String API_KEY = "66602ff984b642d891c5488397c0f64e";

    /**
     * Fetches news headlines based on the provided keywords.
     *
     * @param keywords the search keywords to filter news articles
     * @return a list of news headlines related to the keywords
     * @throws IOException if an I/O error occurs during the HTTP request or response processing
     */
    public static List<String> fetchNews(String keywords) throws IOException {
        List<String> headlines = new ArrayList<>();
        String url = String.format("%s?q=%s&apiKey=%s", API_URL, keywords, API_KEY);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    String json = readResponse(entity.getContent());
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray articles = jsonObject.getJSONArray("articles");
                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject article = articles.getJSONObject(i);
                        String title = article.getString("title");
                        headlines.add(title);
                    }
                } else {
                    System.out.println("Error: " + response.getStatusLine().getStatusCode() + " " + response.getStatusLine().getReasonPhrase());
                }
            }
        }
        return headlines;
    }

    /**
     * Reads the response from the input stream and converts it to a string.
     *
     * @param inputStream the input stream to read from
     * @return the response content as a string
     * @throws IOException if an I/O error occurs during reading from the input stream
     */
    private static String readResponse(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();
        }
    }
}
