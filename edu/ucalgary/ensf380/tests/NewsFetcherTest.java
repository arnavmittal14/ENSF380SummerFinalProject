package tests;

import org.junit.jupiter.api.Test;

import NewsPanel.NewsFetcher;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit test for the {@link NewsFetcher} class.
 * This test class verifies the functionality of the {@link NewsFetcher} class,
 * specifically the ability to fetch news headlines for a given topic.
 */
public class NewsFetcherTest {

    /**
     * Tests the {@link NewsFetcher#fetchNews(String)} method to ensure it
     * correctly fetches news headlines based on a specified topic.
     * <p>
     * This test checks that the fetched list of headlines is not empty and
     * contains at least one headline that includes the keyword 'technology'.
     * </p>
     */
    @Test
    public void testFetchNews() {
        try {
            // Fetch news headlines for the topic 'technology'
            List<String> headlines = NewsFetcher.fetchNews("technology");
            
            // Check if the list is not empty
            assertFalse(headlines.isEmpty(), "Headlines list should not be empty");

            // Check if any headline contains the keyword 'technology'
            boolean hasExpectedHeadline = headlines.stream()
                .anyMatch(title -> title.contains("technology")); // Check if any headline contains the keyword
            
            assertTrue(hasExpectedHeadline, "Headlines should contain the keyword 'technology'");
        } catch (IOException e) {
            e.printStackTrace();
            // If an exception occurs, fail the test with the exception message
            assertTrue(false, "Exception occurred while fetching news: " + e.getMessage());
        }
    }
}
