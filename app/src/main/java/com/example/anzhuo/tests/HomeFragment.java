package com.example.anzhuo.tests;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anzhuo.tests.info.GvInfo;
import com.example.anzhuo.tests.info.HeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anzhuo on 2016/9/28.
 */
public class HomeFragment extends Fragment {
    ViewPager viewPager;
    List<Fragment> mlist = new ArrayList<>();
    HomePage homePage;
    HomePageAdapter adapter;
    Weather weather;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_item, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager_home);
        adapter = new HomePageAdapter(getChildFragmentManager());
        Data da = new Data(getContext());
        da.SetMap(handler);
        weather = new Weather();
        adapter.setFragments(mlist);
        viewPager.setAdapter(adapter);
        return view;
    }
    public void addCityFragment(GvInfo gvInfo) {
        weather.setWeather(handler, gvInfo.getCityName());
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==getActivity().RESULT_OK && requestCode==10){
            GvInfo gvInfo = (GvInfo) data.getSerializableExtra("gvInfo");
            if (gvInfo != null) {
                addCityFragment(gvInfo);
            }
        }


    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    weather.setWeather(handler, msg.obj.toString().substring(0, msg.obj.toString().length() - 1));
                    break;
                case 1:
                    List<HeBean.HeWeather> list = (List<HeBean.HeWeather>) msg.obj;
                    homePage = new HomePage();
                    Bundle bun = new Bundle();
                    bun.putSerializable("bean", list.get(0));
                    homePage.setArguments(bun);
                    mlist.add(homePage);
                    adapter.notifyDataSetChanged();
                    break;

            }
        }
    };
}
