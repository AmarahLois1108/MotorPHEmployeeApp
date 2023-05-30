package motorPHGUIfinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GetData {

	static final String EMPLOYEE_SALARY_FILE = "Employee_salary.txt";
	static final String DEDUCTIONS_FILE = "deductions2.txt";
	static final String EMPLOYEE_INFORMATION = "Employee_details.txt";

	static double getValueFromLine(String line, int index) {
	    String[] parts = line.split(",");
	    if (parts.length > index) {
	        return Double.parseDouble(parts[index]);
	    }
	    return 0.0;
	}

	static double getValueFromFile(String employeeId, String filename, int valueIndex) {
	    double value = 0.0;
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.startsWith(employeeId + ",")) {
	                value = getValueFromLine(line, valueIndex);
	                break;
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return value;
	}
	
	static String getValueFromEmployeeDetailsFile(String employeeId, String employeeInformation, int valueIndex) {
		String value = "";
	    try (BufferedReader reader = new BufferedReader(new FileReader(getEmployeeInformation()))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split("/");
	            if (parts[0].equals(employeeId)) {
	            	value = parts[valueIndex];
	
	                break;
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return value;
	}

	static double getRiceSubsidy(String employeeId) {
	    return getValueFromFile(employeeId, getEmployeeSalaryFile(), 2);
	}
	
	static double getBasicSalary(String employeeId) {
	    return getValueFromFile(employeeId, getEmployeeSalaryFile(), 1);
	}

	static double getPhoneAllowance(String employeeId) {
	    return getValueFromFile(employeeId, getEmployeeSalaryFile(), 3);
	}

	static double getClothingAllowance(String employeeId) {
	    return getValueFromFile(employeeId, getEmployeeSalaryFile(), 4);
	}

	static double getHourlyRate(String employeeId) {
	    return getValueFromFile(employeeId, getEmployeeSalaryFile(), 6);
	}
	
	static double getGrossSemiMonthlyRate(String employeeId) {
	    return getValueFromFile(employeeId, getEmployeeSalaryFile(), 5);
	}

	static double getPhilhealthDeduction(String employeeId) {
	    return getValueFromFile(employeeId, DEDUCTIONS_FILE, 1);
	}

	static double getSssDeduction(String employeeId) {
	    return getValueFromFile(employeeId, DEDUCTIONS_FILE, 2);
	}

	static double getPagibigDeduction(String employeeId) {
	    return getValueFromFile(employeeId, DEDUCTIONS_FILE, 3);
	}
	
	static String getEmployeeId(String employeeId) {
	    return  getValueFromEmployeeDetailsFile(employeeId, getEmployeeInformation(), 0);
	}
	
	static String getLastName(String employeeId) {
	    return getValueFromEmployeeDetailsFile(employeeId, getEmployeeInformation(), 1);
	}
	
	static String getFirstName(String employeeId) {
	    return getValueFromEmployeeDetailsFile(employeeId, getEmployeeInformation(), 2);
	}
	
	static String getBirthday(String employeeId) {
	    return getValueFromEmployeeDetailsFile(employeeId, getEmployeeInformation(), 3);
	}
	
	static String getAddress(String employeeId) {
	    return getValueFromEmployeeDetailsFile(employeeId, getEmployeeInformation(), 4);
	}
	
	static String getPhoneNumber(String employeeId) {
	    return getValueFromEmployeeDetailsFile(employeeId, getEmployeeInformation(), 5);
	}
	
	static String getSssNo(String employeeId) {
	    return getValueFromEmployeeDetailsFile(employeeId, getEmployeeInformation(), 6);
	}
	
	static String getPhilhealthNo(String employeeId) {
	    return getValueFromEmployeeDetailsFile(employeeId, getEmployeeInformation(), 7);
	}
	
	static String getTinNo(String employeeId) {
	    return getValueFromEmployeeDetailsFile(employeeId, getEmployeeInformation(), 8);
	}
	
	static String getPagibigNo(String employeeId) {
	    return getValueFromEmployeeDetailsFile(employeeId, getEmployeeInformation(), 9);
	}
	
	static String getStatus(String employeeId) {
	    return getValueFromEmployeeDetailsFile(employeeId, getEmployeeInformation(), 10);
	}

	static String getPosition(String employeeId) {
	    return getValueFromEmployeeDetailsFile(employeeId, getEmployeeInformation(), 11);
	}

	static String getSupervisor(String employeeId) {
	    return getValueFromEmployeeDetailsFile(employeeId, getEmployeeInformation(), 12);
	}

	
	public static String getEmployeeInformation() {
		return EMPLOYEE_INFORMATION;
	}

	public static String getEmployeeSalaryFile() {
		return EMPLOYEE_SALARY_FILE;
	}
}
