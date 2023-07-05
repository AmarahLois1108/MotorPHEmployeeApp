package motor_PH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import javax.swing.border.LineBorder;



public class UpdateEmployeeDetailsWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField employeeNumberField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField birthdayField;
    private JTextArea addressField;
    private JTextField phoneNumberField;
    private JTextField sssField;
    private JTextField philhealthField;
    private JTextField tinField;
    private JTextField pagibigField;
    private JTextField statusField;
    private JTextField positionField;
    private JTextField supervisorField;
    private JTextField basicSalaryField;
    private JTextField riceSubsidyField;
    private JTextField phoneAllowanceField;
    private JTextField clothingAllowanceField;
    private JTextField grossSemiMonthlyRateField;
    private JTextField hourlyRateField;
    private JButton saveButton;
    
	public UpdateEmployeeDetailsWindow(String employeeID) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Update Employee Details");
		setSize(730, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel employeeNumberLabel = new JLabel("Employee #:");
		employeeNumberLabel.setBounds(20, 20, 100, 20);
		getContentPane().add(employeeNumberLabel);

		employeeNumberField = new JTextField();
		employeeNumberField.setBounds(130, 20, 200, 20);
		getContentPane().add(employeeNumberField);

		JLabel lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setBounds(20, 60, 100, 20);
		getContentPane().add(lastNameLabel);

		lastNameField = new JTextField();
		lastNameField.setBounds(130, 60, 200, 20);
		getContentPane().add(lastNameField);

		JLabel firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setBounds(20, 100, 100, 20);
		getContentPane().add(firstNameLabel);

		firstNameField = new JTextField();
		firstNameField.setBounds(130, 100, 200, 20);
		getContentPane().add(firstNameField);

		JLabel birthdayLabel = new JLabel("Birthday:");
		birthdayLabel.setBounds(20, 140, 100, 20);
		getContentPane().add(birthdayLabel);

		birthdayField = new JTextField();
		birthdayField.setBounds(130, 140, 200, 20);
		getContentPane().add(birthdayField);

		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setBounds(20, 180, 100, 20);
		getContentPane().add(addressLabel);

		addressField = new JTextArea();
		addressField.setBorder(new LineBorder(new Color(192, 192, 192)));
		addressField.setBounds(130, 180, 200, 40);
		addressField.setLineWrap(true); // Enable line wrapping
		addressField.setWrapStyleWord(true); // Wrap at word boundaries
		getContentPane().add(addressField);

		JLabel phoneNumberLabel = new JLabel("Phone Number:");
		phoneNumberLabel.setBounds(20, 241, 100, 20);
		getContentPane().add(phoneNumberLabel);

		phoneNumberField = new JTextField();
		phoneNumberField.setBounds(130, 241, 200, 20);
		getContentPane().add(phoneNumberField);

		JLabel sssLabel = new JLabel("SSS #:");
		sssLabel.setBounds(20, 281, 100, 20);
		getContentPane().add(sssLabel);

		sssField = new JTextField();
		sssField.setBounds(130, 281, 200, 20);
		getContentPane().add(sssField);

		JLabel philhealthLabel = new JLabel("Philhealth #:");
		philhealthLabel.setBounds(20, 321, 100, 20);
		getContentPane().add(philhealthLabel);

		philhealthField = new JTextField();
		philhealthField.setBounds(130, 321, 200, 20);
		getContentPane().add(philhealthField);

		JLabel tinLabel = new JLabel("TIN #:");
		tinLabel.setBounds(20, 361, 100, 20);
		getContentPane().add(tinLabel);

		tinField = new JTextField();
		tinField.setBounds(130, 361, 200, 20);
		getContentPane().add(tinField);

		JLabel pagibigLabel = new JLabel("Pag-ibig #:");
		pagibigLabel.setBounds(20, 401, 100, 20);
		getContentPane().add(pagibigLabel);

		pagibigField = new JTextField();
		pagibigField.setBounds(130, 401, 200, 20);
		getContentPane().add(pagibigField);

		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setBounds(350, 20, 100, 20);
		getContentPane().add(statusLabel);

		statusField = new JTextField();
		statusField.setBounds(500, 20, 200, 20);
		getContentPane().add(statusField);

		JLabel positionLabel = new JLabel("Position:");
		positionLabel.setBounds(350, 60, 100, 20);
		getContentPane().add(positionLabel);

		positionField = new JTextField();
		positionField.setBounds(500, 60, 200, 20);
		getContentPane().add(positionField);

		JLabel supervisorLabel = new JLabel("Immediate Supervisor:");
		supervisorLabel.setBounds(350, 100, 150, 20);
		getContentPane().add(supervisorLabel);

		supervisorField = new JTextField();
		supervisorField.setBounds(500, 100, 200, 20);
		getContentPane().add(supervisorField);

		JLabel basicSalaryLabel = new JLabel("Basic Salary:");
		basicSalaryLabel.setBounds(350, 140, 100, 20);
		getContentPane().add(basicSalaryLabel);

		basicSalaryField = new JTextField();
		basicSalaryField.setBounds(500, 140, 200, 20);
		getContentPane().add(basicSalaryField);

		JLabel riceSubsidyLabel = new JLabel("Rice Subsidy:");
		riceSubsidyLabel.setBounds(350, 180, 100, 20);
		getContentPane().add(riceSubsidyLabel);

		riceSubsidyField = new JTextField();
		riceSubsidyField.setBounds(500, 180, 200, 20);
		getContentPane().add(riceSubsidyField);

		JLabel phoneAllowanceLabel = new JLabel("Phone Allowance:");
		phoneAllowanceLabel.setBounds(350, 220, 120, 20);
		getContentPane().add(phoneAllowanceLabel);

		phoneAllowanceField = new JTextField();
		phoneAllowanceField.setBounds(500, 220, 200, 20);
		getContentPane().add(phoneAllowanceField);

		JLabel clothingAllowanceLabel = new JLabel("Clothing Allowance:");
		clothingAllowanceLabel.setBounds(350, 260, 130, 20);
		getContentPane().add(clothingAllowanceLabel);

		clothingAllowanceField = new JTextField();
		clothingAllowanceField.setBounds(500, 260, 200, 20);
		getContentPane().add(clothingAllowanceField);

		JLabel grossSemiMonthlyRateLabel = new JLabel("Gross Semi-monthly Rate:");
		grossSemiMonthlyRateLabel.setBounds(350, 300, 200, 20);
		getContentPane().add(grossSemiMonthlyRateLabel);

		grossSemiMonthlyRateField = new JTextField();
		grossSemiMonthlyRateField.setBounds(500, 300, 200, 20);
		getContentPane().add(grossSemiMonthlyRateField);

		JLabel hourlyRateLabel = new JLabel("Hourly Rate:");
		hourlyRateLabel.setBounds(350, 340, 100, 20);
		getContentPane().add(hourlyRateLabel);

		hourlyRateField = new JTextField();
		hourlyRateField.setBounds(500, 340, 200, 20);
		getContentPane().add(hourlyRateField);

		saveButton = new JButton("Save");
		saveButton.setBounds(500, 380, 100, 30);
		getContentPane().add(saveButton);

		String[][] employeeDetails = GetData.readEmployeeDetailsFromFile();

		// Search for the employee with the matching employee ID
		int selectedEmployeeIndex = -1;
		for (int i = 0; i < employeeDetails.length; i++) {
			if (employeeDetails[i][0].equals(employeeID)) {
				selectedEmployeeIndex = i;
				break;
			}
		}

		if (selectedEmployeeIndex != -1) {
			// Set the initial values for the fields based on the selected employee
			employeeNumberField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 0, ""));
			lastNameField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 1, ""));
			firstNameField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 2, ""));
			birthdayField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 3, ""));
			addressField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 4, ""));
			phoneNumberField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 5, ""));
			sssField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 6, ""));
			philhealthField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 7, ""));
			tinField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 8, ""));
			pagibigField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 9, ""));
			statusField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 10, ""));
			positionField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 11, ""));
			supervisorField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 12, ""));
			basicSalaryField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 13, ""));
			riceSubsidyField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 14, ""));
			phoneAllowanceField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 15, ""));
			clothingAllowanceField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 16, ""));
			grossSemiMonthlyRateField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 17, ""));
			hourlyRateField.setText(getValueOrDefault(employeeDetails[selectedEmployeeIndex], 18, ""));
		}
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateEmployeeDetails();
			}
		});
	}

	private String getValueOrDefault(String[] array, int index, String defaultValue) {
		if (array != null && index >= 0 && index < array.length && array[index] != null) {
			return array[index];
		}
		return defaultValue;
	}

	private void updateEmployeeDetails() {
		String employeeId = employeeNumberField.getText(); // Get the employee ID to update

		// Read the existing content of the TSV file and update the details
		List<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader("Employee_Data.tsv"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] details = line.split("\t");
				String existingEmployeeId = details[0]; // Assuming employee ID is in the first column

				if (existingEmployeeId.equals(employeeId)) {
					// Update the details if the employee ID matches

					// Resize the details array if it's too short
					if (details.length < 19) {
						String[] resizedDetails = new String[19];
						System.arraycopy(details, 0, resizedDetails, 0, details.length);
						details = resizedDetails;
					}

					// Update the specific fields
					details[1] = lastNameField.getText(); // Update last name
					details[2] = firstNameField.getText();
					details[3] = birthdayField.getText();
					details[4] = addressField.getText();
					details[5] = phoneNumberField.getText();
					details[6] = sssField.getText();
					details[7] = philhealthField.getText();
					details[8] = tinField.getText();
					details[9] = pagibigField.getText();
					details[10] = statusField.getText();
					details[11] = positionField.getText();
					details[12] = supervisorField.getText();
					details[13] = basicSalaryField.getText();
					details[14] = riceSubsidyField.getText();
					details[15] = phoneAllowanceField.getText();
					details[16] = clothingAllowanceField.getText();
					details[17] = grossSemiMonthlyRateField.getText();
					details[18] = hourlyRateField.getText();

					// Add the updated line to the list
					lines.add(String.join("\t", details));
				} else {
					// Add the original line to the list without any changes
					lines.add(line);
				}
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(UpdateEmployeeDetailsWindow.this,
					"An error occurred while updating employee details.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Write the updated content back to the TSV file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Employee_Data.tsv"))) {
			for (String line : lines) {
				writer.write(line);
				writer.newLine();
			}
			JOptionPane.showMessageDialog(UpdateEmployeeDetailsWindow.this, "Employee details updated successfully.",
					"Success", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(UpdateEmployeeDetailsWindow.this,
					"An error occurred while updating employee details.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	static void updateLoginFile(String employeeId, String password, String userType) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("login.txt", true))) {
			// Append the new employee details to the CSV file
			writer.write(employeeId + "," + password + "," + userType);
			writer.newLine();

			JOptionPane.showMessageDialog(null, "Employee added successfully.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "An error occurred while adding the employee.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void updateEmployeeDataFile(String employeeId) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Employee_Data.tsv", true))) {
			// Append the new employee ID to the TSV file with other details blank
			StringBuilder line = new StringBuilder(employeeId);
			for (int i = 1; i <= 18; i++) {
				line.append("\t"); // Append tab character for each empty column
			}
			writer.write(line.toString());
			writer.newLine();

			JOptionPane.showMessageDialog(null, "Employee details updated successfully.", "Success",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "An error occurred while updating employee details.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
