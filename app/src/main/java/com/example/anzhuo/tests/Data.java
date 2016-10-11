package com.example.anzhuo.tests;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by anzhuo on 2016/9/13.
 */
public class Data {
    AMapLocationClient mLocationClient = null;
    AMapLocationClientOption mLocationOption = null;
    Context context;
    String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Data(Context context) {
        this.context = context;
    }

public void SetMap(final Handler handler) {

    AMapLocationListener aMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    city = aMapLocation.getCity();
                    handler.sendMessage(Message.obtain(handler,0,city));
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.i("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }



        }


    };
    mLocationClient = new AMapLocationClient(context);
    mLocationOption = new AMapLocationClientOption();
    mLocationClient.setLocationListener(aMapLocationListener);
    mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
    mLocationOption.setOnceLocationLatest(true);
    mLocationOption.setNeedAddress(true);
    mLocationOption.setWifiActiveScan(false);
    mLocationOption.setMockEnable(false);
    AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);
    mLocationClient.setLocationOption(mLocationOption);
    mLocationClient.startLocation();


}

}