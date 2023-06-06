package motorPHGUIfinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class UserManagementGUI extends JFrame implements ActionListener {

    private JTextField employeeIdField;
    private JPasswordField passwordField;
    private JTextField userTypeField;

    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "login.txt";
    private static final String FILE_DELIMITER = ",";

    public UserManagementGUI() {
        setTitle("User Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(this);
        JButton deleteUserButton = new JButton("Delete User");
        deleteUserButton.addActionListener(this);
        JButton updateUserButton = new JButton("Update User");
        updateUserButton.addActionListener(this);

        buttonPanel.add(addUserButton);
        buttonPanel.add(deleteUserButton);
        buttonPanel.add(updateUserButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = ((JButton) e.getSource()).getText();

        switch (buttonText) {
            case "Add User":
                showAddUserForm();
                break;
            case "Delete User":
                deleteUser();
                break;
            case "Update User":
                updateUser();
                break;
        }
    }

    public void showAddUserForm() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel employeeIdLabel = new JLabel("Employee ID:");
        employeeIdField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel userTypeLabel = new JLabel("User Type:");
        userTypeField = new JTextField();

        formPanel.add(employeeIdLabel);
        formPanel.add(employeeIdField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(userTypeLabel);
        formPanel.add(userTypeField);

        int option = JOptionPane.showConfirmDialog(this, formPanel, "Add User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            addUser();
        }
    }

    public void addUser() {
        String employeeId = employeeIdField.getText();
        String password = new String(passwordField.getPassword());
        String userType = userTypeField.getText();

        String entry = employeeId + FILE_DELIMITER + password + FILE_DELIMITER + userType;
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(entry);
            printWriter.close();
            JOptionPane.showMessageDialog(this, "User added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to add user. Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteUser() {
        String employeeId = JOptionPane.showInputDialog(this, "Enter Employee ID:");

        String firstName = GetData.getFirstName(employeeId);
        String lastName = GetData.getLastName(employeeId);

        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the user?\nEmployee Name: " + firstName + " " + lastName, "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    File inputFile = new File(FILE_PATH);
                    File tempFile = new File("temp.txt");

                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(FILE_DELIMITER);
                        if (!parts[0].equals(employeeId)) {
                            writer.write(line);
                            writer.newLine();
                        }
                    }

                    writer.close();
                    reader.close();

                    if (inputFile.delete()) {
                        if (tempFile.renameTo(inputFile)) {
                            JOptionPane.showMessageDialog(this, "User deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            throw new IOException("Failed to rename temporary file.");
                        }
                    } else {
                        throw new IOException("Failed to delete user from file.");
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Failed to delete user. Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateUser() {
        String employeeId = JOptionPane.showInputDialog(this, "Enter Employee ID:");

        String firstName = GetData.getFirstName(employeeId);
        String lastName = GetData.getLastName(employeeId);

        if (!firstName.isEmpty() && !lastName.isEmpty()) {
            JPanel formPanel = new JPanel();
            formPanel.setLayout(new GridLayout(3, 2, 10, 10));

            JLabel employeeIdLabel = new JLabel("Employee ID:");
            employeeIdField = new JTextField(employeeId);  // Use the class-level field instead of declaring a new one
            employeeIdField.setEditable(false);
            JLabel passwordLabel = new JLabel("Password:");
            passwordField = new JPasswordField();  // Use the class-level field instead of declaring a new one
            JLabel userTypeLabel = new JLabel("User Type:");
            userTypeField = new JTextField();  // Use the class-level field instead of declaring a new one

            formPanel.add(employeeIdLabel);
            formPanel.add(employeeIdField);
            formPanel.add(passwordLabel);
            formPanel.add(passwordField);
            formPanel.add(userTypeLabel);
            formPanel.add(userTypeField);

            int option = JOptionPane.showConfirmDialog(this, formPanel, "Update User", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String password = new String(passwordField.getPassword());
                String userType = userTypeField.getText();

                try {
                    File inputFile = new File(FILE_PATH);
                    File tempFile = new File("temp.txt");

                    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(FILE_DELIMITER);
                        if (parts[0].equals(employeeId)) {
                            writer.write(employeeId + FILE_DELIMITER + password + FILE_DELIMITER + userType);
                        } else {
                            writer.write(line);
                        }
                        writer.newLine();
                    }

                    writer.close();
                    reader.close();

                    if (inputFile.delete()) {
                        if (tempFile.renameTo(inputFile)) {
                            JOptionPane.showMessageDialog(this, "User updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            throw new IOException("Failed to rename temporary file.");
                        }
                    } else {
                        throw new IOException("Failed to update user in file.");
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Failed to update user. Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserManagementGUI();
            }
        });
    }
}


