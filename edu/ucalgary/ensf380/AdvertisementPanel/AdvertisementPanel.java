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

/**
 * The {@code AdvertisementPanel} class is a Swing panel that displays rotating advertisements
 * loaded from a MySQL database. The advertisements are images fetched from a database and are
 * displayed one at a time, changing at regular intervals.
 * <p>
 * This class extends {@link Panel} and uses a {@link Timer} to rotate through the images.
 * The images are resized to fit the panel's dimensions.
 */
public class AdvertisementPanel extends Panel {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/train_station_ads";
    private static final String USER = "root";
    private static final String PASS = "P@$$w0rd123";
    private static final int IMAGE_WIDTH = 1607;
    private static final int IMAGE_HEIGHT = 750;

    private List<BufferedImage> images = new ArrayList<>();
    private int currentIndex = 0;
    private Timer timer;

    /**
     * Constructs an {@code AdvertisementPanel}, initializes the user interface,
     * loads images from the database, and starts displaying them.
     */
    public AdvertisementPanel() {
        initUI();
        loadImages();
        if (!images.isEmpty()) {
            displayNextImage();
            startTimer();
        }
    }

    /**
     * Sets up components for the panel. This method is abstract in the {@link Panel}
     * class and must be implemented by subclasses. In this implementation, it is
     * left empty as there are no additional components to set up.
     */
    @Override
    protected void setupComponents() {
    }

    /**
     * Configures the layout of the panel. This method sets the layout to {@link BorderLayout}.
     */
    @Override
    protected void configureLayout() {
        setLayout(new BorderLayout());
    }

    /**
     * Loads images from the database and resizes them to fit the panel's dimensions.
     * The images are retrieved from the "advertisements" table in the MySQL database.
     */
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

    /**
     * Resizes the given image to the specified width and height.
     *
     * @param originalImage the original image to be resized
     * @param width         the desired width of the resized image
     * @param height        the desired height of the resized image
     * @return the resized image
     */
    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();
        return newImage;
    }

    /**
     * Displays the next image in the list of loaded images. The image is displayed
     * in a {@link JLabel} centered within the panel.
     */
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

    /**
     * Starts a {@link Timer} that rotates through the images at a fixed interval.
     * The interval is set to 10 seconds (10000 milliseconds).
     */
    private void startTimer() {
        timer = new Timer(10000, e -> displayNextImage());
        timer.start();
    }

    /**
     * Returns the preferred size of the panel, which is set to the dimensions of the images.
     *
     * @return the preferred size of the panel
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT);
    }
}
