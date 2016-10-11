package com.example.anzhuo.tests;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.anzhuo.tests.info.GvInfo;


public class MainActivity extends AppCompatActivity {
FrameLayout frameLayout;
//HomePage homePage;
    HomeFragment homeFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    RadioGroup radioGroup;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        radioGroup= (RadioGroup) findViewById(R.id.main_rg_group);
        frameLayout= (FrameLayout) findViewById(R.id.fl);
        showFragment(0);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home:
                       showFragment(0);
                        break;
                }
            }
        });


    }



    private  void showFragment(int i){
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        HideTransaction(fragmentTransaction);
        switch (i){
            case 0:
                if (homeFragment==null){
                   homeFragment=new HomeFragment();
                    fragmentTransaction.add(R.id.fl,homeFragment);
                }else {
                    fragmentTransaction.show(homeFragment);
                }
            break;
        }
        fragmentTransaction.commit();
    }

    private void HideTransaction(FragmentTransaction fragmentTransaction) {
        if (homeFragment!=null){
            fragmentTransaction.hide(homeFragment);
        }
    }

}
