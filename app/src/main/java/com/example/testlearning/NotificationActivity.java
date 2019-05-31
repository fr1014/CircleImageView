package com.example.testlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initBuilder();
    }

    private void initBuilder() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,mIntent,0);

        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background));
        builder.setAutoCancel(true);
        builder.setContentTitle("普通通知");
    }
}
