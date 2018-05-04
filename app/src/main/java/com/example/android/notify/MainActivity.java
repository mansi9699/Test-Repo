package com.example.android.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.android.notify.notDataBase.MyDBHandler;
import com.example.android.notify.notDataBase.database;

public class MainActivity extends AppCompatActivity {


    MyDBHandler dbHandler;

    ImageButton imbtn;
    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHandler = new MyDBHandler(this, null, null, 1);

        imbtn = (ImageButton)findViewById(R.id.mansiButton);
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
    }

    public void buttonClicked(View view){

        if(imbtn.isEnabled()) {

            database data = new database("Add");
            dbHandler.addData(data);

            notification.setSmallIcon(R.drawable.ic_launcher_background);
            notification.setTicker("Coder's Diary");
            notification.setWhen(System.currentTimeMillis());
            notification.setContentTitle("Contest Live");
            notification.setContentText("4 May, 2018");

            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pendingIntent);

            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            try {

                nm.notify(uniqueID, notification.build());
                Log.e("notification", "ACCESSED");
            } catch (Exception e) {
                Log.e("notification", "error");
            }
        }
        else{
            dbHandler.deleteData("Add");
        }
    }
}
