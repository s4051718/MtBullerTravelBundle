import javax.swing.*;

import accommodation.Accommodation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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
    private JComboBox<TravelBundle> bundleSelectJComboBox;
    private JTextField liftPassAddJTextField;
    private JTextField lessonsAddJTextField;
    private JButton btnAddLiftPass;
    private JButton btnAddLessons;

    private MtBullerResort resort = new MtBullerResort();

    @Override
    protected JPanel buildMainPanel() {
        resort = new MtBullerResort(); // I have to initialise this here too because the data is used in the buildMainPanel method which occurs after super calls the constructor. // WITHOUT the type declaration otherwise no shared data

        JPanel panel = new JPanel(new BorderLayout());
        setSize(1300, 600);

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
        for (Customer c : resort.getCustomers()) {
            customerJComboBox.addItem(c);
        }
        customerJComboBox.addActionListener(this);

        JLabel accommodationJLabel = new JLabel("Accommodation:");

        accommodationJComboBox = new JComboBox<Accommodation>();
        for (Accommodation a : resort.getAccommodations()) {
            if (a.isAvailable()) {
                accommodationJComboBox.addItem(a);
            }
        }
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

        JPanel addToBundlePanel = new JPanel();
        addToBundlePanel.setLayout(new BoxLayout(addToBundlePanel, BoxLayout.Y_AXIS));

        JLabel addToBundleJLabel = new JLabel("Add to Bundle:");
        addToBundleJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel bundleSelectJLabel = new JLabel("Bundle:");
        bundleSelectJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        bundleSelectJComboBox = new JComboBox<TravelBundle>();
        bundleSelectJComboBox.setMaximumSize(new Dimension(200, 25));
        bundleSelectJComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (TravelBundle bundle : resort.getBundles()) {
            bundleSelectJComboBox.addItem(bundle);
        }
        bundleSelectJComboBox.addActionListener(this);

        JLabel liftPassAddJLabel = new JLabel("Lift Pass Days:");
        liftPassAddJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        liftPassAddJTextField = new JTextField(4);
        liftPassAddJTextField.setMaximumSize(new Dimension(100, 25));
        liftPassAddJTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        liftPassAddJTextField.addActionListener(this);

        JLabel lessonsAddJLabel = new JLabel("Lessons:");
        lessonsAddJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        lessonsAddJTextField = new JTextField(4);
        lessonsAddJTextField.setMaximumSize(new Dimension(100, 25));
        lessonsAddJTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        lessonsAddJTextField.addActionListener(this);

        btnAddLiftPass = new JButton("Add Lift Pass");
        btnAddLiftPass.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAddLiftPass.addActionListener(this);

        btnAddLessons = new JButton("Add Lessons");
        btnAddLessons.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnAddLessons.addActionListener(this);

        addToBundlePanel.add(addToBundleJLabel);
        addToBundlePanel.add(bundleSelectJLabel);
        addToBundlePanel.add(bundleSelectJComboBox);
        addToBundlePanel.add(liftPassAddJLabel);
        addToBundlePanel.add(liftPassAddJTextField);
        addToBundlePanel.add(lessonsAddJLabel);
        addToBundlePanel.add(lessonsAddJTextField);
        addToBundlePanel.add(btnAddLiftPass);
        addToBundlePanel.add(btnAddLessons);

        panel.add(pnlButtons, BorderLayout.NORTH);
        panel.add(scrollPane,  BorderLayout.CENTER);
        panel.add(newBundlePanel, BorderLayout.SOUTH);
        panel.add(addToBundlePanel, BorderLayout.EAST);


        return panel;
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnShowAll) {
			txtMessage.setText(resort.getAllBundlesAsString());
        } else if (ae.getSource() == btnCreateBundle) {
            Customer customer = (Customer) customerJComboBox.getSelectedItem();

            Accommodation accommodation = (Accommodation) accommodationJComboBox.getSelectedItem();

            LocalDate startDate = resort.parseIssueDate(startDateJTextField.getText());
            int nights = Integer.parseInt(nightsJTextField.getText());
            int liftPassDays = Integer.parseInt(liftPassDaysJTextField.getText());
            int lessons = Integer.parseInt(lessonsJTextField.getText());

            resort.createBundleFromGUI(customer, accommodation, startDate, nights, liftPassDays, lessons);

            txtMessage.setText(resort.getAllBundlesAsString());
            refreshBundleComboBox();
        } else if (ae.getSource() == btnAddLiftPass) {
            TravelBundle selected = (TravelBundle) bundleSelectJComboBox.getSelectedItem();
            int days = Integer.parseInt(liftPassAddJTextField.getText());
            resort.addLiftPassToBundle(selected, days);
            txtMessage.setText(resort.getAllBundlesAsString());
            refreshBundleComboBox();

        } else if (ae.getSource() == btnAddLessons) {
            TravelBundle selected = (TravelBundle) bundleSelectJComboBox.getSelectedItem();
            int lessons = Integer.parseInt(lessonsAddJTextField.getText());
            resort.addLessonsToBundle(selected, lessons);
            txtMessage.setText(resort.getAllBundlesAsString());
            refreshBundleComboBox();
        }
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BundlesWindow().setVisible(true));
    }

    private void refreshBundleComboBox() {
        bundleSelectJComboBox.removeAllItems();
        for (TravelBundle bundle : resort.getBundles()) {
            bundleSelectJComboBox.addItem(bundle);
        }
    }
}