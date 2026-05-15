import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeWindow extends ApplicationWindow implements ActionListener {
    private MtBullerResort resort;

    public HomeWindow(MtBullerResort resort) {
        super("Home");
        this.resort = resort;
        initPanel();
    }

    @Override
    protected JPanel buildMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        return panel;

        /* Hotel Stats (Innovative feature #2)
        X of Y rooms booked
        X number of customers
        X number of travel bundles
        */

        /* Quick Actions (Innovative feature #3)
        New Customer
        New Accommodation
        New Travel Bundle
         */
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new HomeWindow(new MtBullerResort()).setVisible(true));
    }
}
