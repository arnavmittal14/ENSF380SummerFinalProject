import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
	static Line red;
    static Line blue;
    static Line green;
	
    public static void main(String[] args) {
    	String input = args[1];
    	String output = args[3];
    	
    	if (!(output.endsWith("\\") && output.endsWith("/"))) {
    		output = output + "/";
    	}
    	
    	CSVReader csvReader = new CSVReader();
        List<Station> stations = csvReader.readStations(input);

        red = new Line();
        blue = new Line();
        green = new Line();
        
        // Place stations in correct lines
        for (Station station : stations) {
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

        while (true) {
        	updateTrainPositions(lines, output);
        	try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
    
    private static void updateTrainPositions(Line[] lines, String dir) {
    	System.out.println("Train Positions:");

    	List<List<String>> csvs = new ArrayList<>();
    	int loopIteration = 0;
        for (Line line : lines) {
        	String csvString = (loopIteration == 0 ? "Red " : (loopIteration == 1 ? "Blue " : "Green "));

            List<Train> trains = line.getTrains();
            for (Train train : trains) {
                if (train.getDirection() == 'F') {
                    train.incrementStation();
                } else {
                    train.decrementStation();
                }
                
                csvString += "| Train_" + (train.getTrainId() + 1) + "(" + csvString.charAt(0) + train.stationNo + ", " + train.getDirection() + ") |";
            
                List<String> csvRow = new ArrayList<>();
                csvRow.add("" + csvString.charAt(0));
                csvRow.add("" + (train.getTrainId() + 1));
                csvRow.add("" + csvString.charAt(0) + train.stationNo);
                csvRow.add(train.getDirection() == 'F' ? "forward" : "backward");
                csvRow.add(train.getDirection() == 'F' ? line.getStations().getLast().getCode() : line.getStations().getFirst().getCode());
                csvs.add(csvRow);
            }

            System.out.println(csvString);
            loopIteration++;
        }
        
        // Define the headers (columns)
        List<String> headers = Arrays.asList("LineName", "TrainNumber", "StationCode", "Direction", "Destination");

        // Generate the CSV file
        long now = Instant.now().toEpochMilli();
        generateCSV(dir + "Trains_" + now + ".csv", headers, csvs);
        
        System.out.println("");
    }
    
    public static void generateCSV(String filepath, List<String> headers, List<List<String>> rows) {
    	try (PrintWriter writer = new PrintWriter(new FileWriter(filepath))) {
            // Write the headers
            writeRow(writer, headers);

            // Write the data rows
            for (List<String> row : rows) {
                writeRow(writer, row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void writeRow(PrintWriter writer, List<String> row) {
        String rowString = String.join(",", row);
        writer.println(rowString);
    }

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

    public Station getPreviousStation(Line line, int stationIndex) {
        if (stationIndex > 0) {
            return line.getStations().get(stationIndex - 1);
        }
        return null;
    }

    public List<Station> getNextStations(Line line, int stationIndex) {
        List<Station> stations = line.getStations();
        int end = Math.min(stationIndex + 4, stations.size());
        return stations.subList(stationIndex + 1, end);
    }

    // Getter methods for lines
    public Line getRedLine() {
        return red;
    }

    public Line getBlueLine() {
        return blue;
    }

    public Line getGreenLine() {
        return green;
    }
}
