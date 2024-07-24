package WeatherPanel;
import javax.swing.*;
import java.awt.*;

public class WeatherPanel extends JPanel {
    public WeatherPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Weather Info Here", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        // Fetch and update weather info
        updateWeather();
    }

    private void updateWeather() {
        // Logic to fetch and display weather info goes here
    }
}
