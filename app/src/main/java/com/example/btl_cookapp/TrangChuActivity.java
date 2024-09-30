package com.example.btl_cookapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class TrangChuActivity extends AppCompatActivity {
    ArrayList<MonAn> mylist;
  MyAdapterMonAn myAdapterMonAn;
    private  static  final int DATABASE_VERSION=1;
    private  static  final String DATABASE_NAME="App.db";
    private  DBHelperMonAn dbHelp=
            new DBHelperMonAn(TrangChuActivity.this,DATABASE_NAME,null,DATABASE_VERSION);
  ListView listView;
  ImageButton btnprofile;
  ImageButton btnsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        anhxa();
        readData();
        sukien();

    }
    private  void anhxa(){
        listView=findViewById(R.id.lv);
        btnprofile=findViewById(R.id.btnProfile);
        btnsearch=findViewById(R.id.btnSearch);
        mylist=new ArrayList<>();
        myAdapterMonAn=new MyAdapterMonAn(TrangChuActivity.this,R.layout.layout_item,mylist);
        listView.setAdapter(myAdapterMonAn);
    }
    private  void sukien(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentct=new Intent(TrangChuActivity.this,ChiTietMonAnActivity.class);
                //Lấy dữ liệu
               String cttenmon=mylist.get(position).getTenMonAn().toString();
               String ctcongthuc=mylist.get(position).getCongThuc().toString();
               String ctuser=mylist.get(position).getUser().toString();
               byte[] ctanh= mylist.get(position).getImage();
                //Trước khi đưa vào intent chúng ta đóng gói dữ liệu vào bundle
                Bundle mybundle=new Bundle();
                //Đưa dũ liệu vào bundle
                mybundle.putString("tenmonan",cttenmon);
                mybundle.putString("congthuc",ctcongthuc);
                mybundle.putString("user",ctuser);
               mybundle.putByteArray("anh",ctanh);
                //Đưa bundle vào intent
                intentct.putExtra("package",mybundle);
                //Khởi động intent
                startActivity(intentct);

            }
        });
        btnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=getIntent();
                //Lấy dữ liệu
                String username=intent.getStringExtra("username");
                Intent intentprofile=new Intent(TrangChuActivity.this,ProfileActivity.class);
                intentprofile.putExtra("username",username);
                startActivity(intentprofile);
            }
        });
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentsearch=new Intent(TrangChuActivity.this,SearchActivity.class);
                startActivity(intentsearch);
            }
        });
    }
    private  void readData(){

        SQLiteDatabase sqLiteDatabase= dbHelp.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tblmonAn", null);
        //ĐỌc con trỏ
        cursor.moveToFirst();
        mylist.clear();
        while (!cursor.isAfterLast()){
            int ID=cursor.getInt(0);
            String ten= cursor.getString(1);
            String congthuc=cursor.getString(2);
            byte[] anh=cursor.getBlob(3);
            String user=cursor.getString(4);
            mylist.add(new MonAn(anh,ten,congthuc,user));
            cursor.moveToNext();
        }
        myAdapterMonAn.notifyDataSetChanged();
    }


}