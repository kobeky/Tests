package com.example.anzhuo.tests.base_activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.anzhuo.tests.DressIndexInfo;
import com.example.anzhuo.tests.R;
import com.google.gson.Gson;


import java.io.IOException;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by anzhuo on 2016/9/18.
 */
public class DressIndex extends AppCompatActivity {
    ImageView dress_index_iv_back;
    ImageView dress_index_iv_share;
    TextView dress_index_tv_dressindex;
    TextView dress_index_city;
    TextView dress_index_temp;
    TextView dress_index_suggestion;

    AMapLocationClient mLocationClient = null;
    AMapLocationClientOption mLocationOption = null;
    private static final int MSG = 1;
    String string = "";
    OkHttpClient okHttpClient;
String city;
ProgressDialog progressDialog;
    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG:
                    Gson gson = new Gson();
                    DressIndexInfo dressinfo = gson.fromJson(string, DressIndexInfo.class);
                    dress_index_tv_dressindex.setText(dressinfo.getHeWeather().get(0).getSuggestion().getComf().getBrf());
                   dress_index_temp.setText(dressinfo.getHeWeather().get(0).getDaily_forecast().get(0).getTmp().getMin()+"～"+
                   dressinfo.getHeWeather().get(0).getDaily_forecast().get(0).getTmp().getMax()+"℃");
                    dress_index_suggestion.setText(dressinfo.getHeWeather().get(0).getSuggestion().getComf().getTxt());
                    progressDialog.dismiss();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dress_index);
        okHttpClient = new OkHttpClient();

        progressDialog= ProgressDialog.show(DressIndex.this,"请稍等..","努力加载中...",true);

        dress_index_tv_dressindex = (TextView) findViewById(R.id.dress_index_dressindex);
        dress_index_iv_back = (ImageView) findViewById(R.id.dress_index_iv_back);
        dress_index_city= (TextView) findViewById(R.id.dress_index_city);
        dress_index_temp= (TextView) findViewById(R.id.dress_index_temp);
        dress_index_suggestion= (TextView) findViewById(R.id.dress_index_suggestion);


        dress_index_iv_share= (ImageView) findViewById(R.id.calendar_iv_share);




        AMapLocationListener mapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        city = aMapLocation.getCity();
                       dress_index_city.setText(city);
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    String s1 = city.substring(0, city.length() - 1);
                                    getUrl("https://api.heweather.com/x3/weather?city=" + s1 + "&key=2eb12090e45b4827a2a18f180be6f8da");
                                    handler1.sendEmptyMessage(MSG);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.i("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        };
        mLocationClient = new AMapLocationClient(getApplicationContext());
        mLocationOption = new AMapLocationClientOption();
        mLocationClient.setLocationListener(mapLocationListener);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setWifiActiveScan(false);
        mLocationOption.setMockEnable(false);
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();

        dress_index_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    private String getUrl(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        string = response.body().string();
        return string;
    }

    //截屏方法，去掉状态栏


}
