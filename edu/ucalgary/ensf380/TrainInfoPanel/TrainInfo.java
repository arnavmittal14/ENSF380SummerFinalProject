package TrainInfoPanel;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The {@code TrainInfo} class manages subway lines and trains, including initializing lines and trains, updating their positions,
 * and providing information about stations and trains.
 * <p>
 * It initializes subway lines, assigns trains to those lines, and periodically updates the positions of the trains.
 */
public class TrainInfo {
    /** The red subway line. */
    private Line red;

    /** The blue subway line. */
    private Line blue;

    /** The green subway line. */
    private Line green;

    /**
     * Constructs a {@code TrainInfo} object and initializes the subway lines and trains.
     * <p>
     * Reads station data from a CSV file, creates lines, assigns stations to lines based on station codes,
     * and randomly assigns trains to these lines. A scheduled executor updates train positions every 10 seconds.
     */
    public TrainInfo() {
        CSVReader csvReader = new CSVReader();
        List<Station> stations = csvReader.readStations("edu/ucalgary/ensf380/assets/Map.csv");

        this.red = new Line();
        this.blue = new Line();
        this.green = new Line();

        for (Station station : stations) {
            if (station.getCode().startsWith("R")) {
                red.addStation(station);
            } else if (station.getCode().startsWith("B")) {
                blue.addStation(station);
            } else {
                green.addStation(station);
            }
        }

        Line[] lines = {red, blue, green};

        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            Line line = lines[(int) (i / 4)];
            int stationLen = line.getStations().size();
            if (i % 2 == 0) {
                int max = (int) ((stationLen * 3) / 8);
                int min = (int) (stationLen / 8);
                int stationNo = random.nextInt(max - min) + min;
                line.addTrain(new Train(i, i % 4 == 0 ? 'F' : 'B', stationNo));
            } else {
                int max = (int) ((stationLen * 7) / 8);
                int min = (int) ((stationLen * 5) / 8);
                int stationNo = random.nextInt(max - min) + min;
                line.addTrain(new Train(i, (i - 1) % 4 == 0 ? 'F' : 'B', stationNo));
            }
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            updateTrainPositions(lines);
        }, 0, 10, TimeUnit.SECONDS);
    }

    /**
     * Updates the positions of all trains on each subway line.
     * <p>
     * Moves each train forward or backward depending on its current direction. 
     */
    private void updateTrainPositions(Line[] lines) {
        for (Line line : lines) {
            List<Train> trains = line.getTrains();
            for (Train train : trains) {
                if (train.getDirection() == 'F') {
                    train.incrementStation();
                } else {
                    train.decrementStation();
                }
            }
        }
    }

    /**
     * Retrieves a train by its unique identifier.
     *
     * @param trainId the unique identifier of the train
     * @return the {@code Train} object with the specified ID, or {@code null} if no such train exists
     */
    public Train getTrainById(int trainId) {
        for (Line line : new Line[]{red, blue, green}) {
            for (Train train : line.getTrains()) {
                if (train.getTrainId() == trainId) {
                    return train;
                }
            }
        }
        return null;
    }

    /**
     * Retrieves the previous station on a given line based on the station index.
     *
     * @param line          the subway line
     * @param stationIndex  the index of the current station
     * @return the previous {@code Station} object if it exists, or {@code null} if the current station is the first station
     */
    public Station getPreviousStation(Line line, int stationIndex) {
        if (stationIndex > 0) {
            return line.getStations().get(stationIndex - 1);
        }
        return null;
    }

    /**
     * Retrieves a list of the next stations on a given line starting from a specified station index.
     *
     * @param line          the subway line
     * @param stationIndex  the index of the current station
     * @return a {@code List} of {@code Station} objects representing the next stations on the line
     */
    public List<Station> getNextStations(Line line, int stationIndex) {
        List<Station> stations = line.getStations();
        int end = Math.min(stationIndex + 4, stations.size());
        return stations.subList(stationIndex + 1, end);
    }

    /**
     * Returns the red subway line.
     *
     * @return the {@code Line} object representing the red subway line
     */
    public Line getRedLine() {
        return red;
    }

    /**
     * Returns the blue subway line.
     *
     * @return the {@code Line} object representing the blue subway line
     */
    public Line getBlueLine() {
        return blue;
    }

    /**
     * Returns the green subway line.
     *
     * @return the {@code Line} object representing the green subway line
     */
    public Line getGreenLine() {
        return green;
    }
}
