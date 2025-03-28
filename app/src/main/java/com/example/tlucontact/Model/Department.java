package com.example.tlucontact.Model;

public class Department {
    private String id;
    private String name;
    private String phone;
    private String address;

    public Department(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}