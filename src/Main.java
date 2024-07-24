import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Subway Screen Project");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Create panels
            AdvertisementPanel adPanel = new AdvertisementPanel();
            WeatherPanel weatherPanel = new WeatherPanel();
            NewsPanel newsPanel = new NewsPanel();
            TrainInfoPanel trainInfoPanel = new TrainInfoPanel();

            // Add panels to the frame
            frame.add(adPanel, BorderLayout.CENTER);
            frame.add(weatherPanel, BorderLayout.NORTH);
            frame.add(newsPanel, BorderLayout.SOUTH);
            frame.add(trainInfoPanel, BorderLayout.EAST);

            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}
