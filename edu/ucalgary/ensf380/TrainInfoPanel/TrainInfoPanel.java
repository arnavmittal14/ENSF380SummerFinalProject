package TrainInfoPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TrainInfoPanel extends JPanel {
    private JLabel directionLabel;
    private JPanel stationsPanel;
    private JLabel currentStationIndicator;
    private static final Color PAST_STATION_COLOR = Color.decode("#C8ACD6");
    private static final Color CURRENT_STATION_COLOR = Color.decode("#2E236C"); // Blue
    private static final Color FUTURE_STATION_COLOR = Color.decode("#C8ACD6"); // Light blue
    private static final Color BORDER_COLOR = Color.decode("#ffffff"); // White
    private static final Color FONT_COLOR = Color.WHITE; // White font color
    private static final Color FONT_COLOR2 = Color.decode("#2E236C"); // Black font color
    private static final Color PANEL_BACKGROUND_COLOR = Color.decode("#433D8B"); // Background color for the panel
    private static final int HEADER_PANEL_PADDING_TOP = 20; // Padding top for the header panel

    public TrainInfoPanel() {
        setLayout(new BorderLayout());
        setBackground(PANEL_BACKGROUND_COLOR); // Set background color for the panel

        // Create a panel for the station labels and direction
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(PANEL_BACKGROUND_COLOR); // Set background color for the header panel
        headerPanel.setBorder(BorderFactory.createEmptyBorder(HEADER_PANEL_PADDING_TOP, 0, 0, 0)); // Set padding top

        // Add direction label
        directionLabel = new JLabel("Direction: ", SwingConstants.CENTER);
        directionLabel.setFont(new Font("Sans-Serif", Font.BOLD, 18)); // Larger font size
        directionLabel.setForeground(FONT_COLOR); // Set font color to white
        headerPanel.add(directionLabel, BorderLayout.NORTH);

        // Create panel for stations and add it to the center of the header panel
        stationsPanel = new JPanel();
        stationsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center components with gaps
        stationsPanel.setBackground(PANEL_BACKGROUND_COLOR);
        stationsPanel.setPreferredSize(new Dimension(1000, 250)); // Set width and height
// Set background color for the stations panel
        headerPanel.add(stationsPanel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.CENTER);

        // Add current station indicator (e.g., an arrow) to the bottom of the panel
        currentStationIndicator = new JLabel("", SwingConstants.CENTER);
        currentStationIndicator.setFont(new Font("Sans-Serif", Font.BOLD, 36)); // Larger font size for the arrow
        currentStationIndicator.setForeground(FUTURE_STATION_COLOR); // Set font color
        add(currentStationIndicator, BorderLayout.SOUTH);
    }

    public void updatePanel(TrainRoute route) {
        // Update direction label
        directionLabel.setText("Direction: " + route.getTrainDirection());

        // Clear previous station labels
        stationsPanel.removeAll();

        // Define preferred size for station labels
        Dimension labelSize = new Dimension(250, 100); // Width and height for each label

        // Get stations
        List<Station> futureStations = route.getFutureStations(3); // Get 4 future stations
        Station currentStation = route.getCurrentStation();
        Station pastStation = route.getPastStation();

        // Add past station to the panel
        if (pastStation != null) {
            addStationLabel("Past: " + pastStation.getName(), PAST_STATION_COLOR, FONT_COLOR2, labelSize);
        }

        // Add current station to the panel with highlighting
        if (currentStation != null) {
            addStationLabel("Current: " + currentStation.getName(), CURRENT_STATION_COLOR, FONT_COLOR, labelSize);
        }

        // Add future stations to the panel
        for (Station station : futureStations) {
            addStationLabel(station.getName(), FUTURE_STATION_COLOR, FONT_COLOR2, labelSize);
        }

        // Refresh UI to apply changes
        revalidate();
        repaint();
    }

    private void addStationLabel(String text, Color backgroundColor, Color textColor, Dimension size) {
        JLabel stationLabel = new JLabel(text, SwingConstants.CENTER); // Center text
        stationLabel.setPreferredSize(size); // Set preferred size
        stationLabel.setFont(new Font("Sans-Serif", Font.BOLD, 16)); // Larger font size
        stationLabel.setBorder(BorderFactory.createLineBorder(FONT_COLOR));
        stationLabel.setOpaque(true);
        stationLabel.setBackground(backgroundColor); // Set background color
        stationLabel.setForeground(textColor); // Set font color
        stationsPanel.add(stationLabel);

        // Add arrow label if it's not the last station
        if (stationsPanel.getComponentCount() < (4 + 1) * 2 - 1) { // Number of future stations + past and current stations
            JLabel arrowLabel = new JLabel("→", SwingConstants.CENTER);
            arrowLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20)); // Adjust font size for the arrow
            arrowLabel.setForeground(FONT_COLOR); // Arrow color
            stationsPanel.add(arrowLabel);
        }
    }
}
