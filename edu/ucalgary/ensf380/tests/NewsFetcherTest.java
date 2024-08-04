package tests;

import org.junit.jupiter.api.Test;

import NewsPanel.NewsFetcher;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewsFetcherTest {

    @Test
    public void testFetchNews() {
        try {
            // Fetch news headlines
            List<String> headlines = NewsFetcher.fetchNews("technology");
            
            // Check if the list is not empty
            assertFalse(headlines.isEmpty(), "Headlines list should not be empty");

            // Optionally, you can check for specific content in the headlines
            boolean hasExpectedHeadline = headlines.stream()
                .anyMatch(title -> title.contains("technology")); // Check if any headline contains the keyword
            
            assertTrue(hasExpectedHeadline, "Headlines should contain the keyword 'technology'");
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false, "Exception occurred while fetching news: " + e.getMessage());
        }
    }
}
