package com.example.baitap16;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tvNgayHTDisplay, tvGioHTDisplay;
    private Button btnDate, btnTime, btnThemCV;
    private EditText edtCongViec, edtNoiDung;
    private ListView lvDanhSachCV;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> todoList;
    private SimpleDateFormat timeFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo các view
        tvNgayHTDisplay = findViewById(R.id.tvNgayHTDisplay);
        tvGioHTDisplay = findViewById(R.id.tvGioHTDisplay);
        btnDate = findViewById(R.id.btnDate);
        btnTime = findViewById(R.id.btnTime);
        btnThemCV = findViewById(R.id.btnThemCV);
        edtCongViec = findViewById(R.id.edtCongViec);
        edtNoiDung = findViewById(R.id.edtNoiDung);
        lvDanhSachCV = findViewById(R.id.lvDanhSachCV);

        // Khởi tạo định dạng thời gian
        timeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());

        // Khởi tạo danh sách công việc
        todoList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoList);
        lvDanhSachCV.setAdapter(adapter);

        // Sự kiện khi nhấn nút Date
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // Sự kiện khi nhấn nút Time
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        // Sự kiện khi nhấn nút ThêmCV
        btnThemCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thêm công việc vào danh sách
                addToDoList();
            }
        });


        lvDanhSachCV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Hiển thị ContextMenu khi nhấn và giữ lâu vào một item
                openContextMenu(lvDanhSachCV);
                return true;
            }
        });
        registerForContextMenu(lvDanhSachCV);

    }

    // Hiển thị DatePickerDialog
    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Cập nhật ngày hoàn thành
                        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        tvNgayHTDisplay.setText(date);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    // Hiển thị TimePickerDialog
    private void showTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Cập nhật giờ hoàn thành
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);
                        String time = timeFormat.format(calendar.getTime());
                        tvGioHTDisplay.setText(time);
                    }
                }, hourOfDay, minute, false);
        timePickerDialog.show();
    }

    // Thêm công việc vào danh sách
    private void addToDoList() {
        // Lấy thông tin công việc và nội dung từ EditText
        String congViec = edtCongViec.getText().toString().trim();
        String noiDung = edtNoiDung.getText().toString().trim();

        // Kiểm tra xem đã nhập đủ thông tin chưa
        if (congViec.isEmpty() || noiDung.isEmpty() || tvNgayHTDisplay.getText().toString().isEmpty() || tvGioHTDisplay.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        String gioHT = tvGioHTDisplay.getText().toString();
        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
            SimpleDateFormat displayFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
            gioHT = displayFormat.format(parseFormat.parse(gioHT));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String congViecStr = congViec + " - " + tvNgayHTDisplay.getText() + " - " + gioHT;


        todoList.add(congViecStr);
        adapter.notifyDataSetChanged();


        edtCongViec.setText("");
        edtNoiDung.setText("");
        tvNgayHTDisplay.setText("");
        tvGioHTDisplay.setText("");


        Toast.makeText(MainActivity.this, "Thêm công việc thành công", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_completed) {

            return true;
        } else if (id == R.id.menu_uncompleted) {

            return true;
        } else if (id == R.id.menu_clear_all) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        int id = item.getItemId();
        if (id == R.id.context_edit) {
            editToDoItem(position);
            return true;
        } else if (id == R.id.context_delete) {
            deleteToDoItem(position);
            return true;
        } else if (id == R.id.context_count) {
            countToDoItems();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }


    private void editToDoItem(int position) {
        String todoItem = todoList.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Sửa công việc");
        final EditText input = new EditText(MainActivity.this);
        input.setText(todoItem); // Hiển thị nội dung cũ của công việc
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String updatedTodoItem = input.getText().toString().trim();
                if (!updatedTodoItem.isEmpty()) {

                    todoList.set(position, updatedTodoItem);
                    adapter.notifyDataSetChanged();
                } else {

                    Toast.makeText(MainActivity.this, "Vui lòng nhập nội dung công việc mới", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void deleteToDoItem(int position) {
        // Xóa công việc khỏi danh sách
        todoList.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(MainActivity.this, "Đã xóa công việc", Toast.LENGTH_SHORT).show();
    }

    private void countToDoItems() {
        int count = todoList.size();
        Toast.makeText(MainActivity.this, "Số CV: " + count, Toast.LENGTH_SHORT).show();
    }


}
