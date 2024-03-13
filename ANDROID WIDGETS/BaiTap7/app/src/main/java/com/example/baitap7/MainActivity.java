package com.example.baitap7;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_login, btn_exit;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = findViewById(R.id.btn_login);
        btn_exit = findViewById(R.id.btn_exit);
        checkBox = findViewById(R.id.checkBox);
        btn_login.setOnClickListener(v -> {
            if (checkBox.isChecked()) {
                Toast.makeText(this, "chào mừng bạn đăng nhập hệ thống, bạn đã lưu thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "chào mừng bạn đăng nhập hệ thống, bạn không lưu thông tin", Toast.LENGTH_SHORT).show();
            }
        });
        btn_exit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog dialog = builder.setTitle("Thoát ứng dụng")
                    .setMessage("Bạn có muốn thoát ứng dụng không?")
                    .setPositiveButton("Có", (dialog1, which) -> {
                        System.out.println("Bạn đã thoát ứng dụng");
                        finish();
                    })
                    .setNegativeButton("Không", (dialog12, which) -> {
                        System.out.println("Bạn đã không thoát ứng dụng");
                    })
                    .create();

            dialog.show(); // Hiển thị dialog
        });

    }
}