package motorPHGUIfinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

public class AdminMenuGUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final int HEADER_PANEL_WIDTH = 800;
	private static final int HEADER_PANEL_HEIGHT = 100;
	private static final Font WELCOME_LABEL_FONT = new Font("Arial", Font.BOLD, 24);
	private static final Font TITLE_LABEL_FONT = new Font("Arial", Font.BOLD, 36);
	private static final Color HEADER_PANEL_COLOR = Color.BLUE;
	private static final Color CONTENT_PANEL_COLOR = Color.WHITE;
	private static final int MENU_PANEL_ROWS = 6;
	private static final int MENU_PANEL_COLUMNS = 1;
	private static final int MENU_PANEL_HORIZONTAL_GAP = 10;
	private static final int MENU_PANEL_VERTICAL_GAP = 10;
	private static final int EMPTY_PANEL_WIDTH = 100;
	private static final int EMPTY_PANEL_HEIGHT = 100;

	private String employeeId;
	private JLabel welcomeLabel;

	public AdminMenuGUI(String employeeId) {
	    this.employeeId = employeeId;
	    setTitle("Admin Menu");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().setLayout(new BorderLayout());

	    JPanel headerPanel = createHeaderPanel();
	    JPanel contentPanel = createContentPanel();

	    getContentPane().add(headerPanel, BorderLayout.NORTH);
	    getContentPane().add(contentPanel, BorderLayout.CENTER);

	    pack();
	    setLocationRelativeTo(null);
	    setVisible(true);
	}

	private JPanel createHeaderPanel() {
	    JPanel headerPanel = new JPanel();
	    headerPanel.setBackground(HEADER_PANEL_COLOR);
	    headerPanel.setPreferredSize(new Dimension(HEADER_PANEL_WIDTH, HEADER_PANEL_HEIGHT));

	    JLabel titleLabel = new JLabel("MotorPH Employee App Admin");
	    titleLabel.setFont(TITLE_LABEL_FONT);
	    titleLabel.setForeground(Color.WHITE);
	    headerPanel.add(titleLabel);

	    return headerPanel;
	}

	private JPanel createContentPanel() {
	    JPanel contentPanel = new JPanel();
	    contentPanel.setBackground(CONTENT_PANEL_COLOR);
	    contentPanel.setLayout(new BorderLayout());

	    JPanel welcomePanel = createWelcomePanel();

	    JPanel menuPanel = createMenuPanel();
	    JPanel emptyPanel = createEmptyPanel();

	    contentPanel.add(menuPanel, BorderLayout.WEST);
	    contentPanel.add(emptyPanel, BorderLayout.CENTER);

	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BorderLayout());
	    mainPanel.add(welcomePanel, BorderLayout.CENTER);
	    mainPanel.add(contentPanel, BorderLayout.SOUTH);

	    return mainPanel;
	}

	private JPanel createWelcomePanel() {
	    JPanel welcomePanel = new JPanel();
	    welcomePanel.setBackground(CONTENT_PANEL_COLOR);

	    String firstName = GetData.getFirstName(employeeId);
	    String lastName = GetData.getLastName(employeeId);
	    String welcomeMessage = "Welcome, " + firstName + " " + lastName;

	    welcomeLabel = new JLabel(welcomeMessage);
	    welcomeLabel.setFont(WELCOME_LABEL_FONT);

	    welcomePanel.add(welcomeLabel);

	    return welcomePanel;
	}

	private JPanel createMenuPanel() {
	    JPanel menuPanel = new JPanel();
	    menuPanel.setLayout(new GridLayout(MENU_PANEL_ROWS, MENU_PANEL_COLUMNS,
	            MENU_PANEL_HORIZONTAL_GAP, MENU_PANEL_VERTICAL_GAP));
	    menuPanel.setBackground(CONTENT_PANEL_COLOR);

	    JButton viewEmployeeRecordsButton = createMenuButton("Employee Records");
	    JButton userManagementButton = createMenuButton("User Management");
	    JButton leaveApplicationsButton = createMenuButton("Leave Applications");
	    JButton logoutButton = createMenuButton("Logout");

	    menuPanel.add(viewEmployeeRecordsButton);
	    menuPanel.add(userManagementButton);
	    menuPanel.add(leaveApplicationsButton);
	    menuPanel.add(logoutButton);

	    return menuPanel;
	}

	private JButton createMenuButton(String buttonText) {
	    JButton button = new JButton(buttonText);
	    button.addActionListener(this);
	    return button;
	}

	private JPanel createEmptyPanel() {
	    JPanel emptyPanel = new JPanel();
	    emptyPanel.setBackground(CONTENT_PANEL_COLOR);
	    emptyPanel.setPreferredSize(new Dimension(EMPTY_PANEL_WIDTH, EMPTY_PANEL_HEIGHT));
	    return emptyPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    String buttonText = ((JButton) e.getSource()).getText();

	    switch (buttonText) {
	        case "Employee Records":
	            showEmployeeRecords();
	            break;
	        case "User Management":
	            showUserManagement();
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

	private void showUserManagement() {
	    UserManagementGUI userManagementGUI = new UserManagementGUI();
	    userManagementGUI.setVisible(true);
	}

	private void showNotImplementedMessage() {
	    JOptionPane.showMessageDialog(null, "This feature is not implemented yet.", "Not Implemented",
	            JOptionPane.INFORMATION_MESSAGE);
	}

	private void confirmLogout() {
	    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirm Logout",
	            JOptionPane.YES_NO_OPTION);
	    if (confirm == JOptionPane.YES_OPTION) {
	        dispose();
	        MotorPHAppGUI loginScreen = new MotorPHAppGUI();
	        loginScreen.setVisible(true);
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
        viewDetailsButton.setEnabled(false);
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

        frame.pack();
        frame.setMinimumSize(new Dimension(800, 400));
        frame.setLocationRelativeTo(null);
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
}




