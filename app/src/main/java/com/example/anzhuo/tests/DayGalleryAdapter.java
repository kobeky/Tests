package com.example.anzhuo.tests;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anzhuo.tests.info.DayGalleryInfo;

import java.util.List;

/**
 * Created by anzhuo on 2016/9/20.
 */
public class DayGalleryAdapter extends BaseAdapter{
    List<DayGalleryInfo>list;
    Context context;
    DayGalleryInfo info;
    public DayGalleryAdapter(Context context,List<DayGalleryInfo>list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.home_gallery_day,null);
            viewHolder=new ViewHolder();
            viewHolder.date= (TextView) view.findViewById(R.id.day_tv_date);
            viewHolder.formD= (TextView) view.findViewById(R.id.day_tv_formD);
            viewHolder.time= (TextView) view.findViewById(R.id.day_tv_time);
            viewHolder.formN= (TextView) view.findViewById(R.id.day_tv_formN);
            viewHolder.infoD= (ImageView) view.findViewById(R.id.day_iv_formD);
            viewHolder.infoN= (ImageView) view.findViewById(R.id.day_iv_formN);
            viewHolder.widr= (TextView) view.findViewById(R.id.day_tv_widr);
            viewHolder.sc= (TextView) view.findViewById(R.id.day_tv_sc);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        info=list.get(i);
        viewHolder.date.setText(info.getDate());
        viewHolder.time.setText(info.getTime());
        viewHolder.formD.setText(info.getFormDay());
       viewHolder.infoD.setImageResource(info.getImageDay());
        viewHolder.infoN.setImageResource(info.getImageNight());
        viewHolder.formN.setText(info.getFormNight());
        viewHolder.widr.setText(info.getWindDirection());
        viewHolder.sc.setText(info.getSc());
        return view;
    }
    class ViewHolder{
        TextView date;
        TextView time;
        TextView formD;
        ImageView infoD;
        ImageView infoN;
        TextView formN;
        TextView widr;
        TextView sc;
    }
}
