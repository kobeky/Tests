package com.example.anzhuo.tests;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;



public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    HomePage homePage;
    LifeFragment lifeFragment;
    MeFragment meFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    RadioGroup radioGroup;
HomeFragment homeFragment;
    RadioButton rb_me;

String name="";
    String uri="";
    String nick="";
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup= (RadioGroup) findViewById(R.id.main_rg_group);
        frameLayout= (FrameLayout) findViewById(R.id.fl);
        rb_me= (RadioButton) findViewById(R.id.rb_me);

        Intent intent=getIntent();
        if(intent.getStringExtra("a")!=null&&!intent.getStringExtra("a").equals("")&&intent.getStringExtra("b")!=null&&!intent.getStringExtra("b").equals("")){
          name=intent.getStringExtra("a").toString();
            uri=intent.getStringExtra("b").toString();
           rb_me.setChecked(true);
            showFragment(2);
        }else if(intent.getStringExtra("nick")!=null&&!intent.getStringExtra("nick").equals("")){
            nick=intent.getStringExtra("nick").toString();
            rb_me.setChecked(true);
            showFragment(2);
        }else {
        showFragment(0);
            }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home:
                       showFragment(0);
                        break;
                    case R.id.rb_life:
                        showFragment(1);
                        break;
                    case R.id.rb_me:
                        showFragment(2);
                        break;
                }
            }
        });


    }

    private void showFragment(int i) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
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
            case 1:
                if (lifeFragment == null) {
                    lifeFragment = new LifeFragment();
                    fragmentTransaction.add(R.id.fl, lifeFragment);
                } else {
                    fragmentTransaction.show(lifeFragment);
                }
                break;
            case 2:
                if (meFragment == null) {
                    meFragment = new MeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("a", name);
                    bundle.putString("b",uri);
                    bundle.putString("nick",nick);
                    meFragment.setArguments(bundle);
                    fragmentTransaction.add(R.id.fl, meFragment);
                } else {
                    fragmentTransaction.show(meFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    private void HideTransaction(FragmentTransaction fragmentTransaction) {
        if (homeFragment!=null){
            fragmentTransaction.hide(homeFragment);
        }
        if (lifeFragment != null) {
            fragmentTransaction.hide(lifeFragment);
        }
        if (meFragment != null) {
            fragmentTransaction.hide(meFragment);
        }
    }
}
