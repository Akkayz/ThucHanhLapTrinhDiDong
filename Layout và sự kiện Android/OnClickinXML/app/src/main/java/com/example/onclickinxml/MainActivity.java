package com.example.onclickinxml;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickMe(View v) {
        // Kiểm tra bằng dùng phương thức getID() của view so sánh với id của button
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