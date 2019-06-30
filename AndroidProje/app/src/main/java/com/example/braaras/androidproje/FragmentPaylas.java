package com.example.braaras.androidproje;

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

public class FragmentPaylas extends Fragment {
    VeritabaniIslemleri veritabaniIslemleri;
    public static String baslik;

    public FragmentPaylas(){

    }
    ListView ListHatirlatma;
    ArrayList<String> listItem;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_paylas,container,false);

        veritabaniIslemleri = new VeritabaniIslemleri(getContext());
        ListHatirlatma = (ListView)v.findViewById(R.id.ListHatirlatma);

        listItem = new ArrayList<>();
        viewData();

        ListHatirlatma.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                baslik = ListHatirlatma.getItemAtPosition(position).toString();

                try {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    PackageInfo info=getActivity().getPackageManager().getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);

                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT,baslik + "\n" + veritabaniIslemleri.getIcerik(baslik));
                    startActivity(Intent.createChooser(waIntent, "Share with"));

                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(getContext(),"Whatsapp Yüklü Değil!",Toast.LENGTH_LONG).show();
                }
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