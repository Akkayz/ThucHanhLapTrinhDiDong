package com.example.baitap15;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner categorySpinner;
    private ListView productListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorySpinner = findViewById(R.id.category_spinner);
        productListView = findViewById(R.id.product_listview);


        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);


        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();
                // Tải danh sách sản phẩm theo danh mục đã chọn
                ArrayList<Product> products = loadProducts(selectedCategory);
                // Hiển thị danh sách sản phẩm vào ListView
                ProductAdapter adapter = new ProductAdapter(MainActivity.this, products);
                productListView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = (Product) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, selectedProduct.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private ArrayList<Product> loadProducts(String category) {
        ArrayList<Product> products = new ArrayList<>();


        if (category.equals(getString(R.string.category_phone))) {
            // Tải danh sách sản phẩm điện thoại
            products.add(new Product("Samsung Galaxy S21", R.drawable.iphone_5));
            products.add(new Product("iPhone 13 Pro", R.drawable.samsung_s_3));

        } else if (category.equals(getString(R.string.category_computer))) {
            // Tải danh sách sản phẩm máy tính
            products.add(new Product("MacBook Pro", R.drawable.macbook_pro));
            products.add(new Product("Dell XPS 15", R.drawable.dell_xps_15));

        } else if (category.equals(getString(R.string.category_watch))) {
            // Tải danh sách sản phẩm đồng hồ
            products.add(new Product("Apple Watch Series 7", R.drawable.apple_watch_series_7));
            products.add(new Product("Samsung Galaxy Watch 4", R.drawable.samsung_galaxy_watch_4));

        }

        return products;
    }

}
