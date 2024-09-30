package com.example.btl_cookapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class BaiDangActivity extends AppCompatActivity {
    ArrayList<MonAn> mylist;
    MyAdapterMonAn myAdapterMonAn;
    private  static  final int DATABASE_VERSION=1;
    private  static  final String DATABASE_NAME="App.db";
    private  DBHelperMonAn dbHelp=
            new DBHelperMonAn(BaiDangActivity.this,DATABASE_NAME,null,DATABASE_VERSION);
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_dang);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        listView = findViewById(R.id.listviewbaidang);
        mylist = new ArrayList<>();
        myAdapterMonAn = new MyAdapterMonAn(BaiDangActivity.this, R.layout.layout_item, mylist);
        listView.setAdapter(myAdapterMonAn);
        readData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentct = new Intent(BaiDangActivity.this, ChiTietMonAnActivity.class);
                //Lấy dữ liệu
                String cttenmon = mylist.get(position).getTenMonAn().toString();
                String ctcongthuc = mylist.get(position).getCongThuc().toString();
                String ctuser = mylist.get(position).getUser().toString();
                byte[] ctanh = mylist.get(position).getImage();
                //Trước khi đưa vào intent chúng ta đóng gói dữ liệu vào bundle
                Bundle mybundle = new Bundle();
                //Đưa dũ liệu vào bundle
                mybundle.putString("tenmonan", cttenmon);
                mybundle.putString("congthuc", ctcongthuc);
                mybundle.putString("user", ctuser);
                mybundle.putByteArray("anh", ctanh);
                //Đưa bundle vào intent
                intentct.putExtra("package", mybundle);
                //Khởi động intent
                startActivity(intentct);

            }
        });
    }
        private  void readData(){
            Intent  intent=getIntent();
            //Lấy dữ liệu
            String name=intent.getStringExtra("username");
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

                if(user.equals(name)){
                    mylist.add(new MonAn(anh,ten,congthuc,user));
                }
                cursor.moveToNext();
            }
            myAdapterMonAn.notifyDataSetChanged();
        }
    }
