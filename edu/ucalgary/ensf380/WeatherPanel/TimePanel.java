package WeatherPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimePanel extends JPanel {
    private JLabel timeLabel;

    public TimePanel() {
        setLayout(new FlowLayout());
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timeLabel.setForeground(Color.BLACK);
        updateTime();
        add(timeLabel);

        // Update time every second
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
    }

    private void updateTime() {
    	LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeLabel.setText("Current Date: " + currentDateTime.format(formatter));
    }
}
