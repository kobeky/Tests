package com.example.anzhuo.tests;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.anzhuo.tests.info.HeBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by anzhuo on 2016/9/30.
 */
public class Weather {
    OkHttpClient client;
    HeBean dataInfo;
    public void setWeather(final Handler handler, final String city){
        new Thread(){
            @Override
            public void run() {
                try {
                    client=new OkHttpClient();
                    Request request = new Request.Builder().url("https://api.heweather.com/x3/weather?city=" +city+ "&key=2eb12090e45b4827a2a18f180be6f8da").build();
                    Response response=client.newCall(request).execute();
                    Gson gson=new Gson();
                    dataInfo=gson.fromJson(response.body().string(),HeBean.class);
                    handler.sendMessage(Message.obtain(handler,1,dataInfo.getHeWeather()));
                    handler.sendMessage(Message.obtain(handler,2,dataInfo.getHeWeather().get(0).getDaily_forecast()));
                }catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();
    }
}
