package NewsPanel;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.List;

public class NewsPanel extends Pane {
    private static final int SCROLL_DELAY = 20; // Delay in milliseconds for smooth scrolling
    private static final int SCROLL_SPEED = 2; // Number of pixels to move per step

    private Label newsLabel;
    private List<String> headlines;
    private int currentIndex = 0;
    private double xPosition;

    public NewsPanel(List<String> headlines) {
        this.headlines = headlines;
        this.newsLabel = new Label("");
        this.newsLabel.setFont(new Font("Arial", 24));
        this.newsLabel.setWrapText(true);
        this.newsLabel.setStyle("-fx-text-fill: black;");
        setStyle("-fx-background-color: white;");
        getChildren().add(newsLabel);
        startScrolling();
    }

    private void startScrolling() {
        if (headlines == null || headlines.isEmpty()) {
            newsLabel.setText("No news available.");
            return;
        }

        updateHeadline();
        xPosition = getWidth();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(SCROLL_DELAY), e -> {
            xPosition -= SCROLL_SPEED;
            newsLabel.setLayoutX(xPosition);
            if (xPosition + newsLabel.getWidth() < 0) {
                currentIndex = (currentIndex + 1) % headlines.size();
                updateHeadline();
                xPosition = getWidth();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateHeadline() {
        newsLabel.setText(headlines.get(currentIndex));
        newsLabel.applyCss();
        newsLabel.layout();
        newsLabel.setLayoutX(xPosition);
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        // Ensure the news label is sized and positioned correctly
        newsLabel.setPrefWidth(getWidth());
    }
}
