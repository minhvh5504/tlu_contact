package com.example.tlucontact.Model;

public class Staff {
    private String name;
    private String regency;
    private String phone;
    private String email;
    private String department;

    public Staff(String name, String regency, String phone, String email, String department) {
        this.name = name;
        this.regency = regency;
        this.phone = phone;
        this.email = email;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public String getRegency() {
        return regency;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }
}