package TrainInfoPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Line} class represents a subway line, which contains a list of stations and trains.
 * <p>
 * Each {@code Line} object manages its own collection of stations and trains. It also maintains a static
 * list of all {@code Line} objects created, which can be retrieved using the {@link #getAllLines()} method.
 */
public class Line {

    /** 
     * A static list of all {@code Line} instances created. 
     * This allows for retrieval of all lines within the system.
     */
    private static List<Line> lines = new ArrayList<>();

    /** 
     * A list of stations associated with this line. 
     */
    private List<Station> stations;

    /** 
     * A list of trains associated with this line. 
     */
    private List<Train> trains;

    /**
     * Constructs a new {@code Line} object and adds it to the static list of lines.
     */
    public Line() {
        this.stations = new ArrayList<>();
        this.trains = new ArrayList<>();
        Line.lines.add(this);
    }

    /**
     * Adds a {@code Train} to this line's list of trains.
     *
     * @param train the {@code Train} to be added to this line
     */
    public void addTrain(Train train) {
        trains.add(train);
    }

    /**
     * Returns the list of trains associated with this line.
     *
     * @return a list of {@code Train} objects on this line
     */
    public List<Train> getTrains() {
        return trains;
    }

    /**
     * Adds a {@code Station} to this line's list of stations.
     *
     * @param station the {@code Station} to be added to this line
     */
    public void addStation(Station station) {
        stations.add(station);
    }

    /**
     * Returns the list of stations associated with this line.
     *
     * @return a list of {@code Station} objects on this line
     */
    public List<Station> getStations() {
        return stations;
    }

    /**
     * Returns the static list of all {@code Line} objects created.
     *
     * @return a list of all {@code Line} objects
     */
    public static List<Line> getAllLines() {
        return Line.lines;
    }
}
