package motor_PH;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class EmployeeDetailsWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txt_ChooseWeek;
    private JButton btnViewWeekPayslip;

    public EmployeeDetailsWindow(String[] employeeDetails) {
        setPreferredSize(new Dimension(350, 500));
        getContentPane().setPreferredSize(new Dimension(350, 500));
        String employeeId = employeeDetails[0];

        setTitle("Employee Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(454, 552);
        setLocationRelativeTo(null);

        getContentPane().setLayout(null);

        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(340, 400));
        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setBounds(0, 0, 484, 422);
        infoPanel.setLayout(null);
        getContentPane().add(infoPanel);

        // Create data for the employee details table
        String[][] data = {
                { "Employee ID", GetData.getEmployeeId(employeeId) },
                { "Employee Name", GetData.getFirstName(employeeId) + " " + GetData.getLastName(employeeId) },
                { "Birthday", GetData.getBirthday(employeeId) },
                { "Address", GetData.getAddress(employeeId) },
                { "Phone Number", GetData.getPhoneNumber(employeeId) },
                { "SSS", GetData.getSssNo(employeeId) },
                { "Philhealth", GetData.getPhilhealthNo(employeeId) },
                { "TIN", GetData.getTinNo(employeeId) },
                { "Pag-ibig", GetData.getPagibigNo(employeeId) },
                { "Status", GetData.getStatus(employeeId) },
                { "Position", GetData.getPosition(employeeId) },
                { "Supervisor", GetData.getSupervisor(employeeId) }
        };
        String[] columns = { "", "" };

        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(tableModel);
        table.setBounds(89, 61, 315, 358);
        table.setBorder(new LineBorder(Color.WHITE));
        table.setSelectionForeground(Color.WHITE);
        table.setRowSelectionAllowed(false);
        table.setRowMargin(8);
        table.setRowHeight(29);
        table.setGridColor(Color.WHITE);
        table.setForeground(new Color(0, 0, 128));
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        table.setEnabled(false);
        table.setBackground(new Color(255, 255, 255));
        table.setAutoscrolls(false);
        table.setAutoCreateColumnsFromModel(false);
        infoPanel.add(table);

        // Create custom cell renderer
        Main main = new Main();
        DefaultTableCellRenderer renderer = main.createCustomCellRenderer();

        // Set cell renderer for each column in the table
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(300);
        for (int columnIndex = 0; columnIndex < columnModel.getColumnCount(); columnIndex++) {
            columnModel.getColumn(columnIndex).setCellRenderer(renderer);
        }

        JLabel viewInfoLabel = new JLabel("Employee Details");
        viewInfoLabel.setForeground(new Color(0, 0, 128));
        viewInfoLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        viewInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        viewInfoLabel.setBounds(25, 10, 450, 40);
        infoPanel.add(viewInfoLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(255, 255, 255));
        inputPanel.setBounds(0, 420, 484, 93);
        getContentPane().add(inputPanel);
        inputPanel.setLayout(null);

        JLabel label = new JLabel("Enter week from (1-52):");
        label.setForeground(new Color(0, 0, 128));
        label.setFont(new Font("Tahoma", Font.BOLD, 12));
        label.setBounds(80, 48, 149, 14);
        inputPanel.add(label);

        txt_ChooseWeek = new JTextField(10);
        txt_ChooseWeek.setBounds(239, 46, 45, 20);
        inputPanel.add(txt_ChooseWeek);

        btnViewWeekPayslip = new JButton("View");
        btnViewWeekPayslip.setBackground(new Color(25, 25, 112));
        btnViewWeekPayslip.setBorder(null);
        btnViewWeekPayslip.setForeground(new Color(255, 255, 255));
        btnViewWeekPayslip.setBounds(294, 45, 55, 23);
        inputPanel.add(btnViewWeekPayslip);

        JLabel lblNewLabel = new JLabel("View Payslip");
        lblNewLabel.setForeground(new Color(0, 0, 128));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(212, 11, 116, 14);
        inputPanel.add(lblNewLabel);

        // Event listener for the "View" button
        btnViewWeekPayslip.addActionListener(e -> {
            String input = txt_ChooseWeek.getText();

            try {
                int weekNumber = Integer.parseInt(input);
                if (weekNumber < 1 || weekNumber > 52) {
                    JOptionPane.showMessageDialog(null, "Invalid week number. Please enter a number between 1 and 52.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AdminViewPayslipFrame adminViewPayslip = new AdminViewPayslipFrame(employeeId);
                adminViewPayslip.setFrameDesign(employeeId, weekNumber);
                adminViewPayslip.setVisible(true);

            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
