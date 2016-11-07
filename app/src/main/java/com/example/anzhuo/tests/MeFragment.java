package com.example.anzhuo.tests;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by anzhuo on 2016/10/8.
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    View view;
    RelativeLayout me_RelativeLayout_Timing;
    TextView me_tv_login;
    RelativeLayout me_RelativeLayout_Set;
    RelativeLayout me_RelativeLayout_Login;
    com.facebook.drawee.view.SimpleDraweeView me_iv_HeadPortrait;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getActivity().getApplicationContext());
        view = inflater.inflate(R.layout.me, container, false);

        me_RelativeLayout_Timing = (RelativeLayout) view.findViewById(R.id.me_RelativeLayout_Timing);
        me_RelativeLayout_Set = (RelativeLayout) view.findViewById(R.id.me_RelativeLayout_Set);
        me_tv_login = (TextView) view.findViewById(R.id.me_tv_login);
        me_iv_HeadPortrait = (SimpleDraweeView) view.findViewById(R.id.me_iv_HeadPortrait);
        me_RelativeLayout_Login = (RelativeLayout) view.findViewById(R.id.me_RelativeLayout_Login);


        me_RelativeLayout_Login.setOnClickListener(this);
        me_RelativeLayout_Set.setOnClickListener(this);
        me_RelativeLayout_Timing.setOnClickListener(this);
        if (getArguments().getString("a") != null && !getArguments().getString("a").equals("")
                && getArguments().getString("b") != null && !getArguments().getString("b").equals("")) {
            me_tv_login.setText(getArguments().getString("a").toString());
            me_iv_HeadPortrait.setImageURI(Uri.parse(getArguments().getString("b").toString()));
        } else if (getArguments().getString("nick") != null && !getArguments().getString("nick").equals("")) {
            me_tv_login.setText(getArguments().getString("nick").toString());
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.me_RelativeLayout_Login:
                Intent i = new Intent(getContext().getApplicationContext(), UserLogin.class);
                startActivity(i);

                break;
            case R.id.me_RelativeLayout_Timing:
                intent = new Intent(getContext().getApplicationContext(),Timing_Activity.class);
                startActivity(intent);
                break;
            case R.id.me_RelativeLayout_Set:
                intent=new Intent(getContext().getApplicationContext(),Inform_Activity.class);
                startActivity(intent);
                break;
        }
    }
}
