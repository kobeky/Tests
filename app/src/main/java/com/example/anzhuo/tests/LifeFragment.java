package com.example.anzhuo.tests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anzhuo.tests.base_activity.CalendarActivity;
import com.example.anzhuo.tests.base_activity.CarWash;
import com.example.anzhuo.tests.base_activity.ColdActivity;
import com.example.anzhuo.tests.base_activity.DressIndex;
import com.example.anzhuo.tests.base_activity.RaysActivity;
import com.example.anzhuo.tests.base_activity.SportsActivity;
import com.example.anzhuo.tests.base_activity.TravelActivity;

/**
 * Created by anzhuo on 2016/9/12.
 */
public class LifeFragment extends Fragment implements View.OnClickListener{
    TextView life_tv_calendar;
    TextView life_tv_travel;
    TextView life_tv_dress;
    TextView life_tv_carwash;
    TextView life_tv_sport;
    TextView life_tv_cold;
    TextView life_tv_rays;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.life,container,false);
        life_tv_calendar= (TextView) view.findViewById(R.id.life_tv_calendar);
        life_tv_sport= (TextView) view.findViewById(R.id.life_tv_sport);
        life_tv_dress= (TextView) view.findViewById(R.id.life_tv_dress);
        life_tv_cold= (TextView) view.findViewById(R.id.life_tv_cold);
        life_tv_carwash= (TextView) view.findViewById(R.id.life_tv_carwash);
        life_tv_travel= (TextView) view.findViewById(R.id.life_tv_travel);
        life_tv_rays= (TextView) view.findViewById(R.id.life_tv_rays);



        life_tv_calendar.setOnClickListener(this);
        life_tv_travel.setOnClickListener(this);
        life_tv_dress.setOnClickListener(this);
       life_tv_carwash.setOnClickListener(this);
        life_tv_cold.setOnClickListener(this);
        life_tv_sport.setOnClickListener(this);
        life_tv_rays.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.life_tv_calendar:
        Intent intent=new Intent(getContext().getApplicationContext(),CalendarActivity.class);
        startActivity(intent);
        break;
    case R.id.life_tv_travel:
        Intent intent1=new Intent(getContext().getApplicationContext(),TravelActivity.class);
        startActivity(intent1);
        break;
    case R.id.life_tv_dress:
        Intent intent2=new Intent(getContext().getApplicationContext(),DressIndex.class);
        startActivity(intent2);
        break;
    case R.id.life_tv_carwash:
        Intent intent3=new Intent(getContext().getApplicationContext(),CarWash.class);
        startActivity(intent3);
        break;
    case R.id.life_tv_cold:
        Intent intent4=new Intent(getContext().getApplicationContext(),ColdActivity.class);
        startActivity(intent4);
        break;
    case R.id.life_tv_sport:
        Intent intent5=new Intent(getContext().getApplicationContext(),SportsActivity.class);
        startActivity(intent5);
        break;
    case R.id.life_tv_rays:
        Intent intent6=new Intent(getContext().getApplicationContext(),RaysActivity.class);
        startActivity(intent6);
        break;
}
    }
}
