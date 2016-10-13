package com.example.anzhuo.tests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by anzhuo on 2016/9/28.
 */
public class UserLogin extends AppCompatActivity {
    ImageView user_login_back;
    ImageView user_login_iv_user;
    EditText user_login_username;
    ImageView user_login_iv_key;
    EditText user_login_userword;
    Button user_login_login;
    ImageView user_login_qqlogin;
    TextView user_login_regist;

    Tencent tencent;
    IUiListener loginlistener;
    IUiListener userlistener;

    String nickname = "";
    String figureurl="";

    UserInformation userInformation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "56030dba59b60d59c0c053a882d0c088");
        setContentView(R.layout.user_login);

        userInformation=new UserInformation();
        tencent = Tencent.createInstance("1105679018", this.getApplicationContext());


        user_login_back = (ImageView) findViewById(R.id.user_login_back);
        user_login_username = (EditText) findViewById(R.id.user_login_username);
        user_login_userword = (EditText) findViewById(R.id.user_login_userword);
        user_login_login = (Button) findViewById(R.id.user_login_login);
        user_login_qqlogin = (ImageView) findViewById(R.id.user_login_qqlogin);
        user_login_regist = (TextView) findViewById(R.id.user_login_regist);
        user_login_iv_key = (ImageView) findViewById(R.id.user_login_iv_key);
        user_login_iv_user = (ImageView) findViewById(R.id.user_login_iv_user);


        final Intent i = getIntent();
        String name = i.getStringExtra("name");
        user_login_username.setText(name);

        user_login_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start >= before) {
                    user_login_iv_user.setImageResource(R.drawable.login_user_hightlighted);
                } else {
                    user_login_iv_user.setImageResource(R.drawable.login_user);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        user_login_userword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start >= before) {
                    user_login_iv_key.setImageResource(R.drawable.login_key_hightlighted);
                } else {
                    user_login_iv_key.setImageResource(R.drawable.login_keys);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        user_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_login_username.getText().toString().equals("")){
                    Toast.makeText(UserLogin.this, "请输入账号！", Toast.LENGTH_SHORT).show();
                }else {
                userInformation.setUsername(user_login_username.getText().toString());
                userInformation.setPassword(user_login_userword.getText().toString());
                userInformation.login(new SaveListener<UserInformation>() {
                    @Override
                    public void done(UserInformation bmobUser, BmobException e) {
                        if (e == null) {
                            Toast.makeText(UserLogin.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Intent intents = new Intent(UserLogin.this, MainActivity.class);
                            intents.putExtra("nick", bmobUser.getNickname().toString());
                            startActivity(intents);
                            finish();
                        } else {
                                Toast.makeText(UserLogin.this, "账号不存在", Toast.LENGTH_SHORT).show();
                        }
                    }
                });}
            }
        });

        user_login_qqlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tencent.isSessionValid()) {
                    //"all"表示所有登录权限
                    tencent.login(UserLogin.this, "all", loginlistener);

                }
            }
        });
        //登录的回调函数
        loginlistener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                JSONObject jsonObject = (JSONObject) o;
                try {
                    String openId = jsonObject.getString("openid");
                    String accessToken = jsonObject.getString("access_token");
                    String expires = jsonObject.getString("expires_in");
                    tencent.setOpenId(openId);
                    tencent.setAccessToken(accessToken, expires);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
            }

            @Override
            public void onCancel() {
            }
        };

        userlistener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                JSONObject Object = (JSONObject) o;
                try {
                    nickname = Object.getString("nickname").toString();
                    figureurl = Object.getString("figureurl_qq_1").toString();
                    Intent intent = new Intent(UserLogin.this, MainActivity.class);
                    intent.putExtra("a",nickname);
                    intent.putExtra("b",figureurl);
                    startActivity(intent);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
            }

            @Override
            public void onCancel() {
            }
        };
        user_login_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserLogin.this, UserRegist.class);
                startActivity(i);

            }
        });

        user_login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            if (resultCode == -1) {
                Tencent.onActivityResultData(requestCode, resultCode, data, loginlistener);
                Tencent.handleResultData(data, loginlistener);
                UserInfo userInfo = new UserInfo(UserLogin.this, tencent.getQQToken());
                userInfo.getUserInfo(userlistener);
            }
        }
    }
}
