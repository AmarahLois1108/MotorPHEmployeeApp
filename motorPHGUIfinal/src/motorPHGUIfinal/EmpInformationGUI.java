package motorPHGUIfinal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EmpInformationGUI extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

    public EmpInformationGUI(String employeeId) {
        setTitle("Employee Personal Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLUE);
        headerPanel.setPreferredSize(new Dimension(600, 100));
        JLabel titleLabel = new JLabel("Employee Salary Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new GridBagLayout());

        // Create table model and table
        DefaultTableModel tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        // Add columns to the table model
        tableModel.addColumn("");
        tableModel.addColumn("");


        // Retrieve employee details from GetData class
        String[][] employeeDetails = {
                {"Employee ID", GetData.getEmployeeId(employeeId)},
                {"Last Name", GetData.getLastName(employeeId)},
                {"First Name", GetData.getFirstName(employeeId)},
                {"Birthday", GetData.getBirthday(employeeId)},
                {"Address", GetData.getAddress(employeeId)},
                {"Phone Number", GetData.getPhoneNumber(employeeId)},
                {"SSS #", GetData.getSssNo(employeeId)},
                {"Philhealth #", GetData.getPhilhealthNo(employeeId)},
                {"TIN #", GetData.getTinNo(employeeId)},
                {"Pag-ibig #", GetData.getPagibigNo(employeeId)},
                {"Status", GetData.getStatus(employeeId)},
                {"Position", GetData.getPosition(employeeId)},
                {"Supervisor", GetData.getSupervisor(employeeId)}
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10);


        // Add details to the content panel
        for (String[] row : employeeDetails) {
            JLabel label = new JLabel(row[0] + ":");
            label.setFont(new Font("Arial", Font.BOLD, 14));
            contentPanel.add(label, gbc);

            gbc.gridx = 1;
            JLabel value = new JLabel(row[1]);
            value.setFont(new Font("Arial", Font.PLAIN, 14));
            contentPanel.add(value, gbc);

            gbc.gridy++;
            gbc.gridx = 0;
        }

        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.GRAY);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmpInformationGUI("employee123"));
    }
}
