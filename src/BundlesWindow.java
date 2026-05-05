import javax.swing.*;
import java.awt.*;

public class BundlesWindow extends ApplicationWindow {

    public BundlesWindow() {
        super("Bundles");
    }

    @Override
    protected JPanel buildMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(253, 214, 166));
        panel.add(new JLabel("Bundles go here"));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BundlesWindow().setVisible(true));
    }
}