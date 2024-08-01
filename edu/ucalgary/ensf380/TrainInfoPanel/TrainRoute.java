package edu.ucalgary.ensf380;

import java.util.List;

public class TrainRoute {
    private List<Station2> stations;
    private int currentIndex;
    private boolean movingForward;

    public TrainRoute2(List<Station2> stations) {
        this.stations = stations;
        this.currentIndex = 0;
        this.movingForward = true; // Start moving forward initially
    }

    public Station2 getNextStation() {
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

    public List<Station2> getFutureStations(int count) {
        if (movingForward) {
            int end = Math.min(currentIndex + count + 1, stations.size());
            return stations.subList(currentIndex + 1, end);
        } else {
            int start = Math.max(currentIndex - count, 0);
            return stations.subList(start, currentIndex);
        }
    }

    public void moveToNextStation() {
        if (movingForward) {
            if (currentIndex < stations.size() - 1) {
                currentIndex++;
            } else {
                movingForward = false; // Reverse direction at the end
                currentIndex--; // Stay at the last station
            }
        } else {
            if (currentIndex > 0) {
                currentIndex--;
            } else {
                movingForward = true; // Forward direction at the start
                currentIndex++; // Stay at the first station
            }
        }
    }

    public String getTrainDirection() {
        return movingForward ? "Forward" : "Backward";
    }

    public Station2 getPastStation() {
        if (movingForward) {
            if (currentIndex > 0) {
                return stations.get(currentIndex - 1);
            } else {
                return null; // No past station available
            }
        } else {
            return null; // No past station available in backward direction
        }
    }

    public Station2 getCurrentStation() {
        if (currentIndex < stations.size()) {
            return stations.get(currentIndex);
        } else {
            return null; // No current station available
        }
    }
}
