package TrainInfoPanel;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import java.util.List;

public class TrainInfoPanel extends BorderPane {
    private Label directionLabel;
    private FlowPane stationsPanel;
    private Label currentStationIndicator;

    public TrainInfoPanel() {
        directionLabel = new Label("Direction: ");
        directionLabel.setFont(new Font("Arial", 18));
        setTop(directionLabel);

        stationsPanel = new FlowPane();
        setCenter(stationsPanel);

        currentStationIndicator = new Label("→");
//        currentStationIndicator.setFont(new Font("Arial", Font.BOLD, 24));
        setBottom(currentStationIndicator);
    }

    public void updatePanel(TrainRoute route) {
        directionLabel.setText("Direction: " + route.getTrainDirection());

        stationsPanel.getChildren().clear();

        List<Station> futureStations = route.getFutureStations(3);
        Station currentStation = route.getCurrentStation();
        Station pastStation = route.getPastStation();

        if (pastStation != null) {
            Label pastStationLabel = new Label("Past: " + pastStation.getName());
            pastStationLabel.setStyle("-fx-border-color: black; -fx-background-color: lightgray;");
            stationsPanel.getChildren().add(pastStationLabel);
        }

        if (currentStation != null) {
            Label currentStationLabel = new Label("Current: " + currentStation.getName());
            currentStationLabel.setStyle("-fx-border-color: black; -fx-background-color: yellow;");
            stationsPanel.getChildren().add(currentStationLabel);
        }

        for (Station station : futureStations) {
            Label stationLabel = new Label(station.getName());
            stationLabel.setStyle("-fx-border-color: black; -fx-background-color: white;");
            stationsPanel.getChildren().add(stationLabel);
        }
    }
}
