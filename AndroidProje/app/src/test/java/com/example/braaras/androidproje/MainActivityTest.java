package com.example.braaras.androidproje;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {
    MainActivity mainActivity = new MainActivity();

    @Test
    public void kullaniciName() {
        String a = "mehmet@email.com";
        //String b = "mehmet";

        boolean sonuc = mainActivity.KullaniciName(a);
        //boolean sonuc1 = mainActivity.KullaniciName(b);
        boolean beklenenSonuc = true;

        assertEquals(beklenenSonuc, sonuc);
        //assertEquals(beklenenSonuc, sonuc1);
    }

    @Test
    public  void  kullaniciPassword(){
        String a = "asdasdasdad";
        //String b = "asd";

        boolean sonuc = mainActivity.KullaniciPassword(a);
        //boolean sonuc1 = mainActivity.KullaniciPassword(b);

        boolean beklenenSonuc = true;

        assertEquals(beklenenSonuc, sonuc);
        //assertEquals(beklenenSonuc, sonuc1);
    }
}