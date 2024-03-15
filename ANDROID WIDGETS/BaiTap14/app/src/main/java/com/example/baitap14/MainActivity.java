package com.example.baitap14;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView textView;
    private CustomAdapter adapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textView);


        itemList = new ArrayList<>();
        itemList.add(new Item("Hà Nội"));
        itemList.add(new Item("Huế"));
        itemList.add(new Item("Sapa"));
        itemList.add(new Item("Côn Sơn"));
        itemList.add(new Item("Vũng Tàu"));
        itemList.add(new Item("Đà Nẵng"));

        adapter = new CustomAdapter(this, itemList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item selectedItem = itemList.get(position);
                textView.setText(selectedItem.getContent());
            }
        });
    }
}
