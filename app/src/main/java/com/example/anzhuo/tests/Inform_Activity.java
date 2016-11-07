package com.example.anzhuo.tests;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.anzhuo.tests.info.HeBean;

import java.util.List;

/**
 * Created by anzhuo on 2016/9/14.
 */
public class Inform_Activity extends AppCompatActivity {
    private ToggleButton mSwitch;
    private TextView Text;
    private ShouD shouD = new ShouD();
    NotificationCompat.Builder mBuilder;
    int notifyId = 100;
    NotificationManager mNotificationManager;
    int h;
    String T;
    String K;
    Handler handlerNew = new Handler();
    Weather weather;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Data data = new Data(getApplicationContext());
        data.SetMap(handler);
        weather = new Weather();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inform_acrivity);
        mSwitch = (ToggleButton) findViewById(R.id.inform_tb_Switch);
        Text = (TextView) findViewById(R.id.inform_tv_Text);
        SharedPreferences preferences = getSharedPreferences("HJ", MODE_PRIVATE);
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = getSharedPreferences("HJ", MODE_PRIVATE).edit();
                if (mSwitch.isChecked()) {
                    Log.i("YYJ", "isCkecked:::::true" + "H:" + h);
                    Text.setText("已开启");
                    shouD.run();
                    editor.putInt("hj", 1).commit();
                    editor.commit();
                } else {
                    Log.i("YYJ", "isCkecked:::::false" + "H:" + h);
                    Text.setText("是否在通知栏显示天气信息?");
                    editor.putInt("hj", 2).commit();
                    editor.commit();
                    mNotificationManager.cancelAll();
                    handlerNew.removeCallbacks(shouD);
                }
            }
        });
        h = preferences.getInt("hj", h);
        if (h == 1) {
            Log.i("YYJ", "onCreate:::::true" + "H:" + h);
            mSwitch.setChecked(true);
            Text.setText("已开启");
            shouD.run();
        } else {
            Log.i("YYJ", "onCreate:::false" + "H:" + h);
            mSwitch.setChecked(false);
            mNotificationManager.cancelAll();
            Text.setText("是否在通知栏显示天气信息?");
            handlerNew.removeCallbacks(shouD);
        }
    }

    @Override
    protected void onDestroy() {
        SharedPreferences preferences = getSharedPreferences("HJ", MODE_PRIVATE);
        h = preferences.getInt("hj", h);
        if (h == 1) {
            handlerNew.postDelayed(shouD, (600 * 1000));
            Log.i("YYJ", "onDestroy:post" + "H:" + h);
        } else {
            mNotificationManager.cancelAll();
            handlerNew.removeCallbacks(shouD);
            Log.i("YYJ", "onDestroy:remove" + "H:" + h);
        }
        super.onDestroy();
    }


    public class ShouD implements Runnable {
        @Override
        public void run() {
            if (mSwitch.isChecked()) {
                sharedPreferences = getSharedPreferences("Infrom", MODE_PRIVATE);
                T = sharedPreferences.getString("T", T);
                K = sharedPreferences.getString("K", K);
                Log.i("LM","K:"+K);
                mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                handlerNew.postDelayed(shouD, (600 * 1000));
                mBuilder = new NotificationCompat.Builder(Inform_Activity.this);
                mBuilder.setSmallIcon(R.mipmap.ic_launcher).setTicker("标题")
                        .setContentText(T + "°"+"       "+K)
                        .setContentTitle("今日天气");
                Notification mNotification = mBuilder.build();
                mNotification.icon = R.mipmap.ic_launcher;
                mNotification.flags = Notification.FLAG_ONGOING_EVENT;
                mNotification.defaults = Notification.DEFAULT_VIBRATE;
                mNotification.tickerText = "今日天气";
                mNotification.when = System.currentTimeMillis();//设置通知时间
                mNotificationManager.notify(notifyId, mNotification);
            } else {
                mNotificationManager.cancelAll();
                handlerNew.removeCallbacks(shouD);
            }
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
                    sharedPreferences = getSharedPreferences("Infrom", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("T", list.get(0).getNow().getTmp().toString());
                    editor.putString("K", list.get(0).getNow().getCond().getTxt().toString());
                    editor.commit();
                    Log.i("LM", "PUT:" + list.get(0).getNow().getCond().getTxt().toString());
                    break;
            }
        }
    };

}
