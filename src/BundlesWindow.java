import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BundlesWindow extends ApplicationWindow implements ActionListener {

    public BundlesWindow() {
        super("Bundles");
    }

    private JButton btnShowAll;

    private MtBullerResort resort = new MtBullerResort();


    @Override
    protected JPanel buildMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlButtons.setBackground(new Color(253, 214, 166));

        btnShowAll = new JButton("Show All");
        btnShowAll.addActionListener(this);

        pnlButtons.add(btnShowAll);

        JPanel pnlCenter = new JPanel();
        pnlCenter.setBackground(new Color(253, 214, 166));
        pnlCenter.add(new JLabel("Bundles go here"));

        panel.add(pnlButtons, BorderLayout.NORTH);
        panel.add(pnlCenter,  BorderLayout.CENTER);

        return panel;
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnShowAll) {
			resort.listTravelBundles();
		}
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BundlesWindow().setVisible(true));
    }
}