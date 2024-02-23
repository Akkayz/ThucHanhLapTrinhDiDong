package com.example.studentmanagesystem;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    public static final String DATABASE_NAME = "student.db";
    SQLiteDatabase db;
    EditText edtUsername, edtPassword;
    Button btnCloseLogin, btnLogin;

    private void initDB() {
        db = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        String sql;
        try {
            if (!isTableExitst(db,"tbluser")) {
                sql = "CREATE TABLE  tbluser (id INTEGER PRIMARY KEY AUTOINCREMENT,";
                sql += "username TEXT NOT NULL,";
                sql += "password TEXT NOT NULL,";
                db.execSQL(sql);
                sql = "INSERT INTO tbluser (username, password) VALUES ('admin', 'admin')";
                db.execSQL(sql);
            }
            if (!isTableExitst(db,"tblclass")) {
                sql = "CREATE TABLE tnlclass (";
                sql += "id_class INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,";
                sql += "code_class TEXT,";
                sql += "name_class TEXT,";
                sql += "number_student INTEGER);";
                db.execSQL(sql);
            }
            if (!isTableExitst(db, "tblstudent")) {
                sql = "CREATE TABLE tblstudent (";
                sql += "id_student INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,";
                sql += "id_class INTEGER NOT NULL,";
                sql += "code_student TEXT NOT NULL,";
                sql += "name_student TEXT,";
                sql += "gender_student NUMERIC,";
                sql += "birthday_student TEXT,";
                sql += "address_student TEXT,";
                db.execSQL(sql);
            }
        } catch (SQLException ex) {
            Toast.makeText(this, "Khởi tạo dữ liệu không thanh công", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isTableExitst(SQLiteDatabase database, String tableName) {
        Cursor cursor = database.rawQuery("select DISTINCE tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    private boolean isUser(String username, String password) {
        try {
            db = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
            Cursor c = db.rawQuery("SELECT * FROM tbluser WHERE username = ? and password=?", new String[]{username, password});
            c.moveToFirst();
            if (c.getCount() > 0) {
                c.close();
                return true;
            }
        } catch (SQLException ex) {
            Toast.makeText(this, "Lỗi hệ thống", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnCloseLogin = findViewById(R.id.btnCloseLogin);
        btnLogin = findViewById(R.id.btnLogin);
        initDB();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if (username.isEmpty()) {
                    Toast.makeText(Login.this, "Xin vui lòng nhập lại tài khoản", Toast.LENGTH_LONG).show();
                    edtUsername.requestFocus();
                } else if (password.isEmpty()) {
                    Toast.makeText(Login.this, "Xin vui lòng nhập lại tài khoản", Toast.LENGTH_LONG).show();
                    edtPassword.requestFocus();
                } else if (isUser(edtUsername.getText().toString(), edtPassword.getText().toString())) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Tài khoản hoặc mật khẩu không tồn tại", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCloseLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
