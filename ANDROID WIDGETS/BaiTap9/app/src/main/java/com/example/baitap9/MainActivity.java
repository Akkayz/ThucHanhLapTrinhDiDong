package com.example.baitap9;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edt_HoVaTen, edt_CMND, edt_ThongTinBoSung;
    private RadioButton radioTrungCap, radioCaoDang, radioDaiHoc, radioDocBao, radioDocSach, radioDocCoding;
    private Button btn_GuiThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_HoVaTen = findViewById(R.id.edt_HoVaTen);
        edt_CMND = findViewById(R.id.edt_CMND);
        edt_ThongTinBoSung = findViewById(R.id.edt_ThongTinBoSung);
        radioTrungCap = findViewById(R.id.radioTrungCap);
        radioCaoDang = findViewById(R.id.radioCaoDang);
        radioDaiHoc = findViewById(R.id.radioDaiHoc);
        radioDocBao = findViewById(R.id.radioDocBao);
        radioDocSach = findViewById(R.id.radioDocSach);
        radioDocCoding = findViewById(R.id.radioDocCoding);
        btn_GuiThongTin = findViewById(R.id.btn_GuiThongTin);

        btn_GuiThongTin.setOnClickListener(v -> {
            String hoVaTen = edt_HoVaTen.getText().toString();
            String cmnd = edt_CMND.getText().toString().trim();
            String thongTinBoSung = edt_ThongTinBoSung.getText().toString();
            String trinhDo = "";
            if (radioTrungCap.isChecked()) {
                trinhDo = "Trung cấp";
            } else if (radioCaoDang.isChecked()) {
                trinhDo = "Cao đẳng";
            } else if (radioDaiHoc.isChecked()) {
                trinhDo = "Đại học";
            }
            ArrayList<String> soThichList = new ArrayList<>();
            if (radioDocBao.isChecked()) {
                soThichList.add("Đọc báo");
            }
            if (radioDocSach.isChecked()) {
                soThichList.add("Đọc sách");
            }
            if (radioDocCoding.isChecked()) {
                soThichList.add("Lập trình");
            }
            if (hoVaTen.isEmpty() || hoVaTen.length() < 3) {
                Toast.makeText(MainActivity.this, "Tên không được để trống và phải có ít nhất 3 ký tự", Toast.LENGTH_SHORT).show();
            } else if (!TextUtils.isDigitsOnly(cmnd) || cmnd.length() != 9) {
                Toast.makeText(MainActivity.this, "Dữ liệu không phải kiểu số hoặc  chưa đủ 9 chữ số", Toast.LENGTH_SHORT).show();
            } else if (soThichList.isEmpty()) {
                Toast.makeText(MainActivity.this, "Bạn phải chọn ít nhất 1 chọn lựa", Toast.LENGTH_SHORT).show();
            } else {
                String soThich = TextUtils.join(", ", soThichList);
                String thongTin = "Họ và tên: " + hoVaTen + "\n" + "CMND: " + cmnd + "\n" + "Trình độ: " + trinhDo + "\n" + "Sở thích: " + soThich;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Thông tin cá nhân: ");
                alertDialogBuilder.setMessage(thongTin + "\n" + "----------------------------------------" + "\n" + "Thông tin bổ sung: " + "\n" + thongTinBoSung);
                alertDialogBuilder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int arg1) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }
}