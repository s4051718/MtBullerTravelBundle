import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccommodationsWindow extends ApplicationWindow implements ActionListener {

    public AccommodationsWindow() {
        super("Accommodations");
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

    private MtBullerResort resort = new MtBullerResort();

    @Override
    protected JPanel buildMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());

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

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == accommodationTypeJComboBox || e.getSource() == accommodationStatusJComboBox) {
            String type = (String) accommodationTypeJComboBox.getSelectedItem();
            String status = (String) accommodationStatusJComboBox.getSelectedItem();
            txtMessage.setText(resort.getFilteredAccommodationsAsString(type, status));
        } else if (e.getSource() == btnApply) {
            /*
            Price filter logic (Innovative feature #1)
            */
            String type = (String) accommodationTypeJComboBox.getSelectedItem();
            String status = (String) accommodationStatusJComboBox.getSelectedItem();
            txtMessage.setText(resort.getFilteredAccommodationsAsString(type, status));
        } else if (e.getSource() == btnReset) {
            accommodationTypeJComboBox.setSelectedIndex(0);
            accommodationStatusJComboBox.setSelectedIndex(0);
            accommodationPriceJTextField.setText("");
            txtMessage.setText(resort.getAllAccommodationsAsString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AccommodationsWindow().setVisible(true));
    }
}