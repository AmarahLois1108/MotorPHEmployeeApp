package motorPHGUIfinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class EmpPayslipAdminGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private String employeeId;
    private DefaultTableModel tableModel;
	private String firstName;
	private String lastName;

	public EmpPayslipAdminGUI(String employeeId) {
        this.employeeId = employeeId;
        this.firstName = GetData.getFirstName(employeeId);
        this.lastName = GetData.getLastName(employeeId);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose(); // Close the current window
            }
        });

        setTitle("Employee Payslip");

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 122, 204)); // Blue color
        headerPanel.setPreferredSize(new Dimension(600, 100));
        JLabel titleLabel = new JLabel("Employee Payslip");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        tableModel = new DefaultTableModel();
        JTable payslipTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(payslipTable);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.GRAY);
        JButton printButton = new JButton("Print Payslip");

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    payslipTable.print(); // Print the table contents
                } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred while printing the payslip.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonPanel.add(printButton);

        // Customize the table appearance
        payslipTable.setFont(new Font("Arial", Font.PLAIN, 12));

        // Customize the button appearance
        printButton.setBackground(new Color(0, 122, 204)); // Blue color
        printButton.setForeground(Color.WHITE);
        printButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Set the layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        pack();

        displayPayslipDetails();
    }

    void displayPayslipDetails() {
        int weekNumber = -1;

        while (weekNumber < 1 || weekNumber > 52) {
            String input = JOptionPane.showInputDialog(null, "Please enter the week number (1-52) for the payslip:", "Week Number", JOptionPane.QUESTION_MESSAGE);

            if (input == null) {
                // User canceled, exit the method
                return;
            }

            try {
                weekNumber = Integer.parseInt(input);
                if (weekNumber < 1 || weekNumber > 52) {
                    JOptionPane.showMessageDialog(null, "Invalid week number. Please enter a number between 1 and 52.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        double hoursWorkedInWeek = CalculateHours.calculateHoursWorkedInWeek(employeeId, weekNumber);
        double weeklyGrossSalary = GrossSalary.calculateWeeklyGrossSalary(employeeId, weekNumber);
        double hourlyRate = GetData.getHourlyRate(employeeId);
        double riceSubsidy = GetData.getRiceSubsidy(employeeId) / 4;
        double phoneAllowance = GetData.getPhoneAllowance(employeeId) / 4;
        double clothingAllowance = GetData.getClothingAllowance(employeeId) / 4;
        double philhealth = GetData.getPhilhealthDeduction(employeeId) / 4;
        double sss = GetData.getSssDeduction(employeeId) / 4;
        double pagibig = GetData.getPagibigDeduction(employeeId) / 4;
        double tax = Tax.calculateWithholdingTax(weeklyGrossSalary);
        double weeklyDeduction = Deductions.calculateWeeklyDeduction(employeeId, weekNumber, weeklyGrossSalary);
        double weeklyNetSalary = weeklyGrossSalary - weeklyDeduction;

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedWeeklyNetSalary = df.format(weeklyNetSalary);
        String formattedWeeklyDeductions = df.format(weeklyDeduction);
        String formattedWeeklyGrossSalary = df.format(weeklyGrossSalary);
        String formattedTax = df.format(tax);

        LocalDate startDate = LocalDate.now().with(WeekFields.ISO.weekOfYear(), weekNumber).with(WeekFields.ISO.dayOfWeek(), 1);
        LocalDate endDate = startDate.plusDays(6);

        String[] columnNames = {"", ""};
        tableModel.setColumnIdentifiers(columnNames);
        
        

        Object[][] rowData = {
                {"Employee ID", employeeId},
                {"First Name", firstName},
                {"Last Name", lastName},
                {"Week", Integer.toString(weekNumber)},
                {"Pay Period", startDate.format(DateTimeFormatter.ofPattern("MMM d")) + " - " + endDate.format(DateTimeFormatter.ofPattern("MMM d"))},
                {"Hours Worked", Double.toString(hoursWorkedInWeek)},
                {"Hourly Rate", Double.toString(hourlyRate)},
                {"Rice Subsidy", Double.toString(riceSubsidy)},
                {"Phone Allowance", Double.toString(phoneAllowance)},
                {"Clothing Allowance", Double.toString(clothingAllowance)},
                {"Philhealth", Double.toString(philhealth)},
                {"SSS", Double.toString(sss)},
                {"Pagibig", Double.toString(pagibig)},
                {"Tax", formattedTax},
                {"Gross Salary", formattedWeeklyGrossSalary},
                {"Deductions", formattedWeeklyDeductions},
                {"Net Salary", formattedWeeklyNetSalary}
        };

        for (int i = 0; i < rowData.length; i++) {
            tableModel.addRow(rowData[i]);
        }

        setTitle("Employee Payslip - Week " + weekNumber);
    }

    public static void main(String[] args) {
        // Create an instance of MotorPHAppGUI to get the employee ID
        MotorPHAppGUI payrollSystem = new MotorPHAppGUI();

        // Wait for the user to log in and retrieve the employee ID
        String employeeId = payrollSystem.getLoginEmployeeId();

        // Create an instance of EmpInformationGUI with the retrieved employee ID
        SwingUtilities.invokeLater(() -> new EmpInformationGUI(employeeId));
    }
}