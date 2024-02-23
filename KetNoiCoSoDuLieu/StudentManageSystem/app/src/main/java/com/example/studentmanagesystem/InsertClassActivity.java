package com.example.studentmanagesystem;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class InsertClassActivity extends Activity {
    Button btnSaveClass, btnClearClass, btnCloseClass;
    EditText edtClassName,edtClassCode, edtClassNumber;
    SQLiteDatabase db;
    private void initWidget() {
        btnSaveClass = findViewById(R.id.btnSaveInsertClass);
        btnClearClass = findViewById(R.id.btnClearInsertClass);
        btnCloseClass = findViewById(R.id.btnCloseInsertClass);
        edtClassName = findViewById(R.id.edtClassName);
        edtClassCode = findViewById(R.id.edtClassCode);
        edtClassNumber = findViewById(R.id.edtClassNumber);

    }
    private long saveClass() {
        try {
            db = openOrCreateDatabase(Login.DATABASE_NAME, MODE_PRIVATE, null);
            ContentValues values = new ContentValues();
            values.put("code_class", edtClassCode.getText().toString());
            values.put("name_class", edtClassName.getText().toString());
            values.put("number_student", Integer.parseInt(edtClassNumber.getText().toString()));
            long id =db.insert("tbcLass", null, values);
            if (id != -1) {
                return id;
            }
        } catch (Exception ex) {
            Toast.makeText(this, "Thêm lớp học bị lỗi " , Toast.LENGTH_LONG).show();
        }
        return -1;
    }
    private  void clearClass() {
        edtClassName.setText("");
        edtClassCode.setText("");
        edtClassNumber.setText("");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_class);
        initWidget();
        btnSaveClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long id = saveClass();
                Bundle bundle = new Bundle();
                Intent intent = new Intent();
                if (id!=-1){
                    Room r = new Room(id+"", edtClassCode.getText().toString(), edtClassName.getText().toString(), edtClassNumber.getText().toString());
                bundle.putSerializable("room", r);
                intent.putExtra("data", bundle);
                setResult(ClassList.SAVE_CLASS, intent);
                    Toast.makeText(getApplication(), "Thêm lớp học thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnClearClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearClass();
            }
        });
        btnCloseClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notify.exit(InsertClassActivity.this);
            }
        });
    }

}
