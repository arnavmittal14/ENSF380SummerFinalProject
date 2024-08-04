package TrainInfoPanel;

import Panel.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TrainInfoPanel extends Panel {
    private JLabel directionLabel;
    private JPanel stationsPanel;
    private JLabel currentStationIndicator;
    private static final Color PAST_STATION_COLOR = Color.decode("#C8ACD6");
    private static final Color CURRENT_STATION_COLOR = Color.decode("#2E236C");
    private static final Color FUTURE_STATION_COLOR = Color.decode("#C8ACD6");
    private static final Color BORDER_COLOR = Color.decode("#ffffff");
    private static final Color FONT_COLOR = Color.WHITE;
    private static final Color FONT_COLOR2 = Color.decode("#2E236C");
    private static final Color PANEL_BACKGROUND_COLOR = Color.decode("#433D8B");
    private static final int HEADER_PANEL_PADDING_TOP = 20;

    public TrainInfoPanel() {
        initUI();
    }

    @Override
    protected void setupComponents() {
        directionLabel = new JLabel("Direction: ", SwingConstants.CENTER);
        directionLabel.setFont(new Font("Sans-Serif", Font.BOLD, 18));
        directionLabel.setForeground(FONT_COLOR);

        stationsPanel = new JPanel();
        stationsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        stationsPanel.setBackground(PANEL_BACKGROUND_COLOR);
        stationsPanel.setPreferredSize(new Dimension(1000, 250));

        currentStationIndicator = new JLabel("", SwingConstants.CENTER);
        currentStationIndicator.setFont(new Font("Sans-Serif", Font.BOLD, 36));
        currentStationIndicator.setForeground(FUTURE_STATION_COLOR);
    }

    @Override
    protected void configureLayout() {
        setLayout(new BorderLayout());
        setBackground(PANEL_BACKGROUND_COLOR);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(PANEL_BACKGROUND_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(HEADER_PANEL_PADDING_TOP, 0, 0, 0));

        headerPanel.add(directionLabel, BorderLayout.NORTH);
        headerPanel.add(stationsPanel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.CENTER);
        add(currentStationIndicator, BorderLayout.SOUTH);
    }

    public void updatePanel(TrainRoute route, int trainId) {
        List<Line> lines = Line.getAllLines();
        String line = "";
        int lineNumber = trainId / 4;
        if (lineNumber == 0) {
            line = "Red";
        } else if (lineNumber == 1) {
            line = "Blue";
        } else {
            line = "Green";
        }

        Train train = lines.get(lineNumber).getTrains().get(trainId % 4);
        char direction = train.getDirection();

        directionLabel.setText("Line: " + line + " - Train: " + (train.getTrainId() + 1) + " - Moving: " + (direction == 'F' ? "Forward" : "Backwards"));

        stationsPanel.removeAll();
        Dimension labelSize = new Dimension(250, 100);

        List<Station> futureStations = route.getFutureStations(3);
        Station currentStation = route.getCurrentStation();
        Station pastStation = route.getPastStation();

        if (pastStation != null) {
            addStationLabel("Past: " + pastStation.getName(), PAST_STATION_COLOR, FONT_COLOR2, labelSize);
        }

        if (currentStation != null) {
            addStationLabel("Current: " + currentStation.getName(), CURRENT_STATION_COLOR, FONT_COLOR, labelSize);
        }

        for (Station station : futureStations) {
            addStationLabel(station.getName(), FUTURE_STATION_COLOR, FONT_COLOR2, labelSize);
        }

        revalidate();
        repaint();
    }

    private void addStationLabel(String text, Color backgroundColor, Color textColor, Dimension size) {
        JLabel stationLabel = new JLabel(text, SwingConstants.CENTER);
        stationLabel.setPreferredSize(size);
        stationLabel.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        stationLabel.setBorder(BorderFactory.createLineBorder(FONT_COLOR));
        stationLabel.setOpaque(true);
        stationLabel.setBackground(backgroundColor);
        stationLabel.setForeground(textColor);
        stationsPanel.add(stationLabel);

        if (stationsPanel.getComponentCount() < (4 + 1) * 2 - 1) {
            JLabel arrowLabel = new JLabel("→", SwingConstants.CENTER);
            arrowLabel.setFont(new Font("Sans-Serif", Font.BOLD, 20));
            arrowLabel.setForeground(FONT_COLOR);
            stationsPanel.add(arrowLabel);
        }
    }
}
