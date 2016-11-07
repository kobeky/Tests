package com.example.anzhuo.tests;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    ProgressDialog progressDialog;
    BroadcastReceiver receiver;
    SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_item, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager_home);
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            progressDialog = ProgressDialog.show(getContext(), "请稍等..", "努力加载中...", true);
            adapter = new HomePageAdapter(getChildFragmentManager());
            Data da = new Data(getContext());
            da.SetMap(handler);
            weather = new Weather();
            adapter.setFragments(mlist);
            viewPager.setAdapter(adapter);
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "当前网络不可用，请检查网络设置！", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.anzhuo.tests.CityName");
        receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
               GvInfo gvInfo= (GvInfo) intent.getSerializableExtra("gvInfo");
               addCityFragment(gvInfo);
            }
        };
        getActivity().registerReceiver(receiver,filter);
    }


    public void addCityFragment(GvInfo gvInfo) {
        weather.setWeather(handler, gvInfo.getCityName());
        Log.i("LM","cityName:"+gvInfo.getCityName());
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
                    progressDialog.dismiss();
                    break;

            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}
