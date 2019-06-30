package com.example.braaras.androidproje;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class Bildirimler extends IntentService {
    private NotificationManager alarmNotificationManager;
    VeritabaniIslemleri veritabaniIslemleri = new VeritabaniIslemleri(this);
    public static String baslik = FragmentHatirlatma.baslik;
    //public static String icerik = veritabaniIslemleri.getIcerik(FragmentHatirlatma.baslik);


    public static final int NOTIFICATION_ID = 1;
    public Bildirimler() {
        super("bildirim");
    }
    public void onHandleIntent(Intent intent) {
        String a = "asd";
        String b = "qwe";

        a = FragmentHatirlatma.baslik;
        b = veritabaniIslemleri.getIcerik(a);

        sendNotification(a,b);
    }
    private void sendNotification(String baslik,String icerik) {
        alarmNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(baslik);
        builder.setContentText(icerik);
        builder.setSound(soundUri);
        builder.setAutoCancel((true));
        Intent intent = new Intent(this, Bildirimler.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, 0);
        builder.setContentIntent(pendingIntent);

        alarmNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}
