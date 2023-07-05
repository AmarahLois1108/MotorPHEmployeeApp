package motor_PH;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DashboardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel dateTimeLabel;

    public DashboardPanel() {
        setLayout(null);
        setBackground(new Color(230, 230, 250));
        setBounds(218, 0, 564, 561);

        AnalogClock analogClock = new AnalogClock();
        analogClock.setBounds(59, 152, 473, 385);
        analogClock.setBackground(new Color(230, 230, 250));
        add(analogClock);

        dateTimeLabel = new JLabel();
        dateTimeLabel.setBounds(190, 128, 243, 87);
        dateTimeLabel.setPreferredSize(new Dimension(200, 300));
        dateTimeLabel.setForeground(new Color(25, 25, 112));
        dateTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        add(dateTimeLabel);

        JLabel lbl_MotorPH = new JLabel("MotorPH");
        lbl_MotorPH.setBounds(211, 35, 177, 54);
        lbl_MotorPH.setFont(new Font("Tahoma", Font.BOLD, 40));
        lbl_MotorPH.setForeground(new Color(0, 0, 128));
        add(lbl_MotorPH);

        JLabel lbl_EmpMgmt = new JLabel("Employee Management Application");
        lbl_EmpMgmt.setBounds(149, 87, 316, 54);
        lbl_EmpMgmt.setForeground(new Color(255, 0, 0));
        lbl_EmpMgmt.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(lbl_EmpMgmt);

        Thread dateTimeThread = new Thread() {
            public void run() {
                while (true) {
                    Date currentDate = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
                    String formattedDate = dateFormat.format(currentDate);
                    dateTimeLabel.setText(formattedDate);

                    try {
                        Thread.sleep(1000); // Wait for 1 second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        dateTimeThread.start();
    }
}
