package com.example.sqlitedatabaseapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="form.db";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q="create table users (name text primary key , email text , password text )";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        onCreate(db);
    }
    public boolean insert_data(String name,String email,String password){
        SQLiteDatabase dbs=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("name",name);
        c.put("email",email);
        c.put("password",password);
        long r=dbs.insert("users",null,c);
        if(r==-1) return false;
        else{
            return true;
        }
    }
    public Cursor getData(){
        //using for show data from the database
        SQLiteDatabase dbs=this.getWritableDatabase();
        Cursor cursor=dbs.rawQuery("select * from users ",null);
        return cursor;
    }
    public boolean updateData(String name,String email,String password){
        //used to update data
        SQLiteDatabase dbs=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("name",name);
        c.put("password",password);
        Cursor cursor=dbs.rawQuery("select * from users where email=?",new String[]{email});
        if(cursor.getCount()>0){
            long r=dbs.update("users",c,"email=?",new String[]{email});
            if(r==-1) return false;
            else {
                return true;
            }
        }
        else{
            return false;
        }
    }
    public boolean deleteData(String email){
        SQLiteDatabase dbs=this.getWritableDatabase();
        Cursor cursor=dbs.rawQuery("select * from users where email=?",new String[]{email});
        if(cursor.getCount()>0){
            long r=dbs.delete("users","email=?",new String[]{email});
            if (r==-1) return false;
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }
}
