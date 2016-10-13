package com.example.anzhuo.tests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by anzhuo on 2016/9/12.
 */
public class Me_Activity extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private RelativeLayout me_RelativeLayout_Timing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_activity);
        me_RelativeLayout_Timing = (RelativeLayout) findViewById(R.id.me_RelativeLayout_Timing);
        me_RelativeLayout_Timing.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_RelativeLayout_Timing:
    intent = new Intent(Me_Activity.this, Timing_Activity.class);
            startActivity(intent);
                break;
        }
    }
}
