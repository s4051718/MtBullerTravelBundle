import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccommodationsWindow extends ApplicationWindow implements ActionListener {

    public AccommodationsWindow() {
        super("Accommodations");
    }

    private JButton btnShowAll;
    private JButton btnShowAvailable;

    private MtBullerResort resort = new MtBullerResort();


    @Override
    protected JPanel buildMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlButtons.setBackground(new Color(180, 217, 239));

        btnShowAll = new JButton("Show All");
        btnShowAll.addActionListener(this);
        btnShowAvailable = new JButton("Show Available Only");
        btnShowAvailable.addActionListener(this);

        pnlButtons.add(btnShowAll);
        pnlButtons.add(btnShowAvailable);

        JPanel pnlCenter = new JPanel();
        pnlCenter.setBackground(new Color(180, 217, 239));
        pnlCenter.add(new JLabel("Accommodations stuff go here"));

        panel.add(pnlButtons, BorderLayout.NORTH);
        panel.add(pnlCenter,  BorderLayout.CENTER);

        return panel;
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnShowAll) {
			resort.displayAllAccommodations();
		} else if (ae.getSource() == btnShowAvailable) {
            resort.displayAvailableAccommodations();
		}
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AccommodationsWindow().setVisible(true));
    }
}