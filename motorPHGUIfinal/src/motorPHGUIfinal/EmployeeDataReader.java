package motorPHGUIfinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataReader {
    private static final String FILE_PATH = "MotorPH Employee Data.txt";
    private static final String DELIMITER = "\t";

    public static String[][] readEmployeeDetailsFromFile() {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(DELIMITER);
                rows.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows.toArray(new String[0][]);
    }
}
