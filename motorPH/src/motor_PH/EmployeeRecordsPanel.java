package motor_PH;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;


public class EmployeeRecordsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton viewDetailsButton;
    private JButton addEmployeeButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JTable table;
    private JLabel lblNewLabel;

    public EmployeeRecordsPanel() {
        String[] columnNames = {"Employee ID", "Last Name", "First Name", "SSS No.", "Philhealth No.", "TIN", "Pagibig No."};
        String[][] data = getDataFromEmployeeDetails(columnNames);

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Font tableFont = new Font("Tahoma", Font.PLAIN, 11);
        table.setFont(tableFont);

        addEmployeeButton = new JButton("Add"); // Creating the "Add" button
        addEmployeeButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        addEmployeeButton.setForeground(new Color(255, 255, 255));
        addEmployeeButton.setBorder(null);
        addEmployeeButton.setBackground(new Color(220, 20, 60));
        addEmployeeButton.setBounds(227, 512, 89, 23);
        addEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the add employee details window
                showAddEmployeeDialog();
            }
        });

        viewDetailsButton = new JButton("View Details");
        viewDetailsButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        viewDetailsButton.setForeground(new Color(255, 255, 255));
        viewDetailsButton.setBorder(null);
        viewDetailsButton.setBackground(new Color(0, 0, 128));
        viewDetailsButton.setBounds(327, 512, 89, 23);
        viewDetailsButton.setEnabled(false);
        viewDetailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String[] employeeDetails = data[selectedRow];
                    EmployeeDetailsWindow detailsWindow = new EmployeeDetailsWindow(employeeDetails);
                    detailsWindow.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(EmployeeRecordsPanel.this, "Please select an employee.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateButton = new JButton("Update");
        updateButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        updateButton.setForeground(new Color(255, 255, 255));
        updateButton.setBorder(null);
        updateButton.setBackground(new Color(0, 128, 0));
        updateButton.setBounds(427, 512, 89, 23);
        updateButton.setEnabled(false);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String employeeID = data[selectedRow][0]; // Get the value of row[0]
                    UpdateEmployeeDetailsWindow updateWindow = new UpdateEmployeeDetailsWindow(employeeID);
                    updateWindow.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(EmployeeRecordsPanel.this, "Please select an employee.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        deleteButton.setForeground(new Color(255, 255, 255));
        deleteButton.setBorder(null);
        deleteButton.setBackground(new Color(128, 0, 0));
        deleteButton.setBounds(527, 512, 89, 23);
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String employeeId = (String) table.getValueAt(selectedRow, 0); 
                    // Remove the selected row from the table
                    model.removeRow(selectedRow);

                    // Delete the employee record from the employee_data.tsv file
                    deleteEmployeeRecord(employeeId);

                    JOptionPane.showMessageDialog(EmployeeRecordsPanel.this, "Employee deleted successfully.", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(EmployeeRecordsPanel.this, "Please select an employee.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setBackground(new Color(230, 230, 250));
        setBounds(218, 0, 711, 561);
        setLayout(null);
        
        add(addEmployeeButton);
        add(viewDetailsButton);
        add(updateButton);
        add(deleteButton);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 74, 634, 427);
        add(scrollPane);

        lblNewLabel = new JLabel("Employee Records");
        lblNewLabel.setBounds(298, 26, 152, 19);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(lblNewLabel);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                viewDetailsButton.setEnabled(selectedRow != -1);
                updateButton.setEnabled(selectedRow != -1);
                deleteButton.setEnabled(selectedRow != -1);
            }
        });
    }

    private String[][] getDataFromEmployeeDetails(String[] columnNames) {
        String[][] data = GetData.readEmployeeDetailsFromFile();

        // Update the indexes for SSS, Philhealth, TIN, and Pagibig
        for (int i = 0; i < data.length; i++) {
            String[] rowData = data[i];
            if (rowData.length >= 19) {
                String sssNo = rowData[6]; // Get the SSS No. from the 7th column
                String philhealthNo = rowData[7]; // Get the Philhealth No. from the 8th column
                String tinNo = rowData[8]; // Get the TIN from the 9th column
                String pagibigNo = rowData[9]; // Get the Pagibig No. from the 10th column

                // Assign the values to the corresponding indexes in the data array
                rowData[3] = sssNo; // Assign SSS No. to the 4th column
                rowData[4] = philhealthNo; // Assign Philhealth No. to the 5th column
                rowData[5] = tinNo; // Assign TIN to the 6th column
                rowData[6] = pagibigNo; // Assign Pagibig No. to the 7th column
            }
        }

        return data;
    }
    
    private void deleteEmployeeRecord(String employeeId) {
        // Read the existing content of the TSV file and filter out the record to be deleted
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("Employee_Data.tsv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split("\t");
                String existingEmployeeId = details[0];

                if (!existingEmployeeId.equals(employeeId)) {
                    // Add the line to the list if it's not the one to be deleted
                    lines.add(line);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(EmployeeRecordsPanel.this,
                    "An error occurred while deleting the employee record.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Write the updated content back to the TSV file, effectively deleting the employee record
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Employee_Data.tsv"))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(EmployeeRecordsPanel.this,
                    "An error occurred while deleting the employee record.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showAddEmployeeDialog() {
        // Create a new JDialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Add Employee");
        dialog.setSize(400, 400);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new GridLayout(0, 2));

        // Create fields for employee details
        JTextField employeeIdField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField userTypeField = new JTextField();

        // Add the fields to the dialog
        dialog.add(new JLabel("Employee ID:"));
        dialog.add(employeeIdField);
        dialog.add(new JLabel("Password:"));
        dialog.add(passwordField);
        dialog.add(new JLabel("User Type:"));
        dialog.add(userTypeField);

        // Create a button to save the employee details
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the entered values
                String employeeId = employeeIdField.getText();
                String password = passwordField.getText();
                String userType = userTypeField.getText();

                // Update the login.txt CSV file with the new employee details
                UpdateEmployeeDetailsWindow.updateLoginFile(employeeId, password, userType);
                UpdateEmployeeDetailsWindow.updateEmployeeDataFile(employeeId);

                // Close the dialog
                dialog.dispose();
            }
        });

        // Add the save button to the dialog
        dialog.add(saveButton);

        // Display the dialog
        dialog.setVisible(true);
    }

}
