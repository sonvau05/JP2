package Entity;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private Department department;
    private Gender gender;
    private LocalDate dob;

    public Employee(int id, String name, Department department, Gender gender, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public Department getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getDob() {
        return dob;
    }
}


