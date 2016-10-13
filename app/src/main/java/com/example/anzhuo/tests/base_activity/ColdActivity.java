package com.example.anzhuo.tests.base_activity;

import android.app.ProgressDialog;
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
 * Created by anzhuo on 2016/9/20.
 */
public class ColdActivity extends AppCompatActivity{
    ImageView cold_iv_back;
    ImageView cold_iv_share;
    TextView cold_cold;
    TextView cold_city;
    TextView cold_temp;
    TextView cold_suggestion;

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
                    cold_cold.setText(dressinfo.getHeWeather().get(0).getSuggestion().getFlu().getBrf());
                    cold_temp.setText(dressinfo.getHeWeather().get(0).getDaily_forecast().get(0).getTmp().getMin()+"～"+
                            dressinfo.getHeWeather().get(0).getDaily_forecast().get(0).getTmp().getMax()+"℃");
                    cold_suggestion.setText(dressinfo.getHeWeather().get(0).getSuggestion().getFlu().getTxt());
                    progressDialog.dismiss();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cold);

        cold_iv_back= (ImageView) findViewById(R.id.cold_iv_back);
        cold_iv_share= (ImageView) findViewById(R.id.cold_iv_share);
        cold_city= (TextView) findViewById(R.id.cold_city);
        cold_cold= (TextView) findViewById(R.id.cold_cold);
        cold_temp= (TextView) findViewById(R.id.cold_temp);
        cold_suggestion= (TextView) findViewById(R.id.cold_suggestion);

        cold_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        okHttpClient=new OkHttpClient();
        progressDialog= ProgressDialog.show(ColdActivity.this,"请稍等..","努力加载中...",true);
        AMapLocationListener mapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        city = aMapLocation.getCity();
                       cold_city.setText(city);
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
    }
    private String getUrl(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        string = response.body().string();
        return string;
    }
}
