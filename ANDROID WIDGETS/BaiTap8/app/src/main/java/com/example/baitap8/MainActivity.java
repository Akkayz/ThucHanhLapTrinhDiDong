package com.example.baitap8;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_ChuyenDoi;
    private EditText edt_NamDuonglich, edt_NamAmlich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_ChuyenDoi = (Button) findViewById(R.id.btn_ChuyenDoi);
        edt_NamDuonglich = (EditText) findViewById(R.id.edt_NamDuongLich);
        edt_NamAmlich = (EditText) findViewById(R.id.edt_NamAmLich);

        btn_ChuyenDoi.setOnClickListener(v -> {
            int namDuongLich = Integer.parseInt(edt_NamDuonglich.getText().toString());
            if (namDuongLich <= 1900) {
                edt_NamAmlich.setText("Chỉ được nhập số trên 1900!");
                edt_NamAmlich.setBackgroundColor(Color.RED);
                return;
            }
            else {
                String can = "", chi = "";
                switch (namDuongLich % 10) {
                    case 0:
                        can = "Canh";
                        break;
                    case 1:
                        can = "Tân";
                        break;
                    case 2:
                        can = "Nhâm";
                        break;
                    case 3:
                        can = "Quý";
                        break;
                    case 4:
                        can = "Giáp";
                        break;
                    case 5:
                        can = "Ất";
                        break;
                    case 6:
                        can = "Bính";
                        break;
                    case 7:
                        can = "Đinh";
                        break;
                    case 8:
                        can = "Mậu";
                        break;
                    case 9:
                        can = "Kỷ";
                        break;
                }
                switch (namDuongLich % 12) {
                    case 0:
                        chi = "Thân";
                        break;
                    case 1:
                        chi = "Dậu";
                        break;
                    case 2:
                        chi = "Tuất";
                        break;
                    case 3:
                        chi = "Hợi";
                        break;
                    case 4:
                        chi = "Tý";
                        break;
                    case 5:
                        chi = "Sửu";
                        break;
                    case 6:
                        chi = "Dần";
                        break;
                    case 7:
                        chi = "Mẹo";
                        break;
                    case 8:
                        chi = "Thìn";
                        break;
                    case 9:
                        chi = "Tỵ";
                        break;
                    case 10:
                        chi = "Ngọ";
                        break;
                    case 11:
                        chi = "Mùi";
                        break;
                }
                edt_NamAmlich.setText(can + " " + chi);
                edt_NamAmlich.setBackgroundColor(Color.YELLOW);
            }
        });
    }
}