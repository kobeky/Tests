package com.example.anzhuo.tests;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    private ToggleButton timing_tb_Switch1;
    private ToggleButton timing_tb_Switch2;
    private ImageButton timing_ib_New;
    private ImageButton timing_ib_Retrun;
    int GONE = View.GONE;
    int VISIBLE = View.VISIBLE;
    int timing_Switch1;
    int timing_Switch2;
    int SetTime_Hour;
    int SetTime_Minute;
    int Remove;
    int RemoveTime;
    int RemoveMinute;
    Intent intent;
    Intent intentSwitch1;
    Intent intentSwitch2;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferencesSetTime;
    SharedPreferences sharedPreferencesRemove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timing_activity);
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
        //读取数据设置状态
        sharedPreferences = getSharedPreferences("Timing_tb_Switch1", MODE_PRIVATE);
        timing_Switch1 = sharedPreferences.getInt("true", timing_Switch1);
        sharedPreferencesSetTime = getSharedPreferences("SetTime", MODE_PRIVATE);
        SetTime_Hour = sharedPreferencesSetTime.getInt("settime_Time", SetTime_Hour);
        SetTime_Minute = sharedPreferencesSetTime.getInt("settime_Minute", SetTime_Minute);
        timing_tv_Time1.setText(SetTime_Hour + ":" + SetTime_Minute);
        sharedPreferencesRemove = getSharedPreferences("Remove", MODE_PRIVATE);
        Remove = sharedPreferencesRemove.getInt("editorRemove", Remove);
        RemoveTime = sharedPreferencesRemove.getInt("remove_Time", RemoveTime);
        RemoveMinute = sharedPreferencesRemove.getInt("remove_Minute", RemoveMinute);
        if (Remove == 1) {
            timing_tv_Time2.setText(RemoveTime + ":" + RemoveMinute);
            timing_RLayout_layout2.setVisibility(View.VISIBLE);
        } else {
            timing_RLayout_layout2.setVisibility(View.GONE);
        }
        if (timing_Switch1 == 1) {
            intentSwitch1 = new Intent(Timing_Activity.this, Switch1_Service.class);
            startService(intentSwitch1);
            timing_tb_Switch1.setChecked(true);
        } else {
            timing_tb_Switch1.setChecked(false);
        }
        if (timing_Switch2 == 1) {
            intentSwitch2 = new Intent(Timing_Activity.this, Switch2_Service.class);
            startService(intentSwitch2);
            timing_tb_Switch2.setChecked(true);
        } else {
            timing_tb_Switch2.setChecked(false);
            timing_RLayout_layout2.setVisibility(View.GONE);
        }

        //第一按钮
        timing_tb_Switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (timing_tb_Switch1.isChecked()) {
                    sharedPreferences = getSharedPreferences("Timing_tb_Switch1", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("true", 1);
                    editor.commit();
                    String content = (String) timing_tv_Time1.getText();
                    String ste[] = content.split(":");
                    int TimingHour = Integer.valueOf(ste[0]);
                    int TimingMinute = Integer.valueOf(ste[1]);
                    intentSwitch1 = new Intent(Timing_Activity.this, Switch1_Service.class);
                    intentSwitch1.putExtra("TimingHour", TimingHour);
                    intentSwitch1.putExtra("TimingMinute", TimingMinute);
                    timing_tv_Time1.setText(ste[0] + ":" + ste[1]);
                    startService(intentSwitch1);
                } else {
                    sharedPreferences = getSharedPreferences("Timing_tb_Switch1", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("true", 2);
                    editor.commit();
                    intentSwitch1 = new Intent(Timing_Activity.this, Switch1_Service.class);
                    intentSwitch1.putExtra("TimingPut", 1);
                    stopService(intentSwitch1);
                }
            }
        });
        //第二按钮
        timing_tb_Switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (timing_tb_Switch2.isChecked()) {
                    sharedPreferences = getSharedPreferences("Timing_tb_Switch2", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("true", 1);
                    editor.commit();
                    String content = (String) timing_tv_Time1.getText();
                    String ste[] = content.split(":");
                    int TimingHour = Integer.valueOf(ste[0]);
                    int TimingMinute = Integer.valueOf(ste[1]);
                    intentSwitch2 = new Intent(Timing_Activity.this, Switch1_Service.class);
                    intentSwitch2.putExtra("TimingHour", TimingHour);
                    intentSwitch2.putExtra("TimingMinute", TimingMinute);
                    startService(intentSwitch2);
                } else {
                    sharedPreferences = getSharedPreferences("Timing_tb_Switch2", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("true", 2);
                    editor.commit();
                    intentSwitch2 = new Intent(Timing_Activity.this, Switch2_Service.class);
                    intentSwitch2.putExtra("TimingPut", 1);
                    stopService(intentSwitch2);
                }
            }
        });
        intent = getIntent();
        String setHour = intent.getStringExtra("settime_Time");
        String setMinout = intent.getStringExtra("settime_Minute");
        String removeHour = intent.getStringExtra("remove_Time");
        String removeMinout = intent.getStringExtra("remove_Minute");
        if (setHour != null && setMinout != null) {
            timing_tv_Time1.setText(setHour + ":" + setMinout);
        }
        if (removeHour != null & removeMinout != null) {
            timing_RLayout_layout2.setVisibility(View.VISIBLE);
            timing_tv_Time2.setText(removeHour + ":" + removeMinout);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences = getSharedPreferences("Timing_tb_Switch1", MODE_PRIVATE);
        timing_Switch1 = sharedPreferences.getInt("true", timing_Switch1);
        sharedPreferences = getSharedPreferences("Timing_tb_Switch2", MODE_PRIVATE);
        timing_Switch2 = sharedPreferences.getInt("true", timing_Switch2);
        if (timing_Switch1 == 1) {
            sharedPreferences = getSharedPreferences("Timing_tb_Switch1", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("true", 1);
            editor.commit();
            sharedPreferences.edit().putInt("true", 1).commit();
        } else {
            Log.i("YYJ", "timing_Switch:读取" + timing_Switch1);
            sharedPreferences = getSharedPreferences("Timing_tb_Switch1", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("true", 2);
            editor.commit();
        }
        if (timing_Switch2 == 1) {
            sharedPreferences = getSharedPreferences("Timing_tb_Switch2", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("true", 1);
            editor.commit();
        } else {
            sharedPreferences = getSharedPreferences("Timing_tb_Switch2", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("true", 2);
            editor.commit();
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
                finish();
                break;
            case R.id.timing_RLayout_layout1:
                intent = new Intent(Timing_Activity.this, SetTime_Activity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.timing_ib_Retrun:
                finish();
                break;
            case R.id.timing_RLayout_layout2:
                Intent intent = new Intent(Timing_Activity.this, RemoveTime_Activity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
