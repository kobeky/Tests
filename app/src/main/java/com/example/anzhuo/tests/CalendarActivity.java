package com.example.anzhuo.tests;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Range;

import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by anzhuo on 2016/9/12.
 */
public class CalendarActivity extends AppCompatActivity{
    CalendarPickerView calendarPickerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        calendarPickerView= (CalendarPickerView) findViewById(R.id.calendar_view);
        Calendar nextYear=Calendar.getInstance();
        nextYear.add(Calendar.DAY_OF_MONTH,4);
        nextYear.add(Calendar.YEAR,2);
        Date today=new Date();
        calendarPickerView.init(today,nextYear.getTime()).withSelectedDate(today);
//        calendarPickerView.init(today,nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE);


    }
}
