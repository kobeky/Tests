package com.example.anzhuo.tests;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by anzhuo on 2016/10/12.
 */
public class Timing_Activity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout timing_RLayout_layout1;
    private RelativeLayout timing_RLayout_layout2;
    private TextView timing_tv_Time1;
    private TextView timing_tv_Time2;
    private TextView timing_tv_Week1;
    private TextView timing_tv_Week2;
    private ToggleButton timing_tb_Switch1;
    private ToggleButton timing_tb_Switch2;
    private ImageButton timing_ib_New;
    private ImageButton timing_ib_Retrun;
    int GONE = View.GONE;
    int VISIBLE = View.VISIBLE;
    Intent intent;
    long data;
    Intent intentSwitch1;
    Intent intentSwitch2;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timing_activity);
        timing_tv_Week1 = (TextView) findViewById(R.id.timing_tv_Week1);
        timing_tv_Week2 = (TextView) findViewById(R.id.timing_tv_Week2);
        timing_ib_New = (ImageButton) findViewById(R.id.timing_ib_New);
        timing_ib_Retrun = (ImageButton) findViewById(R.id.timing_ib_Retrun);
        timing_tv_Time1 = (TextView) findViewById(R.id.timing_tv_Time1);
        timing_tv_Time2 = (TextView) findViewById(R.id.timing_tv_Time2);
        timing_tb_Switch1 = (ToggleButton) findViewById(R.id.timing_tb_Switch1);
        timing_tb_Switch2 = (ToggleButton) findViewById(R.id.timing_tb_Switch2);
        timing_RLayout_layout1 = (RelativeLayout) findViewById(R.id.timing_RLayout_layout1);
        timing_RLayout_layout2 = (RelativeLayout) findViewById(R.id.timing_RLayout_layout2);
        timing_ib_Retrun.setOnClickListener(this);
        timing_RLayout_layout1.setOnClickListener(this);
        timing_RLayout_layout2.setOnClickListener(this);
        timing_ib_New.setOnClickListener(this);
        data = System.currentTimeMillis();
        java.text.SimpleDateFormat time1 = new java.text.SimpleDateFormat("yyyy-MM-dd-EEEE");
        String data1 = time1.format(data);
        String i[] = data1.split("-");
        timing_tv_Week1.setText(i[3]);
        //第一
        timing_tb_Switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (timing_tb_Switch1.isChecked()) {
                    String content = (String) timing_tv_Time1.getText();
                    String ste[] = content.split(":");
                    int TimingHour = Integer.valueOf(ste[0]);
                    int TimingMinute = Integer.valueOf(ste[1]);
                    intentSwitch1 = new Intent(Timing_Activity.this, Switch1_Service.class);
                    intentSwitch1.putExtra("TimingHour", TimingHour);
                    intentSwitch1.putExtra("TimingMinute", TimingMinute);
                    startService(intentSwitch1);

                } else {
                    intentSwitch1 = new Intent(Timing_Activity.this, Switch1_Service.class);
                    intentSwitch1.putExtra("TimingPut", 1);
                    stopService(intentSwitch1);
                    Toast.makeText(Timing_Activity.this, "", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //第二
        timing_tb_Switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        intent = getIntent();
        String setHour = intent.getStringExtra("settime_Time");
        String setMinout = intent.getStringExtra("settime_Minute");
        String removeHour = intent.getStringExtra("removew_Time");
        String removeMinout = intent.getStringExtra("removew_Minute");
        if (setHour != null && setMinout != null) {
            timing_tv_Time1.setText(setHour + ":" + setMinout);
        }
        if (removeHour != null & removeMinout != null) {
            timing_RLayout_layout2.setVisibility(View.VISIBLE);
            timing_tv_Time2.setText(removeHour + ":" + removeMinout);
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.timing_ib_New:
                if (timing_tb_Switch1.getVisibility() == VISIBLE && timing_RLayout_layout2.getVisibility() == VISIBLE) {
                    Toast.makeText(Timing_Activity.this, "最多添加两个闹钟", Toast.LENGTH_SHORT).show();
                } else {
                    timing_RLayout_layout2.setVisibility(View.GONE);
                    intent = new Intent(Timing_Activity.this, RemoveTime_Activity.class);
                    startActivity(intent);
                }
                break;
            case R.id.timing_RLayout_layout1:
                intent = new Intent(Timing_Activity.this, SetTime_Activity.class);
                startActivity(intent);
                break;
            case R.id.timing_ib_Retrun:
                finish();
                break;
        }
    }
}
