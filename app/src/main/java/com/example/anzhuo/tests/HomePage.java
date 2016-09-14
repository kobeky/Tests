package com.example.anzhuo.tests;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by anzhuo on 2016/9/13.
 */
public class HomePage extends Fragment{
    List<GalleryInfo>list=new ArrayList<>();
    GalleryInfo ginfo;
    GalleryAdapter galleryAdapter;
    StringBuffer sb;
    String url;
    TextView city;//城市
    ImageButton add;//添加城市
    ImageButton share;//分享
    TextView temperature;//温度
    TextView info;//天气状态
    TextView windDirection;//风向
    TextView aeration;//空气指数
    TextView temperatureRange;//温度范围
    TextView temperatureRangeT;//明日天气范围
    ImageView infoToday;//今日天气状态
    ImageView infoTomorrow;//明日天气状态
    Gallery galleryHour;//24小时天气画廊
    Gallery galleryDay;//未来一周天气画廊
    LineChart air;//空气质量图表
    LineChart week;//未来一周的天气图表
    TextView somatosensory;//体感
    TextView tem;//温度
    TextView visible;//可见度
    TextView pressure;//气压
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    city.setText(msg.obj.toString());
                    break;
                case 1:
//                    try {
//                        JSONObject jso=new JSONObject(sb.toString());
//                        JSONArray jsa=jso.optJSONArray("HeWeather data service 3.0");
//for (int i=0; i<jsa.length();i++){
//
//    JSONObject jsonobject= (JSONObject) jsa.get(i);
//
//}
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_layout,null);
        city= (TextView) view.findViewById(R.id.home_tv_city);
        Amap amap=new Amap(getContext());
        amap.Amap(handler);
        add= (ImageButton) view.findViewById(R.id.home_ib_add);
        share= (ImageButton) view.findViewById(R.id.home_ib_share);
        temperature= (TextView) view.findViewById(R.id.home_tv_temperature);
        tem= (TextView) view.findViewById(R.id.home_tv_temperatureNum);
        temperatureRange= (TextView) view.findViewById(R.id.home_tv_temperature_range);
        temperatureRangeT= (TextView) view.findViewById(R.id.home_tv_temperature_rangeT);
        info= (TextView) view.findViewById(R.id.home_tv_info);
        windDirection= (TextView) view.findViewById(R.id.home_tv_windDirection);
        aeration= (TextView) view.findViewById(R.id.home_tv_aeration);
        infoToday= (ImageView) view.findViewById(R.id.home_iv_info);
        infoTomorrow= (ImageView) view.findViewById(R.id.home_iv_infoT);
        galleryHour= (Gallery) view.findViewById(R.id.home_gallery_hour);
        galleryDay= (Gallery) view.findViewById(R.id.home_gallery_day);
        air= (LineChart) view.findViewById(R.id.home_chart_air);
        week= (LineChart) view.findViewById(R.id.home_chart_week);
        somatosensory= (TextView) view.findViewById(R.id.home_tv_ssNum);
        visible= (TextView) view.findViewById(R.id.home_tv_vbNum);
        pressure= (TextView) view.findViewById(R.id.home_tv_psNum);
        ginfo=new GalleryInfo();
        ginfo.setTv("add");
        list.add(ginfo);
        galleryAdapter=new GalleryAdapter(getActivity(),list);
        galleryAdapter.notifyDataSetChanged();
        galleryHour.setAdapter(galleryAdapter);
        setThread();
        return view;
    }

public void setThread(){
    new Thread(){
        @Override
        public void run() {

            try {
                url="http://op.juhe.cn/onebox/weather/query?cityname=" + URLEncoder.encode(city.getText().toString(), "utf-8") + "&dtype=json&key=3b7922fa1cfc3b3e7904bc594770fe60";
                newWork(url);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }.start();
}

    private void newWork(String url) {
        try {
            URL strUrl=new URL(url);
            HttpURLConnection connection= (HttpURLConnection) strUrl.openConnection();
            InputStream input=new BufferedInputStream(connection.getInputStream());
            sb=new StringBuffer();
            byte[]bt=new byte[5*1024];
            int len;
            while ((len=input.read(bt))!=-1){
                sb.append(new String(bt,0,len));
            }
            handler.sendEmptyMessage(1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
