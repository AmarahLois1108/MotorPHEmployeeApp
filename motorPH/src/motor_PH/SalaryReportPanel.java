package motor_PH;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SalaryReportPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SalaryReportPanel() {
		setLayout(null);
        setBackground(new Color(230, 230, 250));
        setBounds(218, 0, 564, 561);
        
        JLabel lbl_MotorPH = new JLabel("MotorPH");
        lbl_MotorPH.setBounds(194, 161, 177, 54);
        lbl_MotorPH.setFont(new Font("Tahoma", Font.BOLD, 40));
        lbl_MotorPH.setForeground(new Color(0, 0, 128));
        add(lbl_MotorPH);

        JLabel lbl_SalaryReport = new JLabel("Salary Report, not implemented yet....");
        lbl_SalaryReport.setBounds(119, 244, 419, 54);
        lbl_SalaryReport.setForeground(new Color(255, 0, 0));
        lbl_SalaryReport.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(lbl_SalaryReport);

	}

}
