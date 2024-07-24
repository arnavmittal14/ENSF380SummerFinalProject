import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdvertisementPanel extends JPanel {
    private List<Advertisement> ads;
    private int currentAdIndex;

    public AdvertisementPanel() {
        setLayout(new BorderLayout());
        ads = AdvertisementFetcher.fetchAds();
        currentAdIndex = 0;

        updateAdvertisement();

        Timer timer = new Timer(10000, e -> updateAdvertisement());
        timer.start();
    }

    private void updateAdvertisement() {
        if (ads.isEmpty()) return;

        Advertisement ad = ads.get(currentAdIndex);
        removeAll();
        JLabel label = new JLabel(ad.getTitle(), SwingConstants.CENTER);
        JLabel imageLabel = new JLabel(new ImageIcon(ad.getImageUrl()), SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);
        add(imageLabel, BorderLayout.CENTER);

        currentAdIndex = (currentAdIndex + 1) % ads.size();
        revalidate();
        repaint();
    }
}
