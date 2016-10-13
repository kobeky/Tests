package com.example.anzhuo.tests;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by YYJ on 2016/10/12.
 */
public class Switch1_Service extends Service {
    private Handler handler = new Handler();
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuidler;
    SwitchService switchService = new SwitchService();
    int TimingPut;
    int ServiceHour;
    int ServiceMinute;
    int notifyId = 100;
    int Total;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        handler.removeCallbacks(switchService);
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        TimingPut = intent.getIntExtra("TimingPut", 2000);
        if (TimingPut != 1) {
            ServiceHour = intent.getIntExtra("TimingHour", 5000);
            ServiceMinute = intent.getIntExtra("TimingMinute", 5000);
            Total = (ServiceHour * 3600000) + (ServiceMinute * 60000);
            handler.postDelayed(switchService, Total);//睡眠时间
            Toast.makeText(Switch1_Service.this, ServiceHour + "小时" + ServiceMinute + "分钟" + "总和" + Total, Toast.LENGTH_SHORT).show();
        } else {
            handler.removeCallbacks(switchService);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public class SwitchService implements Runnable {

        @Override
        public void run() {
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            handler.postDelayed(switchService, Total);
            mBuidler = new android.support.v7.app.NotificationCompat.Builder(Switch1_Service.this);
            mBuidler.setSmallIcon(R.mipmap.ic_launcher).setTicker("标题")
                    .setContentText("这是测试内容")
                    .setContentTitle("这是标题");
            Notification mNotification = mBuidler.build();
            mNotification.icon = R.mipmap.ic_launcher;
            mNotification.flags = Notification.FLAG_AUTO_CANCEL;
            mNotification.defaults = Notification.DEFAULT_VIBRATE;
            mNotification.tickerText = "通知标题";
            mNotification.when = System.currentTimeMillis();//设置通知时间
            mNotificationManager.notify(notifyId, mNotification);
        }
    }
}
