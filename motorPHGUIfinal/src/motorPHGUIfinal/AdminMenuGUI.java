package motorPHGUIfinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;


public class AdminMenuGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private String employeeId;
    private JLabel welcomeLabel;

    public AdminMenuGUI(String employeeId) {
        this.employeeId = employeeId;
        setTitle("Admin Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLUE);
        headerPanel.setPreferredSize(new Dimension(800, 100));

        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.WHITE);
        welcomeLabel = new JLabel("Welcome, " + GetData.getFirstName(employeeId) + " " +
                GetData.getLastName(employeeId));
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomePanel.add(welcomeLabel);

        JLabel titleLabel = new JLabel("MotorPH Employee App Admin");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 10, 10));
        menuPanel.setBackground(Color.WHITE);

        JButton viewEmployeeRecordsButton = new JButton("Employee Records");
        viewEmployeeRecordsButton.addActionListener(this);
        menuPanel.add(viewEmployeeRecordsButton);

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(this);
        menuPanel.add(addUserButton);

        JButton updateUserButton = new JButton("Update User Information");
        updateUserButton.addActionListener(this);
        menuPanel.add(updateUserButton);

        JButton deleteUserButton = new JButton("Delete User");
        deleteUserButton.addActionListener(this);
        menuPanel.add(deleteUserButton);

        JButton leaveApplicationsButton = new JButton("Leave Applications");
        leaveApplicationsButton.addActionListener(this);
        menuPanel.add(leaveApplicationsButton);

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
            case "Employee Records":
                showEmployeeRecords();
                break;
            case "Add User":
                showNotImplementedMessage();
                break;
            case "Update User Information":
                showNotImplementedMessage();
                break;
            case "Delete User":
                showNotImplementedMessage();
                break;
            case "Leave Applications":
                showNotImplementedMessage();
                break;
            case "Logout":
                confirmLogout();
                break;
        }
    }

    private void showEmployeeRecords() {
        EmployeeRecordsPanel recordsPanel = new EmployeeRecordsPanel();
        recordsPanel.displayEmployeeRecords();
    }

    private void showNotImplementedMessage() {
        JOptionPane.showMessageDialog(null, "This feature is not implemented yet.", "Not Implemented", JOptionPane.INFORMATION_MESSAGE);
    }

    private void confirmLogout() {
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Close the current window and go back to the login screen
            dispose();
            MotorPHAppGUI loginScreen = new MotorPHAppGUI();
            loginScreen.setVisible(true);
        }
    }
}


class EmployeeRecordsPanel {
    private JTable table;
    private JButton viewDetailsButton;

    public void displayEmployeeRecords() {
        String[] columnNames = {"Employee Number", "Last Name", "First Name", "SSS No.", "Philhealth No.", "TIN", "Pagibig No."};
        String[][] data = EmployeeDataReader.readEmployeeDetailsFromFile();

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                viewDetailsButton.setEnabled(selectedRow != -1);
            }
        });

        JFrame frame = new JFrame("Employee Records");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setEnabled(false); // Initially disabled until a row is selected
        viewDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String[] employeeDetails = data[selectedRow];
                    showEmployeeDetails(employeeDetails);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select an employee.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(viewDetailsButton);

        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Adjust the size of the frame to fit the content
        frame.pack();

        // Set a minimum size for the frame
        frame.setMinimumSize(new Dimension(800, 400));

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        // Make the frame visible
        frame.setVisible(true);
    }


    private void showEmployeeDetails(String[] employeeDetails) {
        String[] columnNames = {"Employee #", "Last Name", "First Name", "Birthday", "Address", "Phone Number", "SSS #", "Philhealth #", "TIN #", "Pag-ibig #", "Status", "Position", "Immediate Supervisor", "Basic Salary", "Rice Subsidy", "Phone Allowance", "Clothing Allowance", "Gross Semi-monthly Rate", "Hourly Rate"};

        JFrame detailsFrame = new JFrame("Employee Details");
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        detailsFrame.getContentPane().setLayout(new BorderLayout());

        JPanel detailsPanel = new JPanel();
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10);

        for (int i = 0; i < columnNames.length; i++) {
            JLabel label = new JLabel(columnNames[i] + ":");
            label.setFont(new Font("Arial", Font.BOLD, 14));
            detailsPanel.add(label, gbc);

            gbc.gridx = 1;
            JLabel value = new JLabel(employeeDetails[i]);
            value.setFont(new Font("Arial", Font.PLAIN, 14));
            detailsPanel.add(value, gbc);

            gbc.gridy++;
            gbc.gridx = 0;
        }

        JButton payslipButton = new JButton("View Payslip");
        payslipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	EmpPayslipAdminGUI payslipGUI = new EmpPayslipAdminGUI(employeeDetails[0]);
                payslipGUI.setVisible(true);
                detailsFrame.dispose(); // Close the current window
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(payslipButton);

        detailsFrame.getContentPane().add(detailsPanel, BorderLayout.CENTER);
        detailsFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        detailsFrame.pack();
        detailsFrame.setLocationRelativeTo(null);
        detailsFrame.setVisible(true);
    }
}