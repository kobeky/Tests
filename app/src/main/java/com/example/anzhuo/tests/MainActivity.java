package com.example.anzhuo.tests;


import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;



public class MainActivity extends AppCompatActivity {
FrameLayout frameLayout;
HomePage homePage;
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
                if (homePage==null){
                    homePage=new HomePage();
                    fragmentTransaction.add(R.id.fl,homePage);
                }else {
                    fragmentTransaction.show(homePage);
                }
            break;
        }
        fragmentTransaction.commit();
    }

    private void HideTransaction(FragmentTransaction fragmentTransaction) {
        if (homePage!=null){
            fragmentTransaction.hide(homePage);
        }
    }
}
