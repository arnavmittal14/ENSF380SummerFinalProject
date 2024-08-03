package TrainInfoPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TrainInfoPanel extends JPanel {
    private JLabel directionLabel;
    private JPanel stationsPanel;
    private JLabel currentStationIndicator;

    private static final Color PAST_STATION_COLOR = Color.decode("#050C9C"); // Dark blue
    private static final Color CURRENT_STATION_COLOR = Color.decode("#3572EF"); // Blue
    private static final Color FUTURE_STATION_COLOR = Color.decode("#3ABEF9"); // Light blue
    private static final Color BORDER_COLOR = Color.decode("#ffffff"); // White
    private static final Color FONT_COLOR = Color.WHITE; // White font color
    private static final Color FONT_COLOR2 = Color.BLACK; // Black font color
    private static final Color PANEL_BACKGROUND_COLOR = Color.decode("#A7E6FF"); // Background color for the panel
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
        stationsPanel.setBackground(PANEL_BACKGROUND_COLOR); // Set background color for the stations panel
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
            JLabel pastStationLabel = new JLabel("Past: " + pastStation.getName(), SwingConstants.CENTER); // Center text
            pastStationLabel.setPreferredSize(labelSize); // Set preferred size
            pastStationLabel.setFont(new Font("Sans-Serif", Font.BOLD, 16)); // Larger font size
            pastStationLabel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
            pastStationLabel.setOpaque(true);
            pastStationLabel.setBackground(PAST_STATION_COLOR); // Background for past station
            pastStationLabel.setForeground(FONT_COLOR); // Set font color to white
            stationsPanel.add(pastStationLabel);
        }

        // Add current station to the panel with highlighting
        if (currentStation != null) {
            JLabel currentStationLabel = new JLabel("Current: " + currentStation.getName(), SwingConstants.CENTER); // Center text
            currentStationLabel.setPreferredSize(labelSize); // Set preferred size
            currentStationLabel.setFont(new Font("Sans-Serif", Font.BOLD, 16)); // Larger font size
            currentStationLabel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
            currentStationLabel.setOpaque(true);
            currentStationLabel.setBackground(CURRENT_STATION_COLOR); // Highlight current station
            currentStationLabel.setForeground(FONT_COLOR); // Set font color to white
            stationsPanel.add(currentStationLabel);
        }

        // Add future stations to the panel
        for (Station station : futureStations) {
            JLabel stationLabel = new JLabel(station.getName(), SwingConstants.CENTER); // Center text
            stationLabel.setPreferredSize(labelSize); // Set preferred size
            stationLabel.setFont(new Font("Sans-Serif", Font.BOLD, 16)); // Larger font size
            stationLabel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));
            stationLabel.setOpaque(true);
            stationLabel.setBackground(FUTURE_STATION_COLOR); // Regular background for future stations
            stationLabel.setForeground(FONT_COLOR); // Set font color to white
            stationsPanel.add(stationLabel);
        }

        // Refresh UI to apply changes
        revalidate();
        repaint();
    }
}
