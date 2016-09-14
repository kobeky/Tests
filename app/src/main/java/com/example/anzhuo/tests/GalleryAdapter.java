package com.example.anzhuo.tests;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by anzhuo on 2016/9/13.
 */
public class GalleryAdapter extends BaseAdapter {
    List<GalleryInfo>list;
    GalleryInfo info;
    Context context;
    public GalleryAdapter(Context context,List<GalleryInfo>list){
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
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.item_gallery,null);
            viewHolder=new ViewHolder();
            viewHolder.textView= (TextView) view.findViewById(R.id.item_tv_content);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        info=list.get(i);
        viewHolder.textView.setText(info.getTv());


        return view;
    }
    class ViewHolder{
        TextView textView;
    }
}
