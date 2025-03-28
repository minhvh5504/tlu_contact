package com.example.tlucontact.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontact.Activity.StaffDetailActivity;
import com.example.tlucontact.Model.Staff;
import com.example.tlucontact.R;

import java.util.List;

public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.StaffViewHolder> {

    private List<Staff> staffList;
    private Context context;

    public StaffAdapter(Context context, List<Staff> staffList) {
        this.staffList = staffList;
        this.context = context;
    }

    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff, parent, false);
        return new StaffViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
        Staff staff = staffList.get(position);
        holder.bind(staff);
    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }

    public static class StaffViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewPhone;
        private TextView textViewDepartment;
        private Context context;

        public StaffViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewStaffName);
            textViewPhone = itemView.findViewById(R.id.textViewStaffPhone);
            textViewDepartment = itemView.findViewById(R.id.textViewStaffDetailDepartment);
            this.context = context;
        }

        public void bind(Staff staff) {
            textViewName.setText(staff.getName());
            textViewPhone.setText(staff.getPhone());
            textViewDepartment.setText(staff.getDepartment());

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, StaffDetailActivity.class);
                intent.putExtra("staff_email", staff.getEmail());
                context.startActivity(intent);
            });
        }
    }
}