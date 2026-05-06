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
        filterJPanel.add(accommodationTypeJLabel);
        String[] accommodationTypes = {
            "All",
            "Lodge",
            "Apartment",
            "Hotel"
        };

        accommodationTypeJComboBox = new JComboBox<>(accommodationTypes);
        accommodationTypeJComboBox.addActionListener(this);
        filterJPanel.add(accommodationTypeJComboBox);

        accommodationStatusJLabel = new JLabel("Accommodation Status:");
        filterJPanel.add(accommodationStatusJLabel);
        String[] accommodationStatuses = {
            "All",
            "Available"
        };

        accommodationStatusJComboBox = new JComboBox<>(accommodationStatuses);
        accommodationStatusJComboBox.addActionListener(this);
        filterJPanel.add(accommodationStatusJComboBox);

        accommodationPriceJLabel = new JLabel("Max Price:");
        accommodationPriceJTextField = new JTextField(8);
        filterJPanel.add(accommodationPriceJLabel);
        filterJPanel.add(accommodationPriceJTextField);
        btnApply = new JButton("Apply");
        btnReset = new JButton("Reset");
        btnApply.addActionListener(this);
        btnReset.addActionListener(this);
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