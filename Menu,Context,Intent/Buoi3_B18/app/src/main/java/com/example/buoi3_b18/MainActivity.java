package com.example.buoi3_b18;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> employeeList;
    private ArrayAdapter<String> adapter;

    private int selectedEmployeeIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view_nv);
        registerForContextMenu(listView);

        employeeList = new ArrayList<>();
        employeeList.add("Tran Van Teo");
        employeeList.add("Nguyen Thi My Dieu");
        employeeList.add("Ly Van Le");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedEmployeeIndex = position;
                editEmployee(selectedEmployeeIndex);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        if (item.getItemId() == R.id.edit) {
            selectedEmployeeIndex = position;
            editEmployee(selectedEmployeeIndex);
            return true;
        } else if (item.getItemId() == R.id.delete) {
            selectedEmployeeIndex = position;
            confirmDeleteEmployee(selectedEmployeeIndex);
            return true;
        } else if (item.getItemId() == R.id.add) {
            addNewEmployee();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    private void editEmployee(final int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chỉnh sửa thông tin nhân viên");
        final EditText input = new EditText(this);
        input.setText(employeeList.get(index));
        builder.setView(input);

        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String updatedName = input.getText().toString();
                if (!TextUtils.isEmpty(updatedName)) {
                    employeeList.set(index, updatedName);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Thông tin nhân viên đã được cập nhật", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên nhân viên", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    private void confirmDeleteEmployee(final int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa nhân viên này?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteEmployee(index);
            }
        });
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    private void deleteEmployee(int index) {
        employeeList.remove(index);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Đã xóa nhân viên", Toast.LENGTH_SHORT).show();
    }

    private void addNewEmployee() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thêm nhân viên mới");
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newEmployeeName = input.getText().toString();
                if (!TextUtils.isEmpty(newEmployeeName)) {
                    employeeList.add(newEmployeeName);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Đã thêm nhân viên mới", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên nhân viên", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

}
