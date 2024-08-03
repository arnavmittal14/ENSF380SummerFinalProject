package TrainInfoPanel;

import java.util.ArrayList;
import java.util.List;

public class Line {
	private static List<Line> lines = new ArrayList<>();
    private List<Station> stations;
    private List<Train> trains;

    public Line() {
    	this.stations = new ArrayList<>();
        this.trains = new ArrayList<>();
        Line.lines.add(this);
    }

    public void addTrain(Train train) {
        trains.add(train);
    }

    public List<Train> getTrains() {
        return trains;
    }
    
    public void addStation(Station station) {
    	stations.add(station);
    }

    public List<Station> getStations() {
        return stations;
    }
    
    public static List<Line> getAllLines() {
    	return Line.lines;
    }
}
