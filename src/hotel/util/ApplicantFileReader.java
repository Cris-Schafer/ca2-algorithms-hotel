package hotel.util;

import hotel.model.Department;
import hotel.model.DepartmentType;
import hotel.model.Employee;
import hotel.model.Manager;
import hotel.model.ManagerType;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// ============================================================
// ApplicantFileReader
// Reads Applicants_Form.txt (a CSV file) and turns every line
// into an Employee. Columns used:
//   0 First name | 1 Last name | 5 Department | 6 Position | 7 Job title
// The other columns (gender, email, salary, company) are not needed.
// ============================================================
public final class ApplicantFileReader {

    private static final int FIRST_NAME = 0;
    private static final int LAST_NAME = 1;
    private static final int DEPARTMENT = 5;
    private static final int POSITION = 6;
    private static final int JOB_TITLE = 7;
    private static final int MINIMUM_COLUMNS = 8;

    private ApplicantFileReader() {
        // Utility class: no objects are needed.
    }

    public static List<Employee> read(String fileName) throws IOException {
        List<Employee> employees = new ArrayList<>();
        int skippedLines = 0;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
            String line = reader.readLine(); // the first line is the header, so it is not a person

            while ((line = reader.readLine()) != null) {
                line = line.replace("﻿", "").trim(); // removes the invisible BOM mark and spaces
                if (line.isEmpty()) {
                    continue; // skip blank lines
                }

                Employee employee = createEmployee(line);
                if (employee == null) {
                    skippedLines++;
                } else {
                    employees.add(employee);
                }
            }
        }

        if (skippedLines > 0) {
            System.out.println("Warning: " + skippedLines + " line(s) were skipped because they are not valid.");
        }
        return employees;
    }

    // Builds one Employee from one CSV line, or returns null if the line is not valid.
    private static Employee createEmployee(String line) {
        String[] columns = line.split(",", -1); // -1 keeps empty columns (the Position can be empty)
        if (columns.length < MINIMUM_COLUMNS) {
            return null;
        }

        String firstName = columns[FIRST_NAME].trim();
        String lastName = columns[LAST_NAME].trim();
        DepartmentType departmentType = DepartmentType.fromLabel(columns[DEPARTMENT]);

        if (firstName.isEmpty() || lastName.isEmpty() || departmentType == null) {
            return null;
        }

        String name = firstName + " " + lastName;
        String jobTitle = columns[JOB_TITLE].trim();
        ManagerType managerType = ManagerType.fromJobTitle(jobTitle);

        return Employee.create(name, jobTitle, columns[POSITION],
                Manager.create(managerType), Department.create(departmentType));
    }
}
