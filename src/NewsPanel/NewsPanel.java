package NewsPanel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NewsPanel extends JPanel {
    private static final int SCROLL_DELAY = 10000; // milliseconds
    private static final String INITIAL_TEXT = "Fetching news...";

    private JLabel newsLabel;
    private List<String> headlines;
    private int currentIndex = 0;

    public NewsPanel(List<String> headlines) {
        this.headlines = headlines;
        this.newsLabel = new JLabel(INITIAL_TEXT);
        this.newsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.newsLabel.setForeground(Color.BLACK);
        setLayout(new BorderLayout());
        add(newsLabel, BorderLayout.CENTER);
        startScrolling();
    }

    private void startScrolling() {
        Timer timer = new Timer(SCROLL_DELAY, e -> {
            if (headlines != null && !headlines.isEmpty()) {
                String text = headlines.get(currentIndex);
                newsLabel.setText(text);
                currentIndex = (currentIndex + 1) % headlines.size();
            }
        });
        timer.start();
    }
}
