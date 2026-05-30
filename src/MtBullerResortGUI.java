import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MtBullerResortGUI extends JFrame {

    private MtBullerResort resort;
    private JTabbedPane tabbedPane;

    public MtBullerResortGUI() {
        super("Mt Buller Resort Manager");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                resort.writeBundleToFile();
                resort.writeCustomersToFile();
                resort.writeAccommodationsToFile();
                System.exit(0);
            }
        });
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        resort = new MtBullerResort();

        HomeWindow homeWindow = new HomeWindow(resort);
        AccommodationsWindow accWindow = new AccommodationsWindow(resort);
        CustomersWindow custWindow = new CustomersWindow(resort);
        BundlesWindow bundleWindow = new BundlesWindow(resort);

        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Home", homeWindow.getMainPanel());
        tabbedPane.addTab("Accommodations", accWindow.getMainPanel());
        tabbedPane.addTab("Customers", custWindow.getMainPanel());
        tabbedPane.addTab("Travel Bundles", bundleWindow.getMainPanel());

        tabbedPane.setBackgroundAt(1, new Color(180, 217, 239));
        tabbedPane.setBackgroundAt(2, new Color(178, 193, 162));
        tabbedPane.setBackgroundAt(3, new Color(253, 214, 166));

        tabbedPane.setTabPlacement(JTabbedPane.TOP);

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MtBullerResortGUI().setVisible(true));
    }
}