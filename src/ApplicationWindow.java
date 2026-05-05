import javax.swing.*;
import java.awt.*;

public abstract class ApplicationWindow extends JFrame {

    protected JPanel pnlMain;

    public ApplicationWindow(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(Color.WHITE);
        JLabel lblTitle = new JLabel("Mt Buller Resort Admin Panel");
        lblTitle.setForeground(Color.BLACK);
        pnlHeader.setOpaque(true);
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        pnlMain = buildMainPanel();
        add(pnlMain, BorderLayout.CENTER);
    }

    protected abstract JPanel buildMainPanel();
}