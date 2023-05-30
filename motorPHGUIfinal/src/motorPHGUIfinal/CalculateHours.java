package motorPHGUIfinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

public class CalculateHours {
    public static double calculateHoursWorkedInWeek(String employeeId, int weekNumber) {
        double hoursWorkedInWeek = 0.0;
        boolean foundEmployee = false; // Flag to track if any hours were recorded for the employee

        try (BufferedReader reader = new BufferedReader(new FileReader("timesheet2.txt"))) {
            String line;
            String[] parts = null;

            while ((line = reader.readLine()) != null) {
                parts = line.split("\t");
                if (parts[0].equals(employeeId)) {
                    foundEmployee = true;
                    LocalDate date = LocalDate.parse(parts[1]);
                    if (date.getYear() == 2022 && date.get(WeekFields.ISO.weekOfWeekBasedYear()) == weekNumber) {
                        LocalTime timeIn = LocalTime.parse(parts[2], DateTimeFormatter.ofPattern("H:mm"));
                        LocalTime timeOut = LocalTime.parse(parts[3], DateTimeFormatter.ofPattern("H:mm"));
                        double hoursWorked = Duration.between(timeIn, timeOut).toHours();
                        if (hoursWorked > 0) {
                            hoursWorkedInWeek += (hoursWorked - 1);
                        }
                    }
                }
            }
            
            if (!foundEmployee) {
                System.out.println("Employee not found in timesheet.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return hoursWorkedInWeek;
    }
}

