package com.example.anzhuo.tests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by anzhuo on 2016/9/28.
 */
public class UserRegist extends AppCompatActivity {
    ImageView user_regist_back;
    EditText user_regist_username;
    EditText user_regist_userword;
    EditText user_regist_userwords;
    EditText user_regist_nickname;
    Button user_regist_regist;

    UserInformation userInformation = new UserInformation();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "56030dba59b60d59c0c053a882d0c088");
        setContentView(R.layout.user_regist);

        user_regist_back = (ImageView) findViewById(R.id.user_regist_back);
        user_regist_username = (EditText) findViewById(R.id.user_regist_username);
        user_regist_userword = (EditText) findViewById(R.id.user_regist_userword);
        user_regist_userwords = (EditText) findViewById(R.id.user_regist_userwords);
        user_regist_regist = (Button) findViewById(R.id.user_regist_regist);
        user_regist_nickname = (EditText) findViewById(R.id.user_regist_nickname);

        user_regist_userword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start >= before) {
                    if (user_regist_username.getText().toString().equals("")) {
                        Toast.makeText(UserRegist.this, "请输入账号！", Toast.LENGTH_SHORT).show();
                    } else if (user_regist_nickname.getText().toString().equals("")) {
                        Toast.makeText(UserRegist.this, "请输入你的昵称！", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        user_regist_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        user_regist_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_regist_username.getText().toString().equals("") || user_regist_nickname.getText().toString().equals("")) {
                    Toast.makeText(UserRegist.this, "请输入账号或昵称！", Toast.LENGTH_SHORT).show();
                } else {
                    if (user_regist_userword != null && user_regist_userword.getText().toString().equals(user_regist_userwords.getText().toString())) {
                        userInformation.setUsername(user_regist_username.getText().toString());
                        userInformation.setPassword(user_regist_userword.getText().toString());
                        userInformation.setNickname(user_regist_nickname.getText().toString());
                        userInformation.signUp(new SaveListener<UserInformation>() {

                            @Override
                            public void done(UserInformation bmobUser, BmobException e) {
                                if (e == null) {

                                    UserInformation userInformations = BmobUser.getCurrentUser(UserInformation.class);
                                    Intent intent = new Intent(UserRegist.this, UserLogin.class);
                                    intent.putExtra("name", userInformations.getUsername().toString());
                                    startActivity(intent);
                                    Toast.makeText(UserRegist.this, "注册成功！", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    if (e.getMessage().toString().equals("username '" + user_regist_username.getText().toString() + "' already taken.")) {
                                        Toast.makeText(UserRegist.this, "注册失败：该用户名已存在", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(UserRegist.this, "注册失败：" + e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });

                    } else {
                        Toast.makeText(UserRegist.this, "请输入相同密码！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
