package com.example.anzhuo.tests;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anzhuo.tests.info.GvInfo;

import java.util.List;

/**
 * Created by anzhuo on 2016/10/9.
 */
public class GvAdapter extends BaseAdapter {
    Context context;
    List<GvInfo>list;
    private boolean isShowDelete;

    public  GvAdapter(Context context,List<GvInfo>list) {
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return (list.size()+1);
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
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_gv_compile,null);
            viewHolder=new ViewHolder();
            viewHolder.form=(ImageView)view.findViewById(R.id.iv_form_compile);
            viewHolder.cityName=(TextView)view.findViewById(R.id.tv_cityCompile);
            viewHolder.Range= (TextView) view.findViewById(R.id.tv_RangeCompile);
            viewHolder.deleteImage=(ImageView)view.findViewById(R.id.delete_markView);
            view.setTag(viewHolder);//设置tag
        }else{
            viewHolder= (ViewHolder) view.getTag();//由tag获取对象
        }
        if(i<list.size()) {
            GvInfo info= (GvInfo) getItem(i);
            viewHolder.cityName.setText(info.getCityName());
            viewHolder.form.setImageResource(info.getImage());
            viewHolder.Range.setText(info.getRange());
            viewHolder.deleteImage.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);//根据标识位isShowDelete决定是否显示删除图片按钮
            if (isShowDelete) {
                viewHolder.deleteImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.remove(i);
                        setIsShowDelete(false);
                    }
                });
            }
        }else{
            viewHolder.cityName.setText("点击添加");
            viewHolder.form.setImageResource(R.drawable.message_add_background);
            viewHolder.Range.setText(null);
            viewHolder.deleteImage.setVisibility(View.GONE);
        }
        return view;
    }
    //创建ViewHolder类
    class ViewHolder{
       TextView cityName;
        TextView Range;
        ImageView form,deleteImage;
    }
    public void setIsShowDelete(boolean isShowDelete) {
        this.isShowDelete = isShowDelete;
        notifyDataSetChanged();
    }
}
