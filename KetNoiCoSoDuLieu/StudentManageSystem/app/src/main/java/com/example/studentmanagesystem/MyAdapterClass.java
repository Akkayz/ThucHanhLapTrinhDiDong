package com.example.studentmanagesystem;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapterClass  extends ArrayAdapter<Room> {
    ArrayList<Room> classList = new ArrayList<Room>();

    public MyAdapterClass(Context context, int resource, ArrayList<Room> objects) {
        super(context, resource, objects);
        classList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.my_class, null);
        TextView txtcodeclass = v.findViewById(R.id.txtcodeclass);
        TextView txtnameclass = v.findViewById(R.id.txtnameclass);
        TextView txtnuberclass = v.findViewById(R.id.txtnumberclass);

        if (position == 0) {
            txtcodeclass.setBackgroundColor(Color.WHITE);
            txtnameclass.setBackgroundColor(Color.WHITE);
            txtnuberclass.setBackgroundColor(Color.WHITE);
        }
        txtcodeclass.setText(classList.get(position).getCode_class());
        txtnameclass.setText(classList.get(position).getName_class());
        txtnuberclass.setText(classList.get(position).getClass_number());
        return v;
    }
}
