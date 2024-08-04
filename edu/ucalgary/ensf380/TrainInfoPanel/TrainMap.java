package TrainInfoPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The {@code TrainMap} class represents a graphical panel that displays a map of train stations and trains.
 * <p>
 * This panel shows a background image of the train map and highlights the locations of trains on the map.
 */
public class TrainMap extends JPanel {
    /** The ID of the train to be displayed on the map. */
    private int trainId;

    /** Width of the map panel. */
    private static final int MAP_WIDTH = 1500;

    /** Height of the map panel. */
    private static final int MAP_HEIGHT = 700;

    /** Background image of the train map. */
    private BufferedImage backgroundImage;

    /**
     * Constructs a {@code TrainMap} object with the specified train ID.
     * <p>
     * Loads the background image for the map and sets the train ID.
     *
     * @param trainId the ID of the train to be displayed on the map
     */
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
        
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, MAP_WIDTH, MAP_HEIGHT, null);
        double halfMapHeight = MAP_HEIGHT / 2;
        
        // Parse all the stations and trains
        List<Line> lines = Line.getAllLines();
        
        for (Line line : lines) {
            List<Train> trains = line.getTrains();        	
            for (Station station : line.getStations()) {
                int stationYCoord = (int) (Math.floor(station.getYCoordinate()));
                
                // Adjust station Y-coordinate based on its position
                if (station.getYCoordinate() < 75) {
                    stationYCoord = (int) (Math.floor(station.getYCoordinate() / 1.3));
                } else if (station.getYCoordinate() < 200) {
                    stationYCoord = (int) (Math.floor(station.getYCoordinate() / 1.05));
                } else if (station.getYCoordinate() > 420) {
                    stationYCoord = (int) (Math.floor(station.getYCoordinate() * 1.025));
                }

                boolean filled = false;
                for (Train train : trains) {
                    boolean atCurrentStation = (Integer.parseInt(station.getCode().substring(1)) == train.getStationNo());
                    
                    if (atCurrentStation) {
                        if (train.getTrainId() == trainId) {
                            // Highlight the current train's station
                            g.setColor(Color.BLACK);
                            g.drawOval((int) (Math.floor(station.getXCoordinate() * 1.25)), stationYCoord, 12, 12);
                            g.setColor(Color.YELLOW);
                            g.fillOval((int) (Math.floor(station.getXCoordinate() * 1.25)), stationYCoord, 11, 11);
                        } else {
                            // Indicate other trains' stations
                            g.setColor(Color.BLACK);
                            g.fillOval((int) (Math.floor(station.getXCoordinate() * 1.25)), stationYCoord, 12, 12);
                        }
                        
                        filled = true;
                    }
                }
                
                if (!filled) {
                    // Draw an empty station if no train is present
                    g.setColor(Color.BLACK);
                    g.drawOval((int) (Math.floor(station.getXCoordinate() * 1.25)), stationYCoord, 12, 12);
                }
            }
        }
    }
}
