package Entity;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String name;
    private int deptId;
    private LocalDate dob;
    private Gender gender;
    private String city;
    private String province;
    private String phoneNumber;

    public Employee(int id, String name, int deptId, LocalDate dob, Gender gender,
                    String city, String province, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.deptId = deptId;
        this.dob = dob;
        this.gender = gender;
        this.city = city;
        this.province = province;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDeptId() {
        return deptId;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Gender getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}



