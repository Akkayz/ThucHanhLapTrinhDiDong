package com.example.studentmanagesystem;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyAdapterStudent extends ArrayAdapter<Student> {
    ArrayList<Student> studentList = new ArrayList<>();

    public MyAdapterStudent(@NonNull Context context, int resource, @NonNull ArrayList<Student> objects) {
        super(context, resource, objects);
        studentList = objects;
    }

    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.my_student, null);
        TextView txtclassstudent = (TextView) v.findViewById(R.id.txtStudentClass);
        TextView txtnamestudent = (TextView) v.findViewById(R.id.txtStudentName);
        TextView txtbirthdaystudent = (TextView) v.findViewById(R.id.txtStudentBirthday);
        TextView txtgenderstudent = (TextView) v.findViewById(R.id.txtStudentGender);
        TextView txtaddressstudent = (TextView) v.findViewById(R.id.txtStudentAddress);
        if (position == 0) {
            txtclassstudent.setBackgroundColor(Color.WHITE);
            txtnamestudent.setBackgroundColor(Color.WHITE);
            txtbirthdaystudent.setBackgroundColor(Color.WHITE);
            txtgenderstudent.setBackgroundColor(Color.WHITE);
            txtaddressstudent.setBackgroundColor(Color.WHITE);
        }
        txtclassstudent.setText("Mã lớp: "+ studentList.get(position).getName_class());
        txtnamestudent.setText("Mã lớp: "+ studentList.get(position).getName_class());
        txtbirthdaystudent.setText("Mã lớp: "+ studentList.get(position).getName_class());
        txtgenderstudent.setText("Mã lớp: "+ studentList.get(position).getName_class());
        txtaddressstudent.setText("Mã lớp: "+ studentList.get(position).getName_class());

    }
}

