package CP2MotorPHGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeMenuGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private String employeeId;
    private JLabel welcomeLabel;

    public EmployeeMenuGUI(String employeeId) {
        this.employeeId = employeeId;
        setTitle("Employee Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLUE);
        headerPanel.setPreferredSize(new Dimension(800, 100));

        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.WHITE);
        welcomeLabel = new JLabel("Welcome, " + GetData.getFirstName(employeeId) + " " +
                GetData.getLastName(employeeId));
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);

        JLabel titleLabel = new JLabel("MotorPH Employee App");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 10, 10));
        menuPanel.setBackground(Color.WHITE);

        JButton viewPersonalDetailsButton = new JButton("View Personal Details");
        viewPersonalDetailsButton.addActionListener(this);
        menuPanel.add(viewPersonalDetailsButton);

        JButton viewSalaryDetailsButton = new JButton("View Salary Details");
        viewSalaryDetailsButton.addActionListener(this);
        menuPanel.add(viewSalaryDetailsButton);

        JButton viewPayslipButton = new JButton("View Payslip");
        viewPayslipButton.addActionListener(this);
        menuPanel.add(viewPayslipButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);
        menuPanel.add(logoutButton);

        contentPanel.add(menuPanel, BorderLayout.WEST);

        JPanel emptyPanel = new JPanel(); // Add an empty panel to create space between the menu and content
        emptyPanel.setBackground(Color.WHITE);
        contentPanel.add(emptyPanel, BorderLayout.CENTER);

        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(welcomePanel, BorderLayout.CENTER);
        getContentPane().add(contentPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override

    public void actionPerformed(ActionEvent e) {
        String buttonText = ((JButton) e.getSource()).getText();

        switch (buttonText) {
            case "View Personal Details":
                new EmpInformationGUI(employeeId).setVisible(true);
                break;
            case "View Salary Details":
                new EmpSalaryGUI(employeeId).setVisible(true);
                break;
            case "View Payslip":
                new EmpPayslipGUI(employeeId).setVisible(true);
                break;
            case "Logout":
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Close the current window and go back to the login screen
                    dispose();
                    MotorPHAppGUI loginScreen = new MotorPHAppGUI();
                    loginScreen.setVisible(true);
                }
                break;
        }
    }
}

