package Service;

import Entity.Department;
import Entity.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService {

    public static List<Employee> searchByName(List<Employee> employees, String name) {
        return employees.stream()
                .filter(employee -> employee.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public static Map<String, List<Employee>> groupByDepartment(List<Employee> employees, List<Department> departments) {
        return employees.stream()
                .collect(Collectors.groupingBy(employee -> {
                    return departments.stream()
                            .filter(department -> department.getId() == employee.getDeptId())
                            .map(Department::getName)
                            .findFirst()
                            .orElse("Unknown Department");
                }));
    }

    public static Map<String, Long> countEmployeesByDepartment(List<Employee> employees, List<Department> departments) {
        return employees.stream()
                .collect(Collectors.groupingBy(employee -> {
                    return departments.stream()
                            .filter(department -> department.getId() == employee.getDeptId())
                            .map(Department::getName)
                            .findFirst()
                            .orElse("Unknown Department");
                }, Collectors.counting()));
    }
}

