package WeatherPanel;

import javax.swing.*;

import edu.ucalgary.ensf380.WeatherReport;

import java.awt.*;

public class WeatherPanel extends JPanel {

    private JTextPane weatherTextPane;

    public WeatherPanel(String city) {
        setLayout(new BorderLayout());
        weatherTextPane = new JTextPane();
        weatherTextPane.setContentType("text/html");
        weatherTextPane.setEditable(false);
        add(new JScrollPane(weatherTextPane), BorderLayout.CENTER);

        // Fetch weather data
        SwingUtilities.invokeLater(() -> {
            try {
                String weatherReport = WeatherReport.getWeatherReport(city);
                weatherTextPane.setText(formatForHtml(weatherReport));
            } catch (Exception e) {
                weatherTextPane.setText("<html><body style='font-family:sans-serif; color:red;'>Error fetching weather data: " + e.getMessage() + "</body></html>");
            }
        });
    }

    private String formatForHtml(String weatherReport) {
        // Replace newlines with <br> tags and set font
        String htmlFormatted = "<html><body style='font-family:Courier New; color:darkgray;'>" + weatherReport.replace("\n", "<br>") + "</body></html>";
        return htmlFormatted;
    }
}
