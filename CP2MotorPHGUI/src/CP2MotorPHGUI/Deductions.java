package CP2MotorPHGUI;

public class Deductions {
	
	public static double calculateWeeklyDeduction(String employeeId, int weekNumber, double weeklyGrossSalary) {
	    double philhealth = GetData.getPhilhealthDeduction(employeeId);
	    double sss = GetData.getSssDeduction(employeeId);
	    double pagibig = GetData.getPagibigDeduction(employeeId);
	    double tax = Tax.calculateWithholdingTax(weeklyGrossSalary);
	    double totalDeduction = philhealth + sss + pagibig;
	    double weeklyDeduction = (totalDeduction / 4.0)+ tax;
	    return weeklyDeduction;
	}
	
	

	}




