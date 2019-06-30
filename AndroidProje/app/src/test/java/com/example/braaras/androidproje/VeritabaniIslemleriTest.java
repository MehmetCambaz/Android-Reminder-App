package com.example.braaras.androidproje;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

public class VeritabaniIslemleriTest extends TestCase {
    @Mock
    VeritabaniIslemleri mockVeritabanı;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHatirlatmaKaydet(){
        String baslik = "testBaslik";
        String icerik = "testIcerik";

        Mockito.when(mockVeritabanı.HatirlatmaKaydet(anyString(), anyString())).thenReturn(true);

        boolean a = mockVeritabanı.HatirlatmaKaydet(baslik, icerik);
        boolean b = true;

        assertEquals(a,b);
    }

    @Test
    public void testHatirlatmaGuncelle(){
        String baslik = "testBaslik";
        String icerik = "testIcerik";

        Mockito.when(mockVeritabanı.HatirlatmaGuncelle(anyString(), anyString())).thenReturn(true);

        boolean a = mockVeritabanı.HatirlatmaGuncelle(baslik, icerik);
        boolean b = true;

        assertEquals(a,b);
    }

    @Test
    public void testHatirlatmaSil(){
        String baslik = "testBaslik";

        Mockito.when(mockVeritabanı.HatirlatmaSil(anyString())).thenReturn(true);

        boolean a = mockVeritabanı.HatirlatmaSil(baslik);
        boolean b = true;

        assertEquals(a,b);
    }

    @Test
    public void testHatirlatmaGonder(){
        String isim = "testIsim";

        Mockito.when(mockVeritabanı.HatirlatmaGonder(anyString())).thenReturn(true);

        boolean a = mockVeritabanı.HatirlatmaGonder(isim);
        boolean b = true;

        assertEquals(a,b);
    }


}