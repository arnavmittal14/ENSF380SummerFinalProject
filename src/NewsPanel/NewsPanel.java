package NewsPanel;
import javax.swing.*;
import java.awt.*;

public class NewsPanel extends JPanel {
    private JLabel newsLabel;

    public NewsPanel() {
        setLayout(new BorderLayout());
        newsLabel = new JLabel("News Ticker Here", SwingConstants.CENTER);
        add(newsLabel, BorderLayout.CENTER);

        // Timer to update news every 15 seconds
        Timer timer = new Timer(15000, e -> updateNews());
        timer.start();
    }

    private void updateNews() {
        // Logic to fetch and display news goes here
    }
}
