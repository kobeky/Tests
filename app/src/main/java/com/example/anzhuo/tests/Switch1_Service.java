package com.example.anzhuo.tests;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.anzhuo.tests.info.HeBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by YYJ on 2016/10/12.
 */
public class Switch1_Service extends Service {
    private Handler handlerNew = new Handler();
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuidler;
    Switch1Service switch1Service = new Switch1Service();
    int TimingPut;
    int ServiceHour;
    int ServiceMinute;
    int notifyId = 100;
    Date date;
    String Time;
    int mHour;
    int mMinute;
    int Current;
    int Choose;
    int Total;
    String T;
    String K;
    Weather weather;
    SharedPreferences sharedPreferences;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        handlerNew.removeCallbacks(switch1Service);
        super.onDestroy();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Data data = new Data(getApplicationContext());
        data.SetMap(handler);
        weather = new Weather();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH-mm-ss");
        Time = simpleDateFormat.format(date);
        String s[] = Time.split("-");
        mHour = Integer.valueOf(s[0]);
        mMinute = Integer.valueOf(s[1]);
        Current = (mHour * 3600000) + (mMinute * 60000);
        TimingPut = intent.getIntExtra("TimingPut", 2000);
        ServiceHour = intent.getIntExtra("TimingHour", 5000);
        ServiceMinute = intent.getIntExtra("TimingMinute", 5000);
        Choose = (ServiceHour * 3600000) + (ServiceMinute * 60000);
        if (Choose >= Current) {
            Total = Choose - Current;
            if (TimingPut != 1) {
                Log.i("LM", "Current:" + Current + "Choose:" + Choose);
                handlerNew.postDelayed(switch1Service, Total);//睡眠时间
            } else {
                handlerNew.removeCallbacks(switch1Service);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public class Switch1Service implements Runnable {

        @Override
        public void run() {
            sharedPreferences = getSharedPreferences("Switch1_Service", MODE_PRIVATE);
            T = sharedPreferences.getString("T", T);
            K = sharedPreferences.getString("K", K);
            Log.i("LM", "GET:" + T);
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            handlerNew.postDelayed(switch1Service, Total);
            mBuidler = new android.support.v7.app.NotificationCompat.Builder(Switch1_Service.this);
            mBuidler.setSmallIcon(R.mipmap.images)
                    .setContentText(T + "°" + "       " + K)
                    .setContentTitle("今日天气");
            Notification mNotification = mBuidler.build();
            mNotification.icon = R.mipmap.ic_launcher;
            mNotification.flags = Notification.FLAG_AUTO_CANCEL;
            mNotification.defaults = Notification.DEFAULT_VIBRATE;
            mNotification.tickerText = "有新天气信息";
            mNotification.when = System.currentTimeMillis();//设置通知时间
            mNotificationManager.notify(notifyId, mNotification);
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    weather.setWeather(handler, msg.obj.toString().substring(0, msg.obj.toString().length() - 1));
                    break;
                case 1:
                    List<HeBean.HeWeather> list = (List<HeBean.HeWeather>) msg.obj;
                    sharedPreferences = getSharedPreferences("Switch1_Service", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("T", list.get(0).getNow().getTmp().toString());
                    editor.putString("K", list.get(0).getNow().getCond().getTxt().toString());
                    editor.commit();
                    Log.i("LM", "PUT:" + editor.toString());
                    break;
            }

        }
    };
}
