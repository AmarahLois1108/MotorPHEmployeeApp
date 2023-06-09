package motor_PH;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class LeaveManager {
	
	private static final String STATUS_FILE_PATH = "Leave_Status.csv";
	
	// Apply leave for the specified employee, leave type, start date, and end date
	public static void applyLeave(String employeeId, String leaveType, String startDate, String endDate) {
	    int days = calculateLeaveDays(startDate, endDate);
	    int availableCredits = LeaveCreditManager.getAvailableLeaveCredits(employeeId, leaveType);
	    if (availableCredits >= days) {
	        // Proceed with leave application
	        String dateFiled = getCurrentDate();

	        // Write leave status to leave_status.csv file
	        try (FileWriter writer = new FileWriter(STATUS_FILE_PATH, true)) {
	            writer.append(employeeId).append(",")
	                    .append(dateFiled).append(",")
	                    .append(leaveType).append(",")
	                    .append(startDate).append(",")
	                    .append(endDate).append(",")
	                    .append("pending").append("\n");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("Insufficient leave credits.");
	    }
	}


    // Calculate the number of leave days between the start date and end date
    static int calculateLeaveDays(String startDate, String endDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);

            int numberOfDays = (int) ChronoUnit.DAYS.between(start, end); // Adding 1 to include both the start and end dates

            if (numberOfDays < 0) {
                return 1;
            }

            return numberOfDays;
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
            return -1; // Return -1 to indicate an error
        }
    }

    // Get the current date in the format "MM-dd-yyy"
    static String getCurrentDate() {
        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy");
        return format.format(currentDate);
    }
    
    // Get the leave status for the specified employee and leave type
    public static String getLeaveStatus(String employeeId, String leaveType) {
        // Implementation for getting leave status goes here
        return null;
    }

    // Update the leave status for the specified employee, leave details, and status
    public static void updateLeaveStatus(String employeeId, String dateFiled, String leaveType, String startDate, String endDate, String status) {
        try (FileWriter writer = new FileWriter(STATUS_FILE_PATH, true)) {
            writer.append(employeeId).append(",")
                    .append(dateFiled).append(",")
                    .append(leaveType).append(",")
                    .append(startDate).append(",")
                    .append(endDate).append(",")
                    .append(status).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    