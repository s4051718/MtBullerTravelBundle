import javax.swing.*;
import java.awt.*;

public class CustomersWindow extends ApplicationWindow {

    public CustomersWindow() {
        super("Customers");
    }

    @Override
    protected JPanel buildMainPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(178, 193, 162));
        panel.add(new JLabel("Customer stuff go here"));
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomersWindow().setVisible(true));
    }
}