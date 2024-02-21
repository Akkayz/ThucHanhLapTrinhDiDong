package com.example.baitap3;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btncong, btntru, btnnhan, btnchia,btnuocsochunglonnhat,btnexit;
    private TextView textketqua;
    private EditText editsoa, editsob;

    class UocSoChung implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String strSoA = editsoa.getText().toString();
            String strSoB = editsob.getText().toString();
            if (strSoA.isEmpty() || strSoB.isEmpty()) {
                Toast.makeText(getApplication(), "Vui lòng nhập 2 số a và b", Toast.LENGTH_LONG).show();
                return;
            }
            int a = Integer.parseInt(strSoA);
            int b = Integer.parseInt(strSoB);
            int uoc = 1;
            for (int i = 1; i <= a && i <= b; i++) {
                if (a % i == 0 && b % i == 0) {
                    uoc = i;
                }
            }
            textketqua.setText(String.valueOf(uoc));
        }
    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnchia) {

                String strSoA = editsoa.getText().toString();
                String strSoB = editsob.getText().toString();
                if (strSoA.isEmpty() || strSoB.isEmpty()) {
                    Toast.makeText(getApplication(), "Vui lòng nhập 2 số a và b", Toast.LENGTH_LONG).show();
                    return;
                }
                int a = Integer.parseInt(strSoA);
                int b = Integer.parseInt(strSoB);
                if (b == 0) {
                    Toast.makeText(getApplication(), "Số b phải khác 0", Toast.LENGTH_LONG).show();
                    return;
                }
                int thuong = a / b;
                textketqua.setText(String.valueOf(thuong));
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editsoa = (EditText) findViewById(R.id.editsoa);
        editsob = (EditText) findViewById(R.id.editsob);
        btncong = (Button) findViewById(R.id.btncong);
        btntru = (Button) findViewById(R.id.btntru);
        btnnhan = (Button) findViewById(R.id.btnnhan);
        btnchia = (Button) findViewById(R.id.btnchia);
        btnuocsochunglonnhat = (Button) findViewById(R.id.btnuocsochunglonnhan);
        btnexit = (Button) findViewById(R.id.btnexit);
        textketqua = (TextView) findViewById(R.id.textketqua);

        btnnhan.setOnClickListener(this);
        btnchia.setOnClickListener(onClick);
        btnuocsochunglonnhat.setOnClickListener(new UocSoChung());
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btntru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSoA = editsoa.getText().toString();
                String strSoB = editsob.getText().toString();
                if (strSoA.isEmpty() || strSoB.isEmpty()) {
                    Toast.makeText(getApplication(), "Vui lòng nhập 2 số a và b", Toast.LENGTH_LONG).show();
                    return;
                }
                int a = Integer.parseInt(strSoA);
                int b = Integer.parseInt(strSoB);
                int hieu = a - b;
                textketqua.setText(String.valueOf(hieu));
            }
        });
    }

    public void buttontinhtong2so(View view) {
        String strSoA = editsoa.getText().toString();
        String strSoB = editsob.getText().toString();

        if (strSoA.isEmpty() || strSoB.isEmpty()) {
            Toast.makeText(getApplication(), "Vui lòng nhập 2 số a và b", Toast.LENGTH_LONG).show();
            return;
        }

        int a = Integer.parseInt(strSoA);
        int b = Integer.parseInt(strSoB);
        int tong = a + b;
        textketqua.setText(String.valueOf(tong));
    }

    @Override
    public void onClick(View view) {
        String strSoA = editsoa.getText().toString();
        String strSoB = editsob.getText().toString();

        if (strSoA.isEmpty() || strSoB.isEmpty()) {
            Toast.makeText(getApplication(), "Vui lòng nhập 2 số a và b", Toast.LENGTH_LONG).show();
            return;
        }

        int a = Integer.parseInt(strSoA);
        int b = Integer.parseInt(strSoB);
        int tong = a * b;
        textketqua.setText(String.valueOf(tong));
    }

}