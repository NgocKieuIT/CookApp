package com.example.btl_cookapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelperlogin  extends SQLiteOpenHelper {
    public  static  final  String DBNAME="Login.db";

    public DBHelperlogin(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("Create Table users(username TEXT primary key," +
               "password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop Table if exists users");

    }
    public Boolean insertData(String username, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password", password);
        long ketqua=db.insert("users", null,contentValues);
        if(ketqua<0)
            return  false;
        else
            return true;
    }
    public  boolean updateData(String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("password", password);
        long ketqua=db.update("users",contentValues,"username=?",new String[]{username+""});
        if(ketqua<0)
            return  false;
        else
            return true;
    }
    public boolean delete(String username, String password){
        SQLiteDatabase db= getWritableDatabase();
        long ketqua=db.delete("users" ,"username=?", new String[]{username+""});
        if(ketqua<0)
            return  false;
        else
            return true;
    }
    public Boolean checkusername( String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor= db.rawQuery("Select * from users Where username = ?", new  String[]{username});
        if(cursor.getCount()>0)
            return  true;
        else
            return false;
    }
    public boolean checkusernamepassword(String username, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("Select * from users Where username=? and password=?", new String[]{username,password});
        if(cursor.getCount()>0)
            return  true;
        else
            return false;
    }


}
