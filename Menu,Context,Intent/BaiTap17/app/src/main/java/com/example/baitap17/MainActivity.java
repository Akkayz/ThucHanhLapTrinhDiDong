package com.example.baitap17;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
private Button btn_them, btn_xemds, btn_quanlybaihat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn_them = findViewById(R.id.btn_them);
        btn_xemds = findViewById(R.id.btn_xemds);
        btn_quanlybaihat = findViewById(R.id.btn_quanlybaihat);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }
    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate layout cho dialog
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.them_album, null);
        // Thiết lập view cho AlertDialog
        builder.setView(dialogView);

        // Tạo AlertDialog từ AlertDialog.Builder
        AlertDialog alertDialog = builder.create();
        Button btn_luualbum = dialogView.findViewById(R.id.btn_luualbum);
        Button btn_clear = dialogView.findViewById(R.id.btn_clear);
        EditText edt_maalbum = dialogView.findViewById(R.id.edt_maalbum);
        EditText edt_tenalbum = dialogView.findViewById(R.id.edt_tenalbum);
        // Hiển thị dialog
        alertDialog.show();
        btn_luualbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin từ EditText
                String maAlbum = edt_maalbum.getText().toString();
                String tenAlbum = edt_tenalbum.getText().toString();

                // Tạo Intent để gửi danh sách album sang AlbumListActivity
                Intent intent = new Intent(MainActivity.this, AlbumListActivity.class);
                intent.putExtra("ma_album", maAlbum);
                intent.putExtra("ten_album", tenAlbum);
                startActivity(intent);
                // Đóng dialog
                alertDialog.dismiss();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_maalbum.setText("");
                edt_tenalbum.setText("");
            }
        });
    }
}