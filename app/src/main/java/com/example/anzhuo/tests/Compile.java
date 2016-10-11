package com.example.anzhuo.tests;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.anzhuo.tests.info.GvInfo;
import com.example.anzhuo.tests.info.HeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anzhuo on 2016/9/26.
 */
public class Compile extends AppCompatActivity {
    ImageView iv_back;
    GridView view_addCity;
    private static final int RQ=1;
    Weather weather;
    String str;
    boolean isShowDelete;
    GvAdapter adapter;
    List<GvInfo>list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compile);
        iv_back= (ImageView) findViewById(R.id.iv_back_compile);
        view_addCity= (GridView) findViewById(R.id.view_compile);
        weather=new Weather();
        adapter=new GvAdapter(this,list);
        view_addCity.setAdapter(adapter);
iv_back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        finish();
    }
});

        view_addCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==adapterView.getChildCount()-1) {
                    Intent intent = new Intent(getApplicationContext(), AddCity.class);
                    startActivityForResult(intent, RQ);
                }else{
                    setResult(RESULT_OK,new Intent().putExtra("gvInfo",list.get(i)).addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT));

                }

            }
        });
        view_addCity.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i<list.size()) {

                    if (isShowDelete) {//删除图片显示时长按隐藏
                        isShowDelete = false;
                        adapter.setIsShowDelete(isShowDelete);

                    } else {//删除图片隐藏式长按显示
                        isShowDelete = true;
                        adapter.setIsShowDelete(isShowDelete);
                    }
                }
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RQ&&resultCode==RESULT_OK){
            str=data.getStringExtra("city");
            weather.setWeather(handler,str);
        }

    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==2){
                List<HeBean.HeWeather.DailyForecastBean> lists= (List<HeBean.HeWeather.DailyForecastBean>) msg.obj;
                String range=lists.get(0).getTmp().getMin()+"°"+"～"+lists.get(0).getTmp().getMax()+"°";
                GvInfo infoAdd=new GvInfo();
                infoAdd.setCityName(str);
                infoAdd.setImage(SetImageView.setImage(lists.get(0).getCond().getTxt_d()));
                infoAdd.setRange(range);
                list.add(infoAdd);
                adapter.notifyDataSetChanged();
            }
        }
    };

}
