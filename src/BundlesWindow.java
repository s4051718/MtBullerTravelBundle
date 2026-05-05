import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BundlesWindow extends ApplicationWindow implements ActionListener {

    public BundlesWindow() {
        super("Bundles");
    }

    private JButton btnShowAll;
    private JTextArea txtMessage;

    private MtBullerResort resort = new MtBullerResort();


    @Override
    protected JPanel buildMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlButtons.setBackground(new Color(253, 214, 166));

        btnShowAll = new JButton("Show All");
        btnShowAll.addActionListener(this);

        pnlButtons.add(btnShowAll);

        txtMessage = new JTextArea(5, 20);
        txtMessage.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtMessage);

        panel.add(pnlButtons, BorderLayout.NORTH);
        panel.add(scrollPane,  BorderLayout.CENTER);

        return panel;
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnShowAll) {
			txtMessage.setText(resort.getAllBundlesAsString());
		}
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BundlesWindow().setVisible(true));
    }
}