package com.example.anzhuo.tests;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by anzhuo on 2016/9/12.
 */
public class LifeActivity extends AppCompatActivity{
    TextView life_tv_calendar;
    TextView life_tv_date;
    TextView life_tv_dress;
    TextView life_tv_carwash;
    TextView life_tv_sport;
    TextView life_tv_cold;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.life);
        life_tv_calendar= (TextView) findViewById(R.id.life_tv_calendar);
        life_tv_carwash= (TextView) findViewById(R.id.life_tv_carwash);
        life_tv_cold= (TextView) findViewById(R.id.life_tv_cold);
        life_tv_date= (TextView) findViewById(R.id.life_tv_date);
        life_tv_dress= (TextView) findViewById(R.id.life_tv_dress);
        life_tv_sport= (TextView) findViewById(R.id.life_tv_sport);

    }
}
