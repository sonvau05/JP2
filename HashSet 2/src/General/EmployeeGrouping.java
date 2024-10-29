package General;

import Entity.Department;
import Entity.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeGrouping {

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
}

