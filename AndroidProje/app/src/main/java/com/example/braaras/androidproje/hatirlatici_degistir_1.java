package com.example.braaras.androidproje;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class hatirlatici_degistir_1 extends AppCompatActivity{
    public TextView Baslik, Icerik ;
    public EditText editText, editText2, Saat, Dakika;
    public Button Degistir,Sil,AlarmEkle;

    VeritabaniIslemleri veritabaniislemleri = new VeritabaniIslemleri(this);

    public static String icerik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hatirlatici_degistir_1);


        Degistir = (Button)findViewById(R.id.Degistir);
        AlarmEkle= (Button)findViewById(R.id.AlarmEkle);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        Saat = (EditText)findViewById(R.id.Saat);
        Dakika = (EditText)findViewById(R.id.Dakika);
        Sil = (Button)findViewById(R.id.Sil);

        editText.setText(FragmentHatirlatma.baslik);
        veritabaniislemleri.getIcerik(FragmentHatirlatma.baslik);
        editText2.setText(veritabaniislemleri.getIcerik(FragmentHatirlatma.baslik));

        Degistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                String baslik = editText.getText().toString();
                String icerik = editText2.getText().toString();

                if (baslik.equals("") || icerik.equals("")) {
                    Toast.makeText(getApplicationContext(), "Lütfen Değer Giriniz", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean hatirlatguncelle = veritabaniislemleri.HatirlatmaGuncelle(baslik, icerik);
                    if (hatirlatguncelle == true) {
                        Toast.makeText(getApplicationContext(), "Güncelleme Başarılı", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Güncelleme Hatası", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String baslik = editText.getText().toString();

                Boolean hatirlatmasil = veritabaniislemleri.HatirlatmaSil(baslik);
                if (hatirlatmasil == true) {
                    Toast.makeText(getApplicationContext(), "Silme Başarılı", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Silme Hatası", Toast.LENGTH_SHORT).show();
                }
            }
        });


        AlarmEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zamanHesapla(Saat,Dakika);
            }
        });

    }

    public void zamanHesapla(EditText saat,EditText dakika){
        int a = Integer.parseInt(saat.getText().toString());
        int b = Integer.parseInt(dakika.getText().toString());

        int alarmZamani = ((a * 60 * 60) + (b * 60));

        Calendar rightNow = Calendar.getInstance();
        int currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY);// return the hour in 24 hrs format (ranging from 0-23)
        int currentMinute = rightNow.get(Calendar.MINUTE);
        int currentSecond = rightNow.get(Calendar.SECOND);

        int simdikiZaman = ((currentHourIn24Format * 60 * 60) + (currentMinute * 60) + (currentSecond));

        int kalanZaman = alarmZamani - simdikiZaman;

        startAlert(kalanZaman);

    }

    public void startAlert(int kalanZaman){

        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (kalanZaman * 1000), pendingIntent);
        Toast.makeText(this, "Alarm " + kalanZaman + " saniye sonraya ayarlandı",Toast.LENGTH_LONG).show();

    }
}
