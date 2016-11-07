package com.example.anzhuo.tests;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anzhuo.tests.info.HeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anzhuo on 2016/9/13.
 */
public class ParticularsActivity extends AppCompatActivity {
    ImageView back;
    TextView city;
    ParticularsFragment particularsFragment;
    Weather weather;
    ViewPager viewPager;
    TabLayout tabLayout;
    List<Fragment>mlist=new ArrayList<>();
    CNKFixedPagerAdapter adapter;
    List<String> time;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.particulars_layout);
        time = new ArrayList<>();
        back= (ImageView) findViewById(R.id.particulars_iv_back);
        city= (TextView) findViewById(R.id.particulars_tv_city);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        tabLayout= (TabLayout) findViewById(R.id.tab_title);
        adapter=new CNKFixedPagerAdapter(getSupportFragmentManager());
        weather=new Weather();
        Intent intent=getIntent();
        String name=intent.getStringExtra("cityName");
        city.setText(name);
        weather.setWeather(handler,name);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (msg.what==2){
                List<HeBean.HeWeather.DailyForecastBean> list = (List<HeBean.HeWeather.DailyForecastBean>) msg.obj;
                for (int i = 0; i < list.size(); i++) {
                    particularsFragment = new ParticularsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bean", list.get(i));
                    particularsFragment.setArguments(bundle);
                    time.add(SetDate.setDate(list.get(i).getDate()));
                    mlist.add(particularsFragment);
                }
                adapter.setTitles(time);
                adapter.setFragments(mlist);
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        }
    }
    };

}
