package WeatherPanel;

import javax.swing.*;
import java.awt.*;

import edu.ucalgary.ensf380.WeatherReport;
import Panel.Panel;

/**
 * The {@code WeatherPanel} class extends {@code Panel} and displays weather information for a specified city.
 * <p>
 * This class fetches weather data asynchronously and displays it in a {@code JTextPane}. It also includes a {@code TimePanel} 
 * at the top of the panel to show the current date and time.
 */
public class WeatherPanel extends Panel {

    /** {@code JTextPane} to display the weather report in HTML format. */
    private JTextPane weatherTextPane;

    /**
     * Constructs a {@code WeatherPanel} object for the specified city and initializes the user interface.
     * <p>
     * Fetches weather data asynchronously and updates the {@code weatherTextPane} with the formatted report. 
     * In case of an error, an error message is displayed instead.
     * 
     * @param city the name of the city for which the weather report is to be fetched
     */
    public WeatherPanel(String city) {
        initUI();
        
        // Fetch weather data asynchronously
        SwingUtilities.invokeLater(() -> {
            try {
                String weatherReport = WeatherReport.getWeatherReport(city);
                weatherTextPane.setText(formatForHtml(weatherReport));
            } catch (Exception e) {
                weatherTextPane.setText("<html><body style='font-family:sans-serif; color:red;'>Error fetching weather data: " + e.getMessage() + "</body></html>");
            }
        });
    }

    /**
     * Initializes the components of the panel.
     * <p>
     * Creates and configures the {@code weatherTextPane} to display HTML content and sets it as non-editable.
     */
    @Override
    protected void setupComponents() {
        weatherTextPane = new JTextPane();
        weatherTextPane.setContentType("text/html");
        weatherTextPane.setEditable(false);
    }

    /**
     * Configures the layout of the panel.
     * <p>
     * Adds a {@code TimePanel} to the top of the panel and the {@code weatherTextPane} inside a {@code JScrollPane} 
     * to the center of the panel.
     */
    @Override
    protected void configureLayout() {
        TimePanel timePanel = new TimePanel();
        add(timePanel, BorderLayout.NORTH);
        add(new JScrollPane(weatherTextPane), BorderLayout.CENTER);
    }

    /**
     * Formats the weather report for display in HTML.
     * <p>
     * Replaces newline characters in the weather report with HTML line breaks and wraps the text in basic HTML tags.
     * 
     * @param weatherReport the raw weather report text to format
     * @return a string containing the HTML formatted weather report
     */
    private String formatForHtml(String weatherReport) {
        return "<html><body style='font-family:Arial; color:black;'>" + weatherReport.replace("\n", "<br>") + "</body></html>";
    }
}
