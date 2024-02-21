package com.example.baitap4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_converttocelsius, btn_converttofahrenheit, btn_clear;
    private EditText edt_doC, edt_doF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_converttocelsius = findViewById(R.id.btn_converttocelsius);
        btn_converttofahrenheit = findViewById(R.id.btn_converttofahrenheit);
        btn_clear = findViewById(R.id.btn_clear);
        edt_doC = findViewById(R.id.edit_doC);
        edt_doF = findViewById(R.id.edit_doF);

        btn_converttocelsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edt_doF.getText().toString().isEmpty()) {
                    double doF = Double.parseDouble(edt_doF.getText().toString());
                    double doC = (doF - 32) * 5 / 9;
                    edt_doC.setText(String.valueOf(doC));
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập độ F", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_converttofahrenheit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edt_doC.getText().toString().isEmpty()) {
                    double doC = Double.parseDouble(edt_doC.getText().toString());
                    double doF = doC * 9 / 5 + 32;
                    edt_doF.setText(String.valueOf(doF));
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập độ C", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_doC.setText("");
                edt_doF.setText("");
            }
        });
    }
}