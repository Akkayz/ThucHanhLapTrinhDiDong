package com.example.baitap14;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Item> {
    private Context mContext;
    private List<Item> itemList;

    public CustomAdapter(Context context, List<Item> list) {
        super(context, 0, list);
        mContext = context;
        itemList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        }

        Item currentItem = itemList.get(position);

        ImageView imageView = listItem.findViewById(R.id.imageView);
        TextView textView = listItem.findViewById(R.id.textView);


        if (currentItem.getContent().length() <= 3) {
            imageView.setImageResource(R.drawable.ngoisao);
        } else {
            imageView.setImageResource(R.drawable.traidat);
        }
        textView.setText(currentItem.getContent());

        return listItem;
    }
}


