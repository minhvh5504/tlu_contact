package com.example.tlucontact.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tlucontact.Data.FirestoreHelper;
import com.example.tlucontact.R;

public class StaffDetailActivity extends AppCompatActivity {

    private TextView textViewStaffDetailName;
    private TextView textViewStaffDetailRegency;
    private TextView textViewStaffDetailPhone;
    private TextView textViewStaffDetailEmail;
    private TextView textViewStaffDetailDepartment;
    private Button buttonBackStaff;
    private ImageView imgCall, imgEmail, imgSend;
    private String staffName, staffRegency, staffPhone, staffEmail, staffDepartment;
    private FirestoreHelper firestoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_detail);

        firestoreHelper = new FirestoreHelper();

        initViews();
        getIntentData();
        loadStaffDetails();
        setupListeners();
    }

    private void initViews() {
        textViewStaffDetailName = findViewById(R.id.textViewStaffDetailName);
        textViewStaffDetailRegency = findViewById(R.id.textViewStaffDetailRegency);
        textViewStaffDetailPhone = findViewById(R.id.textViewStaffDetailPhone);
        textViewStaffDetailEmail = findViewById(R.id.textViewStaffDetailEmail);
        textViewStaffDetailDepartment = findViewById(R.id.textViewStaffDetailDepartment);
        buttonBackStaff = findViewById(R.id.buttonBackStaffDetail);
        imgCall = findViewById(R.id.imageCall);
        imgEmail = findViewById(R.id.imageEmail);
        imgSend = findViewById(R.id.imageSend);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        staffEmail = intent.getStringExtra("staff_email");
    }

    private void loadStaffDetails() {
        firestoreHelper.getStaffByEmail(staffEmail, staff -> {
            staffName = staff.getName();
            staffRegency = staff.getRegency();
            staffPhone = staff.getPhone();
            staffEmail = staff.getEmail();
            staffDepartment = staff.getDepartment();
            setStaffDetails();
        });
    }

    private void setStaffDetails() {
        textViewStaffDetailName.setText(staffName);
        textViewStaffDetailRegency.setText(staffRegency);
        textViewStaffDetailPhone.setText(staffPhone);
        textViewStaffDetailEmail.setText(staffEmail);
        textViewStaffDetailDepartment.setText(staffDepartment);
    }

    private void setupListeners() {
        buttonBackStaff.setOnClickListener(v -> finish());

        imgCall.setOnClickListener(v -> handleCall());
        imgEmail.setOnClickListener(v -> handleEmail());
        imgSend.setOnClickListener(v -> handleSendMessege());
    }

    private void handleCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + staffPhone));
        startActivity(intent);
    }

    private void handleEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + staffEmail));
        intent.putExtra(Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(intent);
    }

    private void handleSendMessege() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + staffPhone));
        startActivity(intent);
    }
}