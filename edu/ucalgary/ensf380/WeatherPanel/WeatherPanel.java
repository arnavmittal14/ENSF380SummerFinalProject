package WeatherPanel;
import javax.swing.*;
import java.awt.*;

import edu.ucalgary.ensf380.WeatherReport;
import Panel.Panel;


public class WeatherPanel extends Panel {

    private JTextPane weatherTextPane;

    public WeatherPanel(String city) {
        initUI();
        
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

    
    protected void setupComponents() {
        weatherTextPane = new JTextPane();
        weatherTextPane.setContentType("text/html");
        weatherTextPane.setEditable(false);
    }

    
    protected void configureLayout() {
        TimePanel timePanel = new TimePanel();
        add(timePanel, BorderLayout.NORTH);
        add(new JScrollPane(weatherTextPane), BorderLayout.CENTER);
    }

    private String formatForHtml(String weatherReport) {
        return "<html><body style='font-family:Arial; color:black;'>" + weatherReport.replace("\n", "<br>") + "</body></html>";
    }
}
