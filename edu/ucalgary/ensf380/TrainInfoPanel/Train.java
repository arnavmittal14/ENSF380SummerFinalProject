package TrainInfoPanel;

public class Train {
    private int trainId;
    private char direction;
    int stationNo;

    public Train(int trainId, char direction, int stationNo) {
        this.trainId = trainId;
        this.direction = direction;
        this.stationNo = stationNo;
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
    	if (this.stationNo == line.getStations().size()) {
    		this.direction = 'B';
    		this.stationNo--;
    	} else {
    		this.stationNo++;
    	}
    }

    public void decrementStation() {
    	if (this.stationNo == 1) {
    		this.direction = 'F';
    		this.stationNo++;
    	} else {
    		this.stationNo--;
    	}
    }
    
    public String toString() {
    	int trainLine = (int) (trainId / 4);
    	char code = trainLine == 0 ? 'R' : (trainLine == 1 ? 'B' : 'G');
    	return "Train " + trainId + " : Station " + code + stationNo + " Direction " + direction;
    }
}
