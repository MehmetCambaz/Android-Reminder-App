package com.example.braaras.androidproje;

import android.app.LauncherActivity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentGonder extends Fragment {
    VeritabaniIslemleri veritabaniIslemleri;
    public static String baslik;

    public FragmentGonder(){

    }
    ListView ListHatirlatma;
    ArrayList<String> listItem;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_gonder,container,false);

        veritabaniIslemleri = new VeritabaniIslemleri(getContext());
        ListHatirlatma = (ListView)v.findViewById(R.id.ListHatirlatma);

        listItem = new ArrayList<>();
        viewData();

        ListHatirlatma.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                baslik = ListHatirlatma.getItemAtPosition(position).toString();

                Intent intent = new Intent(getActivity(),KullaniciyaGonder.class);
                startActivity(intent);


            }
        });

        return v;

    }

    public void viewData(){
        Cursor cursor = veritabaniIslemleri.getHatirlatma();

        if(cursor.getCount() == 0){
            Toast.makeText(getContext(), "Kayıtlı Hatırlatıcı Yok", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,listItem);
            ListHatirlatma.setAdapter(adapter);
        }
    }

}