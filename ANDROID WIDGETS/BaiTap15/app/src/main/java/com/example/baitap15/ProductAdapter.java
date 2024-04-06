package com.example.baitap15;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, ArrayList<Product> products) {
        super(context, 0, products);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_product, parent, false);
        }

        Product currentProduct = getItem(position);

        ImageView productImageView = listItemView.findViewById(R.id.product_image);
        if (currentProduct != null) {
            productImageView.setImageResource(currentProduct.getImageResource());
        }

        TextView productNameTextView = listItemView.findViewById(R.id.product_name);
        if (currentProduct != null) {
            productNameTextView.setText(currentProduct.getName());
        }

        return listItemView;
    }
}

