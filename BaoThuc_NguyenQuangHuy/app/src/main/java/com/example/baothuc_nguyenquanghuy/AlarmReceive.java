package com.example.baothuc_nguyenquanghuy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String chuoi_string = intent.getExtras().getString("extra");

        Intent intent1 = new Intent(context,Music.class);
        intent1.putExtra("extra",chuoi_string);
        context.startService(intent1);
    }
}
