package NewsPanel;

import Panel.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The {@code NewsPanel} class displays scrolling news headlines in a panel.
 * It extends {@link Panel} to use a custom layout and component initialization.
 * <p>
 * The news headlines scroll horizontally across the panel, with a new headline displayed 
 * when the current one scrolls out of view. The scrolling speed and delay are controlled by
 * constants in the class.
 */
public class NewsPanel extends Panel {

    private static final int SCROLL_DELAY = 20; // Delay in milliseconds between scroll updates
    private static final int SCROLL_SPEED = 2;  // Pixels to scroll per update

    private JLabel newsLabel;    // Label to display news headlines
    private List<String> headlines; // List of news headlines to be displayed
    private int currentIndex = 0; // Index of the currently displayed headline
    private int xPosition;        // Current x position of the news label for scrolling

    /**
     * Constructs a {@code NewsPanel} with the given list of headlines.
     * 
     * @param headlines a list of news headlines to be displayed
     */
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

    /**
     * Starts the scrolling of news headlines across the panel.
     * <p>
     * If no headlines are provided, displays a message indicating that no news is available.
     * The headlines scroll horizontally, and a new headline is displayed when the current one
     * scrolls out of view.
     */
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

    /**
     * Updates the {@code newsLabel} to display the current headline.
     */
    private void updateHeadline() {
        String text = headlines.get(currentIndex);
        newsLabel.setText(text);
        newsLabel.setSize(newsLabel.getPreferredSize());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 100);
    }
}
