package motor_PH;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

	public class LeaveEmployeePanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private DefaultTableModel tableModel;
		private JDialog invalidDateDialog;

		public LeaveEmployeePanel(String employeeId) {
			setBackground(new Color(240, 240, 240));
			setBounds(new Rectangle(218, 0, 564, 561));
			setLayout(new BorderLayout());

			JPanel leaveFilingPanel = new JPanel();
			leaveFilingPanel.setBackground(new Color(245, 245, 245));
			leaveFilingPanel.setPreferredSize(new Dimension(280, 100));
			leaveFilingPanel.setLayout(new BorderLayout());

			// Create a nested panel for the top section
			JPanel topPanel = new JPanel(new BorderLayout());
			topPanel.setBackground(new Color(240, 240, 240));
			topPanel.setPreferredSize(new Dimension(560, 160));
			topPanel.add(leaveFilingPanel, BorderLayout.EAST);
			add(topPanel, BorderLayout.NORTH);

			JPanel leaveCreditsPanel = new JPanel();
			leaveCreditsPanel.setBackground(new Color(240, 240, 240));
			leaveCreditsPanel.setBorder(null);
			leaveCreditsPanel.setPreferredSize(new Dimension(280, 160));
			topPanel.add(leaveCreditsPanel, BorderLayout.WEST);
			leaveCreditsPanel.setLayout(null);

			// Create a label for available leave credits
			JLabel leaveCreditsLbl = new JLabel("Available Leave Credits");
			leaveCreditsLbl.setBounds(21, 25, 154, 42);
			leaveCreditsLbl.setBackground(new Color(240, 240, 240));
			leaveCreditsLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
			leaveCreditsLbl.setHorizontalAlignment(SwingConstants.CENTER); // Center align the label text
			leaveCreditsPanel.add(leaveCreditsLbl);

			// Create labels for the leave credit values
			JLabel lblSickLeaveCredits = new JLabel("Sick Leave:");
			lblSickLeaveCredits.setBounds(new Rectangle(0, 0, 40, 10));
			lblSickLeaveCredits.setPreferredSize(new Dimension(40, 10));
			lblSickLeaveCredits.setBounds(27, 64, 178, 24);
			lblSickLeaveCredits.setFont(new Font("Tahoma", Font.PLAIN, 12));
			leaveCreditsPanel.add(lblSickLeaveCredits);

			JLabel lblVacationLeaveCredits = new JLabel("Vacation Leave:");
			lblVacationLeaveCredits.setPreferredSize(new Dimension(40, 10));
			lblVacationLeaveCredits.setBounds(27, 90, 178, 24);
			lblVacationLeaveCredits.setFont(new Font("Tahoma", Font.PLAIN, 12));
			leaveCreditsPanel.add(lblVacationLeaveCredits);

			JLabel lblEmergencyLeaveCredits = new JLabel("Emergency Leave:");
			lblEmergencyLeaveCredits.setBounds(27, 113, 178, 24);
			lblEmergencyLeaveCredits.setFont(new Font("Tahoma", Font.PLAIN, 12));
			leaveCreditsPanel.add(lblEmergencyLeaveCredits);

			// Retrieve leave credits from the CSV file
			Map<String, Map<String, Integer>> leaveCreditsMap = LeaveCreditManager.readLeaveCredits();
			Map<String, Integer> employeeCredits = leaveCreditsMap.get(employeeId);

			if (employeeCredits != null) {
				
				Integer sickLeaveCredits = employeeCredits.get("Sick Leave");
				Integer vacationLeaveCredits = employeeCredits.get("Vacation Leave");
				Integer emergencyLeaveCredits = employeeCredits.get("Emergency Leave");

				// Update the labels with leave credit values
				lblSickLeaveCredits.setText("Sick Leave: " + (sickLeaveCredits != null ? sickLeaveCredits : 0));
				lblVacationLeaveCredits.setText("Vacation Leave: " + (vacationLeaveCredits != null ? vacationLeaveCredits : 0));
				lblEmergencyLeaveCredits.setText("Emergency Leave: " + (emergencyLeaveCredits != null ? emergencyLeaveCredits : 0));
			}

			JPanel leaveFormPanel = new JPanel();
			leaveFormPanel.setLayout(null);
			leaveFilingPanel.add(leaveFormPanel, BorderLayout.CENTER);

			// Create labels and fields for the leave filing form
			JLabel leaveTypeLbl = new JLabel("Leave Type:");
			leaveTypeLbl.setBounds(10, 57, 80, 14);
			leaveFormPanel.add(leaveTypeLbl);

			JComboBox<String> leaveTypeComboBox = new JComboBox<>();
			leaveTypeComboBox.setBounds(100, 54, 150, 20);
			leaveTypeComboBox.addItem("Sick Leave");
			leaveTypeComboBox.addItem("Vacation Leave");
			leaveTypeComboBox.addItem("Emergency Leave");
			leaveFormPanel.add(leaveTypeComboBox);

			JLabel startDateLbl = new JLabel("Start Date:");
			startDateLbl.setBounds(10, 82, 80, 14);
			leaveFormPanel.add(startDateLbl);

			JFormattedTextField startDateField = createFormattedTextField("##/##/####");
			startDateField.setBounds(100, 79, 150, 20);
			leaveFormPanel.add(startDateField);

			JLabel endDateLbl = new JLabel("End Date:");
			endDateLbl.setBounds(10, 107, 80, 14);
			leaveFormPanel.add(endDateLbl);

			JFormattedTextField endDateField = createFormattedTextField("##/##/####");
			endDateField.setBounds(100, 104, 150, 20);
			leaveFormPanel.add(endDateField);

			JButton submitButton = new JButton("Submit");
			submitButton.setBounds(100, 129, 80, 20);
			leaveFormPanel.add(submitButton);

			JScrollPane leaveStatusScrollPane = new JScrollPane();
			leaveStatusScrollPane.setBorder(null);
			leaveStatusScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			leaveStatusScrollPane.setBackground(new Color(245, 245, 245));
			leaveStatusScrollPane.setPreferredSize(new Dimension(561, 350));
			add(leaveStatusScrollPane, BorderLayout.SOUTH);

			// Create a table for displaying leave status
			tableModel = new DefaultTableModel();
			tableModel.addColumn("Employee ID");
			tableModel.addColumn("Date");
			tableModel.addColumn("Leave Type");
			tableModel.addColumn("Start Date");
			tableModel.addColumn("End Date");
			tableModel.addColumn("Status");

			JTable leaveStatusTable = new JTable(tableModel);
			leaveStatusScrollPane.setViewportView(leaveStatusTable);

			// Call the method to update the leave status table
			updateLeaveStatusTable(tableModel,employeeId);
			// Add action listener to the submit button
			
				
			submitButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        String leaveType = (String) leaveTypeComboBox.getSelectedItem();
			        String startDate = startDateField.getText();
			        String endDate = endDateField.getText();

			        // Validate the start and end dates
			        if (!isValidDate(startDate) || !isValidDate(endDate)) {
			            showInvalidDateDialog();
			            return;
			        }

			        // Calculate the number of leave days
			        int numberOfDays = LeaveManager.calculateLeaveDays(startDate, endDate);

			        // Check if there are enough leave credits
			        int availableCredits = LeaveCreditManager.getAvailableLeaveCredits(employeeId, leaveType);
			        if (availableCredits < numberOfDays) {
			            showInsufficientCreditsDialog();
			            return;
			        }

			        // Perform leave filing logic
			        LeaveManager.applyLeave(employeeId, leaveType, startDate, endDate);

			        // Update the table with the new leave status
			        String currentDate = LeaveManager.getCurrentDate();
			        tableModel.addRow(new Object[]{employeeId, currentDate, leaveType, startDate, endDate, "Pending"});

			        // Clear the text fields
			        startDateField.setText("");
			        endDateField.setText("");

			        // Show leave filed successfully dialog
			        showLeaveFiledSuccessfullyDialog();
			    }
			});
		}

	

			private void showLeaveFiledSuccessfullyDialog() {
			   
			    JOptionPane.showMessageDialog(null, "Leave filed successfully.");
			}
			
			
			private void showInsufficientCreditsDialog() {
				   
			    JOptionPane.showMessageDialog(null, "Insufficient Leave Credits.");
			}
			
			
		public static void updateLeaveStatusTable(DefaultTableModel tableModel, String employeeId) {
		    // Clear the existing rows from the table
		    tableModel.setRowCount(0);

		    try (BufferedReader reader = new BufferedReader(new FileReader("Leave_Status.csv"))) {
		        String line;
		        while ((line = reader.readLine()) != null) {
		            String[] rowData = line.split(",");
		            if (rowData.length >= 6 && rowData[0].equals(employeeId)) {
		                tableModel.addRow(rowData);
		            }
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}


		private JFormattedTextField createFormattedTextField(String mask) {
			JFormattedTextField formattedTextField;
			try {
				MaskFormatter formatter = new MaskFormatter(mask);
				formatter.setPlaceholderCharacter('_');
				formattedTextField = new JFormattedTextField(formatter);
			} catch (ParseException e) {
				formattedTextField = new JFormattedTextField();
			}
			return formattedTextField;
		}

		private boolean isValidDate(String dateStr) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			dateFormat.setLenient(false);
			try {
				dateFormat.parse(dateStr);
				return true;
			} catch (ParseException e) {
				return false;
			}
		}

		private void showInvalidDateDialog() {
			if (invalidDateDialog == null) {
				invalidDateDialog = new JDialog();
				invalidDateDialog.setTitle("Invalid Date");
				invalidDateDialog.setSize(300, 100);
				invalidDateDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				invalidDateDialog.setLocationRelativeTo(this);
				JLabel label = new JLabel("Invalid date format. Please enter a valid date.");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				invalidDateDialog.getContentPane().add(label);
			}
			invalidDateDialog.setVisible(true);
		}
	}
