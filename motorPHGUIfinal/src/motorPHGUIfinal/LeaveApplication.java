package motorPHGUIfinal;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LeaveApplication extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LeaveApplication(String employeeId) {
        setTitle("Leave Application");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Display a message indicating that the leave application feature is not implemented yet
        JOptionPane.showMessageDialog(this, "Leave application feature is not implemented yet.", "Not Implemented", JOptionPane.INFORMATION_MESSAGE);
    }
}
