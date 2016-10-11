package com.example.anzhuo.tests;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.anzhuo.tests.info.HeBean;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by anzhuo on 2016/9/13.
 */
public class HomePage extends Fragment{
    List<Entry> entries = new ArrayList<>();//一周天气图表list
    List<Entry>entriesN=new ArrayList<>();
    TextView city;//城市
    ImageButton add;//添加城市
    ImageButton share;//分享
    TextView temperature;//温度
    TextView info;//天气状态
    TextView windDirection;//风向
    TextView aeration;//空气指数
    TextView temperatureRange;//温度范围
    TextView temperatureRangeT;//明日天气范围
    ImageView infoToday;//今日天气状态
    ImageView infoTomorrow;//明日天气状态
    LineChart week;//未来一周的天气图表
    TextView somatosensory;//体感
    TextView tem;//温度
    TextView visible;//可见度
    TextView pressure;//气压
TextView tv_particulars;
    @Nullable

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout,container,false);
        city = (TextView) view.findViewById(R.id.home_tv_city);
        add = (ImageButton) view.findViewById(R.id.home_ib_add);
        share = (ImageButton) view.findViewById(R.id.home_ib_share);
        temperature = (TextView) view.findViewById(R.id.home_tv_temperature);
        tem = (TextView) view.findViewById(R.id.home_tv_tpNum);
        temperatureRange = (TextView) view.findViewById(R.id.home_tv_temperature_range);
        temperatureRangeT = (TextView) view.findViewById(R.id.home_tv_temperature_rangeT);
        info = (TextView) view.findViewById(R.id.home_tv_info);
        windDirection = (TextView) view.findViewById(R.id.home_tv_windDirection);
        aeration = (TextView) view.findViewById(R.id.home_tv_aeration);
        infoToday = (ImageView) view.findViewById(R.id.home_iv_info);
        infoTomorrow = (ImageView) view.findViewById(R.id.home_iv_infoT);
        week = (LineChart) view.findViewById(R.id.home_chart_week);
        somatosensory = (TextView) view.findViewById(R.id.home_tv_ssNum);
        visible = (TextView) view.findViewById(R.id.home_tv_vbNum);
        pressure = (TextView) view.findViewById(R.id.home_tv_psNum);
        tv_particulars= (TextView) view.findViewById(R.id.tv_particulars_home);
        refresh();
        LineDataSet dataSet=new LineDataSet(entries,"");
        dataSet.setColor(R.color.colorPrimaryDark);
        dataSet.setValueTextColor(R.color.colorAccent);
        LineData lineData=new LineData(dataSet);
        week.setData(lineData);
        week.invalidate();
        week.setTouchEnabled(true);
        week.setDragEnabled(true);
        week.setSaveEnabled(true);
        week.setScaleXEnabled(false);
        week.setScaleYEnabled(false);
        week.setHighlightPerDragEnabled(false);
        week.setHighlightPerTapEnabled(false);
        week.setDescription(null);
        XAxis xAxis=week.getXAxis();
        xAxis.setDrawGridLines(false);
        week.getAxisRight().setEnabled(false);
        week.getAxisLeft().setEnabled(false);
        week.animateX(1500);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Compile.class).addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivityForResult(intent,10);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share.setImageBitmap(Screen.takeScreenShot(getActivity()));
            }
        });
        tv_particulars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),ParticularsActivity.class);
                i.putExtra("cityName",city.getText().toString());
                startActivity(i);
            }
        });
        return view;
    }
    public  void refresh(){
        HeBean.HeWeather Bean= (HeBean.HeWeather) getArguments().getSerializable("bean");
        temperature.setText(Bean.getNow().getTmp()+"°");
        tem.setText(Bean.getNow().getTmp()+"°");
        somatosensory.setText(Bean.getNow().getFl());
        visible.setText(Bean.getNow().getVis());
        info.setText(Bean.getNow().getCond().getTxt());
        windDirection.setText(Bean.getNow().getWind().getDir());
        pressure.setText(Bean.getNow().getPres());
        aeration.setText(Bean.getAqi().getCity().getAqi());
        city.setText(Bean.getBasic().getCity());
        String []day1=Bean.getDaily_forecast().get(0).getDate().split("-");
        String []day2=Bean.getDaily_forecast().get(1).getDate().split("-");
        String []day3=Bean.getDaily_forecast().get(2).getDate().split("-");
        String []day4=Bean.getDaily_forecast().get(3).getDate().split("-");
        String []day5=Bean.getDaily_forecast().get(4).getDate().split("-");
        String []day6=Bean.getDaily_forecast().get(5).getDate().split("-");
        String []day7=Bean.getDaily_forecast().get(6).getDate().split("-");
        float  daily1=Float.parseFloat(day1[1]+"."+day1[2]);
        float daily2=Float.parseFloat(day2[1]+"."+day2[2]);
        float daily3=Float.parseFloat(day3[1]+"."+day3[2]);
        float daily4=Float.parseFloat(day4[1]+"."+day4[2]);
        float daily5=Float.parseFloat(day5[1]+"."+day5[2]);
        float daily6=Float.parseFloat(day6[1]+"."+day6[2]);
        float daily7=Float.parseFloat(day7[1]+"."+day7[2]);
        int temP1= (int) Float.parseFloat(Bean.getDaily_forecast().get(0).getTmp().getMax());
        int temP2= (int) Float.parseFloat(Bean.getDaily_forecast().get(1).getTmp().getMax());
        int temP3= (int) Float.parseFloat(Bean.getDaily_forecast().get(2).getTmp().getMax());
        int temP4= (int) Float.parseFloat(Bean.getDaily_forecast().get(3).getTmp().getMax());
        int temP5= (int) Float.parseFloat(Bean.getDaily_forecast().get(4).getTmp().getMax());
        int temP6= (int) Float.parseFloat(Bean.getDaily_forecast().get(5).getTmp().getMax());
        int temP7= (int) Float.parseFloat(Bean.getDaily_forecast().get(6).getTmp().getMax());
        int temN1= (int) Float.parseFloat(Bean.getDaily_forecast().get(0).getTmp().getMin());
        int temN2= (int) Float.parseFloat(Bean.getDaily_forecast().get(1).getTmp().getMin());
        int temN3= (int) Float.parseFloat(Bean.getDaily_forecast().get(2).getTmp().getMin());
        int temN4= (int) Float.parseFloat(Bean.getDaily_forecast().get(3).getTmp().getMin());
        int temN5= (int) Float.parseFloat(Bean.getDaily_forecast().get(4).getTmp().getMin());
        int temN6= (int) Float.parseFloat(Bean.getDaily_forecast().get(5).getTmp().getMin());
        int temN7= (int) Float.parseFloat(Bean.getDaily_forecast().get(6).getTmp().getMin());
        Entry d1=new Entry(daily1,temP1);
        Entry d2=new Entry(daily2,temP2);
        Entry d3=new Entry(daily3,temP3);
        Entry d4=new Entry(daily4,temP4);
        Entry d5=new Entry(daily5,temP5);
        Entry d6=new Entry(daily6,temP6);
        Entry d7=new Entry(daily7,temP7);
        Entry n1=new Entry(daily1,temN1);
        Entry n2=new Entry(daily2,temN2);
        Entry n3=new Entry(daily3,temN3);
        Entry n4=new Entry(daily4,temN4);
        Entry n5=new Entry(daily5,temN5);
        Entry n6=new Entry(daily6,temN6);
        Entry n7=new Entry(daily7,temN7);
        entries.add(d1);
        entries.add(d2);
        entries.add(d3);
        entries.add(d4);
        entries.add(d5);
        entries.add(d6);
        entries.add(d7);
        entriesN.add(n1);
        entriesN.add(n2);
        entriesN.add(n3);
        entriesN.add(n4);
        entriesN.add(n5);
        entriesN.add(n6);
        entriesN.add(n7);
    }

}
