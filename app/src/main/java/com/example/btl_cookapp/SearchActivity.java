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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {
ListView listViewTK;
ArrayList<MonAn> mylist;
MyAdapterMonAn myAdapterMonAn;
    private  static  final int DATABASE_VERSION=1;
    private  static  final String DATABASE_NAME="App.db";
    private  DBHelperMonAn dbHelp=
            new DBHelperMonAn(SearchActivity.this,DATABASE_NAME,null,DATABASE_VERSION);
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Tìm Kiếm");

        listViewTK = findViewById(R.id.listviewtimkiem);
        mylist = new ArrayList<>();
        myAdapterMonAn = new MyAdapterMonAn(SearchActivity.this, R.layout.layout_item, mylist);
        listViewTK.setAdapter(myAdapterMonAn);
        readData();
        listViewTK.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentct = new Intent(SearchActivity.this, ChiTietMonAnActivity.class);
                //Lấy dữ liệu
                String cttenmon = mylist.get(position).getTenMonAn().toString();
                String ctcongthuc = mylist.get(position).getCongThuc().toString();
                byte[] ctanh = mylist.get(position).getImage();
                String ctuser=mylist.get(position).getUser().toString();
                //Trước khi đưa vào intent chúng ta đóng gói dữ liệu vào bundle
                Bundle mybundle = new Bundle();
                //Đưa dũ liệu vào bundle
                mybundle.putString("tenmonan", cttenmon);
                mybundle.putString("congthuc", ctcongthuc);
                mybundle.putByteArray("anh", ctanh);
                mybundle.putString("user",ctuser);
                //Đưa bundle vào intent
                intentct.putExtra("package", mybundle);
                //Khởi động intent
                startActivity(intentct);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        //Căn lề cho searchview settting các thuộc tính của searchview
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //ánh xa về main_menu đã tạo
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //myAdapterMonAn.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapterMonAn.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if(!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}