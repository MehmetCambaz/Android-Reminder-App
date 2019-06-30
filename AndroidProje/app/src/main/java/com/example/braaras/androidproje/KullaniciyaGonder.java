package com.example.braaras.androidproje;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class KullaniciyaGonder extends AppCompatActivity {
    VeritabaniIslemleri veritabaniIslemleri;

    ListView ListHatirlatma;
    ArrayList<String> listItem;

    public static String isim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullaniciya_gonder);

        veritabaniIslemleri = new VeritabaniIslemleri(this);
        ListHatirlatma = (ListView)findViewById(R.id.ListHatirlatma);

        listItem = new ArrayList<>();
        viewUser();


        ListHatirlatma.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isim = ListHatirlatma.getItemAtPosition(position).toString();

                Boolean hatirlatmagonder = veritabaniIslemleri.HatirlatmaGonder(isim);

                if(hatirlatmagonder == true){
                    Toast.makeText(getApplicationContext(), "Hatırlatmanız Kullanıcıya Gönderildi", Toast.LENGTH_SHORT).show();
                }
                else if(hatirlatmagonder == false){
                    Toast.makeText(getApplicationContext(), "Hatırlatma Kullanıcıya Gönderilemedi", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

    public void viewUser(){
        Cursor cursor = veritabaniIslemleri.getUser();

        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Kayıtlı Kullanıcı Yok", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(0));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listItem);
            ListHatirlatma.setAdapter(adapter);
        }
    }

}
