package com.example.anzhuo.tests.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anzhuo.tests.R;
import com.example.anzhuo.tests.info.CalendarInfo;

import java.util.List;

/**
 * Created by anzhuo on 2016/9/13.
 */
public class CalendarAdapter extends BaseAdapter {
    CalendarInfo calendarInfo;
    List<CalendarInfo> list;
    LayoutInflater layoutInflater;
    ViewHolder viewHolder;

    public CalendarAdapter(Context context, List<CalendarInfo> list) {
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder = new ViewHolder();
        if (convertView == null || convertView.getTag() == null) {
            convertView = layoutInflater.inflate(R.layout.calendar, null);
            viewHolder.calendar_tv_day = (TextView) convertView.findViewById(R.id.calendar_tv_day);
            viewHolder.calendar_tv_month = (TextView) convertView.findViewById(R.id.calendar_tv_month);
            viewHolder.calendar_tv_date = (TextView) convertView.findViewById(R.id.calendar_tv_date);
            viewHolder.calendar_tv_lunaryear = (TextView) convertView.findViewById(R.id.calendar_tv_lunaryear);
            viewHolder.calendar_tv_weekday = (TextView) convertView.findViewById(R.id.calendar_tv_weekday);
            viewHolder.calendar_tv_holiday = (TextView) convertView.findViewById(R.id.calendar_tv_holiday);
            viewHolder.calendar_tv_suit = (TextView) convertView.findViewById(R.id.calendar_tv_suit);
            viewHolder.calendar_tv_avoid = (TextView) convertView.findViewById(R.id.calendar_tv_avoid);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        calendarInfo = list.get(position);
        viewHolder.calendar_tv_day.setText(calendarInfo.getDay());
        viewHolder.calendar_tv_month.setText(calendarInfo.getMonth());
        viewHolder.calendar_tv_lunaryear.setText(calendarInfo.getLunaryear());
        viewHolder.calendar_tv_date.setText(calendarInfo.getDate());
        viewHolder.calendar_tv_weekday.setText(calendarInfo.getWeekday());
        viewHolder.calendar_tv_holiday.setText(calendarInfo.getHoliday());
        viewHolder.calendar_tv_suit.setText(calendarInfo.getSuit());
        viewHolder.calendar_tv_avoid.setText(calendarInfo.getAvoid());

        return convertView;
    }

    public class ViewHolder {
        TextView calendar_tv_day;
        TextView calendar_tv_month;
        TextView calendar_tv_date;
        TextView calendar_tv_lunaryear;
        TextView calendar_tv_weekday;
        TextView calendar_tv_holiday;
        TextView calendar_tv_suit;
        TextView calendar_tv_avoid;
    }
}
