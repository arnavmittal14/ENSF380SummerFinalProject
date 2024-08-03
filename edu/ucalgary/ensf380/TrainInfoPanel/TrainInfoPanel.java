package TrainInfoPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TrainInfoPanel extends JPanel {
    private JLabel directionLabel;
    private JPanel stationsPanel;
    private JLabel currentStationIndicator;

    public TrainInfoPanel() {
        setLayout(new BorderLayout());

        // Create a panel for the station labels and direction
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());

        // Add direction label
        directionLabel = new JLabel("Direction: ", SwingConstants.CENTER);
        headerPanel.add(directionLabel, BorderLayout.NORTH);

        // Create panel for stations and add it to the center of the header panel
        stationsPanel = new JPanel();
        stationsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.add(stationsPanel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.CENTER);

        // Add current station indicator (e.g., an arrow) to the bottom of the panel
        currentStationIndicator = new JLabel("→", SwingConstants.CENTER);
        currentStationIndicator.setFont(new Font("Arial", Font.BOLD, 24));
        add(currentStationIndicator, BorderLayout.SOUTH);
    }

    public void updatePanel(TrainRoute route) {
        // Update direction label
        directionLabel.setText("Direction: " + route.getTrainDirection());

        // Clear previous station labels
        stationsPanel.removeAll();

        // Get stations
        List<Station> futureStations = route.getFutureStations(4); // Get 4 future stations
        Station currentStation = route.getCurrentStation();
        Station pastStation = route.getPastStation();

        // Add past station to the panel
        if (pastStation != null) {
            JLabel pastStationLabel = new JLabel("Past: " + pastStation.getName());
            pastStationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            pastStationLabel.setOpaque(true);
            pastStationLabel.setBackground(Color.LIGHT_GRAY); // Background for past station
            stationsPanel.add(pastStationLabel);
        }

        // Add current station to the panel with highlighting
        if (currentStation != null) {
            JLabel currentStationLabel = new JLabel("Current: " + currentStation.getName());
            currentStationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            currentStationLabel.setOpaque(true);
            currentStationLabel.setBackground(Color.YELLOW); // Highlight current station
            stationsPanel.add(currentStationLabel);
        }

        // Add future stations to the panel
        for (Station station : futureStations) {
            JLabel stationLabel = new JLabel(station.getName());
            stationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            stationLabel.setOpaque(true);
            stationLabel.setBackground(Color.WHITE); // Regular background for future stations
            stationsPanel.add(stationLabel);
        }

        // Refresh UI to apply changes
        revalidate();
        repaint();
    }
}
