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
    private JLabel nameJLabel;
    private JTextField nameJTextField;
    private JLabel levelJLabel;
    private ButtonGroup levelButtonGroup;
    private JRadioButton beginnerJRadioButton;
    private JRadioButton intermediateJRadioButton;
    private JRadioButton expertJRadioButton;
    private JButton addCustomerJButton;

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

        JPanel newCustomerPanel = new JPanel();
        newCustomerPanel.setLayout(new BoxLayout(newCustomerPanel, BoxLayout.Y_AXIS));
        JLabel newCustomerJLabel = new JLabel("Add New Customer");
        nameJLabel = new JLabel("Name:");
        nameJTextField = new JTextField();
        nameJTextField.setMaximumSize(new Dimension(240, 25));
        levelJLabel = new JLabel("Ski Level:");
        levelButtonGroup = new ButtonGroup();
        beginnerJRadioButton = new JRadioButton("Beginner");
        intermediateJRadioButton = new JRadioButton("Intermediate");
        expertJRadioButton = new JRadioButton("Expert");
        levelButtonGroup.add(beginnerJRadioButton);
        levelButtonGroup.add(intermediateJRadioButton);
        levelButtonGroup.add(expertJRadioButton);
        addCustomerJButton = new JButton("Add Customer");
        addCustomerJButton.addActionListener(this);
        newCustomerPanel.add(newCustomerJLabel);
        newCustomerPanel.add(nameJLabel);
        newCustomerPanel.add(nameJTextField);
        newCustomerPanel.add(levelJLabel);
        newCustomerPanel.add(beginnerJRadioButton);
        newCustomerPanel.add(intermediateJRadioButton);
        newCustomerPanel.add(expertJRadioButton);
        newCustomerPanel.add(addCustomerJButton);

        panel.add(pnlButtons, BorderLayout.NORTH);
        panel.add(scrollPane,  BorderLayout.CENTER);
        panel.add(newCustomerPanel, BorderLayout.EAST);

        return panel;
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == btnShowAll) {
			txtMessage.setText(resort.getAllCustomersAsString());
		} else if (ae.getSource() == addCustomerJButton) {
            String name = nameJTextField.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a name.", "Error", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(this, "Please select a level.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
                }
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Add customer: " + name + "?",
                    "Confirm",
                    JOptionPane.OK_CANCEL_OPTION);
                if (confirm == JOptionPane.OK_OPTION) {
                    resort.addCustomerFromGUI(name, level);
                    txtMessage.setText(resort.getAllCustomersAsString());
                    nameJTextField.setText("");
                    levelButtonGroup.clearSelection();
                }
		}
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomersWindow().setVisible(true));
    }
}