package General;

import Entity.Department;
import Entity.Employee;
import Entity.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static List<Department> generateDepartments() {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department(1, "HR"));
        departments.add(new Department(2, "IT"));
        departments.add(new Department(3, "Finance"));
        return departments;
    }

    public static List<Employee> generateEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", 1, LocalDate.of(1990, 5, 20), Gender.FEMALE, "New York", "NY", "123456789"));
        employees.add(new Employee(2, "Bob", 2, LocalDate.of(1985, 8, 15), Gender.MALE, "Los Angeles", "CA", "987654321"));
        employees.add(new Employee(3, "Charlie", 1, LocalDate.of(1992, 1, 30), Gender.MALE, "Chicago", "IL", "456123789"));
        employees.add(new Employee(4, "Diana", 3, LocalDate.of(1995, 11, 25), Gender.FEMALE, "Houston", "TX", "321654987"));
        employees.add(new Employee(5, "Eve", 2, LocalDate.of(1991, 4, 12), Gender.FEMALE, "Phoenix", "AZ", "789654123"));
        return employees;
    }
}


