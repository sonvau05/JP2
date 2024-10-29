package General;

import Entity.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeSearch {

    public static List<Employee> searchByName(List<Employee> employees, String name) {
        return employees.stream()
                .filter(employee -> employee.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
}

