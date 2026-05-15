import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomersWindow extends ApplicationWindow implements ActionListener {
    private MtBullerResort resort;

    public CustomersWindow(MtBullerResort resort) {
        super("Customers");
        this.resort = resort;
        initPanel();
    }

    private JButton btnCreateCustomer;
    private JTextArea txtMessage;
    private JLabel nameJLabel;
    private JTextField nameJTextField;
    private JLabel levelJLabel;
    private ButtonGroup levelButtonGroup;
    private JRadioButton beginnerJRadioButton;
    private JRadioButton intermediateJRadioButton;
    private JRadioButton expertJRadioButton;
    private JButton addCustomerJButton;

    @Override
    protected JPanel buildMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlButtons.setBackground(new Color(178, 193, 162));

        btnCreateCustomer = new JButton("Create a New Customer");
        btnCreateCustomer.addActionListener(this);

        pnlButtons.add(btnCreateCustomer);

        txtMessage = new JTextArea(5, 20);
        txtMessage.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtMessage);

        panel.add(pnlButtons, BorderLayout.NORTH);
        panel.add(scrollPane,  BorderLayout.CENTER);

        txtMessage.setText(resort.getAllCustomersAsString());
        return panel;
    }

    private void showAddCustomerDialog() {
        JDialog dialog = new JDialog(this, "Add New Customer", true);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JPanel newCustomerPanel = new JPanel();
        newCustomerPanel.setLayout(new BoxLayout(newCustomerPanel, BoxLayout.Y_AXIS));

        nameJLabel = new JLabel("Name:");

        nameJTextField = new JTextField();
        nameJTextField.setMaximumSize(new Dimension(240, 25));

        levelJLabel = new JLabel("Ski Level:");

        levelButtonGroup = new ButtonGroup();

        beginnerJRadioButton = new JRadioButton("Beginner");
        levelButtonGroup.add(beginnerJRadioButton);

        intermediateJRadioButton = new JRadioButton("Intermediate");
        levelButtonGroup.add(intermediateJRadioButton);

        expertJRadioButton = new JRadioButton("Expert");
        levelButtonGroup.add(expertJRadioButton);

        addCustomerJButton = new JButton("Add Customer");
        addCustomerJButton.addActionListener(e -> {
            String name = nameJTextField.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter a name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            SkiingLevel level = null;
            if (beginnerJRadioButton.isSelected()) {
                level = SkiingLevel.BEGINNER;
            } else if (intermediateJRadioButton.isSelected()) {
                level = SkiingLevel.INTERMEDIATE;
            } else if (expertJRadioButton.isSelected()) {
                level = SkiingLevel.EXPERT;
            } else {
                JOptionPane.showMessageDialog(dialog, "Please select a level.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(dialog,
                "Add customer: " + name + "?",
                "Confirm",
                JOptionPane.OK_CANCEL_OPTION);
            if (confirm == JOptionPane.OK_OPTION) {
                resort.addCustomerFromGUI(name, level);
                txtMessage.setText(resort.getAllCustomersAsString());
                dialog.dispose();
            }
        });

        newCustomerPanel.add(nameJLabel);
        newCustomerPanel.add(nameJTextField);
        newCustomerPanel.add(levelJLabel);
        newCustomerPanel.add(beginnerJRadioButton);
        newCustomerPanel.add(intermediateJRadioButton);
        newCustomerPanel.add(expertJRadioButton);
        newCustomerPanel.add(addCustomerJButton);

        dialog.add(newCustomerPanel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnCreateCustomer) {
            showAddCustomerDialog();
        }
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new CustomersWindow(new MtBullerResort()).setVisible(true));
    }
}