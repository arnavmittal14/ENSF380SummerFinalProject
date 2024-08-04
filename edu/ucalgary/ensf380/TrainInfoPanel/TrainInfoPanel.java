package TrainInfoPanel;

import Panel.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The {@code TrainInfoPanel} class represents a GUI panel that displays information about a specific train's route,
 * including the train's direction, the current station, past stations, and future stations.
 * <p>
 * This panel updates dynamically based on the train's route and displays the information in a visually organized manner.
 */
public class TrainInfoPanel extends Panel {
    /** Label displaying the train's direction. */
    private JLabel directionLabel;

    /** Panel containing the station labels. */
    private JPanel stationsPanel;

    /** Label indicating the current station. */
    private JLabel currentStationIndicator;

    /** Color for past station labels. */
    private static final Color PAST_STATION_COLOR = Color.decode("#C8ACD6");

    /** Color for the current station label. */
    private static final Color CURRENT_STATION_COLOR = Color.decode("#2E236C");

    /** Color for future station labels. */
    private static final Color FUTURE_STATION_COLOR = Color.decode("#C8ACD6");

    /** Color for borders. */
    private static final Color BORDER_COLOR = Color.decode("#ffffff");

    /** Color for text in general. */
    private static final Color FONT_COLOR = Color.WHITE;

    /** Alternative color for text. */
    private static final Color FONT_COLOR2 = Color.decode("#2E236C");

    /** Background color of the panel. */
    private static final Color PANEL_BACKGROUND_COLOR = Color.decode("#433D8B");

    /** Padding at the top of the header panel. */
    private static final int HEADER_PANEL_PADDING_TOP = 20;

    /**
     * Constructs a {@code TrainInfoPanel} object and initializes the user interface components.
     */
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

    /**
     * Updates the panel with information about a specific train's route.
     * <p>
     * Updates the direction label, clears and repopulates the stations panel with labels for the past, current,
     * and future stations based on the provided train route and train ID.
     *
     * @param route  the {@code TrainRoute} object containing the train's route information
     * @param trainId  the unique identifier of the train
     */
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

    /**
     * Adds a station label to the {@code stationsPanel} with specified text, background color, text color, and size.
     * <p>
     * Optionally adds an arrow label between station labels if the number of components is less than a specified number.
     *
     * @param text           the text to display on the label
     * @param backgroundColor  the background color of the label
     * @param textColor        the color of the text
     * @param size             the preferred size of the label
     */
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
