package TrainInfoPanel;

import java.util.List;
import java.util.Random;

public class TrainInfo {
	private Line red;
	private Line blue;
	private Line green;
	
	public TrainInfo() {
		// Read the station data
        CSVReader csvReader = new CSVReader();
        List<Station> stations = csvReader.readStations("edu/ucalgary/ensf380/assets/Map.csv");
        
        this.red = new Line();
        this.blue = new Line();
        this.green = new Line();
        
        // Place stations in correct lines
        for (Station station: stations) {
        	if (station.getCode().startsWith("R")) {
        		red.addStation(station);
        	} else if (station.getCode().startsWith("B")) {
        		blue.addStation(station);
        	} else {
        		green.addStation(station);
        	}
        }
        
        // Generate random bounds & set up trains
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
        
//        Take elsewhere
        for (Line line: lines) {
        	List<Train> trains = line.getTrains();
        	for (Train train: trains) {
        		if (train.getDirection() == 'F') {
        			train.incrementStation();
        		} else {
        			train.decrementStation();
        		}
        	}
        	
        	System.out.println(trains);
        }
	}
}
