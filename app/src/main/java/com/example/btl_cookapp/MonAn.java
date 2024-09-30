package com.example.btl_cookapp;

public class MonAn {
    int ID;
    byte[] image;
    String tenMonAn;
    String congThuc;
    String user;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getCongThuc() {
        return congThuc;
    }

    public void setCongThuc(String congThuc) {
        this.congThuc = congThuc;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public MonAn(int ID, byte[] image, String tenMonAn, String congThuc, String user) {
        this.ID = ID;
        this.image = image;
        this.tenMonAn = tenMonAn;
        this.congThuc = congThuc;
        this.user=user;
    }
    public MonAn( byte[] image, String tenMonAn, String congThuc, String user) {
        this.ID = ID;
        this.image = image;
        this.tenMonAn = tenMonAn;
        this.congThuc = congThuc;
        this.user=user;
    }

    public MonAn(byte[] image, String tenMonAn, String user) {
        this.image = image;
        this.tenMonAn = tenMonAn;
        this.user=user;
    }
}
