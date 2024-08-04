package TrainInfoPanel;

/**
 * The {@code Train} class represents a train operating on a subway line.
 * <p>
 * Each {@code Train} object contains information about the train's ID, direction, and the current station number.
 */
public class Train {

    /** The unique identifier for the train. */
    private int trainId;

    /** The direction in which the train is moving ('F' for forward, 'B' for backward). */
    private char direction;

    /** The current station number where the train is located. */
    private int stationNo;

    /**
     * Constructs a new {@code Train} object with the specified ID, direction, and current station number.
     *
     * @param trainId    the unique identifier for the train
     * @param direction  the direction in which the train is moving ('F' or 'B')
     * @param stationNo  the current station number where the train is located
     */
    public Train(int trainId, char direction, int stationNo) {
        this.trainId = trainId;
        this.direction = direction;
        this.setStationNo(stationNo);
    }

    /**
     * Returns the unique identifier for the train.
     *
     * @return the train ID
     */
    public int getTrainId() {
        return trainId;
    }

    /**
     * Returns the direction in which the train is moving.
     *
     * @return the direction of the train ('F' for forward, 'B' for backward)
     */
    public char getDirection() {
        return direction;
    }

    /**
     * Increments the station number of the train.
     * <p>
     * If the train reaches the last station on its line, it changes direction to backward and moves to the previous station.
     * If the train is not at the last station, it moves to the next station.
     */
    public void incrementStation() {
        int trainLine = (int) (trainId / 4);
        Line line = Line.getAllLines().get(trainLine);
        if (this.getStationNo() == line.getStations().size()) {
            this.direction = 'B';
            this.setStationNo(this.getStationNo() - 1);
        } else {
            this.setStationNo(this.getStationNo() + 1);
        }
    }

    /**
     * Decrements the station number of the train.
     * <p>
     * If the train reaches the first station on its line, it changes direction to forward and moves to the next station.
     * If the train is not at the first station, it moves to the previous station.
     */
    public void decrementStation() {
        if (this.getStationNo() == 1) {
            this.direction = 'F';
            this.setStationNo(this.getStationNo() + 1);
        } else {
            this.setStationNo(this.getStationNo() - 1);
        }
    }

    /**
     * Returns a string representation of the train, including its ID, current station, and direction.
     *
     * @return a string representation of the train
     */
    @Override
    public String toString() {
        int trainLine = (int) (trainId / 4);
        char code = trainLine == 0 ? 'R' : (trainLine == 1 ? 'B' : 'G');
        return "Train " + trainId + " : Station " + code + getStationNo() + " Direction " + direction;
    }

    /**
     * Returns the current station number where the train is located.
     *
     * @return the current station number
     */
    public int getStationNo() {
        return stationNo;
    }

    /**
     * Sets the current station number where the train is located.
     *
     * @param stationNo the new station number
     */
    public void setStationNo(int stationNo) {
        this.stationNo = stationNo;
    }
}
