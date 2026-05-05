import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomersWindow extends ApplicationWindow implements ActionListener {

    public CustomersWindow() {
        super("Customers");
    }

    private JButton btnShowAll;
    private JTextArea txtMessage;

    private MtBullerResort resort = new MtBullerResort();


    @Override
    protected JPanel buildMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlButtons.setBackground(new Color(178, 193, 162));

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
			txtMessage.setText(resort.getAllCustomersAsString());
		}
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomersWindow().setVisible(true));
    }
}