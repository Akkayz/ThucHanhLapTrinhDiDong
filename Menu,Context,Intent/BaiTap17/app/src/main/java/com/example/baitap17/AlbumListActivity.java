package com.example.baitap17;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class AlbumListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String maAlbum = intent.getStringExtra("ma_album");
        String tenAlbum = intent.getStringExtra("ten_album");

        // Tạo một danh sách album mới từ dữ liệu nhận được
        ArrayList<Album> albums = new ArrayList<>();
        albums.add(new Album(maAlbum, tenAlbum));

        // Tạo một custom adapter để hiển thị danh sách album
        AlbumAdapter adapter = new AlbumAdapter(this, albums);

        // Tìm đến ListView trong layout và gắn adapter vào ListView
        ListView listViewAlbum = findViewById(R.id.listViewAlbum);
        listViewAlbum.setAdapter(adapter);
    }
}
