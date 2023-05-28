package CP2MotorPHGUI;

import javax.swing.SwingUtilities;

public class Tax {
	public static double calculateWithholdingTax(double weeklyGrossSalary) {
	    double annualGrossSalary = weeklyGrossSalary * 52;
	    double taxableIncome = annualGrossSalary - 250000;
	    double taxAmount = 0.0;
	    if (taxableIncome <= 0) {
	        taxAmount = 0.0;
	    } else if (taxableIncome <= 400000) {
	        taxAmount = taxableIncome * 0.20;
	    } else if (taxableIncome <= 800000) {
	        taxAmount = 40000 + (taxableIncome - 400000) * 0.25;
	    } else if (taxableIncome <= 2000000) {
	        taxAmount = 140000 + (taxableIncome - 800000) * 0.30;
	    } else if (taxableIncome <= 8000000) {
	        taxAmount = 540000 + (taxableIncome - 2000000) * 0.32;
	    } else {
	        taxAmount = 2320000 + (taxableIncome - 8000000) * 0.35;
	    }
	    double withholdingTax = taxAmount / 52;
	    return withholdingTax;
	}
	
	public static void main(String[] args) {
        // Create an instance of MotorPHAppGUI to get the employee ID
        MotorPHAppGUI payrollSystem = new MotorPHAppGUI();

        // Wait for the user to log in and retrieve the employee ID
        String employeeId = payrollSystem.getLoginEmployeeId();

        // Create an instance of EmpInformationGUI with the retrieved employee ID
        SwingUtilities.invokeLater(() -> new EmpInformationGUI(employeeId));

}
}
