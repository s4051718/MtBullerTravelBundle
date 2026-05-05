import javax.swing.*;
import java.awt.*;

public class AccommodationsWindow extends ApplicationWindow {

    public AccommodationsWindow() {
        super("Accommodations");
    }

    @Override
    protected JPanel buildMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(180, 217, 239));
        panel.add(new JLabel("Accommodations stuff go here"));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AccommodationsWindow().setVisible(true));
    }
}