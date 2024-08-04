package AdvertisementPanel;

import Panel.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class AdvertisementPanel extends Panel {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/train_station_ads";
    private static final String USER = "root";
    private static final String PASS = "P@$$w0rd123";
    private static final int IMAGE_WIDTH = 1607; 
    private static final int IMAGE_HEIGHT = 750; 

    private List<BufferedImage> images = new ArrayList<>();
    private int currentIndex = 0;
    private Timer timer;

    public AdvertisementPanel() {
        initUI();
        loadImages();
        if (!images.isEmpty()) {
            displayNextImage();
            startTimer();
        }
    }

    @Override
    protected void setupComponents() {
    }

    @Override
    protected void configureLayout() {
        setLayout(new BorderLayout());
    }

    private void loadImages() {
        String sql = "SELECT image FROM advertisements";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                byte[] imageBytes = rs.getBytes("image");
                if (imageBytes != null) {
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
                    if (img != null) {
                        BufferedImage resizedImg = resizeImage(img, IMAGE_WIDTH, IMAGE_HEIGHT);
                        images.add(resizedImg);
                    }
                }
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();
        return newImage;
    }

    private void displayNextImage() {
        if (images.isEmpty()) {
            return;
        }
        removeAll();
        BufferedImage img = images.get(currentIndex);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        add(imageLabel, BorderLayout.CENTER);
        revalidate();
        repaint();
        currentIndex = (currentIndex + 1) % images.size();
    }

    private void startTimer() {
        timer = new Timer(10000, e -> displayNextImage());
        timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT);
    }
}
