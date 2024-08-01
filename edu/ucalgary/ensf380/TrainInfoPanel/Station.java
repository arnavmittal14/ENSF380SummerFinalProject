package edu.ucalgary.ensf380;

public class Station {
    private String name;
    private double xCoordinate;
    private double yCoordinate;

    public Station2(String name, double xCoordinate, double yCoordinate) {
        this.name = name;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public String getName() {
        return name;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }
}
