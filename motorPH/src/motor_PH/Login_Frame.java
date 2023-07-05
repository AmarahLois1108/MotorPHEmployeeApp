package motor_PH;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login_Frame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField empIdField;
    private JPasswordField passwordField;

    public Login_Frame() {
    	setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 112));
        panel.setPreferredSize(new Dimension(300, 500));
        contentPane.add(panel, BorderLayout.WEST);
        panel.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("MotorPH");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 35));
        lblNewLabel_2.setBounds(70, 123, 164, 72);
        panel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Employee Management Application");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setBounds(48, 199, 231, 14);
        panel.add(lblNewLabel_3);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.EAST);
        panel_1.setPreferredSize(new Dimension(376, 500));
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setForeground(new Color(25, 25, 112));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel.setBounds(138, 38, 100, 68);
        lblNewLabel.setPreferredSize(new Dimension(100, 50));
        panel_1.add(lblNewLabel);

        JLabel empIDLabel = new JLabel("Employee ID:");
        empIDLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
        empIDLabel.setBounds(90, 138, 100, 14);
        panel_1.add(empIDLabel);

        empIdField = new JTextField();
        empIdField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        empIdField.setBounds(90, 161, 193, 20);
        panel_1.add(empIdField);
        empIdField.setColumns(10);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
        passwordLabel.setBounds(90, 192, 100, 14);
        panel_1.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        passwordField.setBounds(90, 219, 193, 20);
        panel_1.add(passwordField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
        loginBtn.setBorder(null);
        loginBtn.setForeground(new Color(255, 255, 255));
        loginBtn.setBackground(new Color(25, 25, 112));
        loginBtn.setBounds(149, 293, 89, 23);
        panel_1.add(loginBtn);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId = empIdField.getText();
                String password = new String(passwordField.getPassword());

                boolean isAuthenticated = checkCredentials(employeeId, password);
                boolean isAdmin = isAdmin(employeeId);

                if (isAuthenticated) {
                    if (isAdmin) {
                        JOptionPane.showMessageDialog(null, "Admin login successful", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                        MainMenu_Frame mainMenu = new MainMenu_Frame(employeeId);
                        mainMenu.enableAdminButton();
                        mainMenu.setVisible(true);
                    } else {
                        // Perform regular user login action
                        JOptionPane.showMessageDialog(null, "User login successful", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                        MainMenu_Frame mainMenu = new MainMenu_Frame(employeeId);
                        mainMenu.disableAdminButton();
                        mainMenu.setVisible(true);
                    }
                    
                    dispose();
                } else {
                    // Handle invalid login attempt
                    JOptionPane.showMessageDialog(null, "Invalid ID or password", "Login Failed", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private boolean checkCredentials(String employeeId, String password) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login_Frame frame = new Login_Frame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String getLoggedInEmployeeId() {
        String employeeId = empIdField.getText();
        return employeeId;
    }
}


