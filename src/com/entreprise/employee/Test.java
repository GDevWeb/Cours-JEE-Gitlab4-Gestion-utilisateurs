package com.entreprise.employee;

import com.entreprise.employee.*;
import com.entreprise.exception.InvalidEmployeeException;

public class Test {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();

        try {
            service.addEmployee(new Employee(1, "Alice", "IT", 4000));
            service.addEmployee(new Employee(2, "Bob", "HR", 3500));
            service.addEmployee(new Employee(3, "Charlie", "IT", 4500));
            service.addEmployee(new Employee(4, "Diana", "Sales", 3000));
            service.addEmployee(new Employee(5, "Edward", "Marketing", 3800));
            service.addEmployee(new Employee(6, "Fiona", "IT", 4200));
        } catch (InvalidEmployeeException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        System.out.println("\n👥 Tous les employés :");
        service.displayAllEmployees();

        System.out.println("\n🔎 Employés du département IT :");
        service.filterByDepartment("IT").forEach(System.out::println);

        System.out.println("\n💰 Employés triés par salaire décroissant :");
        service.sortBySalaryDescending().forEach(System.out::println);

        System.out.println("\n📊 Somme totale des salaires : " + service.calculateTotalSalary());

        System.out.println("\n🏆 Employé le mieux payé :");
        Employee top = service.getHighestPaidEmployee();
        if (top != null) {
            System.out.println(top);
        }
    }
}
