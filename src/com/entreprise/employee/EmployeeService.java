package com.entreprise.employee;

import com.entreprise.exception.InvalidEmployeeException;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    // Méthodo pour ajouter un employé après vérif
    public void addEmployee(Employee employee) throws InvalidEmployeeException {
        if (employee.getName() == null || employee.getName().isEmpty()) {
            throw new InvalidEmployeeException("Nom vide interdit !");
        }
        if (employee.getSalary() < 0) {
            throw new InvalidEmployeeException("Salaire négatif interdit !");
        }
        employees.add(employee);
    }

    // Méthode pour afficher tous les employés
    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("Aucun employé enregistré.");
        } else {
            for (Employee e : employees) {
                System.out.println(e);
            }
        }
    }

    // Filtrer par département
    public List<Employee> filterByDepartment(String department) {
        return employees.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase(department))
                .toList();
    }

    //Trier par salaire croissant
    public List<Employee> sortBySalaryDescending() {
        return employees.stream()
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .toList();
    }

    //Calcul de la somme totale des salaires
    public double calculateTotalSalary() {
        return employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
}

    //Trouver le salarié qui a la plus grosse ... paye 💸
    public Employee getHighestPaidEmployee() {
        return employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .orElse(null);
    }



}
