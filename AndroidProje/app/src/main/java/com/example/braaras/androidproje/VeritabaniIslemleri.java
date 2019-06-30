package com.example.braaras.androidproje;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class VeritabaniIslemleri extends SQLiteOpenHelper {
    static String kullanici;

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }


    public VeritabaniIslemleri(Context context) {

        super(context, "Kaydet.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table Kullanici(isim text PRIMARY KEY,sifre text)");
        db.execSQL("create table Hatirlatmalar(kullanici text,baslik text,icerik text,FOREIGN KEY (kullanici) REFERENCES Kullanici(isim))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Kullanici");
        db.execSQL("drop table if exists Hatirlatmalar");

        onCreate(db);
    }


    public Boolean Kaydet(String isim, String sifre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isim", isim);
        contentValues.put("sifre", sifre);
        long kaydet = db.insert("Kullanici",null, contentValues);
        if (kaydet == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean Giris(String isim, String sifre) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Kullanici where isim=? and sifre=?", new String[]{isim, sifre});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean HatirlatmaKaydet(String baslik,String icerik){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();;
        contentValues.put("kullanici",kullanici);
        contentValues.put("baslik",baslik);
        contentValues.put("icerik",icerik);
        long hatirlatmakaydet = db.insert("Hatirlatmalar",null,contentValues);
        if(hatirlatmakaydet == -1){
            return false;
        }else{
            return true;
        }
    }


    public  Boolean HatirlatmaGuncelle(String baslik,String icerik){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();;
        contentValues.put("kullanici",kullanici);
        contentValues.put("baslik",baslik);
        contentValues.put("icerik",icerik);
        long hatirlatmaguncelle = db.update("Hatirlatmalar",contentValues,"baslik=?",new String[]{FragmentHatirlatma.baslik});
        if(hatirlatmaguncelle == -1){
            return false;
        }else{
            return true;
        }

    }

    public Boolean HatirlatmaSil(String baslik) {
        SQLiteDatabase db = this.getWritableDatabase();
        long hatirlatmasil = db.delete("Hatirlatmalar","baslik=?",new String[]{baslik});
        if(hatirlatmasil == -1)
            return false;
        else
            return true;
    }

    public Boolean HatirlatmaGonder(String isim){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();;
        contentValues.put("kullanici",isim);
        contentValues.put("baslik",FragmentGonder.baslik);
        contentValues.put("icerik",getIcerik(FragmentGonder.baslik));
        long hatirlatmagonder = db.insert("Hatirlatmalar",null,contentValues);
        if(hatirlatmagonder == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getHatirlatma() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from Hatirlatmalar where kullanici= '"+kullanici+"' ";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor getUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from Kullanici";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }



    public String getIcerik(String baslik)
    {
        String icerik = "";
        SQLiteDatabase db=this.getReadableDatabase();
        String query = "select icerik from Hatirlatmalar where baslik='"+baslik+"'";
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()){
        icerik = cursor.getString(0);
        }

        return icerik;
    }


}