package com.example.studentmanagesystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentListActivity extends Activity {
    Button btnOpenStudent;
    ListView lstStudent;
    SQLiteDatabase db;
    ArrayList<Student> studentList = new ArrayList<Student>();
    MyAdapterClass adapter;
    int posselected = -1;
    public static final int OPEN_STUDENT = 113;
    public static final int EDIT_STUDENT = 113;
    public static final int SAVE_STUDENT = 113;

    private void getStudenList() {
        db = openOrCreateDatabase(Login.DATABASE_NAME, MODE_PRIVATE, null);
        Cursor c = db.rawQuery("select tblclass.id.class, tblclass.name_class, tblstudent.id.student," + "tblstudent.code_student, tblstudent.name_student, tblstudent.gender_student," + "tblstudent.birthday_student, tblstudent.address_student from tblclass," + "tblstudent where tblclass.id_class= tblstudent.id_class ", null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            studentList.add(new Student(c.getString(0).toString(), c.getString(1).toString(),
                    c.getString(2).toString(), c.getString(3).toString(), c.getString(4).toString(),

                    c.getString(5).toString(), c.getString(6).toString(), c.getString(7).toString()));
            c.moveToNext();
        }
        adapter = new MyAdapterStudent(this, android.R.layout.simple_list_item_1, studentList);
        lslStudent.setAdapter(adapter);
    }

    public void confirmDelete() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Xác nhận để xóa sinh viên..!!!");
        alertDialogBuilder.setMessage("Ban có chắc xóa sinh viên?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //Đông Activity hiện tại
                db = openOrCreateDatabase(Login.DATABASE_NAME, MODE_PRIVATE, null);
                String id_student = studentList.get(posselected).getId_class();
                if (db.delete("tblstudent", "id_student=?", new String[]{id_student}) != 1) {
                    studentList.remove(posselected);// Xoa lop hoc ra khoi danh sach
                    adapter.notifyDataSetChanged(); // cap nhat lai adapter
                    Toast.makeText(getApplication(), "Xóa sinh viên thành công!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialogBuilder.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialogBuilder.create();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnustudent, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.mnueditstudent:
                Student student = new Student();
                Bundle bundle = new Bundle();
                Intent intent = new Intent(StudentListActivity.this, EditStudentActivity.class);
                student = studentList.get(posselected);
                bundle.putSerializable("student", student);
                intent.putExtra("data", bundle);
                startActivityForResult(intent, StudentListActivity.EDIT_STUDENT);
                return true;
            case R.id.mnudeletestudent:
                confirmDelete();
                return true;
            case R.id.mnuclosestudent:
                Notify.exit(this);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
// TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case StudentListActivity.OPEN_STUDENT:
                if (StudentListActivity.SAVE_STUDENT == resultCode) {
                    Bundle bundle = data.getBundleExtra("data");
                    Student student = (Student) bundle.getSerializable("student");
                    studentList.add(student);
                    adapter.notifyDataSetChanged();
                }
                break;
            case StudentListActivity.EDIT_STUDENT:
                Bundle bundle = data.getBundleExtra("data");
                Student student = (Student) bundle.getSerializable("student");
                studentList.set(posselected, student);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        btnOpenStudent = (Button) findViewById(R.id.btnOpenStudent);
        lstStudent = (ListView) findViewById(R.id.lststudent);
        getStudenList();
        registerForContextMenu(lstStudent);
        btnOpenStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentListActivity.this, InsertStudentActivity.class);
                startActivityForResult(intent, StudentListActivity.OPEN_STUDENT);

            }
        });
        lstStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                posselected = position;
                return false;
            }
        });
    }
}
