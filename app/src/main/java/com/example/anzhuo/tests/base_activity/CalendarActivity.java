package com.example.anzhuo.tests.base_activity;

import android.app.ProgressDialog;
import android.content.Intent;
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

import com.bigkoo.alertview.AlertView;
import com.example.anzhuo.tests.R;
import com.example.anzhuo.tests.info.CalendarInfo;
import com.google.gson.Gson;
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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by anzhuo on 2016/9/12.
 */
public class CalendarActivity extends AppCompatActivity implements OnDateSelectedListener {

    String dates;
    String urls;

    private static final int MSG = 1;
    String string = "";
    OkHttpClient okHttpClient;

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
    ImageView calendar_historytoday;
    String s = "";

    SimpleDateFormat simpleDateFormat;
    ProgressDialog progressDialog;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG:
                    Gson gson = new Gson();
                    CalendarInfo calendarInfo = gson.fromJson(string, CalendarInfo.class);
                    s = calendarInfo.getResult().getData().getDate();
                    s = calendarInfo.getResult().getData().getDate();
                    String[] strings = s.split("-");
                    calendar_tv_day.setText(strings[2]);
                    calendar_tv_month.setText(strings[0] + "-" + strings[1]);
                    calendar_tv_date.setText(calendarInfo.getResult().getData().getLunar().toString());
                    calendar_tv_lunaryear.setText(calendarInfo.getResult().getData().getLunarYear());
                    calendar_tv_weekday.setText(calendarInfo.getResult().getData().getWeekday());
                    if (calendarInfo.getResult().getData().getHoliday() == null) {
                        calendar_tv_holiday.setText("该工作的认真工作，能休息的放松休息");
                    } else {
                        calendar_tv_holiday.setText(calendarInfo.getResult().getData().getHoliday() + "  " + calendarInfo.getResult().getData().getDesc());
                    }
                    calendar_tv_suit.setText(calendarInfo.getResult().getData().getSuit());
                    calendar_tv_avoid.setText(calendarInfo.getResult().getData().getAvoid());
                    progressDialog.dismiss();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        okHttpClient = new OkHttpClient();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        progressDialog= new ProgressDialog(CalendarActivity.this);

        calendarView = (MaterialCalendarView) findViewById(R.id.mCalendar);
        calendar_iv_back = (ImageView) findViewById(R.id.calendar_iv_back);
        calendar_iv_share = (ImageView) findViewById(R.id.calendar_iv_share);

        calendar_tv_day = (TextView) findViewById(R.id.calendar_tv_day);
        calendar_tv_month = (TextView) findViewById(R.id.calendar_tv_month);
        calendar_tv_date = (TextView) findViewById(R.id.calendar_tv_date);
        calendar_tv_lunaryear = (TextView) findViewById(R.id.calendar_tv_lunaryear);
        calendar_tv_weekday = (TextView) findViewById(R.id.calendar_tv_weekday);
        calendar_tv_holiday = (TextView) findViewById(R.id.calendar_tv_holiday);
        calendar_tv_suit = (TextView) findViewById(R.id.calendar_tv_suit);
        calendar_tv_avoid = (TextView) findViewById(R.id.calendar_tv_avoid);
        calendar_historytoday = (ImageView) findViewById(R.id.calendar_historytoday);

        setThread(simpleDateFormat.format(new Date()));
        progressDialog.setMessage("正在加载中...");
        progressDialog.show();

        calendarView.setDateSelected(new Date(), true);

        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(1949, 1, 1))
                .setMaximumDate(CalendarDay.from(2049, 1, 1))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        calendar_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        calendar_historytoday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarActivity.this, HistoryToday.class);
                intent.putExtra("da", selectedDate);
                startActivity(intent);
            }
        });
        calendarView.setOnDateChangedListener(this);

    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        date = widget.getSelectedDate();
        selectedDate = simpleDateFormat.format(date.getDate());
        Toast.makeText(CalendarActivity.this,selectedDate, Toast.LENGTH_SHORT).show();
        Log.i("KY","aaa");
        setThread(selectedDate);
        progressDialog.setMessage("正在加载...");
        progressDialog.show();

    }

    private void setThread(final String requestDate) {
        new Thread() {
            @Override
            public void run() {
                try {
                    getUrlString(getUrl(requestDate));
                    handler.sendEmptyMessage(MSG);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private String getUrl(String date) {
        String[] strings = date.split("-");
        if (Integer.parseInt(strings[1]) < 10) {
            if (Integer.parseInt(strings[2]) < 10) {
                strings[1] = String.valueOf(Integer.parseInt(strings[1]));
                strings[2] = String.valueOf(Integer.parseInt(strings[2]));
                dates = strings[0] + "-" + strings[1] + "-" + strings[2];
                urls = "http://japi.juhe.cn/calendar/day?date=" + dates +
                        "&key=bc7773acac37866d030c5b7790725e1f";
            } else {
                strings[1] = String.valueOf(Integer.parseInt(strings[1]));
                dates = strings[0] + "-" + strings[1] + "-" + strings[2];
                urls = "http://japi.juhe.cn/calendar/day?date=" + dates +
                        "&key=bc7773acac37866d030c5b7790725e1f";
            }
        } else {
            if (Integer.parseInt(strings[2]) < 10) {
                strings[2] = String.valueOf(Integer.parseInt(strings[2]));
                dates = strings[0] + "-" + strings[1] + "-" + strings[2];
                urls = "http://japi.juhe.cn/calendar/day?date=" + dates +
                        "&key=bc7773acac37866d030c5b7790725e1f";
            } else {
                urls = "http://japi.juhe.cn/calendar/day?date=" + date +
                        "&key=bc7773acac37866d030c5b7790725e1f";
            }
        }
        return urls;
    }

    private String getUrlString(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        string = response.body().string();
        return string;
    }
}
