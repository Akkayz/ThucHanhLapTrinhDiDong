package com.example.baitap15;

public class Product {

    private String name;
    private int imageResource;

    public Product(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }
}

