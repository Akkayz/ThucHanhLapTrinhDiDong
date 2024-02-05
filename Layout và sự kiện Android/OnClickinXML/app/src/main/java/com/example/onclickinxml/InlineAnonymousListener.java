package com.example.onclickinxml;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class InlineAnonymousListener extends Activity {
    //Cần khai báo 2 nút Button
    Button btnLogin, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        btnLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplication(), "Nguyễn Hưng: Bạn đang Click vào Button Login", Toast.LENGTH_LONG).show();

            }
        });
        btnLogout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplication(), "Nguyễn Hưng: Bạn đang Click vào Button Logout", Toast.LENGTH_LONG).show();
            }
        });
    }


}