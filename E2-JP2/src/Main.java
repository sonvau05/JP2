import Entity.Department;
import Entity.Employee;
import Entity.Gender;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Department> departments = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        Map<String, Long> countEmployee = new HashMap<>();

        departments.add(new Department(1, "HR", "Human Resource"));
        departments.add(new Department(2, "IT", "Information Technology"));
        employees.add(new Employee(1, "Lo Van Hieu", departments.get(0), Gender.MALE, LocalDate.of(2004, 10, 3)));
        employees.add(new Employee(2, "Nguyen Van Hieu", departments.get(0), Gender.MALE, LocalDate.of(2005, 11, 2)));
        employees.add(new Employee(3, "Dat Nguyen", departments.get(1), Gender.MALE, LocalDate.of(2005, 10, 1)));

        // Cách 1
        departments.forEach(department -> {
            long totalEmp = employees.stream()
                    .filter(emp -> emp.getDepartment().getId() == department.getId())
                    .count();
            countEmployee.put(department.getCode(), totalEmp);
        });
        System.out.println("Cách 1: " + countEmployee);

        // Cách 2
        countEmployee.clear();
        departments.forEach(d -> {
            employees.stream()
                    .filter(emp -> emp.getDepartment().getId() == d.getId())
                    .forEach(employee -> countEmployee.put(d.getCode(), countEmployee.getOrDefault(d.getCode(), 0L) + 1));
        });
        System.out.println("Cách 2: " + countEmployee);

        // Cách 3
        countEmployee.clear();
        departments.forEach(department -> {
            Set<Employee> employeeSet = employees.stream()
                    .filter(employee -> employee.getDepartment().getId() == department.getId())
                    .collect(Collectors.toSet());
            countEmployee.put(department.getCode(), (long) employeeSet.size());
        });
        System.out.println("Cách 3: " + countEmployee);

        // Đếm số nhân viên nam trong các phòng
        departments.forEach(department -> {
            long maleCount = employees.stream()
                    .filter(emp -> emp.getDepartment().getId() == department.getId() && emp.getGender() == Gender.MALE)
                    .count();
            System.out.println("Số nhân viên nam trong phòng " + department.getName() + ": " + maleCount);
        });

        // Đếm số nhân viên có sinh nhật trong tháng này
        int currentMonth = LocalDate.now().getMonthValue();
        Map<Department, List<Employee>> birthdayEmployees = employees.stream()
                .filter(emp -> emp.getDob().getMonthValue() == currentMonth)
                .collect(Collectors.groupingBy(Employee::getDepartment));

        birthdayEmployees.forEach((department, empList) -> {
            System.out.println("Phòng " + department.getName() + " có " + empList.size() + " người sinh nhật trong tháng này:");
            empList.forEach(emp -> System.out.println("- " + emp.getName()));
        });
    }
}


