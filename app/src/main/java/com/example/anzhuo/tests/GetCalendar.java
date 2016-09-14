package com.example.anzhuo.tests;

import android.os.Handler;
import android.os.Message;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by anzhuo on 2016/9/13.
 */
public  class GetCalendar {
    StringBuffer stringBuffer;
    private static final int MSG=1;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG:
                    try {
                        JSONObject jsonObject=new JSONObject(stringBuffer.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };



    public void network(String url){
        try {
            URL url1=new URL(url);
            HttpURLConnection connection= (HttpURLConnection) url1.openConnection();
            InputStream inputStream=new BufferedInputStream(connection.getInputStream());
            stringBuffer=new StringBuffer();
            byte[]bytes=new byte[5*1024];
            int len;
            while ((len=inputStream.read(bytes))!=-1){
                stringBuffer.append(new String(bytes,0,len));
            }
handler.sendEmptyMessage(MSG);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
