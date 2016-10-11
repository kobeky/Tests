package com.example.anzhuo.tests;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anzhuo.tests.info.HeBean;

/**
 * Created by anzhuo on 2016/9/23.
 */
public class ParticularsFragment extends Fragment {

    private View view;
    ImageView iv_form;
    TextView temMax;
    TextView temMin;
    TextView tv_form;
    TextView windDir;
    TextView windSr;
    TextView tv_sr;
    TextView tv_ss;
    TextView press;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.viewpager_item,container,false);
        iv_form= (ImageView) view.findViewById(R.id.iv_form_viewPager);
        temMax= (TextView) view.findViewById(R.id.tv_temMax);
        temMin= (TextView) view.findViewById(R.id.tv_temMin);
        tv_form= (TextView) view.findViewById(R.id.tv_form_viewPager);
        windDir= (TextView) view.findViewById(R.id.tv_dir_wind);
        windSr= (TextView) view.findViewById(R.id.tv_sc_wind);
        tv_sr= (TextView) view.findViewById(R.id.tv_sr_viewPager);
        tv_ss= (TextView) view.findViewById(R.id.tv_ss_viewPager);
        press= (TextView) view.findViewById(R.id.tv_press_viewPager);
        refresh();
        return view;
    }


    public void refresh(){
        HeBean.HeWeather.DailyForecastBean bean = (HeBean.HeWeather.DailyForecastBean) getArguments().getSerializable("bean");
        press.setText("气压："+bean.getPres());
        tv_ss.setText("日落时间："+bean.getAstro().getSs());
        tv_sr.setText("日出时间："+bean.getAstro().getSr());
        temMax.setText(bean.getTmp().getMax()+"°"+"/");
        temMin.setText(bean.getTmp().getMin()+"°");
        tv_form.setText("天气状态："+bean.getCond().getTxt_d());
        windDir.setText("风向："+bean.getWind().getDir());
        windSr.setText("风速："+bean.getWind().getSpd());
        FormSetImage.setImage(bean.getCond().getTxt_d(),iv_form);

    }

}
