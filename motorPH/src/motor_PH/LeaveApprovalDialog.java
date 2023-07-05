package motor_PH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

public class LeaveApprovalDialog extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isApproved;

    public LeaveApprovalDialog(String employeeId, String dateFiled, String leaveType, String startDate, String endDate, String status) {
    	setPreferredSize(new Dimension(300, 400));
        isApproved = false;

        setTitle("Leave Approval");
        setSize(283, 262);
        setModal(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        employeeIdLabel.setBounds(62, 11, 88, 18);
        JLabel dateFiledLabel = new JLabel("Date Filed:");
        dateFiledLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        dateFiledLabel.setBounds(62, 40, 88, 18);
        JLabel leaveTypeLabel = new JLabel("Leave Type:");
        leaveTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        leaveTypeLabel.setBounds(62, 69, 88, 18);
        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        startDateLabel.setBounds(62, 98, 88, 18);
        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        endDateLabel.setBounds(62, 127, 88, 18);
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        statusLabel.setBounds(62, 155, 88, 18);

        JLabel employeeIdValue = new JLabel(employeeId);
        employeeIdValue.setBounds(144, 11, 113, 18);
        JLabel dateFiledValue = new JLabel(dateFiled);
        dateFiledValue.setBounds(144, 40, 113, 18);
        JLabel leaveTypeValue = new JLabel(leaveType);
        leaveTypeValue.setBounds(144, 69, 113, 18);
        JLabel startDateValue = new JLabel(startDate);
        startDateValue.setBounds(144, 98, 113, 18);
        JLabel endDateValue = new JLabel(endDate);
        endDateValue.setBounds(144, 127, 113, 18);
        JLabel statusValue = new JLabel(status);
        statusValue.setBounds(144, 155, 88, 18);

        JButton approveButton = new JButton("Approve");
        approveButton.setForeground(new Color(0, 128, 0));
        approveButton.setBorder(new LineBorder(new Color(0, 0, 0)));
        approveButton.setBackground(new Color(192, 192, 192));
        approveButton.setBounds(36, 194, 88, 18);
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isApproved = true;
                dispose();
            }
        });

        JButton disapproveButton = new JButton("Disapprove");
        disapproveButton.setForeground(new Color(220, 20, 60));
        disapproveButton.setBackground(new Color(192, 192, 192));
        disapproveButton.setBorder(new LineBorder(new Color(0, 0, 0)));
        disapproveButton.setPreferredSize(new Dimension(120, 23));
        disapproveButton.setBounds(141, 194, 91, 18);
        disapproveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isApproved = false;
                dispose();
            }
        });
        mainPanel.setLayout(null);

        mainPanel.add(employeeIdLabel);
        mainPanel.add(employeeIdValue);
        mainPanel.add(dateFiledLabel);
        mainPanel.add(dateFiledValue);
        mainPanel.add(leaveTypeLabel);
        mainPanel.add(leaveTypeValue);
        mainPanel.add(startDateLabel);
        mainPanel.add(startDateValue);
        mainPanel.add(endDateLabel);
        mainPanel.add(endDateValue);
        mainPanel.add(statusLabel);
        mainPanel.add(statusValue);
        mainPanel.add(approveButton);
        mainPanel.add(disapproveButton);

        getContentPane().add(mainPanel);
    }

    public boolean isApproved() {
        return isApproved;
    }
}
