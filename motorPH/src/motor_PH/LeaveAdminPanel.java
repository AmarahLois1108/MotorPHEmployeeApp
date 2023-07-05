package motor_PH;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Font;

public class LeaveAdminPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable pendingTable;
    private JTable historyTable;

    public LeaveAdminPanel() {
        // Create the pending leave applications table
        DefaultTableModel pendingTableModel = new DefaultTableModel();
        pendingTableModel.addColumn("Employee ID");
        pendingTableModel.addColumn("Date Filed");
        pendingTableModel.addColumn("Leave Type");
        pendingTableModel.addColumn("Start Date");
        pendingTableModel.addColumn("End Date");
        pendingTableModel.addColumn("Status");

        pendingTable = new JTable(pendingTableModel);
        JScrollPane pendingScrollPane = new JScrollPane(pendingTable);

        // Create the leave application history table
        DefaultTableModel historyTableModel = new DefaultTableModel();
        historyTableModel.addColumn("Employee ID");
        historyTableModel.addColumn("Date Filed");
        historyTableModel.addColumn("Leave Type");
        historyTableModel.addColumn("Start Date");
        historyTableModel.addColumn("End Date");
        historyTableModel.addColumn("Status");

        historyTable = new JTable(historyTableModel);
        JScrollPane historyScrollPane = new JScrollPane(historyTable);

        // Create a split pane to display the tables
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, pendingScrollPane, historyScrollPane);
        splitPane.setDividerSize(20);
        splitPane.setBounds(10, 37, 642, 456);
        splitPane.setResizeWeight(0.5);

        // Load leave status data from CSV file
        loadLeaveStatusData("Leave_Status.csv", pendingTableModel, historyTableModel);
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 662, 504);
        add(panel);
        panel.setLayout(null);

        // Add the split pane to the main panel
        panel.add(splitPane);

        // Create labels for the split pane tables
        JLabel requestLabel = new JLabel("Leave Request");
        requestLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        requestLabel.setBounds(292, 6, 165, 20);
        panel.add(requestLabel);

        JLabel historyLabel = new JLabel("Leave History");
        historyLabel.setBounds(10, 220, 100, 20);
        panel.add(historyLabel);

        // Add mouse listener to the pendingTable
        pendingTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int selectedRow = pendingTable.getSelectedRow();
                    String employeeId = (String) pendingTable.getValueAt(selectedRow, 0);
                    String dateFiled = (String) pendingTable.getValueAt(selectedRow, 1);
                    String leaveType = (String) pendingTable.getValueAt(selectedRow, 2);
                    String startDate = (String) pendingTable.getValueAt(selectedRow, 3);
                    String endDate = (String) pendingTable.getValueAt(selectedRow, 4);
                    String status = (String) pendingTable.getValueAt(selectedRow, 5);

                    // Open the JDialog for approval or disapproval
                    LeaveApprovalDialog approvalDialog = new LeaveApprovalDialog(employeeId, dateFiled, leaveType, startDate, endDate, status);
                    approvalDialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                            boolean isApproved = approvalDialog.isApproved();
                            if (isApproved) {
                                int leaveDays = LeaveManager.calculateLeaveDays(startDate, endDate);
                                deductLeaveCredits(employeeId, leaveType, leaveDays);
                            }
                            updateLeaveStatusData(selectedRow, pendingTableModel, historyTableModel);
                        }
                    });
                    approvalDialog.setVisible(true);
                }
            }
        });
    }

    private void loadLeaveStatusData(String filePath, DefaultTableModel pendingTableModel, DefaultTableModel historyTableModel) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 6) {
                    String employeeId = data[0];
                    String dateFiled = data[1];
                    String leaveType = data[2];
                    String startDate = data[3];
                    String endDate = data[4];
                    String status = data[5];

                    if (status.equals("pending")) {
                        pendingTableModel.addRow(new Object[]{employeeId, dateFiled, leaveType, startDate, endDate, status});
                    } else {
                        historyTableModel.addRow(new Object[]{employeeId, dateFiled, leaveType, startDate, endDate, status});
                    }
                } else {
                    // Handle case when the line does not contain enough elements
                    System.err.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void deductLeaveCredits(String employeeId, String leaveType, int days) {
        LeaveCreditManager.deductLeaveCredits(employeeId, leaveType, days);
    }

    private void updateLeaveStatusData(int selectedRow, DefaultTableModel pendingTableModel, DefaultTableModel historyTableModel) {
        String employeeId = (String) pendingTableModel.getValueAt(selectedRow, 0);
        String dateFiled = (String) pendingTableModel.getValueAt(selectedRow, 1);
        String leaveType = (String) pendingTableModel.getValueAt(selectedRow, 2);
        String startDate = (String) pendingTableModel.getValueAt(selectedRow, 3);
        String endDate = (String) pendingTableModel.getValueAt(selectedRow, 4);
        String status = (String) pendingTableModel.getValueAt(selectedRow, 5);

        pendingTableModel.removeRow(selectedRow);

        if (status.equals("pending")) {
            historyTableModel.addRow(new Object[]{employeeId, dateFiled, leaveType, startDate, endDate, "approved"});
            updateLeaveStatusFile(employeeId, dateFiled, leaveType, startDate, endDate, "approved");

        } else {
            historyTableModel.addRow(new Object[]{employeeId, dateFiled, leaveType, startDate, endDate, status});
        }
    }



    private void updateLeaveStatusFile(String employeeId, String dateFiled, String leaveType, String startDate, String endDate, String status) {
        String filePath = "Leave_Status.csv";
        String tempFile = "tempo.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             FileWriter fw = new FileWriter(tempFile, false)) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String currentEmployeeId = data[0];
                String currentDateFiled = data[1];
                String leaveTypeField = data[2];
                String startDateFiled = data[3];
                String endDateFiled = data[4];

                if (currentEmployeeId.equals(employeeId) && currentDateFiled.equals(dateFiled) && startDateFiled.equals(startDate) && endDateFiled.equals(endDate) && leaveTypeField.equals(leaveType)) {
                    fw.write(employeeId + "," + dateFiled + "," + leaveType + "," + startDate + "," + endDate + "," + status + "\n");
                } else {
                    fw.write(line + "\n"); // Write the unmodified line
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Delete the original file
        File originalFile = new File(filePath);
        if (originalFile.delete()) {
            // Rename the temporary file to replace the original file
            File tempFileObj = new File(tempFile);
            if (tempFileObj.renameTo(originalFile)) {
                System.out.println("Leave status file updated successfully.");
            } else {
                System.out.println("Failed to update leave status file.");
            }
        } else {
            System.out.println("Failed to delete original leave status file.");
        }
    }
}