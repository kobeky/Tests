package com.example.anzhuo.tests;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by anzhuo on 2016/9/12.
 */
public class CalendarActivity extends AppCompatActivity implements OnDateSelectedListener {

    StringBuffer stringBuffer;

    String dates;

    private static final int MSG=1;
    String weekday;

    String selectedDate;
    MaterialCalendarView calendarView;
    ImageView calendar_iv_back;
    ImageView calendar_iv_share;
TextView calendar_tv_day;
    TextView calendar_tv_month;
    TextView calendar_tv_date;
    TextView calendar_tv_lunaryear;
    TextView calendar_tv_weekday;
    TextView calendar_tv_holiday;
    TextView calendar_tv_suit;
    TextView calendar_tv_avoid;

Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case MSG:
                try {

                    JSONObject jsonObject=new JSONObject(stringBuffer.toString());
                    JSONObject jsonObject1=jsonObject.getJSONObject("result");
                    JSONObject jsonObject2=jsonObject1.getJSONObject("data");
                    String holiday=jsonObject2.getString("holiday");
                    String desc=jsonObject2.getString("desc");
                    String date=jsonObject2.getString("date");
                    String[]strings=date.split("-");

                   String weekday=jsonObject2.getString("weekday");
                    String avoid=jsonObject2.getString("avoid");
                    String animalsYear=jsonObject2.getString("animalsYear");
                    String suit=jsonObject2.getString("suit");
                    String lunar=jsonObject2.getString("lunar");
                    String lunaryear=jsonObject2.getString("lunarYear");
                  calendar_tv_day.setText(strings[2]);
                    calendar_tv_month.setText(strings[0]+"-"+strings[1]);
calendar_tv_date.setText("农历"+lunar);
                    calendar_tv_lunaryear.setText(lunaryear+"("+animalsYear+")");
                    calendar_tv_weekday.setText(weekday);
                    if(holiday==null){
                      calendar_tv_holiday.setText("该上班的上班，该休息的休息。");
                    }else {
                        calendar_tv_holiday.setText(holiday+"  "+desc);
                    }
calendar_tv_suit.setText(suit);
                    calendar_tv_avoid.setText(avoid);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        Log.i("KY","789");

        calendarView = (MaterialCalendarView) findViewById(R.id.mCalendar);
        calendar_iv_back = (ImageView) findViewById(R.id.calendar_iv_back);
        calendar_iv_share = (ImageView) findViewById(R.id.calendar_iv_share);

calendar_tv_day= (TextView) findViewById(R.id.calendar_tv_day);
        calendar_tv_month= (TextView) findViewById(R.id.calendar_tv_month);
        calendar_tv_date= (TextView) findViewById(R.id.calendar_tv_date);
        calendar_tv_lunaryear= (TextView) findViewById(R.id.calendar_tv_lunaryear);
        calendar_tv_weekday= (TextView) findViewById(R.id.calendar_tv_weekday);
        calendar_tv_holiday= (TextView) findViewById(R.id.calendar_tv_holiday);
        calendar_tv_suit= (TextView) findViewById(R.id.calendar_tv_suit);
        calendar_tv_avoid= (TextView) findViewById(R.id.calendar_tv_avoid);



        calendarView.setDateSelected(new Date(), true);
        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2000, 1, 1))
                .setMaximumDate(CalendarDay.from(2030, 1, 1))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        calendar_tv_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setThread();
                Toast.makeText(CalendarActivity.this,"a",Toast.LENGTH_SHORT).show();
            }
        });


        calendarView.setOnDateChangedListener(this);
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget,@NonNull CalendarDay date, boolean selected) {
        date = widget.getSelectedDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        selectedDate = simpleDateFormat.format(date.getDate());
        }


    private void setThread() {
        new Thread() {
            @Override
            public void run() {


                   String url = "http://japi.juhe.cn/calendar/day?date=2016-9-14&key=bc7773acac37866d030c5b7790725e1f";


                network(url);

            }
        }.start();


    }



    public void network(String url){
        Log.i("KY","123");
        try {
            URL url2=new URL(url);
            HttpURLConnection connection= (HttpURLConnection) url2.openConnection();
            InputStream inputStream=new BufferedInputStream(connection.getInputStream());
            stringBuffer=new StringBuffer();
            byte[]bytes=new byte[4*1024];
            int len;
            while ((len=inputStream.read(bytes))!=-1){
                stringBuffer.append(new String(bytes,0,len));
            }
            handler.sendEmptyMessage(MSG);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private String getUrl(String date){
//        String[]strings=date.split("-");
//        if(Integer.parseInt(strings[1])<10){
//            if(Integer.parseInt(strings[2])<10){
//                strings[1]=String.valueOf(Integer.parseInt(strings[1]));
//                strings[2]=String.valueOf(Integer.parseInt(strings[2]));
//                dates=strings[0]+"-"+strings[1]+"-"+strings[2];
//                url="http://japi.juhe.cn/calendar/day?date="+dates+"&key=bc7773acac37866d030c5b7790725e1f";
//            }else {
//                strings[1]=String.valueOf(Integer.parseInt(strings[1]));
//                dates=strings[0]+"-"+strings[1]+"-"+strings[2];
//                url="http://japi.juhe.cn/calendar/day?date="+dates+"&key=bc7773acac37866d030c5b7790725e1f";
//            }
//        }else {
//            if(Integer.parseInt(strings[2])<10){
//                strings[2]=String.valueOf(Integer.parseInt(strings[2]));
//                dates=strings[0]+"-"+strings[1]+"-"+strings[2];
//                url="http://japi.juhe.cn/calendar/day?date="+dates+"&key=bc7773acac37866d030c5b7790725e1f";
//            }else {
//                url="http://japi.juhe.cn/calendar/day?date="+selectedDate+"&key=bc7773acac37866d030c5b7790725e1f";
//            }
//
//
//    }
//        return url;
//}
}
