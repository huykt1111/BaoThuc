package com.example.baothuc_nguyenquanghuy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnHenGio,btnDungLai;
    TextView tvHienThi;
    TimePicker timePicker;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        OnClickListenerBtn();
    }

    private void OnClickListenerBtn() {
        Intent intent = new Intent(MainActivity.this,AlarmReceive.class);
        btnHenGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());
                int gio = timePicker.getCurrentHour();
                int phut = timePicker.getCurrentMinute();

                String string_gio = String.valueOf(gio);
                String string_phut = String.valueOf(phut);

                if(gio > 12){
                    string_gio = String.valueOf(gio -12);
                }
                if(phut < 10){
                    string_phut = "0" + String.valueOf(phut);
                }
                intent.putExtra("extra","on");
                pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT
                );
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                tvHienThi.setText("Giờ bạn đặt là: "+string_gio+" : "+string_phut);

            }
        });

        btnDungLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvHienThi.setText("Dừng lại");
                alarmManager.cancel(pendingIntent);
                intent.putExtra("extra","off");
                sendBroadcast(intent);
            }
        });
    }

    @SuppressLint("ServiceCast")
    private void AnhXa() {
        btnHenGio = findViewById(R.id.btndatgio);
        btnDungLai = findViewById(R.id.btndunglai);
        tvHienThi = findViewById(R.id.tvhienthi);
        timePicker = findViewById(R.id.timePicker);
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }
}