package motor_PH;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login_Frame loginFrame = new Login_Frame();
                    loginFrame.setVisible(true);
                    loginFrame.setLocationRelativeTo(null);
                                       
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        
    }
    
    DefaultTableCellRenderer createCustomCellRenderer() {
        return new DefaultTableCellRenderer() {
          
			private static final long serialVersionUID = 1L;

			public Component getTableCellRendererComponent(JTable table_1, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table_1, value, isSelected, hasFocus, row, column);

                if (cell instanceof JLabel) {
                    JLabel label = (JLabel) cell;

                    // Check if it's row 4
                    if (row == 3) {
                        label.setText("<html><body style='width: 180px'>" + value + "</body></html>"); // Set the maximum width for wrapping
                        table_1.setRowHeight(row, 40); // Set the row height for row 4 (adjust the value as needed)
                    } else {
                        label.setText(value.toString()); // Remove wrapping for other rows
                        table_1.setRowHeight(row, 25); // Set the default row height for other rows (adjust the value as needed)
                    }

                    // Set the text alignment within the cell
                    if (column == 0) {
                        label.setHorizontalAlignment(JLabel.LEFT);
                        label.setFont(new Font("Tahoma", Font.BOLD, 12)); // Font for column 1
                    } else {
                        label.setHorizontalAlignment(JLabel.LEFT);
                        label.setFont(new Font("Tahoma", Font.PLAIN, 12)); // Font for column 2
                    }
                }

                return cell;
            }
        };
    }
    
    
}