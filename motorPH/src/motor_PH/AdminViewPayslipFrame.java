package motor_PH;

import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class AdminViewPayslipFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public AdminViewPayslipFrame(String employeeId) {
        super(employeeId);
    }

    public void setFrameDesign(String employeeId, int weekNumber) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 552);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(460, 650));
        infoPanel.setBackground(new Color(255, 255, 255));
        infoPanel.setBounds(0, 0, 484, 650);
        infoPanel.setLayout(null);
        getContentPane().add(infoPanel);

        String[] columns = { "", "" };
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        table.setBounds(89, 61, 460, 650);
        table.setBorder(new LineBorder(Color.WHITE));
        table.setSelectionForeground(Color.WHITE);
        table.setRowSelectionAllowed(false);
        table.setRowMargin(8);
        table.setRowHeight(29);
        table.setGridColor(Color.WHITE);
        table.setForeground(new Color(0, 0, 128));
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        table.setEnabled(false);
        table.setBackground(Color.WHITE);
        table.setAutoscrolls(false);
        table.setAutoCreateColumnsFromModel(false);
        infoPanel.add(table);

        PayslipTablePanel payslipTablePanel = new PayslipTablePanel(employeeId);
        payslipTablePanel.displayPayslipDetails(weekNumber);
        payslipTablePanel.setBounds(89, 60, 460, 650);
        infoPanel.add(payslipTablePanel);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(120);
        columnModel.getColumn(1).setPreferredWidth(250);
        for (int columnIndex = 0; columnIndex < columnModel.getColumnCount(); columnIndex++) {
            columnModel.getColumn(columnIndex).setCellRenderer(renderer);
        }

        JLabel viewInfoLabel = new JLabel("Employee Payslip");
        viewInfoLabel.setForeground(new Color(0, 0, 128));
        viewInfoLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        viewInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        viewInfoLabel.setBounds(25, 10, 450, 40);
        infoPanel.add(viewInfoLabel);

        setVisible(true);
    }
}

