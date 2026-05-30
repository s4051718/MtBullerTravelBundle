import javax.swing.*;

import accommodation.Accommodation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class BundlesWindow extends ApplicationWindow implements ActionListener {
    private MtBullerResort resort;

    public BundlesWindow(MtBullerResort resort) {
        super("Bundles");
        this.resort = resort;
        initPanel();
    }

    private JButton btnCreateNewBundle;
    private JButton btnAddToBundle;
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
    private JButton btnUpdateBundle;

    @Override
    protected JPanel buildMainPanel() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(253, 214, 166));
        panel.setOpaque(true);
        setSize(1300, 600);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlButtons.setBackground(new Color(253, 214, 166));

        btnCreateNewBundle = new JButton("Create New Bundle");
        btnCreateNewBundle.addActionListener(this);

        btnAddToBundle = new JButton("Add to Bundle");
        btnAddToBundle.addActionListener(this);

        pnlButtons.add(btnCreateNewBundle);
        pnlButtons.add(btnAddToBundle);

        txtMessage = new JTextArea(5, 20);
        txtMessage.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtMessage);

        JPanel newBundlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel addToBundlePanel = new JPanel();
        addToBundlePanel.setLayout(new BoxLayout(addToBundlePanel, BoxLayout.Y_AXIS));

        panel.add(pnlButtons, BorderLayout.NORTH);
        panel.add(scrollPane,  BorderLayout.CENTER);
        panel.add(newBundlePanel, BorderLayout.SOUTH);
        panel.add(addToBundlePanel, BorderLayout.EAST);

        txtMessage.setText(resort.getAllBundlesAsString());
        txtMessage.setCaretPosition(0);
        return panel;
    }

    public void showCreateNewBundleDialog() {
        JDialog dialog = new JDialog(this, "Create New Bundle", true);
        dialog.setSize(300, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel newBundlePanel = new JPanel();
        newBundlePanel.setLayout(new BoxLayout(newBundlePanel, BoxLayout.Y_AXIS));
        newBundlePanel.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        newBundlePanel.setBackground(new Color(253, 214, 166));

        JLabel customerJLabel = new JLabel("Customer:");
        customerJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        customerJComboBox = new JComboBox<Customer>();
        customerJComboBox.setMaximumSize(new Dimension(200, 25));
        customerJComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (Customer c : resort.getCustomers()) {
            customerJComboBox.addItem(c);
        }
        customerJComboBox.addActionListener(this);

        JLabel accommodationJLabel = new JLabel("Accommodation:");
        accommodationJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        accommodationJComboBox = new JComboBox<Accommodation>();
        accommodationJComboBox.setMaximumSize(new Dimension(200, 25));
        accommodationJComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (Accommodation a : resort.getAccommodations()) {
            if (a.isAvailable()) {
                accommodationJComboBox.addItem(a);
            }
        }
        accommodationJComboBox.addActionListener(this);

        JLabel dateJLabel = new JLabel("Date:");
        dateJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        startDateJTextField = new JTextField(7);
        startDateJTextField.setMaximumSize(new Dimension(100, 25));
        startDateJTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        startDateJTextField.addActionListener(this);

        JLabel accommodationNightsJLabel = new JLabel("Accommodation Nights:");
        accommodationNightsJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        nightsJTextField = new JTextField(6);
        nightsJTextField.setMaximumSize(new Dimension(100, 25));
        nightsJTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        nightsJTextField.addActionListener(this);

        JLabel liftPassDaysJLabel = new JLabel("Lift Pass Days:");
        liftPassDaysJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        liftPassDaysJTextField = new JTextField(4);
        liftPassDaysJTextField.setMaximumSize(new Dimension(100, 25));
        liftPassDaysJTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        liftPassDaysJTextField.addActionListener(this);

        JLabel lessonsJLabel = new JLabel("Lessons:");
        lessonsJLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        lessonsJTextField = new JTextField(4);
        lessonsJTextField.setMaximumSize(new Dimension(100, 25));
        lessonsJTextField.setAlignmentX(Component.LEFT_ALIGNMENT);

        btnCreateBundle = new JButton("Create Bundle");
        btnCreateBundle.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnCreateBundle.addActionListener(e -> {
            try {
                Customer customer = (Customer) customerJComboBox.getSelectedItem();
                Accommodation accommodation = (Accommodation) accommodationJComboBox.getSelectedItem();
                LocalDate startDate = resort.parseIssueDate(startDateJTextField.getText());
                int nights = Integer.parseInt(nightsJTextField.getText());
                int liftPassDays = Integer.parseInt(liftPassDaysJTextField.getText());
                int lessons = Integer.parseInt(lessonsJTextField.getText());
                resort.createBundleFromGUI(customer, accommodation, startDate, nights, liftPassDays, lessons);
                txtMessage.setText(resort.getAllBundlesAsString());
                txtMessage.setCaretPosition(0);
                dialog.dispose();
            } catch (MtBullerException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid input. Please check all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        newBundlePanel.add(customerJLabel);
        newBundlePanel.add(customerJComboBox);
        newBundlePanel.add(Box.createVerticalStrut(10));
        newBundlePanel.add(accommodationJLabel);
        newBundlePanel.add(accommodationJComboBox);
        newBundlePanel.add(Box.createVerticalStrut(10));
        newBundlePanel.add(dateJLabel);
        newBundlePanel.add(startDateJTextField);
        newBundlePanel.add(Box.createVerticalStrut(10));
        newBundlePanel.add(accommodationNightsJLabel);
        newBundlePanel.add(nightsJTextField);
        newBundlePanel.add(Box.createVerticalStrut(10));
        newBundlePanel.add(liftPassDaysJLabel);
        newBundlePanel.add(liftPassDaysJTextField);
        newBundlePanel.add(Box.createVerticalStrut(10));
        newBundlePanel.add(lessonsJLabel);
        newBundlePanel.add(lessonsJTextField);
        newBundlePanel.add(Box.createVerticalStrut(10));
        newBundlePanel.add(btnCreateBundle);

        dialog.add(newBundlePanel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    private void showAddToBundleDialog() {
        JDialog dialog = new JDialog(this, "Add to Bundle", true);
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel addToBundlePanel = new JPanel();
        addToBundlePanel.setLayout(new BoxLayout(addToBundlePanel, BoxLayout.Y_AXIS));
        addToBundlePanel.setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));
        addToBundlePanel.setBackground(new Color(253, 214, 166));

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

        btnUpdateBundle = new JButton("Update Bundle");
        btnUpdateBundle.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnUpdateBundle.addActionListener(e -> {
            try {
                TravelBundle selected = (TravelBundle) bundleSelectJComboBox.getSelectedItem();

                String liftPassText = liftPassAddJTextField.getText().trim();
                if (!liftPassText.isEmpty()) {
                    int days = Integer.parseInt(liftPassText);
                    resort.addLiftPassToBundle(selected, days);
                }

                String lessonsText = lessonsAddJTextField.getText().trim();
                if (!lessonsText.isEmpty()) {
                    int lessons = Integer.parseInt(lessonsText);
                    resort.addLessonsToBundle(selected, lessons);
                }

                txtMessage.setText(resort.getAllBundlesAsString());
                refreshBundleComboBox();
                txtMessage.setCaretPosition(0);
                dialog.dispose();

            } catch (MtBullerException ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid input.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        addToBundlePanel.add(bundleSelectJLabel);
        addToBundlePanel.add(bundleSelectJComboBox);
        addToBundlePanel.add(Box.createVerticalStrut(10));
        addToBundlePanel.add(liftPassAddJLabel);
        addToBundlePanel.add(liftPassAddJTextField);
        addToBundlePanel.add(Box.createVerticalStrut(10));
        addToBundlePanel.add(lessonsAddJLabel);
        addToBundlePanel.add(lessonsAddJTextField);
        addToBundlePanel.add(Box.createVerticalStrut(10));
        addToBundlePanel.add(btnUpdateBundle);

        dialog.add(addToBundlePanel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnCreateNewBundle) {
            showCreateNewBundleDialog();
        } else if (ae.getSource() == btnAddToBundle) {
            showAddToBundleDialog();
        }
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new BundlesWindow(new MtBullerResort()).setVisible(true));
    }

    // Helper methods
    private void refreshBundleComboBox() {
        bundleSelectJComboBox.removeAllItems();
        for (TravelBundle bundle : resort.getBundles()) {
            bundleSelectJComboBox.addItem(bundle);
        }
    }

    // private void refreshAccommodationComboBox() {
    //     accommodationJComboBox.removeAllItems();
    //     for (Accommodation a : resort.getAccommodations()) {
    //         if (a.isAvailable()) {
    //             accommodationJComboBox.addItem(a);
    //         }
    //     }
    // }
}