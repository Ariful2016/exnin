package com.es.netschool24.Services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.es.netschool24.Activities.NotificationDemoActivity;
import com.es.netschool24.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyService extends FirebaseMessagingService {
    public MyService() {
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();

        sendNotification(title,body);

        // don't forget intent filter in service into manifest
        /* <intent-filter>
                <action android:name="com.google.firebase.MESSAGING_EVENT"/>
            </intent-filter>
*/
    }
    private void sendNotification(String title, String body) {

        // complete build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),"id");
        builder.setAutoCancel(true);
        builder.setContentText(body);
        builder.setContentTitle(title);
        builder.setSmallIcon(R.drawable.ic_notification);

        // complete intent

        Intent intent = new Intent(getApplicationContext(),NotificationDemoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),101,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        // Manage Notification based APi version level

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("id","name",NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
            manager.notify(120,builder.build());
        }else {
            manager.notify(120,builder.build());
        }


        // 4 fundamental component of android is
        // 1. activity
        // 2. service
        // 3. broadcast receiver
        // 4. content provider


    }
}