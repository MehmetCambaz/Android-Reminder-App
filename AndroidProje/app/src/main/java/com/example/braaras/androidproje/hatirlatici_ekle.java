package com.example.braaras.androidproje;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class hatirlatici_ekle extends AppCompatActivity {
    TextView Baslik, Icerik ;
    EditText editText, editText2;
    Button Kaydet;

    VeritabaniIslemleri veritabaniislemleri;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hatirlatici_ekle);


        Kaydet = (Button)findViewById(R.id.Degistir);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);

        veritabaniislemleri = new VeritabaniIslemleri(this);

        Kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                String baslik = editText.getText().toString();
                String icerik = editText2.getText().toString();

                if (baslik.equals("") || icerik.equals("")) {
                    Toast.makeText(getApplicationContext(), "Lütfen Değer Giriniz", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean hatirlatmakayit = veritabaniislemleri.HatirlatmaKaydet(baslik, icerik);
                    if (hatirlatmakayit == true) {
                        Toast.makeText(getApplicationContext(), "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Kayıt Hatası", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
