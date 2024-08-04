package TrainInfoPanel;

/**
 * The {@code Station} class represents a subway station with its associated properties.
 * <p>
 * Each {@code Station} object contains information about the station's name, code, and coordinates.
 */
public class Station {

    /** The name of the station. */
    private String name;

    /** The code identifying the station. */
    private String code;

    /** The X-coordinate of the station's location. */
    private double xCoordinate;

    /** The Y-coordinate of the station's location. */
    private double yCoordinate;

    /**
     * Constructs a new {@code Station} object with the specified name, code, and coordinates.
     *
     * @param name        the name of the station
     * @param code        the code identifying the station
     * @param xCoordinate the X-coordinate of the station's location
     * @param yCoordinate the Y-coordinate of the station's location
     */
    public Station(String name, String code, double xCoordinate, double yCoordinate) {
        this.name = name;
        this.code = code;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Returns the name of the station.
     *
     * @return the name of the station
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the code identifying the station.
     *
     * @return the code of the station
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the X-coordinate of the station's location.
     *
     * @return the X-coordinate of the station
     */
    public double getXCoordinate() {
        return xCoordinate;
    }

    /**
     * Returns the Y-coordinate of the station's location.
     *
     * @return the Y-coordinate of the station
     */
    public double getYCoordinate() {
        return yCoordinate;
    }

    /**
     * Returns a string representation of the station, which is its code.
     *
     * @return the code of the station as a string
     */
    @Override
    public String toString() {
        return this.code;
    }
}
