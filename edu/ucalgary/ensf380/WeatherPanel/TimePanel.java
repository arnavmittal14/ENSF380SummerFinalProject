package WeatherPanel;

import Panel.Panel; 

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code TimePanel} class extends {@code Panel} and displays the current date and time on the panel.
 * <p>
 * This class updates the displayed time every second to ensure it remains current. The time is formatted 
 * as "yyyy-MM-dd HH:mm:ss".
 */
public class TimePanel extends Panel {
    /** Label to display the current date and time. */
    private JLabel timeLabel;

    /**
     * Constructs a {@code TimePanel} object and initializes the user interface.
     * <p>
     * The constructor sets up the components and starts a timer to update the time every second.
     */
    public TimePanel() {
        super();
        initUI(); 
    }

    /**
     * Sets up the components of the panel.
     * <p>
     * Initializes the {@code timeLabel} with a font and color, and sets up a timer to call {@code updateTime()} 
     * every second.
     */
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

    /**
     * Configures the layout of the panel.
     * <p>
     * Uses a {@code FlowLayout} to arrange components and adds {@code timeLabel} to the panel.
     */
    @Override
    protected void configureLayout() {
        setLayout(new FlowLayout());
        add(timeLabel);
    }

    /**
     * Updates the {@code timeLabel} with the current date and time.
     * <p>
     * The date and time are formatted as "yyyy-MM-dd HH:mm:ss" using {@code DateTimeFormatter}.
     */
    private void updateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        timeLabel.setText("Current Date: " + currentDateTime.format(formatter));
    }
}
