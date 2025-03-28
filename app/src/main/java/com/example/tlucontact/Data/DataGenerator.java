package com.example.tlucontact.Data;

import com.example.tlucontact.Model.Staff;
import com.example.tlucontact.Model.Department;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    public static List<Department> generateSampleDepartment() {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department("Khoa Cong Nghe Thong Tin", "0243.555.555", "Tang 3, Nha A1"));
        departments.add(new Department("Khoa Cong Trinh", "0243.666.666", "Tang 1, Nha A1"));
        departments.add(new Department("Khoa Ngoai Ngu - Nganh Anh", "0243.777.777", "Tang 2, Nha A1"));
        departments.add(new Department("Phong Dao Tao", "0243.888.888", "Tang 4, Nha H1"));
        departments.add(new Department("Phong Hanh Chinh - Tong Hop", "0243.999.999", "Tang 1, Nha H1"));
        departments.add(new Department("Trung Tam Thong Tin - Thu Vien", "0243.123.456", "Tang 5, Nha A1"));
        departments.add(new Department("Trung Tam Tin Hoc", "0243.789.012", "Tang 6, Nha A1"));
        departments.add(new Department("Bo Mon Toan", "0243.234.567", "Tang 7, Nha A1"));
        departments.add(new Department("Bo Mon Vat Ly", "0243.345.678", "Tang 8, Nha A1"));
        departments.add(new Department("Bo Mon Hoa", "0243.456.789", "Tang 9, Nha A1"));
        return departments;
    }

    public static List<Staff> generateSampleStaff() {
        List<Staff> staff = new ArrayList<>();
        staff.add(new Staff("Nguyen Van An", "Giang vien", "0901.111.111", "an.nguyen@tlu.edu.vn", "Khoa Cong Nghe Thong Tin"));
        staff.add(new Staff("Tran Thi Binh", "Truong phong", "0902.222.222", "binh.tran@tlu.edu.vn", "Phong Dao Tao"));
        staff.add(new Staff("Le Van Chi", "Chuyen vien", "0903.333.333", "chi.le@tlu.edu.vn", "Phong Hanh Chinh - Tong Hop"));
        staff.add(new Staff("Pham Thi Diep", "Pho Giam Doc", "0904.444.444", "diep.pham@tlu.edu.vn", "Trung Tam Thong Tin - Thu Vien"));
        staff.add(new Staff("Hoang Xuan Mai", "Nhan vien", "0905.555.555", "mai.hoang@tlu.edu.vn", "Trung Tam Tin Hoc"));
        staff.add(new Staff("Vu Duc Huy", "Tro giang", "0906.666.666", "huy.vu@tlu.edu.vn", "Bo Mon Toan"));
        staff.add(new Staff("Phan Thanh Long", "Giao vu", "0907.777.777", "long.phan@tlu.edu.vn", "Bo Mon Vat Ly"));
        staff.add(new Staff("Do Thi Kim Anh", "Giang vien", "0908.888.888", "anh.do@tlu.edu.vn", "Bo Mon Hoa"));
        staff.add(new Staff("Dang Ngoc Anh", "Giang vien", "0909.999.999", "anh.dang@tlu.edu.vn", "Khoa Cong Nghe Thong Tin"));
        staff.add(new Staff("Cao Thi Mai", "Truong phong", "0901.222.333", "mai.cao@tlu.edu.vn", "Phong Dao Tao"));
        return staff;
    }
}