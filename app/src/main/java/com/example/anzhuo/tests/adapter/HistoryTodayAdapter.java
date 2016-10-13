package com.example.anzhuo.tests.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anzhuo.tests.R;
import com.example.anzhuo.tests.info.HistoryBean;
import com.example.anzhuo.tests.info.HistoryTodayInfo;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by anzhuo on 2016/9/22.
 */
public class HistoryTodayAdapter extends BaseAdapter{
    List<HistoryTodayInfo>list;
  HistoryBean bean;
    Context context;
    ViewHolder viewHolder;
    HistoryTodayInfo historyTodayInfo;
    public  HistoryTodayAdapter(Context context,List<HistoryTodayInfo>list){
        this.list=list;
       this.context=context;
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
        bean=new HistoryBean();
        viewHolder=new ViewHolder();
        if(convertView==null||convertView.getTag()==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.historytoday_item,null);
            viewHolder.history_content= (TextView) convertView.findViewById(R.id.history_content);
            viewHolder.history_pic= (SimpleDraweeView) convertView.findViewById(R.id.history_pic);
            viewHolder.history_title= (TextView) convertView.findViewById(R.id.history_title);
            convertView.setTag(viewHolder);
        }
viewHolder= (ViewHolder) convertView.getTag();
        historyTodayInfo=list.get(position);
        viewHolder.history_title.setText(historyTodayInfo.getTitle());
        viewHolder.history_content.setText(historyTodayInfo.getContent());
//        Picasso.with(context).load(bean.getResult().get(position).getPic()).into(viewHolder.history_pic);
viewHolder.history_pic.setImageURI(Uri.parse(historyTodayInfo.getPic()));
        return convertView;
    }
    class ViewHolder{
        TextView history_title;
        com.facebook.drawee.view.SimpleDraweeView history_pic;
      TextView  history_content;
    }
}
