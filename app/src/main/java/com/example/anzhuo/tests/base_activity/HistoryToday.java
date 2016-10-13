package com.example.anzhuo.tests.base_activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.anzhuo.tests.R;
import com.example.anzhuo.tests.adapter.HistoryTodayAdapter;
import com.example.anzhuo.tests.info.HistoryBean;
import com.example.anzhuo.tests.info.HistoryTodayInfo;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.yalantis.phoenix.PullToRefreshView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by anzhuo on 2016/9/22.
 */
public class HistoryToday extends AppCompatActivity{

    OkHttpClient okHttpClient;
    String string;
    String historyurl;

    ImageView history_iv_back;
    ImageView history_iv_share;
    com.yalantis.phoenix.PullToRefreshView pullToRefreshView;
    ListView listView;
    HistoryTodayInfo info;
    List<HistoryTodayInfo>list=new ArrayList<>();
    HistoryTodayAdapter historyTodayAdapter;

    ProgressDialog progressDialog;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:

                    Gson gson=new Gson();
                    HistoryBean bean=gson.fromJson(string,HistoryBean.class);
                    for(int i=0;i<bean.getResult().toArray().length;i++){
                        info=new HistoryTodayInfo();
                        info.setTitle(bean.getResult().get(i).getTitle());
                        info.setContent(bean.getResult().get(i).getDes());
                            info.setPic(bean.getResult().get(i).getPic());

                       list.add(0,info);
                   }
                    historyTodayAdapter=new HistoryTodayAdapter(getApplicationContext(),list);
                    listView.setAdapter(historyTodayAdapter);
                    progressDialog.dismiss();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(HistoryToday.this);
        setContentView(R.layout.historytoday);

        history_iv_back= (ImageView) findViewById(R.id.history_iv_back);
        history_iv_share= (ImageView) findViewById(R.id.history_iv_share);
        pullToRefreshView= (PullToRefreshView) findViewById(R.id.history_pull_to_refresh);
        listView= (ListView) findViewById(R.id.history_lv);

        pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
pullToRefreshView.postDelayed(new Runnable() {
    @Override
    public void run() {
        pullToRefreshView.setRefreshing(false);
        setThread();
    }
},2000);
            }
        });

        history_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



okHttpClient=new OkHttpClient();
        progressDialog=ProgressDialog.show(HistoryToday.this,"请稍等..","努力加载中...",true);
       setThread();

    }
    private String getUrl(String s) throws IOException {
        Request request=new Request.Builder().url(s).build();
        Response response=okHttpClient.newCall(request).execute();
        string=response.body().string();
        return string;
    }
    private String historyUrl(String date){
        String[] strings = date.split("-");
        if (Integer.parseInt(strings[1]) < 10) {
            if (Integer.parseInt(strings[2]) < 10) {
                strings[1] = String.valueOf(Integer.parseInt(strings[1]));
                strings[2] = String.valueOf(Integer.parseInt(strings[2]));

                historyurl = "http://api.juheapi.com/japi/toh?v=1.0&month=" + strings[1] +"&day="+ strings[2]+"&key=258ffc142682195ca5979ba23496335e";
            } else {
                strings[1] = String.valueOf(Integer.parseInt(strings[1]));
                historyurl = "http://api.juheapi.com/japi/toh?v=1.0&month=" + strings[1] +"&day="+ strings[2]+"&key=258ffc142682195ca5979ba23496335e";
            }
        } else {
            if (Integer.parseInt(strings[2]) < 10) {
                strings[2] = String.valueOf(Integer.parseInt(strings[2]));
                historyurl = "http://api.juheapi.com/japi/toh?v=1.0&month=" + strings[1] +"&day="+ strings[2]+"&key=258ffc142682195ca5979ba23496335e";
            } else {
                historyurl = "http://api.juheapi.com/japi/toh?v=1.0&month=" + strings[1] +"&day="+ strings[2]+"&key=258ffc142682195ca5979ba23496335e";
            }


        }
        return  historyurl;
    }
    public void setThread(){
        Intent intent=getIntent();
        final String date=intent.getStringExtra("da");
        new Thread(){
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                try {
                    if(date==null){

                        getUrl(historyUrl(simpleDateFormat.format(new Date())));

                    } else {
                        getUrl(historyUrl(date));
                    }
                    handler.sendEmptyMessage(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
