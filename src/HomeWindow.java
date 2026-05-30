import javax.swing.*;

import accommodation.Accommodation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeWindow extends ApplicationWindow implements ActionListener {
    private MtBullerResort resort;
    private CustomersWindow customersWindow;
    private BundlesWindow bundlesWindow;

    public HomeWindow(MtBullerResort resort, CustomersWindow customersWindow, BundlesWindow bundlesWindow) {
        super("Home");
        this.resort = resort;
        this.customersWindow = customersWindow;
        this.bundlesWindow = bundlesWindow;
        initPanel();
    }

    @Override
    protected JPanel buildMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel quickActionsPanel = new JPanel();
        quickActionsPanel.setLayout(new BoxLayout(quickActionsPanel, BoxLayout.X_AXIS));
        quickActionsPanel.setBorder(BorderFactory.createTitledBorder("Quick Actions"));

        JButton btnQuickCustomer = new JButton("New Customer");
        btnQuickCustomer.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuickCustomer.addActionListener(e -> {
            customersWindow.showAddCustomerDialog();
        });

        JButton btnQuickBundle = new JButton("New Travel Bundle");
        btnQuickBundle.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnQuickBundle.addActionListener(e -> {
            bundlesWindow.showCreateNewBundleDialog();
        });

        quickActionsPanel.add(btnQuickCustomer);
        quickActionsPanel.add(Box.createHorizontalStrut(10));
        quickActionsPanel.add(btnQuickBundle);

        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 10, 10));

        JPanel accommodationsCard = new JPanel();
        accommodationsCard.setLayout(new BoxLayout(accommodationsCard, BoxLayout.Y_AXIS));
        accommodationsCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel accommodationsNumber = new JLabel(String.valueOf(resort.getAccommodations().size()));
        accommodationsNumber.setFont(new Font("SansSerif", Font.BOLD, 48));
        accommodationsNumber.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel accommodationsDescription = new JLabel("Accommodations");
        accommodationsDescription.setAlignmentX(Component.CENTER_ALIGNMENT);

        int booked = 0;
        for (Accommodation a : resort.getAccommodations()) {
            if (!a.isAvailable()) {
                booked++;
            }
        }

        JLabel bookedLabel = new JLabel(booked + " of " + resort.getAccommodations().size() + " booked");
        bookedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        accommodationsCard.add(accommodationsNumber);
        accommodationsCard.add(accommodationsDescription);
        accommodationsCard.add(bookedLabel);

        JPanel customersCard = new JPanel();
        customersCard.setLayout(new BoxLayout(customersCard, BoxLayout.Y_AXIS));
        customersCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel customersNumber = new JLabel(String.valueOf(resort.getCustomers().size()));
        customersNumber.setFont(new Font("SansSerif", Font.BOLD, 48));
        customersNumber.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel customersDescription = new JLabel("customers");
        customersDescription.setAlignmentX(Component.CENTER_ALIGNMENT);

        customersCard.add(customersNumber);
        customersCard.add(customersDescription);

        JPanel bundlesCard = new JPanel();
        bundlesCard.setLayout(new BoxLayout(bundlesCard, BoxLayout.Y_AXIS));
        bundlesCard.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel bundlesNumber = new JLabel(String.valueOf(resort.getBundles().size()));
        bundlesNumber.setFont(new Font("SansSerif", Font.BOLD, 48));
        bundlesNumber.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel bundlesDescription = new JLabel("bundles");
        bundlesDescription.setAlignmentX(Component.CENTER_ALIGNMENT);

        bundlesCard.add(bundlesNumber);
        bundlesCard.add(bundlesDescription);

        cardsPanel.add(accommodationsCard);
        cardsPanel.add(customersCard);
        cardsPanel.add(bundlesCard);

        panel.add(quickActionsPanel, BorderLayout.NORTH);
        panel.add(cardsPanel, BorderLayout.CENTER);

        return panel;
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new HomeWindow(new MtBullerResort(), null, null).setVisible(true));
    }
}
