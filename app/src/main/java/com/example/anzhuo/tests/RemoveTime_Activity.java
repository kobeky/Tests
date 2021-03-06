package com.example.anzhuo.tests;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TimePicker;

/**
 * Created by anzhuo on 2016/10/11.
 */
public class RemoveTime_Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton remove_ib_Retrun;
    private ImageButton removetime_ib_Confirm;
    private TimePicker remove_TimePicker;
    private String removew_Time;
    private String removew_Minute;
    private ImageButton removetime_ib_imageButton;
    private ImageButton removetime_tv_Remove;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_activity);
        remove_ib_Retrun = (ImageButton) findViewById(R.id.remove_ib_Retrun);
        removetime_ib_Confirm = (ImageButton) findViewById(R.id.removetime_ib_Confirm);
        remove_TimePicker = (TimePicker) findViewById(R.id.remove_TimePicker);
        removetime_tv_Remove = (ImageButton) findViewById(R.id.removetime_ib_Remove);
        removetime_tv_Remove.setOnClickListener(this);
        remove_TimePicker.setIs24HourView(true);
        remove_ib_Retrun.setOnClickListener(this);
        removetime_ib_Confirm.setOnClickListener(this);
        removetime_ib_imageButton = (ImageButton) findViewById(R.id.removetime_ib_Remove);
        removetime_ib_imageButton.setOnClickListener(this);
        remove_TimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                removew_Time = String.valueOf(hourOfDay);
                removew_Minute = String.valueOf(minute);
            }
        });

        removew_Time = String.valueOf(remove_TimePicker.getCurrentHour());
        removew_Minute = String.valueOf(remove_TimePicker.getCurrentMinute());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.remove_ib_Retrun:
                finish();
                break;
            case R.id.removetime_ib_Confirm:
                sharedPreferences = getSharedPreferences("Remove", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("remove_Time", Integer.parseInt(removew_Time));
                editor.putInt("remove_Minute", Integer.parseInt(removew_Minute));
                editor.putInt("editorRemove", 1);
                editor.commit();
                removew_Time = String.valueOf(remove_TimePicker.getCurrentHour());
                removew_Minute = String.valueOf(remove_TimePicker.getCurrentMinute());
                Intent intent = new Intent(RemoveTime_Activity.this, Timing_Activity.class);
                intent.putExtra("remove_Time", removew_Time);
                intent.putExtra("remove_Minute", removew_Minute);
                startActivity(intent);
                finish();
                break;
            case R.id.removetime_ib_Remove:
                sharedPreferences = getSharedPreferences("Remove", MODE_PRIVATE);
                SharedPreferences.Editor editorRemove = sharedPreferences.edit();
                editorRemove.putInt("editorRemove", 2);
                editorRemove.commit();
                finish();
                break;
        }
    }
}
