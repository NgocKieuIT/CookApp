package com.example.btl_cookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DBHelperMonAn extends SQLiteOpenHelper {
    public  static  final  String DBNAME="App.db";

    public DBHelperMonAn(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DBNAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        String queryStr="Create Table tblmonAn(ID integer PRIMARY KEY AUTOINCREMENT,tenMonAn TEXT," +
                "congThuc TEXT, anh BLOB, user TEXT)";
        db.execSQL(queryStr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists tblmonAn");
    }
    public Boolean insertData(MonAn monAn){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenMonAn",monAn.getTenMonAn());
        contentValues.put("congThuc",monAn.getCongThuc());
        contentValues.put("user",monAn.getUser());
        contentValues.put("anh",monAn.getImage());
        long ketqua=db.insert("tblmonAn", null,contentValues);
        if(ketqua<0)
            return  false;
        else
            return true;
    }

    public  boolean updateData(MonAn monAn){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenMonAn",monAn.getTenMonAn());
        contentValues.put("congThuc",monAn.getCongThuc());
        contentValues.put("user",monAn.getUser());
        contentValues.put("anh",monAn.getImage());
        long ketqua=db.update("tblmonAn",contentValues,"ID=?",new String[]{monAn.getID()+""});
        if(ketqua<0)
            return  false;
        else
            return true;
    }
    public boolean delete(MonAn monAn){
        SQLiteDatabase db= getWritableDatabase();
        long ketqua=db.delete("tblmonAn", "ID=?", new String[]{monAn.getID()+""});
        if(ketqua<0)
            return  false;
        else
            return true;
    }
}
