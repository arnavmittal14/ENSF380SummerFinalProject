package WeatherPanel;

import javafx.application.Platform;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimePanel extends FlowPane {
    private Label timeLabel;

    public TimePanel() {
        timeLabel = new Label();
        timeLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 16;");
        updateTime();
        getChildren().add(timeLabel);

        // Update time every second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTime()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        Platform.runLater(() -> timeLabel.setText("Current Time: " + currentTime.format(formatter)));
    }
}
