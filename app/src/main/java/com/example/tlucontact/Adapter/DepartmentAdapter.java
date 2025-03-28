package com.example.tlucontact.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontact.Activity.DepartmentDetailActivity;
import com.example.tlucontact.Model.Department;
import com.example.tlucontact.R;

import java.util.List;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder> {

    private List<Department> departments;
    private Context context;

    public DepartmentAdapter(Context context, List<Department> departments) {
        this.departments = departments;
        this.context = context;
    }

    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_department, parent, false);
        return new DepartmentViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder holder, int position) {
        Department department = departments.get(position);
        holder.bind(department);
    }

    @Override
    public int getItemCount() {
        return departments.size();
    }

    public static class DepartmentViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewPhone;
        private Context context;

        public DepartmentViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewDepartmentName);
            textViewPhone = itemView.findViewById(R.id.textViewDepartmentPhone);
            this.context = context;
        }

        public void bind(Department department) {
            textViewName.setText(department.getName());
            textViewPhone.setText(department.getPhone());

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DepartmentDetailActivity.class);
                intent.putExtra("department_id", department.getId());
                context.startActivity(intent);
            });
        }
    }
}