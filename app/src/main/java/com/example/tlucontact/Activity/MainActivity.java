package com.example.tlucontact.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.tlucontact.Adapter.StaffAdapter;
import com.example.tlucontact.Adapter.DepartmentAdapter;
import com.example.tlucontact.Data.FirestoreHelper;
import com.example.tlucontact.Model.Staff;
import com.example.tlucontact.Model.Department;
import com.example.tlucontact.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaffAdapter staffAdapter;
    private DepartmentAdapter departmentAdapter;
    private List<Staff> staffList, filteredStaffList;
    private List<Department> departmentList, filteredDepartmentList;
    private Button buttonDepartments, buttonStaff;
    private EditText searchView;
    private boolean showDepartments = true;
    private FirestoreHelper firestoreHelper;
    private ImageView iconList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firestoreHelper = new FirestoreHelper();

        initViews();
        setupRecyclerView();
        initAdapters();
        setupListeners();

        loadDepartmentData();
        loadStaffData();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewData);
        buttonDepartments = findViewById(R.id.buttonDepartment);
        buttonStaff = findViewById(R.id.buttonStaff);
        searchView = findViewById(R.id.searchEditText);
        iconList = findViewById(R.id.iconList);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initAdapters() {
        staffList = new ArrayList<>();
        departmentList = new ArrayList<>();
        filteredStaffList = new ArrayList<>();
        filteredDepartmentList = new ArrayList<>();

        staffAdapter = new StaffAdapter(this, filteredStaffList);
        departmentAdapter = new DepartmentAdapter(this, filteredDepartmentList);

        recyclerView.setAdapter(departmentAdapter);
    }

    private void setupListeners() {
        buttonDepartments.setOnClickListener(v -> {
            showDepartmentList();
            highlightButton(buttonDepartments, buttonStaff);
        });
        buttonStaff.setOnClickListener(v -> {
            showStaffList();
            highlightButton(buttonStaff, buttonDepartments);
        });
        setupSearchListener();
        setupIconListListener();
    }

    private void highlightButton(Button selectedButton, Button unselectedButton) {
        selectedButton.setBackgroundResource(R.drawable.button_selector);
        selectedButton.setSelected(true);  // Kích hoạt trạng thái selected
        unselectedButton.setSelected(false); // Tắt trạng thái selected
        // Nếu muốn giữ logic màu chữ:
        selectedButton.setTextColor(getResources().getColor(R.color.textSelected));
        unselectedButton.setTextColor(getResources().getColor(R.color.textDefault));
    }

    private void showDepartmentList() {
        showDepartments = true;
        recyclerView.setAdapter(departmentAdapter);
        filterDepartmentList(searchView.getText().toString());
    }

    private void showStaffList() {
        showDepartments = false;
        recyclerView.setAdapter(staffAdapter);
        filterStaffList(searchView.getText().toString());
    }

    private void loadDepartmentData() {
        departmentList.clear();
        firestoreHelper.getDepartments(departments -> {
            departmentList.addAll(departments);
            filterDepartmentList(searchView.getText().toString());
        });
    }

    private void loadStaffData() {
        staffList.clear();
        firestoreHelper.getStaff(staff -> {
            staffList.addAll(staff);
            filterStaffList(searchView.getText().toString());
        });
    }

    private void setupSearchListener() {
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterResults(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterResults(String query) {
        if (showDepartments) {
            filterDepartmentList(query);
        } else {
            filterStaffList(query);
        }
    }

    private void filterDepartmentList(String query) {
        filteredDepartmentList.clear();
        for (Department department : departmentList) {
            if (department.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredDepartmentList.add(department);
            }
        }
        departmentAdapter.notifyDataSetChanged();
    }

    private void filterStaffList(String query) {
        filteredStaffList.clear();
        for (Staff staff : staffList) {
            if (staff.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredStaffList.add(staff);
            }
        }
        staffAdapter.notifyDataSetChanged();
    }

    private void setupIconListListener() {
        iconList.setOnClickListener(v -> showSortMenu());
    }

    private void showSortMenu() {
        PopupMenu popupMenu = new PopupMenu(this, iconList);

        if (showDepartments) {
            popupMenu.getMenu().add("Sắp xếp theo tên");
        } else {
            popupMenu.getMenu().add("Sắp xếp theo tên");
            popupMenu.getMenu().add("Sắp xếp theo chức vụ");
            popupMenu.getMenu().add("Sắp xếp theo phòng ban");
        }

        popupMenu.setOnMenuItemClickListener(item -> {
            String title = item.getTitle().toString();
            if (showDepartments) {
                if (title.equals("Sắp xếp theo tên")) {
                    sortDepartmentByName();
                }
            } else {
                if (title.equals("Sắp xếp theo tên")) {
                    sortStaffByName();
                } else if (title.equals("Sắp xếp theo chức vụ")) {
                    sortStaffByRegency();
                } else if (title.equals("Sắp xếp theo phòng ban")) {
                    sortStaffByDepartment();
                }
            }
            return true;
        });

        popupMenu.show();
    }

    private void sortDepartmentByName() {
        if (showDepartments) {
            Collections.sort(departmentList, (d1, d2) -> d1.getName().compareToIgnoreCase(d2.getName()));
            filterDepartmentList(searchView.getText().toString());
        }
    }

    private void sortStaffByName() {
        if (!showDepartments) {
            Collections.sort(staffList, (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
            filterStaffList(searchView.getText().toString());
        }
    }

    private void sortStaffByRegency() {
        if (!showDepartments) {
            Collections.sort(staffList, (s1, s2) -> s1.getRegency().compareToIgnoreCase(s2.getRegency()));
            filterStaffList(searchView.getText().toString());
        }
    }

    private void sortStaffByDepartment() {
        if (!showDepartments) {
            Collections.sort(staffList, (s1, s2) -> s1.getDepartment().compareToIgnoreCase(s2.getDepartment()));
            filterStaffList(searchView.getText().toString());
        }
    }


}