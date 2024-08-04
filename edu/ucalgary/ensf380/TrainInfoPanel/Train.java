package TrainInfoPanel;

public class Train {
    private int trainId;
    private char direction;
    private int stationNo;

    public Train(int trainId, char direction, int stationNo) {
        this.trainId = trainId;
        this.direction = direction;
        this.setStationNo(stationNo);
    }

    public int getTrainId() {
        return trainId;
    }

    public char getDirection() {
    	return direction;
    }
    
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

    public void decrementStation() {
    	if (this.getStationNo() == 1) {
    		this.direction = 'F';
    		this.setStationNo(this.getStationNo() + 1);
    	} else {
    		this.setStationNo(this.getStationNo() - 1);
    	}
    }
    
    public String toString() {
    	int trainLine = (int) (trainId / 4);
    	char code = trainLine == 0 ? 'R' : (trainLine == 1 ? 'B' : 'G');
    	return "Train " + trainId + " : Station " + code + getStationNo() + " Direction " + direction;
    }

	public int getStationNo() {
		return stationNo;
	}

	public void setStationNo(int stationNo) {
		this.stationNo = stationNo;
	}
}
