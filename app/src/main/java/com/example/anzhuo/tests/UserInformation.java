package com.example.anzhuo.tests;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by anzhuo on 2016/9/28.
 */
public class UserInformation extends BmobUser {
  private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
