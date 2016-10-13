package com.example.anzhuo.tests;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TimePicker;

/**
 * Created by anzhuo on 2016/10/11.
 */
public class SetTime_Activity extends AppCompatActivity implements OnClickListener {
    private TimePicker timePicker;
    private ImageButton settime_ib_Retrun;
    private ImageButton settime_Confirm;
    private String settime_Time;
    private String settime_Minute;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settime_activity);
        timePicker = (TimePicker) findViewById(R.id.settime_TimePicker);
        settime_Confirm = (ImageButton) findViewById(R.id.settime_Confirm);
        settime_ib_Retrun = (ImageButton) findViewById(R.id.settime_ib_Retrun);
        settime_ib_Retrun.setOnClickListener(this);
        settime_Confirm.setOnClickListener(this);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                settime_Time = String.valueOf(hourOfDay);
                settime_Minute = String.valueOf(minute);
            }
        });
        settime_Time = String.valueOf(timePicker.getCurrentHour());
        settime_Minute = String.valueOf(timePicker.getCurrentMinute());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settime_ib_Retrun:
                finish();
                break;
            case R.id.settime_Confirm:
                settime_Time = String.valueOf(timePicker.getCurrentHour());
                settime_Minute = String.valueOf(timePicker.getCurrentMinute());
                Intent intent = new Intent(SetTime_Activity.this, Timing_Activity.class);
                intent.putExtra("settime_Time", settime_Time);
                intent.putExtra("settime_Minute", settime_Minute);
                startActivity(intent);
                finish();
                break;

        }
    }
}
