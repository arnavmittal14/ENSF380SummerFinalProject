package Panel;
import javax.swing.*;
import java.awt.*;

/**
 * The {@code Panel} class is an abstract base class for creating custom Swing panels.
 * It extends {@link JPanel} and provides a common setup for panels with a {@link BorderLayout}.
 * Subclasses must implement {@link #setupComponents()} and {@link #configureLayout()} to
 * define specific components and layout configurations.
 */
public abstract class Panel extends JPanel {
    
    /**
     * Constructs a {@code Panel} and initializes the layout to {@link BorderLayout}.
     */
    public Panel() {
        super();
        setLayout(new BorderLayout());
    }

    /**
     * Initializes the user interface by setting up components and configuring the layout.
     * This method calls {@link #setupComponents()} to add components and {@link #configureLayout()}
     * to arrange them within the panel.
     */
    public void initUI() {
        setupComponents();
        configureLayout();
    }

    /**
     * Abstract method for setting up components in the panel.
     * Subclasses should implement this method to add and initialize their specific components.
     */
    protected abstract void setupComponents();

    /**
     * Abstract method for configuring the layout of the panel.
     * Subclasses should implement this method to define how components are arranged within the panel.
     */
    protected abstract void configureLayout();
}
