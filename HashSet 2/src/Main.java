import Entity.Department;
import Entity.Employee;
import General.DataGenerator;
import General.EmployeeSearch;
import General.EmployeeGrouping;
import General.EmployeeCount;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Department> departments = DataGenerator.generateDepartments();
        List<Employee> employees = DataGenerator.generateEmployees();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee name to search: ");
        String searchName = scanner.nextLine();

        List<Employee> foundEmployees = EmployeeSearch.searchByName(employees, searchName);
        System.out.println("Employees found with name '" + searchName + "': " + foundEmployees.size());
        
        for (Employee emp : foundEmployees) {
            String departmentName = getDepartmentName(departments, emp.getDeptId());
            System.out.println("Name: " + emp.getName() +
                    ", Department: " + departmentName +
                    ", Gender: " + emp.getGender() +
                    ", City: " + emp.getCity());
        }

        Map<String, List<Employee>> groupedByDept = EmployeeGrouping.groupByDepartment(employees, departments);
        System.out.println("Employees grouped by department:");
        groupedByDept.forEach((deptName, empList) -> {
            System.out.println(deptName + ": " + empList.size() + " employees");
        });

        Map<String, Long> employeeCountByDept = EmployeeCount.countEmployeesByDepartment(employees, departments);
        System.out.println("Employee count by department:");
        employeeCountByDept.forEach((deptName, count) -> {
            System.out.println(deptName + ": " + count + " employees");
        });

        scanner.close();
    }

    private static String getDepartmentName(List<Department> departments, int deptId) {
        return departments.stream()
                .filter(dept -> dept.getId() == deptId)
                .map(Department::getName)
                .findFirst()
                .orElse("Unknown Department");
    }
}


