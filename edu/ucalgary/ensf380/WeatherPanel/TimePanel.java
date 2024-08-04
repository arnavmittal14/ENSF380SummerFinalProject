package WeatherPanel;

import Panel.Panel; 

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimePanel extends Panel {
    private JLabel timeLabel;

    public TimePanel() {
        super();
        initUI(); // Initialize UI components
    }

    @Override
    protected void setupComponents() {
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timeLabel.setForeground(Color.BLACK);
        updateTime();
        
        // Update time every second
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
    }

    @Override
    protected void configureLayout() {
        setLayout(new FlowLayout());
        add(timeLabel);
    }

    private void updateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeLabel.setText("Current Date: " + currentDateTime.format(formatter));
    }
}
