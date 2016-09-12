package com.example.anzhuo.tests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    public AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= (TextView) findViewById(R.id.tv);

        mLocationClient=new AMapLocationClient(getApplicationContext());
        mLocationOption=new AMapLocationClientOption();
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setNeedAddress(true);
        mLocationOption.setWifiActiveScan(false);
        mLocationOption.setMockEnable(false);
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();

    }
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    String province=aMapLocation.getProvince();
                   String city= aMapLocation.getCity();
                    String district=aMapLocation.getDistrict();
                    String street=aMapLocation.getStreet();
                    String streetnum=aMapLocation.getStreetNum();
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date(aMapLocation.getTime());
                    String time=df.format(date);
                    tv.setText("你当前位于"+province+city+district+street+streetnum+"\n"+time);
//                    Toast.makeText(MainActivity.this,"你当前位于"+city+""+time,Toast.LENGTH_LONG).show();
//可在其中解析amapLocation获取相应内容。
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.i("AmapError","location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };
}
