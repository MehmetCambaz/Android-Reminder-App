package com.example.braaras.androidproje;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    EditText isimEditText, sifreEditText;
    Button girisButton, kayitolButton;
    VeritabaniIslemleri veritabaniislemleri;

    public Boolean KullaniciName(String email){
        String end = "@email.com";

        if(email.endsWith(end)){
            return true;
        }else {
            return false;
        }
    }

    public Boolean KullaniciPassword(String password){
        if(password.length() <= 6){
            return false;
        }else {
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isimEditText = (EditText) findViewById(R.id.isimEditText);
        sifreEditText = (EditText) findViewById(R.id.sifreEditText);
        girisButton = (Button) findViewById(R.id.girisButton);
        kayitolButton = (Button) findViewById(R.id.kayitolButton);

        veritabaniislemleri = new VeritabaniIslemleri(this);

        kayitolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isim = isimEditText.getText().toString();
                String sifre = sifreEditText.getText().toString();

                if (isim.equals("") || sifre.equals("")) {
                    Toast.makeText(getApplicationContext(), "Lütfen Değer Giriniz", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean kayit = veritabaniislemleri.Kaydet(isim, sifre);
                    if (kayit == true) {
                        Toast.makeText(getApplicationContext(), "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        girisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isim = isimEditText.getText().toString();
                String sifre = sifreEditText.getText().toString();

                Boolean giris = veritabaniislemleri.Giris(isim, sifre);
                if (giris == true) {
                    Toast.makeText(getApplicationContext(), "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    VeritabaniIslemleri.kullanici = isim;
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Giriş Başarısız", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
