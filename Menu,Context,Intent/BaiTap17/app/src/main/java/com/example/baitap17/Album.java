package com.example.baitap17;

public class Album {
    private String maAlbum;
    private String tenAlbum;

    public Album(String maAlbum, String tenAlbum) {
        this.maAlbum = maAlbum;
        this.tenAlbum = tenAlbum;
    }

    public String getMaAlbum() {
        return maAlbum;
    }

    public void setMaAlbum(String maAlbum) {
        this.maAlbum = maAlbum;
    }

    public String getTenAlbum() {
        return tenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
        this.tenAlbum = tenAlbum;
    }
}
