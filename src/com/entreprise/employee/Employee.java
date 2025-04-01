package com.entreprise.employee;

public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;


    //Constructor

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDepartment() {
        return department;
    }
    public double getSalary() {
        return salary;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    //Log
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", department=" + department + "]";
    }

}


