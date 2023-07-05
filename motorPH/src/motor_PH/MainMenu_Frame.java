package motor_PH;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu_Frame extends JFrame {
	private JPanel currentButton = null;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel btn_AdminMenu;
	private JLabel lbl_Admin;
	private JPanel btn_Logout;
	private JTextField txt_ChooseWeek;
	private PayslipTablePanel tablePanel;
	private DashboardPanel dashboardPanel;

	public MainMenu_Frame(String employeeId) {
		
		setResizable(false);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(550, 200, 798, 600);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(800, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel view_PayslipPanel = new JPanel();
		view_PayslipPanel.setBackground(new Color(255, 255, 255));
		view_PayslipPanel.setForeground(new Color(220, 220, 220));
		view_PayslipPanel.setBounds(218, 0, 564, 561);
		contentPane.add(view_PayslipPanel);
		view_PayslipPanel.setLayout(null);

		JLabel Lbl_ChooseWeek = new JLabel("Enter week from (1-52):");
		Lbl_ChooseWeek.setBounds(96, 24, 164, 14);
		view_PayslipPanel.add(Lbl_ChooseWeek);
		Lbl_ChooseWeek.setForeground(new Color(0, 0, 128));
		Lbl_ChooseWeek.setFont(new Font("Tahoma", Font.BOLD, 13));

		txt_ChooseWeek = new JTextField();

		txt_ChooseWeek.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_ChooseWeek.setColumns(10);
		txt_ChooseWeek.setBounds(278, 21, 55, 20);
		view_PayslipPanel.add(txt_ChooseWeek);

		JPanel view_PayslipTablePanel = new JPanel();
		view_PayslipTablePanel.setLayout(null);
		view_PayslipTablePanel.setBackground(new Color(255, 255, 255));
		view_PayslipTablePanel.setBounds(129, 52, 353, 440);
		view_PayslipPanel.add(view_PayslipTablePanel);

		JButton btnViewWeekPayslip = new JButton("View");
		btnViewWeekPayslip.setBorder(null);
		btnViewWeekPayslip.setBackground(new Color(25, 25, 112));
		btnViewWeekPayslip.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnViewWeekPayslip.setForeground(new Color(255, 255, 255));
		btnViewWeekPayslip.setBounds(343, 20, 89, 23);
		view_PayslipPanel.add(btnViewWeekPayslip);

		JButton btnPrint_Payslip = new JButton("Print");

		btnPrint_Payslip.setForeground(new Color(255, 255, 255));
		btnPrint_Payslip.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPrint_Payslip.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPrint_Payslip.setBackground(new Color(25, 25, 112));
		btnPrint_Payslip.setBounds(236, 527, 129, 23);
		view_PayslipPanel.add(btnPrint_Payslip);

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

		JPanel btn_PersonalInfo = new JPanel();
		btn_PersonalInfo.setForeground(new Color(255, 255, 255));
		btn_PersonalInfo.setBackground(new Color(25, 25, 112));
		btn_PersonalInfo.setBounds(0, 155, 216, 35);
		panel.add(btn_PersonalInfo);
		btn_PersonalInfo.setLayout(null);

		JLabel lbl_PersonalInfo = new JLabel("Personal Information");

		lbl_PersonalInfo.setForeground(new Color(255, 255, 255));
		lbl_PersonalInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_PersonalInfo.setBounds(20, 0, 216, 32);
		btn_PersonalInfo.add(lbl_PersonalInfo);

		JPanel btn_SalaryInfo = new JPanel();
		btn_SalaryInfo.setForeground(new Color(255, 255, 255));
		btn_SalaryInfo.setBackground(new Color(25, 25, 112));
		btn_SalaryInfo.setLayout(null);
		btn_SalaryInfo.setBounds(0, 188, 216, 35);
		panel.add(btn_SalaryInfo);

		JLabel lbl_SalaryInfo = new JLabel("Salary Information");
		lbl_SalaryInfo.setForeground(new Color(255, 255, 255));
		lbl_SalaryInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_SalaryInfo.setBounds(20, 0, 216, 32);
		btn_SalaryInfo.add(lbl_SalaryInfo);

		JPanel btn_ViewPayslip = new JPanel();
		btn_ViewPayslip.setForeground(new Color(255, 255, 255));
		btn_ViewPayslip.setBackground(new Color(25, 25, 112));
		btn_ViewPayslip.setLayout(null);
		btn_ViewPayslip.setBounds(0, 224, 216, 35);
		panel.add(btn_ViewPayslip);

		JLabel lbl_ViewPayslip = new JLabel("View Payslip");

		lbl_ViewPayslip.setForeground(new Color(255, 255, 255));
		lbl_ViewPayslip.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_ViewPayslip.setBounds(20, 0, 216, 32);
		btn_ViewPayslip.add(lbl_ViewPayslip);

		JPanel btn_LeaveApp = new JPanel();
		btn_LeaveApp.setForeground(new Color(255, 255, 255));
		btn_LeaveApp.setBackground(new Color(25, 25, 112));
		btn_LeaveApp.setLayout(null);
		btn_LeaveApp.setBounds(0, 259, 216, 35);
		panel.add(btn_LeaveApp);

		JLabel lbl_LeaveApp = new JLabel("Leave Application");
		lbl_LeaveApp.setForeground(new Color(255, 255, 255));
		lbl_LeaveApp.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_LeaveApp.setBounds(20, 0, 216, 32);
		btn_LeaveApp.add(lbl_LeaveApp);

		btn_AdminMenu = new JPanel();
		btn_AdminMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				dispose();
				AdminMenu_Frame adminMenu = new AdminMenu_Frame(employeeId);
				adminMenu.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btn_AdminMenu.setBackground(new Color(25, 100, 200));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn_AdminMenu.setBackground(new Color(25, 25, 112));
			}
		});

		btn_AdminMenu.setForeground(new Color(255, 255, 255));
		btn_AdminMenu.setBackground(new Color(25, 25, 112));
		btn_AdminMenu.setLayout(null);
		btn_AdminMenu.setBounds(0, 327, 216, 35);
		panel.add(btn_AdminMenu);

		lbl_Admin = new JLabel("Admin Menu");
		lbl_Admin.setForeground(new Color(255, 255, 255));
		lbl_Admin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_Admin.setBounds(20, 0, 216, 32);
		btn_AdminMenu.add(lbl_Admin);

		btn_Logout = new JPanel();
		btn_Logout.setBackground(new Color(25, 25, 112));
		btn_Logout.setForeground(new Color(255, 69, 0));
		btn_Logout.setLayout(null);
		btn_Logout.setBounds(0, 467, 216, 35);
		panel.add(btn_Logout);

		JLabel lbl_Logout = new JLabel("Logout");
		lbl_Logout.setForeground(new Color(255, 0, 0));
		lbl_Logout.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbl_Logout.setBounds(20, 0, 216, 32);
		btn_Logout.add(lbl_Logout);

		JPanel btn_Dashboard = new JPanel();

		btn_Dashboard.setLayout(null);
		btn_Dashboard.setForeground(Color.WHITE);
		btn_Dashboard.setBackground(new Color(25, 25, 112));
		btn_Dashboard.setBounds(0, 98, 216, 35);
		panel.add(btn_Dashboard);

		JLabel DashboardLabel = new JLabel("Dashboard");

		DashboardLabel.setForeground(Color.WHITE);
		DashboardLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		DashboardLabel.setBounds(20, 0, 216, 32);
		btn_Dashboard.add(DashboardLabel);

		JPanel view_InfoPanel = new JPanel();
		view_InfoPanel.setBackground(new Color(255, 255, 255));
		view_InfoPanel.setForeground(new Color(220, 220, 220));
		view_InfoPanel.setBounds(218, 0, 564, 561);
		contentPane.add(view_InfoPanel);
		view_InfoPanel.setLayout(null);

		JPanel view_InfoTablePanel = new JPanel();
		view_InfoTablePanel.setBackground(new Color(255, 255, 255));
		view_InfoTablePanel.setBounds(64, 40, 469, 462);
		view_InfoPanel.add(view_InfoTablePanel);
		view_InfoTablePanel.setLayout(null);

		// Create the JTable with the model
		JTable tableInfo1 = new JTable(
				new DefaultTableModel(new Object[][] { { "Employee ID", GetData.getEmployeeId(employeeId) },
						{ "Employee Name", GetData.getFirstName(employeeId) + " " + GetData.getLastName(employeeId) },
						{ "Birthday", GetData.getBirthday(employeeId) }, { "Address", GetData.getAddress(employeeId) },
						{ "Phone Number", GetData.getPhoneNumber(employeeId) }, { "SSS", GetData.getSssNo(employeeId) },
						{ "Philhealth", GetData.getPhilhealthNo(employeeId) }, { "TIN", GetData.getTinNo(employeeId) },
						{ "Pag-ibig", GetData.getPagibigNo(employeeId) }, { "Status", GetData.getStatus(employeeId) },
						{ "Position", GetData.getPosition(employeeId) },
						{ "Supervisor", GetData.getSupervisor(employeeId) }, }, new String[] { "", "" }));
		tableInfo1.setBorder(new LineBorder(Color.WHITE));

		tableInfo1.setBounds(78, 79, 366, 316);
		tableInfo1.setSelectionForeground(Color.WHITE);
		tableInfo1.setRowSelectionAllowed(false);
		tableInfo1.setRowMargin(8);
		tableInfo1.setRowHeight(29);
		tableInfo1.setPreferredSize(new Dimension(150, 300));
		tableInfo1.setGridColor(Color.WHITE);
		tableInfo1.setForeground(new Color(0, 0, 128));
		tableInfo1.setFont(new Font("Tahoma", Font.BOLD, 12));
		tableInfo1.setEnabled(false);
		tableInfo1.setBackground(new Color(255, 255, 255));
		tableInfo1.setAutoscrolls(false);
		tableInfo1.setAutoCreateColumnsFromModel(false);
		view_InfoTablePanel.add(tableInfo1);

		JLabel viewInfoLabel = new JLabel("Personal Information");
		viewInfoLabel.setForeground(new Color(0, 0, 128));
		viewInfoLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		viewInfoLabel.setBounds(148, 11, 194, 54);
		view_InfoTablePanel.add(viewInfoLabel);

		JButton btnPrint_PersonalInfo = new JButton("Print");
		btnPrint_PersonalInfo.setBounds(226, 498, 129, 23);
		view_InfoPanel.add(btnPrint_PersonalInfo);
		btnPrint_PersonalInfo.setForeground(new Color(255, 255, 255));
		btnPrint_PersonalInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPrint_PersonalInfo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPrint_PersonalInfo.setBackground(new Color(25, 25, 112));

		view_InfoPanel.setVisible(false);

		JPanel view_SalaryPanel = new JPanel();
		view_SalaryPanel.setBackground(new Color(255, 255, 255));
		view_SalaryPanel.setForeground(new Color(220, 220, 220));
		view_SalaryPanel.setBounds(218, 0, 564, 561);
		contentPane.add(view_SalaryPanel);
		view_SalaryPanel.setLayout(null);

		JPanel view_SalaryTablePanel = new JPanel();
		view_SalaryTablePanel.setLayout(null);
		view_SalaryTablePanel.setBackground(new Color(255, 255, 255));
		view_SalaryTablePanel.setBounds(0, 0, 564, 497);
		view_SalaryPanel.add(view_SalaryTablePanel);

		JTable table_Salary = new JTable(
				new DefaultTableModel(new Object[][] { { "Employee ID", GetData.getEmployeeId(employeeId) },
						{ "Employee Name", GetData.getFirstName(employeeId) + " " + GetData.getLastName(employeeId) },
						{ "Basic Salary", GetData.getBasicSalary(employeeId) },
						{ "Rice Subsidy", GetData.getRiceSubsidy(employeeId) },
						{ "Phone Allowance", GetData.getPhoneAllowance(employeeId) },
						{ "Clothing Allowance", GetData.getClothingAllowance(employeeId) },
						{ "Gross Semi-monthly Rate", GetData.getGrossSemiMonthlyRate(employeeId) },
						{ "Hourly Rate", GetData.getHourlyRate(employeeId) },

				}, new String[] { "", "" }));
		table_Salary.setBorder(new LineBorder(new Color(255, 255, 255)));
		table_Salary.setSelectionForeground(Color.WHITE);
		table_Salary.setRowSelectionAllowed(false);
		table_Salary.setRowMargin(10);
		table_Salary.setRowHeight(28);
		table_Salary.setPreferredSize(new Dimension(150, 300));
		table_Salary.setGridColor(Color.WHITE);
		table_Salary.setForeground(new Color(0, 0, 128));
		table_Salary.setFont(new Font("Tahoma", Font.BOLD, 12));
		table_Salary.setEnabled(false);
		table_Salary.setBackground(new Color(255, 255, 255));
		table_Salary.setAutoscrolls(false);
		table_Salary.setAutoCreateColumnsFromModel(false);
		table_Salary.setBounds(147, 115, 376, 212);
		view_SalaryTablePanel.add(table_Salary);

		JLabel lblSalaryInformation = new JLabel("Salary Information");
		lblSalaryInformation.setForeground(new Color(0, 0, 128));
		lblSalaryInformation.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSalaryInformation.setBounds(222, 39, 194, 54);
		view_SalaryTablePanel.add(lblSalaryInformation);

		JButton btnPrint_SalaryInfo = new JButton("Print");
		btnPrint_SalaryInfo.setForeground(new Color(255, 255, 255));
		btnPrint_SalaryInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPrint_SalaryInfo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnPrint_SalaryInfo.setBackground(new Color(25, 25, 112));
		btnPrint_SalaryInfo.setBounds(226, 498, 129, 23);
		view_SalaryPanel.add(btnPrint_SalaryInfo);

		JPanel leave_ApplicationPanel = new JPanel();
		leave_ApplicationPanel.setBackground(new Color(220, 220, 220));
		leave_ApplicationPanel.setForeground(new Color(220, 220, 220));
		leave_ApplicationPanel.setBounds(218, 0, 564, 561);
		contentPane.add(leave_ApplicationPanel);
		leave_ApplicationPanel.setLayout(null);

		LeaveEmployeePanel leaveManagementPanel = new LeaveEmployeePanel(employeeId);
		leaveManagementPanel.setBounds(0, 0, 564, 561);
		leave_ApplicationPanel.add(leaveManagementPanel);

		TableColumnModel columnModel = tableInfo1.getColumnModel();
		TableColumnModel columnModel2 = table_Salary.getColumnModel();

		dashboardPanel = new DashboardPanel();
		getContentPane().add(dashboardPanel);

		// Update the date and time label periodically

		Main main = new Main();
		DefaultTableCellRenderer renderer = main.createCustomCellRenderer();
		{

		}
		;

		// Assign the cell renderer to each column
		for (int columnIndex = 0; columnIndex < tableInfo1.getColumnCount(); columnIndex++) {
			tableInfo1.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);

		}

		for (int columnIndex = 0; columnIndex < table_Salary.getColumnCount(); columnIndex++) {
			table_Salary.getColumnModel().getColumn(columnIndex).setCellRenderer(renderer);

			columnModel.getColumn(0).setPreferredWidth(150);
			columnModel.getColumn(1).setPreferredWidth(350);
			columnModel2.getColumn(0).setPreferredWidth(280);
			columnModel2.getColumn(1).setPreferredWidth(350);

			dashboardPanel.setVisible(true);
			view_SalaryPanel.setVisible(false);
			view_PayslipPanel.setVisible(false);
			leave_ApplicationPanel.setVisible(false);

			// Mouse click event listener for dashboard panel
			btn_Dashboard.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dashboardPanel.setVisible(true);
					view_InfoPanel.setVisible(false);
					view_SalaryPanel.setVisible(false);
					view_PayslipPanel.setVisible(false);
					leave_ApplicationPanel.setVisible(false);
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

			// Mouse click event listener for view_InfoPanel
			btn_PersonalInfo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dashboardPanel.setVisible(false);
					view_InfoPanel.setVisible(true);
					view_SalaryPanel.setVisible(false);
					view_PayslipPanel.setVisible(false);
					leave_ApplicationPanel.setVisible(false);

					if (currentButton != btn_PersonalInfo) {
						// Reset the previous button's color
						if (currentButton != null) {

						}
						// Set the current button as clicked and update its color
						currentButton = btn_PersonalInfo;
						btn_PersonalInfo.setBackground(new Color(25, 100, 200));
					}

				};

				@Override
				public void mouseEntered(MouseEvent e) {
					btn_PersonalInfo.setBackground(new Color(25, 100, 200));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btn_PersonalInfo.setBackground(new Color(25, 25, 112));
				}
			});

			btn_SalaryInfo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dashboardPanel.setVisible(false);
					view_InfoPanel.setVisible(false);
					view_SalaryPanel.setVisible(true);
					view_PayslipPanel.setVisible(false);
					leave_ApplicationPanel.setVisible(false);

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					btn_SalaryInfo.setBackground(new Color(25, 100, 200));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btn_SalaryInfo.setBackground(new Color(25, 25, 112));
				}
			});
			btn_ViewPayslip.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					clearPanelContent(view_PayslipTablePanel);
					initializePanelContent(view_PayslipPanel);
					dashboardPanel.setVisible(false);
					view_InfoPanel.setVisible(false);
					view_SalaryPanel.setVisible(false);
					view_PayslipPanel.setVisible(true);
					leave_ApplicationPanel.setVisible(false);

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					btn_ViewPayslip.setBackground(new Color(25, 100, 200));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btn_ViewPayslip.setBackground(new Color(25, 25, 112));
				}

			});

			btnPrint_PersonalInfo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					PanelPrinter panelPrinter = new PanelPrinter(view_InfoTablePanel);
					panelPrinter.printPanel();
				}
			});
			btnPrint_SalaryInfo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					PanelPrinter panelPrinter = new PanelPrinter(view_SalaryTablePanel);
					panelPrinter.printPanel();
				}
			});

			btnPrint_Payslip.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					PanelPrinter panelPrinter = new PanelPrinter(view_InfoTablePanel);
					panelPrinter.printPanel();
				}
			});

			btn_LeaveApp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					dashboardPanel.setVisible(false);
					view_InfoPanel.setVisible(false);
					view_SalaryPanel.setVisible(false);
					view_PayslipPanel.setVisible(false);
					leave_ApplicationPanel.setVisible(true);

				}

				@Override
				public void mouseEntered(MouseEvent e) {
					btn_LeaveApp.setBackground(new Color(25, 100, 200));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btn_LeaveApp.setBackground(new Color(25, 25, 112));

				}

			});

			btn_Logout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// Show the login frame and dispose the main menu frame
					Main.main(null);
					dispose();
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

			btnViewWeekPayslip.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String input = txt_ChooseWeek.getText();
					int weekNumber;

					try {
						weekNumber = Integer.parseInt(input);
						if (weekNumber < 1 || weekNumber > 52) {
							JOptionPane.showMessageDialog(null,
									"Invalid week number. Please enter a number between 1 and 52.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						if (tablePanel != null) {
							view_PayslipTablePanel.remove(tablePanel);
						}

						tablePanel = new PayslipTablePanel(employeeId);
						tablePanel.setBounds(0, 30, view_PayslipTablePanel.getWidth(),
						view_PayslipTablePanel.getHeight() - 30);
						view_PayslipTablePanel.setLayout(null);
						view_PayslipTablePanel.add(tablePanel);

						JLabel payslipLabel = new JLabel("Employee Payslip");
						payslipLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
						payslipLabel.setForeground(new Color(0, 0, 128));
						payslipLabel.setHorizontalAlignment(SwingConstants.CENTER);
						payslipLabel.setBounds(0, 0, view_PayslipTablePanel.getWidth(), 20);
						view_PayslipTablePanel.add(payslipLabel);

						tablePanel.displayPayslipDetails(weekNumber);
						view_PayslipTablePanel.revalidate();
						view_PayslipTablePanel.repaint();
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			});

		}

	}

	public void setAdminButtonEnabled(boolean isAdmin) {

		if (isAdmin) {
			enableAdminButton();
		} else {
			disableAdminButton();
		}
	}

	public void enableAdminButton() {
		btn_AdminMenu.setEnabled(true);
		btn_AdminMenu.setOpaque(true);

	}

	public void disableAdminButton() {
		btn_AdminMenu.setEnabled(false);
		btn_AdminMenu.setOpaque(false);
		lbl_Admin.setText("");

	}

	private void clearPanelContent(JPanel panel) {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}

	private void initializePanelContent(JPanel panel) {
		Component[] components = panel.getComponents();
		for (Component component : components) {
			if (component instanceof JTextField) {
				JTextField textField = (JTextField) component;
				textField.setText("");
			}

		}
	}
}

