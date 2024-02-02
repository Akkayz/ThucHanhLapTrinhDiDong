package com.example.onclickinxml;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class InterfaceOnClickListener extends Activity implements OnClickListener {

    Button btnLogin, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin	= (Button)findViewById(R.id.btnLogin);
        btnLogout	= (Button)findViewById(R.id.btnLogout);
        //Xử lý sự kiện cho 2 button
        btnLogin.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.btnLogin) {
            // Hiển thị thông báo trong vòng vài giây
            Toast.makeText(getApplication(), "Nguyễn Hưng: Bạn đang Click vào Button Login", Toast.LENGTH_LONG).show();
        }

        if (viewId == R.id.btnLogout) {
            Toast.makeText(getApplication(), "Nguyễn Hưng: Bạn đang Click vào Button Logout", Toast.LENGTH_LONG).show();
        }

    }
}