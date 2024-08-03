package TrainInfoPanel;

import javax.swing.*;
import java.awt.*;

public class TrainMap extends JPanel {
    private static final int MAP_WIDTH = 600;
    private static final int MAP_HEIGHT = 400;

    public TrainMap() {
        setPreferredSize(new Dimension(MAP_WIDTH, MAP_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Add code to draw the train map here
        g.drawString("Train Map", 50, 50);
        // You can use g.drawLine, g.drawRect, etc. to draw the map
    }
}
