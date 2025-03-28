package com.example.tlucontact.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tlucontact.Data.FirestoreHelper;
import com.example.tlucontact.Model.Department;
import com.example.tlucontact.R;

public class DepartmentDetailActivity extends AppCompatActivity {

    private TextView textViewDepartmentDetailName;
    private TextView textViewDepartmentDetailPhone;
    private TextView textViewDepartmentDetailAddress;
    private Button buttonBackDepartment;
    private ImageView imgCall, imgSend;
    private String departmentName, departmentPhone, departmentAddress;
    private FirestoreHelper firestoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_detail);

        firestoreHelper = new FirestoreHelper();

        initViews();
        getIntentData();
        loadDepartmentDetails();
        setupListeners();
    }

    private void initViews() {
        textViewDepartmentDetailName = findViewById(R.id.textViewDepartmentDetailName);
        textViewDepartmentDetailPhone = findViewById(R.id.textViewDepartmentDetailPhone);
        textViewDepartmentDetailAddress = findViewById(R.id.textViewDepartmentDetailAddress);
        buttonBackDepartment = findViewById(R.id.buttonBackDepartmentDetail);
        imgCall = findViewById(R.id.imageCall);
        imgSend = findViewById(R.id.imageSend);
    }

    private void getIntentData() {
        departmentName = getIntent().getStringExtra("department_id");
    }

    private void loadDepartmentDetails() {
        firestoreHelper.getDepartmentByName(departmentName, department -> {
            departmentName = department.getName();
            departmentPhone = department.getPhone();
            departmentAddress = department.getAddress();
            setDepartmentDetails();
        });
    }

    private void setDepartmentDetails() {
        textViewDepartmentDetailName.setText(departmentName);
        textViewDepartmentDetailPhone.setText(departmentPhone);
        textViewDepartmentDetailAddress.setText(departmentAddress);
    }

    private void setupListeners() {
        buttonBackDepartment.setOnClickListener(v -> finish());

        imgCall.setOnClickListener(v -> handleCall());
        imgSend.setOnClickListener(v -> handleSendMessege());
    }

    private void handleCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + departmentPhone));
        startActivity(intent);
    }

    private void handleSendMessege() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + departmentPhone));
        startActivity(intent);
    }
}