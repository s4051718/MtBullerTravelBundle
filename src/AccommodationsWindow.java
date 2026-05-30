import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccommodationsWindow extends ApplicationWindow implements ActionListener {
    private MtBullerResort resort;

    public AccommodationsWindow(MtBullerResort resort) {
        super("Accommodations");
        this.resort = resort;
        initPanel();
    }

    private JLabel accommodationTypeJLabel;
    private JLabel accommodationStatusJLabel;
    private JTextArea txtMessage;

    private JComboBox<String> accommodationTypeJComboBox;
    private JComboBox<String> accommodationStatusJComboBox;
    private JLabel accommodationPriceJLabel;
    private JTextField accommodationPriceJTextField;
    private JButton btnApply;
    private JButton btnReset;

    @Override
    protected JPanel buildMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(180, 217, 239));
        panel.setOpaque(true);

        JPanel filterJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterJPanel.setBackground(new Color(180, 217, 239));

        accommodationTypeJLabel = new JLabel("Accommodation Type:");

        String[] accommodationTypes = {
            "All",
            "Lodge",
            "Apartment",
            "Hotel"
        };
        accommodationTypeJComboBox = new JComboBox<>(accommodationTypes);
        accommodationTypeJComboBox.addActionListener(this);

        accommodationStatusJLabel = new JLabel("Accommodation Status:");

        String[] accommodationStatuses = {
            "All",
            "Available"
        };
        accommodationStatusJComboBox = new JComboBox<>(accommodationStatuses);
        accommodationStatusJComboBox.addActionListener(this);

        accommodationPriceJLabel = new JLabel("Max Price:");

        accommodationPriceJTextField = new JTextField(8);

        btnApply = new JButton("Apply");
        btnApply.addActionListener(this);

        btnReset = new JButton("Reset");
        btnReset.addActionListener(this);

        filterJPanel.add(accommodationTypeJLabel);
        filterJPanel.add(accommodationTypeJComboBox);
        filterJPanel.add(accommodationStatusJLabel);
        filterJPanel.add(accommodationStatusJComboBox);
        filterJPanel.add(accommodationPriceJLabel);
        filterJPanel.add(accommodationPriceJTextField);
        filterJPanel.add(btnApply);
        filterJPanel.add(btnReset);

        txtMessage = new JTextArea(5, 20);
        txtMessage.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtMessage);

        panel.add(filterJPanel, BorderLayout.NORTH);
        panel.add(scrollPane,  BorderLayout.CENTER);

        txtMessage.setText(resort.getFilteredAccommodationsAsString("All", "All", 0));
        txtMessage.setCaretPosition(0);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == accommodationTypeJComboBox || e.getSource() == accommodationStatusJComboBox) {
            String type = (String) accommodationTypeJComboBox.getSelectedItem();
            String status = (String) accommodationStatusJComboBox.getSelectedItem();
            txtMessage.setText(resort.getFilteredAccommodationsAsString(type, status, 0));
        } else if (e.getSource() == btnApply) {
            String type = (String) accommodationTypeJComboBox.getSelectedItem();
            String status = (String) accommodationStatusJComboBox.getSelectedItem();
            double maxPrice = 0;
            try {
                String priceText = accommodationPriceJTextField.getText().trim();
                if (!priceText.isEmpty()) {
                    maxPrice = Double.parseDouble(priceText);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid price. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            txtMessage.setText(resort.getFilteredAccommodationsAsString(type, status, maxPrice));
            txtMessage.setCaretPosition(0);
        } else if (e.getSource() == btnReset) {
            accommodationTypeJComboBox.setSelectedIndex(0);
            accommodationStatusJComboBox.setSelectedIndex(0);
            accommodationPriceJTextField.setText("");
            txtMessage.setText(resort.getAllAccommodationsAsString());
            txtMessage.setCaretPosition(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new AccommodationsWindow(new MtBullerResort()).setVisible(true));
    }
}