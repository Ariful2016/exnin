package com.es.netschool24.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.es.netschool24.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationDemoActivity extends AppCompatActivity {

    Button send_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_demo);

        FirebaseMessaging.getInstance().subscribeToTopic("sports").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(NotificationDemoActivity.this, "subscrived to sports", Toast.LENGTH_SHORT).show();
                }
            }
        });

        send_btn = findViewById(R.id.send_btn);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendNotification("this title","this is body");
            }
        });
    }

    private void sendNotification(String title, String body) {

        // complete build notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationDemoActivity.this,"id");
        builder.setAutoCancel(true);
        builder.setContentText(body);
        builder.setContentTitle(title);
        builder.setSmallIcon(R.drawable.ic_notification);

        // complete intent

        Intent intent = new Intent(getApplicationContext(),NotificationDemoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(NotificationDemoActivity.this,101,intent,PendingIntent.FLAG_UPDATE_CURRENT);
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