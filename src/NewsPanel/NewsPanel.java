package NewsPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NewsPanel extends JPanel {
    private static final int SCROLL_DELAY = 20; // Delay in milliseconds for smooth scrolling
    private static final int SCROLL_SPEED = 2; // Number of pixels to move per step

    private JLabel newsLabel;
    private List<String> headlines;
    private int currentIndex = 0;
    private int xPosition;

    public NewsPanel(List<String> headlines) {
        this.headlines = headlines;
        this.newsLabel = new JLabel("");
        this.newsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        this.newsLabel.setForeground(Color.BLACK);
        setLayout(null); // Use null layout for custom positioning
        add(newsLabel);
        setBackground(Color.WHITE);
        startScrolling();
    }

    private void startScrolling() {
        if (headlines == null || headlines.isEmpty()) {
            newsLabel.setText("No news available.");
            return;
        }

        // Start with the first headline
        updateHeadline();
        xPosition = getWidth();

        Timer timer = new Timer(SCROLL_DELAY, e -> {
            xPosition -= SCROLL_SPEED;
            newsLabel.setBounds(xPosition, 0, getWidth(), getHeight());
            if (xPosition + newsLabel.getPreferredSize().width < 0) {
                // Move to the next headline
                currentIndex = (currentIndex + 1) % headlines.size();
                updateHeadline();
                xPosition = getWidth();
            }
            repaint();
        });
        timer.start();
    }

    private void updateHeadline() {
        String text = headlines.get(currentIndex);
        newsLabel.setText(text);
        // Update the size of the label based on the new text
        newsLabel.setSize(newsLabel.getPreferredSize());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 50); // Set the preferred size of the news panel
    }
}

