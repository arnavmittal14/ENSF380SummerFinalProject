import javax.swing.*;
import java.awt.*;


public abstract class Panel extends JPanel {
    public Panel() {
        super();
        setLayout(new BorderLayout());
    }

    public void initUI() {
        setupComponents();
        configureLayout();
    }

    protected abstract void setupComponents();
    protected abstract void configureLayout();
}
