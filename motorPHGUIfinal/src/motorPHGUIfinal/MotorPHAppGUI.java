package motorPHGUIfinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

public class MotorPHAppGUI extends JFrame implements Serializable {

    private static final long serialVersionUID = 1L;

    private JTextField employeeIdField;
    private JPasswordField passwordField;
    private JButton loginButton;
    
    

    public MotorPHAppGUI() {
        setTitle("MotorPH Employee App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.BLUE);
        headerPanel.setPreferredSize(new Dimension(800, 100));
        JLabel titleLabel = new JLabel("MotorPH Employee App");
        titleLabel.setToolTipText("");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.white);
        contentPanel.setLayout(new GridBagLayout());
        

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        addComponent(contentPanel, gbc, new JLabel("Employee ID:"), 0, 0);
        addComponent(contentPanel, gbc, new JLabel("Password:"), 0, 1);

        employeeIdField = new JTextField(10);
        addComponent(contentPanel, gbc, employeeIdField, 1, 0);

        passwordField = new JPasswordField(10);
        addComponent(contentPanel, gbc, passwordField, 1, 1);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId = employeeIdField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // Login Action
                if (login(employeeId, password)) {
                    // Successful login
                    JOptionPane.showMessageDialog(MotorPHAppGUI.this, "Login successful", "Login Success", JOptionPane.INFORMATION_MESSAGE);

                    // Check if user is admin
                    if (isAdmin(employeeId)) {
                        // User is admin, open the AdminMenuGUI window
                        new AdminMenuGUI(employeeId);
                    } else {
                        // User is not admin, open the EmployeeMenuGUI window
                        new EmployeeMenuGUI(employeeId);
                    }

                    dispose();
                } else {
                    // Invalid login
                    JOptionPane.showMessageDialog(MotorPHAppGUI.this, "Invalid employee ID or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


     
        addComponent(contentPanel, gbc, loginButton, 1, 2);

        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addComponent(JPanel panel, GridBagConstraints gbc, JComponent component, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }

    private boolean login(String employeeId, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("login.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(employeeId) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    private boolean isAdmin(String employeeId) {
        try (BufferedReader reader = new BufferedReader(new FileReader("login.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(employeeId) && parts.length >= 3 && parts[2].equals("admin")) {
                    return true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false;
    }
    public String getLoginEmployeeId() {
        return employeeIdField.getText();
    }
  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MotorPHAppGUI::new);
    }
}