package com.entreprise.util;

import com.entreprise.employee.Employee;
import com.entreprise.exception.InvalidEmployeeException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataLoader {
    public static List<Employee> loadEmployees(String filePath) throws IOException, InvalidEmployeeException {
        List<Employee> employees = new ArrayList<>();

        // try-with-resources : fermeture auto du fichier même si erreur
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                // Ignorer les lignes mal formées
                if (parts.length < 4) continue;

                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String department = parts[2].trim();
                double salary = Double.parseDouble(parts[3].trim());

                employees.add(new Employee(id, name, department, salary));
            }
        }

        return employees;
    }
}
