package TrainInfoPanel;

import java.util.List;

/**
 * The {@code TrainRoute} class represents the route of a train, including its current station, 
 * the direction of travel, and the ability to move to the next station or retrieve future and past stations.
 * <p>
 * This class provides methods to get the next station, future stations, and the current station, as well as
 * to move the train forward or backward and determine the direction of travel.
 */
public class TrainRoute {
    /** List of stations in the train route. */
    private List<Station> stations;

    /** The index of the current station in the route. */
    private int currentIndex;

    /** Boolean indicating whether the train is moving forward. */
    private boolean movingForward;

    /**
     * Constructs a {@code TrainRoute} object with the specified list of stations.
     * <p>
     * Initializes the route to start at the first station and sets the direction to forward.
     *
     * @param stations the list of stations in the route
     */
    public TrainRoute(List<Station> stations) {
        this.stations = stations;
        this.currentIndex = 0;
        this.movingForward = true; 
    }

    /**
     * Returns the next station in the route based on the current direction of travel.
     * <p>
     * If moving forward, returns the next station; if moving backward, returns the previous station.
     * Returns {@code null} if there is no next station in the current direction.
     *
     * @return the next station or {@code null} if there is no next station
     */
    public Station getNextStation() {
        if (movingForward) {
            if (currentIndex < stations.size() - 1) {
                return stations.get(currentIndex + 1);
            } else {
                return null;
            }
        } else {
            if (currentIndex > 0) {
                return stations.get(currentIndex - 1);
            } else {
                return null;
            }
        }
    }

    /**
     * Returns a list of future stations based on the current direction of travel.
     * <p>
     * If moving forward, returns a list of stations ahead of the current station; if moving backward,
     * returns a list of stations behind the current station.
     *
     * @param count the number of future stations to retrieve
     * @return a list of future stations
     */
    public List<Station> getFutureStations(int count) {
        if (movingForward) {
            int end = Math.min(currentIndex + count + 1, stations.size());
            return stations.subList(currentIndex + 1, end);
        } else {
            int start = Math.max(currentIndex - count, 0);
            return stations.subList(start, currentIndex);
        }
    }

    /**
     * Moves the train to the next station based on the current direction of travel.
     * <p>
     * Updates the current index of the station. If the train reaches the end or beginning of the route,
     * the direction of travel is reversed.
     */
    public void moveToNextStation() {
        if (movingForward) {
            if (currentIndex < stations.size() - 1) {
                currentIndex++;
            } else {
                movingForward = false; 
                currentIndex--;
            }
        } else {
            if (currentIndex > 0) {
                currentIndex--;
            } else {
                movingForward = true; 
                currentIndex++; 
            }
        }
    }

    /**
     * Returns the direction of travel of the train.
     * <p>
     * Returns "Forward" if the train is moving forward and "Backward" if the train is moving backward.
     *
     * @return the direction of travel
     */
    public String getTrainDirection() {
        return movingForward ? "Forward" : "Backward";
    }

    /**
     * Returns the past station, which is the station immediately before the current station.
     * <p>
     * Returns {@code null} if the train is at the first station or moving backward.
     *
     * @return the past station or {@code null} if there is no past station
     */
    public Station getPastStation() {
        if (movingForward) {
            if (currentIndex > 0) {
                return stations.get(currentIndex - 1);
            } else {
                return null; 
            }
        } else {
            return null; 
        }
    }

    /**
     * Returns the current station in the route.
     * <p>
     * Returns {@code null} if the current index is out of bounds of the station list.
     *
     * @return the current station or {@code null} if the index is out of bounds
     */
    public Station getCurrentStation() {
        if (currentIndex < stations.size()) {
            return stations.get(currentIndex);
        } else {
            return null; 
        }
    }
}
