package AdvertisementPanel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class AdvertisementPanel extends BorderPane {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/train_station_ads";
    private static final String USER = "root";
    private static final String PASS = "P@$$w0rd123";
    private static final int IMAGE_WIDTH = 800;
    private static final int IMAGE_HEIGHT = 600;

    private List<Image> images = new ArrayList<>();
    private int currentIndex = 0;
    private ImageView imageView;
    private Timeline timeline;

    public AdvertisementPanel() {
        imageView = new ImageView();
        imageView.setFitWidth(IMAGE_WIDTH);
        imageView.setFitHeight(IMAGE_HEIGHT);
        setCenter(imageView);

        loadImages();
        if (!images.isEmpty()) {
            displayNextImage();
            startTimer();
        }
    }

    private void loadImages() {
        String sql = "SELECT image FROM advertisements";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                byte[] imageBytes = rs.getBytes("image");
                if (imageBytes != null) {
                    Image img = new Image(new String(imageBytes));
                    images.add(img);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void displayNextImage() {
        if (images.isEmpty()) {
            return;
        }

        Image image = images.get(currentIndex);
        imageView.setImage(image);
    }

    private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(10), e -> {
            currentIndex = (currentIndex + 1) % images.size();
            displayNextImage();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
