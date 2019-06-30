package com.example.braaras.androidproje;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent a=new Intent(context,Bildirimler.class);
        context.startService(a);

        context.startService(a);
        Toast.makeText(context, "Alarm", Toast.LENGTH_LONG).show();
    }
}
