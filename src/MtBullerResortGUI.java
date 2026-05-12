import javax.swing.*;
import java.awt.*;

public class MtBullerResortGUI extends JFrame {

    private MtBullerResort resort;
    private JTabbedPane tabbedPane;

    public MtBullerResortGUI() {
        super("Mt Buller Resort Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        resort = new MtBullerResort();

        AccommodationsWindow accWindow = new AccommodationsWindow(resort);
        CustomersWindow custWindow = new CustomersWindow(resort);
        BundlesWindow bundleWindow = new BundlesWindow(resort);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Accommodations", accWindow.getMainPanel());
        tabbedPane.addTab("Customers", custWindow.getMainPanel());
        tabbedPane.addTab("Travel Bundles", bundleWindow.getMainPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MtBullerResortGUI().setVisible(true));
    }
}