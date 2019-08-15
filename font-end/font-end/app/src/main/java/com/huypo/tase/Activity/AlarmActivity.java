package com.huypo.tase.Activity;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import com.huypo.tase.R;
import com.huypo.tase.Utils.Broadcast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AlarmActivity extends AppCompatActivity{
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";
    Button btnDate;
    TextView txtDate;
    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btnDate = findViewById(R.id.btnaddDate);
        txtDate = findViewById(R.id.tvDate);
        Date date = myCalendar.getTime();
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  scheduleNotification(getNotification(btnDate.getText().toString()), date.getTime());
            }
        });

    }


    private void scheduleNotification(Notification notification, long delay) {
        Intent notificationIntent = new Intent(AlarmActivity.this, Broadcast.class);
        notificationIntent.putExtra(com.huypo.tase.Utils.Broadcast.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(com.huypo.tase.Utils.Broadcast.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        if (alarmManager != null) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, delay,
                    AlarmManager.INTERVAL_DAY, pendingIntent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, delay, pendingIntent);
            }
        }
    }

    private Notification getNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, default_notification_channel_id);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_notifications_black_50dp);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        // Add as notification

//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel edmtChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, default_notification_channel_id, NotificationManager.IMPORTANCE_DEFAULT);
//            edmtChannel.enableLights(true);
//            edmtChannel.enableVibration(true);
//            edmtChannel.setLightColor(Color.GREEN);
//            edmtChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
//
//            manager.createNotificationChannel(edmtChannel);
//        }
//            manager.notify(0, builder.build());
        return builder.build();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    public void setDate(View view) {
        new DatePickerDialog(
                AlarmActivity.this, date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        Date date = myCalendar.getTime();
        btnDate.setText(sdf.format(date));
    }
}


