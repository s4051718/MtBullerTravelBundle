import javax.swing.*;

import accommodation.Accommodation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BundlesWindow extends ApplicationWindow implements ActionListener {

    public BundlesWindow() {
        super("Bundles");
    }

    private JButton btnShowAll;
    private JTextArea txtMessage;
    private JComboBox<Customer> customerJComboBox;
    private JComboBox<Accommodation> accommodationJComboBox;
    private JTextField startDateJTextField;
    private JTextField nightsJTextField;
    private JTextField liftPassDaysJTextField;
    private JTextField lessonsJTextField;
    private JButton btnCreateBundle;

    private MtBullerResort resort = new MtBullerResort();

    @Override
    protected JPanel buildMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        setSize(1000, 600);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlButtons.setBackground(new Color(253, 214, 166));

        btnShowAll = new JButton("Show All");
        btnShowAll.addActionListener(this);

        pnlButtons.add(btnShowAll);

        txtMessage = new JTextArea(5, 20);
        txtMessage.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtMessage);

        JPanel newBundlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel customerJLabel = new JLabel("Customer:");
        customerJComboBox = new JComboBox<Customer>();
        customerJComboBox.addActionListener(this);
        JLabel accommodationJLabel = new JLabel("Accommodation:");
        accommodationJComboBox = new JComboBox<Accommodation>();
        accommodationJComboBox.addActionListener(this);
        JLabel dateJLabel = new JLabel("Date:");
        startDateJTextField = new JTextField(7);
        startDateJTextField.addActionListener(this);
        JLabel accommodationNightsJLabel = new JLabel("Accommodation Nights:");
        nightsJTextField = new JTextField(6);
        nightsJTextField.addActionListener(this);
        JLabel liftPassDaysJLabel = new JLabel("Lift Pass Days:");
        liftPassDaysJTextField = new JTextField(4);
        liftPassDaysJTextField.addActionListener(this);
        JLabel lessonsJLabel = new JLabel("Lessons:");
        lessonsJTextField = new JTextField(4);
        btnCreateBundle = new JButton("Create Bundle");
        btnCreateBundle.addActionListener(this);

        newBundlePanel.add(customerJLabel);
        newBundlePanel.add(customerJComboBox);
        newBundlePanel.add(accommodationJLabel);
        newBundlePanel.add(accommodationJComboBox);
        newBundlePanel.add(dateJLabel);
        newBundlePanel.add(startDateJTextField);
        newBundlePanel.add(accommodationNightsJLabel);
        newBundlePanel.add(nightsJTextField);
        newBundlePanel.add(liftPassDaysJLabel);
        newBundlePanel.add(liftPassDaysJTextField);
        newBundlePanel.add(lessonsJLabel);
        newBundlePanel.add(lessonsJTextField);
        newBundlePanel.add(btnCreateBundle);

        panel.add(pnlButtons, BorderLayout.NORTH);
        panel.add(scrollPane,  BorderLayout.CENTER);
        panel.add(newBundlePanel, BorderLayout.SOUTH);

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