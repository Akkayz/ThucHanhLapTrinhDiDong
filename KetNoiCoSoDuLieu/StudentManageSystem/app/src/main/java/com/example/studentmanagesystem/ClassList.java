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

public class ClassList extends Activity {
    ListView lstClass;
    Button btnopenclass;
    ArrayList<Room> classlist = new ArrayList<Room>();
    MyAdapterClass adapter;
    SQLiteDatabase db;
    int posslected = -1;
    public static final int OPEN_CLASS = 113;
    public static final int EDIT_CLASS = 114;
    public static final int SAVE_CLASS = 115;

    private void getClassList() {
        try {
            classlist.add(new Room("Mã lớp", "Tên lớp", "Sỉ số"));
            db = openOrCreateDatabase(Login.DATABASE_NAME, MODE_PRIVATE, null);
            Cursor c = db.query("tbcLass", null, null, null, null, null, null);
            c.moveToFirst();
            while (!c.isAfterLast()) {
                classlist.add(new Room(c.getInt(0) + "", c.getString(1).toString(), c.getString(3).toString(), c.getInt(3) + ""));
                c.moveToNext();
            }
            adapter = new MyAdapterClass(this, R.layout.simple_list_item_1, classlist);
            lstClass.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Loi" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void confirmDelete() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Xác nhận để xoá lớp học");
        alertDialogBuilder.setIcon(R.drawable.dauchamhoi);
        alertDialogBuilder.setMessage("Bạn có chắc chắn muốn xoá lớp học này không?");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int argl) {
                db = openOrCreateDatabase(Login.DATABASE_NAME, MODE_PRIVATE, null);
                String id_class = classlist.get(posslected).getId_class();
                if (db.delete("tbClass", "id_class=?", new String[]{id_class}) != -1) {
                    classlist.remove(posslected);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplication(), "Xoá lớp học thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
        alertDialogBuilder.setNegativeButton("Không đồng ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mnuclass, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnueditclass:
                Room room = classlist.get(posslected);
                Bundle bundle = new Bundle();
                Intent intent = new Intent(ClassList.this, EditClass.class);
                bundle.putSerializable("room", room);
                intent.putExtra("data", bundle);
                startActivityForResult(intent, EDIT_CLASS);
                return true;
            case R.id.mnudeleteclass:
                confirmDelete();
                return true;
            case R.id.mnucloseclass:
                Notify.exit(this);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case ClassList.OPEN_CLASS:
                if (resultCode == ClassList.SAVE_CLASS{
                    Bundle bundle = data.getBundleExtra("data");
                    Room room = (Room) bundle.getSerializable("room");
                    classlist.add(room);
                    adapter.notifyDataSetChanged();
            }
            break;
                case ClassList.EDIT_CLASS:
                    if (resultCode == ClassList.SAVE_CLASS) {
                        Bundle bundle = data.getBundleExtra("data");
                        Room room = (Room) bundle.getSerializable("room");
                        classlist.set(posslected, room);
                        adapter.notifyDataSetChanged();
                    }
                    break;
        }
    }

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        lstClass = (ListView)findViewById(R.id.lstclass);
        btnopenclass = (Button)findViewById(R.id.btnOpenClass);
        getClassList();
        registerForContextMenu(lstClass);
        btnopenclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassList.this, InsertClassActivity.class);
                startActivityForResult(intent, ClassList.OPEN_CLASS);
            }
        });
        lstClass.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                posslected = position;
                return false;
            }
        });
    }
}



