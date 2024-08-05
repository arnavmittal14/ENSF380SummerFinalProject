public class Station {
    private String name;
    private String code;
    private double xCoordinate;
    private double yCoordinate;

    public Station(String name, String code, double xCoordinate, double yCoordinate) {
        this.name = name;
        this.code = code;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public String getName() {
        return name;
    }
    
    public String getCode() {
    	return code;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }
    
    @Override
    public String toString() {
    	return this.code;
    }
}
