package motor_PH;

import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class PayslipTablePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private DefaultTableModel tableModel;
    protected JTable table;
    private String employeeId;

    public PayslipTablePanel(String employeeId) {
        this.employeeId = employeeId;
        setLayout(new BorderLayout());
        setBackground(new Color(220, 220, 220));
    }

    public void displayPayslipDetails(int weekNumber) {
        double hoursWorkedInWeek = Calculations.calculateHoursWorkedInWeek(employeeId, weekNumber);
        double weeklyGrossSalary = Calculations.calculateWeeklyGrossSalary(employeeId, weekNumber);
        double hourlyRate = GetData.getHourlyRate(employeeId);
        double riceSubsidy = GetData.getRiceSubsidy(employeeId) / 4;
        double phoneAllowance = GetData.getPhoneAllowance(employeeId) / 4;
        double clothingAllowance = GetData.getClothingAllowance(employeeId) / 4;
        double philhealth = GetData.getPhilhealthDeduction(employeeId) / 4;
        double sss = GetData.getSssDeduction(employeeId) / 4;
        double pagibig = GetData.getPagibigDeduction(employeeId) / 4;
        double tax = Calculations.calculateWithholdingTax(weeklyGrossSalary);
        double weeklyDeduction = Calculations.calculateWeeklyDeduction(employeeId, weekNumber, weeklyGrossSalary);
        double weeklyNetSalary = weeklyGrossSalary - weeklyDeduction;

        DecimalFormat df = new DecimalFormat("#.##");
        String formattedWeeklyNetSalary = df.format(weeklyNetSalary);
        String formattedWeeklyDeductions = df.format(weeklyDeduction);
        String formattedWeeklyGrossSalary = df.format(weeklyGrossSalary);
        String formattedTax = df.format(tax);

        LocalDate startDate = LocalDate.now().with(WeekFields.ISO.weekOfYear(), weekNumber).with(WeekFields.ISO.dayOfWeek(), 1);
        LocalDate endDate = startDate.plusDays(6);

        String[] columnNames = {"", ""};

        Object[][] rowData = {
            {"Employee ID", employeeId},
            {"Employee Name", GetData.getFirstName(employeeId) + " " + GetData.getLastName(employeeId)},
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

        // Create the table with the given data
        createTable(columnNames, rowData);

        // Set the table properties
        setTableProperties();

        add(table, BorderLayout.CENTER);
    }

    private void createTable(String[] columnNames, Object[][] rowData) {
        tableModel = new DefaultTableModel(rowData, columnNames);
        table = new JTable(tableModel);
    }

    private void setTableProperties() {
        table.setBorder(new LineBorder(new Color(255, 255, 255)));
        table.setSelectionForeground(Color.WHITE);
        table.setRowSelectionAllowed(false);
        table.setRowMargin(10);
        table.setRowHeight(28);
        table.setGridColor(Color.WHITE);
        table.setForeground(new Color(0, 0, 128));
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        table.setEnabled(false);
        table.setBackground(Color.WHITE);
        table.setAutoscrolls(false);
        table.setAutoCreateColumnsFromModel(false);

        Main main = new Main();
        DefaultTableCellRenderer renderer = main.createCustomCellRenderer();
        TableColumnModel columnModel = table.getColumnModel();
        for (int columnIndex = 0; columnIndex < columnModel.getColumnCount(); columnIndex++) {
            columnModel.getColumn(columnIndex).setCellRenderer(renderer);
        }

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 12));
        header.setBackground(new Color(220, 220, 220));
    }
}
