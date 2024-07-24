package TrainInfoPanel;
import javax.swing.*;
import java.awt.*;

public class TrainInfoPanel extends JPanel {
    public TrainInfoPanel() {
        setLayout(new BorderLayout());
        JLabel label = new JLabel("Train Info Here", SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        // Logic to update train info goes here
        updateTrainInfo();
    }

    private void updateTrainInfo() {
        // Logic to fetch and display train info goes here
    }
}
