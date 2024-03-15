package com.example.baitap12;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private TextView textView2;
    private Button btn_ten;
    private EditText edt_ten;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);
        textView2 = findViewById(R.id.textView2);
        btn_ten = findViewById(R.id.btn_ten);
        edt_ten = findViewById(R.id.edt_ten);

        ArrayList<String> arrayList_ten = new ArrayList<>(); // Khởi tạo ArrayList để lưu danh sách tên

        String[] list_ten = getResources().getStringArray(R.array.list_ten);
        arrayList_ten.addAll(Arrays.asList(list_ten));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList_ten);
        listView.setAdapter(adapter);


        btn_ten.setOnClickListener(view -> {
            String tenMoi = edt_ten.getText().toString();
            if (!tenMoi.isEmpty()) {
                arrayList_ten.add(tenMoi);
                adapter.notifyDataSetChanged();
                edt_ten.setText("");
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedValue = (String) parent.getItemAtPosition(position);
            textView2.setText("Vị trí: " + position + ", Giá trị: " + selectedValue);

            int color = ContextCompat.getColor(MainActivity.this, android.R.color.holo_blue_dark);
            textView2.setTextColor(color);
        });
    }
}
