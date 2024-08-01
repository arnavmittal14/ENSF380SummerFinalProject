package WeatherPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimePanel extends JPanel {
    private JLabel timeLabel;

    public TimePanel() {
        setLayout(new FlowLayout());
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        updateTime();
        add(timeLabel);

        // Update time every second
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
    }

    private void updateTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        timeLabel.setText("Current Time: " + currentTime.format(formatter));
    }
}
