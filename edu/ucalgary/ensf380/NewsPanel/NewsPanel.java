package NewsPanel;

import Panel.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NewsPanel extends Panel {

    private static final int SCROLL_DELAY = 20;
    private static final int SCROLL_SPEED = 2;
    private JLabel newsLabel;
    private List<String> headlines;
    private int currentIndex = 0;
    private int xPosition;

    public NewsPanel(List<String> headlines) {
        this.headlines = headlines;
        initUI();
        startScrolling();
    }

    @Override
    protected void setupComponents() {
        newsLabel = new JLabel("");
        newsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        newsLabel.setForeground(Color.WHITE);
    }

    @Override
    protected void configureLayout() {
        setLayout(null);
        add(newsLabel);
        setBackground(Color.DARK_GRAY);
    }

    private void startScrolling() {
        if (headlines == null || headlines.isEmpty()) {
            newsLabel.setText("No news available.");
            return;
        }
        updateHeadline();
        xPosition = getWidth();
        Timer timer = new Timer(SCROLL_DELAY, e -> {
            xPosition -= SCROLL_SPEED;
            newsLabel.setBounds(xPosition, 0, getWidth(), getHeight());
            if (xPosition + newsLabel.getPreferredSize().width < 0) {
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
        newsLabel.setSize(newsLabel.getPreferredSize());
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 100);
    }
}
