package com.example.tlucontact.Data;

import com.example.tlucontact.Model.Department;
import com.example.tlucontact.Model.Staff;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirestoreHelper {
    private FirebaseFirestore db;

    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
    }

    public void getDepartments(OnDataLoadedListener<Department> listener) {
        List<Department> departmentList = new ArrayList<>();
        db.collection("department")
                .get()

                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        String id = document.getId();
                        String name = document.getString("name");
                        String phone = document.getString("phone");
                        String address = document.getString("address");
                        Department dept = new Department(name, phone, address);
                        dept.setId(id);
                        departmentList.add(dept);
                    }
                    listener.onDataLoaded(departmentList);
                });
    }

    public void getStaff(OnDataLoadedListener<Staff> listener) {
        List<Staff> staffList = new ArrayList<>();
        db.collection("staff")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        String name = document.getString("name");
                        String position = document.getString("regency");
                        String phone = document.getString("phone");
                        String email = document.getString("email");
                        String department = document.getString("department");
                        Staff staff = new Staff(name, position, phone, email, department);
                        staffList.add(staff);
                    }
                    listener.onDataLoaded(staffList);
                });
    }

    public void getDepartmentByName(String departmentId, OnSingleDataLoadedListener<Department> listener) {
        db.collection("department")
                .document(departmentId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    String name = documentSnapshot.getString("name");
                    String phone = documentSnapshot.getString("phone");
                    String address = documentSnapshot.getString("address");
                    Department dept = new Department(name, phone, address);
                    listener.onDataLoaded(dept);
                });
    }

    public void getStaffByEmail(String staffEmail, OnSingleDataLoadedListener<Staff> listener) {
        db.collection("staff")
                .document(staffEmail)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    String name = documentSnapshot.getString("name");
                    String position = documentSnapshot.getString("regency");
                    String phone = documentSnapshot.getString("phone");
                    String email = documentSnapshot.getString("email");
                    String department = documentSnapshot.getString("department");
                    Staff staff = new Staff(name, position, phone, email, department);
                    listener.onDataLoaded(staff);
                });
    }

    // Interface để callback danh sách dữ liệu
    public interface OnDataLoadedListener<T> {
        void onDataLoaded(List<T> data);
    }

    // Interface để callback một đối tượng dữ liệu
    public interface OnSingleDataLoadedListener<T> {
        void onDataLoaded(T data);
    }
}