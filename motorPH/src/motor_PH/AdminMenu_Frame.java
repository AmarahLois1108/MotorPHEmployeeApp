package motor_PH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AdminMenu_Frame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel btn_MainMenu;
    private JLabel lbl_MainMenu;
    private JPanel btn_Dashboard;
    private JLabel lbl_Dashboard;
    private JPanel btn_EmployeeRecords;
    private JLabel lbl_EmployeeRecords;
    private JPanel btn_SalaryReport;
    private JLabel lbl_SalaryReport;
    private JPanel btn_ViewLeaveApplications;
    private JLabel lbl_ViewLeaveApplications;
    private JPanel btn_Logout;
    private JLabel lbl_Logout;
    private DashboardPanel dashboardPanel;
    private EmployeeRecordsPanel empRecordsPanel;
    private JPanel employeeRecordsPanel;

    public AdminMenu_Frame(String employeeId) {
        setResizable(false);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setPreferredSize(new Dimension(900, 600));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 112));
        panel.setForeground(new Color(255, 255, 255));
        panel.setBounds(0, 0, 216, 561);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel icon_User = new JLabel("");
        icon_User.setIcon(new ImageIcon("user.png"));
        icon_User.setBounds(10, 11, 61, 49);
        panel.add(icon_User);

        JLabel empName = new JLabel(GetData.getFirstName(employeeId) + " " + (GetData.getLastName(employeeId)));
        empName.setPreferredSize(new Dimension(300, 50));
        empName.setFont(new Font("Tahoma", Font.BOLD, 13));
        empName.setForeground(new Color(255, 255, 255));
        empName.setBounds(58, 22, 148, 14);
        panel.add(empName);

        JLabel empPosition = new JLabel(GetData.getPosition(employeeId));
        empPosition.setForeground(Color.WHITE);
        empPosition.setFont(new Font("Tahoma", Font.BOLD, 13));
        empPosition.setBounds(58, 36, 148, 14);
        panel.add(empPosition);

        btn_Dashboard = new JPanel();
        btn_Dashboard.setLayout(null);
        btn_Dashboard.setForeground(Color.WHITE);
        btn_Dashboard.setBackground(new Color(25, 25, 112));
        btn_Dashboard.setBounds(0, 98, 216, 35);
        panel.add(btn_Dashboard);

        lbl_Dashboard = new JLabel("Dashboard");
        lbl_Dashboard.setForeground(Color.WHITE);
        lbl_Dashboard.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbl_Dashboard.setBounds(20, 0, 216, 32);
        btn_Dashboard.add(lbl_Dashboard);

        btn_EmployeeRecords = new JPanel();
        btn_EmployeeRecords.setForeground(new Color(255, 255, 255));
        btn_EmployeeRecords.setBackground(new Color(25, 25, 112));
        btn_EmployeeRecords.setBounds(0, 155, 216, 35);
        panel.add(btn_EmployeeRecords);
        btn_EmployeeRecords.setLayout(null);

        lbl_EmployeeRecords = new JLabel("Employee Records");
        lbl_EmployeeRecords.setForeground(new Color(255, 255, 255));
        lbl_EmployeeRecords.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbl_EmployeeRecords.setBounds(20, 0, 216, 32);
        btn_EmployeeRecords.add(lbl_EmployeeRecords);

        btn_SalaryReport = new JPanel();
        btn_SalaryReport.setForeground(new Color(255, 255, 255));
        btn_SalaryReport.setBackground(new Color(25, 25, 112));
        btn_SalaryReport.setLayout(null);
        btn_SalaryReport.setBounds(0, 188, 216, 35);
        panel.add(btn_SalaryReport);

        lbl_SalaryReport = new JLabel("Salary Report");
        lbl_SalaryReport.setForeground(new Color(255, 255, 255));
        lbl_SalaryReport.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbl_SalaryReport.setBounds(20, 0, 216, 32);
        btn_SalaryReport.add(lbl_SalaryReport);

        btn_ViewLeaveApplications = new JPanel();
        btn_ViewLeaveApplications.setForeground(new Color(255, 255, 255));
        btn_ViewLeaveApplications.setBackground(new Color(25, 25, 112));
        btn_ViewLeaveApplications.setLayout(null);
        btn_ViewLeaveApplications.setBounds(0, 222, 216, 35);
        panel.add(btn_ViewLeaveApplications);

        lbl_ViewLeaveApplications = new JLabel("View Leave Applications");
        lbl_ViewLeaveApplications.setForeground(new Color(255, 255, 255));
        lbl_ViewLeaveApplications.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbl_ViewLeaveApplications.setBounds(20, 0, 216, 32);
        btn_ViewLeaveApplications.add(lbl_ViewLeaveApplications);

        btn_MainMenu = new JPanel();
        btn_MainMenu.setForeground(new Color(255, 255, 255));
        btn_MainMenu.setBackground(new Color(25, 25, 112));
        btn_MainMenu.setLayout(null);
        btn_MainMenu.setBounds(0, 290, 216, 35);
        panel.add(btn_MainMenu);

        lbl_MainMenu = new JLabel("Main Menu");
        lbl_MainMenu.setForeground(new Color(255, 255, 255));
        lbl_MainMenu.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbl_MainMenu.setBounds(20, 0, 216, 32);
        btn_MainMenu.add(lbl_MainMenu);

        btn_Logout = new JPanel();
        btn_Logout.setBackground(new Color(25, 25, 112));
        btn_Logout.setForeground(new Color(255, 69, 0));
        btn_Logout.setLayout(null);
        btn_Logout.setBounds(0, 467, 216, 35);
        panel.add(btn_Logout);

        lbl_Logout = new JLabel("Logout");
        lbl_Logout.setForeground(new Color(255, 0, 0));
        lbl_Logout.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbl_Logout.setBounds(20, 0, 216, 32);
        btn_Logout.add(lbl_Logout);

        dashboardPanel = new DashboardPanel();
        dashboardPanel.setBounds(218, 0, 666, 561);
        getContentPane().add(dashboardPanel);

        JPanel salaryReportPanel = new JPanel();
        salaryReportPanel.setLayout(null);
        salaryReportPanel.setBounds(216, 0, 668, 561);
        contentPane.add(salaryReportPanel);

        JPanel viewLeaveAppPanel = new JPanel();
        viewLeaveAppPanel.setLayout(null);
        viewLeaveAppPanel.setBounds(216, 0, 668, 561);
        contentPane.add(viewLeaveAppPanel);

        // Create the leave application panel
        LeaveAdminPanel leaveAppPanel = new LeaveAdminPanel();
        leaveAppPanel.setBounds(0, 0, 668, 561);

        // Add the leave application panel to the viewLeaveAppPanel
        viewLeaveAppPanel.add(leaveAppPanel);

        

        employeeRecordsPanel = new JPanel();
        employeeRecordsPanel.setLayout(null);
        employeeRecordsPanel.setBounds(216, 0, 668, 561);
        contentPane.add(employeeRecordsPanel);
        empRecordsPanel = new EmployeeRecordsPanel();
        empRecordsPanel.setBounds(0, 0, 668, 561);
        employeeRecordsPanel.add(empRecordsPanel);
        
        SalaryReportPanel salaryPanel = new SalaryReportPanel();
        salaryPanel.setBounds(0, 0, 668, 561);
        salaryReportPanel.add(salaryPanel);

        btn_EmployeeRecords.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dashboardPanel.setVisible(false);
                employeeRecordsPanel.setVisible(true);
                salaryReportPanel.setVisible(false);
                viewLeaveAppPanel.setVisible(false);
                

            }
            
            @Override
 		    public void mouseEntered(MouseEvent e) {
 		        btn_EmployeeRecords.setBackground(new Color(25, 100, 200));
 		    }

 		    @Override
 		    public void mouseExited(MouseEvent e) {
 		        btn_EmployeeRecords.setBackground(new Color(25, 25, 112));
 		    }
        });
        
        btn_Dashboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dashboardPanel.setVisible(true);
                employeeRecordsPanel.setVisible(false);
                salaryReportPanel.setVisible(false);
                viewLeaveAppPanel.setVisible(false);
               

            }
            @Override
 		    public void mouseEntered(MouseEvent e) {
 		        btn_Dashboard.setBackground(new Color(25, 100, 200));
 		    }

 		    @Override
 		    public void mouseExited(MouseEvent e) {
 		        btn_Dashboard.setBackground(new Color(25, 25, 112));
 		    }
        });
        
        btn_ViewLeaveApplications.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dashboardPanel.setVisible(false);
                employeeRecordsPanel.setVisible(false);
                salaryReportPanel.setVisible(false);
                viewLeaveAppPanel.setVisible(true);
                
                
            }
            @Override
 		    public void mouseEntered(MouseEvent e) {
 		        btn_ViewLeaveApplications.setBackground(new Color(25, 100, 200));
 		    }

 		    @Override
 		    public void mouseExited(MouseEvent e) {
 		        btn_ViewLeaveApplications.setBackground(new Color(25, 25, 112));
 		    }
        });
        
        btn_SalaryReport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dashboardPanel.setVisible(false);
                employeeRecordsPanel.setVisible(false);
                salaryReportPanel.setVisible(true);
                viewLeaveAppPanel.setVisible(false);
               

            }
            @Override
 		    public void mouseEntered(MouseEvent e) {
 		        btn_SalaryReport.setBackground(new Color(25, 100, 200));
 		    }

 		    @Override
 		    public void mouseExited(MouseEvent e) {
 		        btn_SalaryReport.setBackground(new Color(25, 25, 112));
 		    }
        });



        btn_MainMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                MainMenu_Frame mainMenu = new MainMenu_Frame(employeeId);
                mainMenu.setVisible(true);

            }
            @Override
 		    public void mouseEntered(MouseEvent e) {
 		        btn_MainMenu.setBackground(new Color(25, 100, 200));
 		    }

 		    @Override
 		    public void mouseExited(MouseEvent e) {
 		        btn_MainMenu.setBackground(new Color(25, 25, 112));
 		    }
        });

        btn_Logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Login_Frame login = new Login_Frame();
                login.setVisible(true);

            }
            @Override
 		    public void mouseEntered(MouseEvent e) {
 		        btn_Logout.setBackground(new Color(25, 100, 200));
 		    }

 		    @Override
 		    public void mouseExited(MouseEvent e) {
 		        btn_Logout.setBackground(new Color(25, 25, 112));
 		    }
        });
    }
}
