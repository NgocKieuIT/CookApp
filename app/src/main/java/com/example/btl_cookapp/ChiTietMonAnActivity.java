package com.example.btl_cookapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ChiTietMonAnActivity extends AppCompatActivity {
TextView tenmonan,congthuc,user;
ImageView anh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon_an);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        tenmonan=findViewById(R.id.cttenmonan);
        congthuc=findViewById(R.id.ctcongthuc);
        user=findViewById(R.id.user);
        anh=findViewById(R.id.ctanh);
        //Nhận Intent
        Intent intentct=getIntent();
        //Đưa bundle ra khỏi intent
        Bundle mybundle=intentct.getBundleExtra("package");
        //Lấy dữ liệu khỏi bundle
        String ten=mybundle.getString("tenmonan");
         String ct=mybundle.getString("congthuc");
        String nguoitao=mybundle.getString("user");
        byte[] hinh=mybundle.getByteArray("anh");
        tenmonan.setText(ten);
         congthuc.setText(ct);
         user.setText(nguoitao);
        Bitmap bitmap= BitmapFactory.decodeByteArray(hinh,0,hinh.length);
        anh.setImageBitmap(bitmap);


    }
}