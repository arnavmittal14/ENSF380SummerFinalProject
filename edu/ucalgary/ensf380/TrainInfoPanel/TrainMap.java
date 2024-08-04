package TrainInfoPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TrainMap extends JPanel {
	private int trainId;
    private static final int MAP_WIDTH = 1500;
    private static final int MAP_HEIGHT = 700;
    private BufferedImage backgroundImage;

    public TrainMap(int trainId) {
        setPreferredSize(new Dimension(MAP_WIDTH, MAP_HEIGHT));
        try {
           this.backgroundImage = ImageIO.read(new File("edu/ucalgary/ensf380/assets/Trains.png"));
           this.trainId = trainId;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(backgroundImage, 0, 0, MAP_WIDTH, MAP_HEIGHT, null);
        double halfMapHeight = MAP_HEIGHT / 2;
        
        // Parse all the stations
        List<Line> lines = Line.getAllLines();
        String stationCode = "";
        
        for (Line line: lines) {
        	List<Train> trains = line.getTrains();        	
        	for (Station station: line.getStations()) {
        		int stationYCoord = (int) (Math.floor(station.getYCoordinate()));
        		
        		if (station.getYCoordinate() < 75) {
        			stationYCoord = (int) (Math.floor(station.getYCoordinate() / 1.3));
        		} else if (station.getYCoordinate() < 200) {
        			stationYCoord = (int) (Math.floor(station.getYCoordinate() / 1.05));
        		} else if(station.getYCoordinate() > 420) {
        			stationYCoord = (int) (Math.floor(station.getYCoordinate() * 1.025));
        		}

        		boolean filled = false;
        		for (Train train: trains) {
        			boolean atCurrentStation = (Integer.parseInt(station.getCode().substring(1)) == train.getStationNo());
        			
        			if (atCurrentStation) {
        				if (train.getTrainId() == trainId) {
        					g.setColor(Color.BLACK);
                			g.drawOval((int) (Math.floor(station.getXCoordinate() * 1.25)), stationYCoord, 12, 12);
        					g.setColor(Color.YELLOW);
        					g.fillOval((int) (Math.floor(station.getXCoordinate() * 1.25)), stationYCoord, 11, 11);
        				} else {
        					// Other train colour
        					g.setColor(Color.BLACK);
        					g.fillOval((int) (Math.floor(station.getXCoordinate() * 1.25)), stationYCoord, 12, 12);
        				}
        				
        				filled = true;
        			}
        		}
        		
        		if (!filled) {
        			g.setColor(Color.BLACK);
        			g.drawOval((int) (Math.floor(station.getXCoordinate() * 1.25)), stationYCoord, 12, 12);
        		}
        	}
        }
    }
}
