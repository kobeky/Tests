package com.example.anzhuo.tests;

import android.widget.ImageView;

/**
 * Created by anzhuo on 2016/10/9.
 */
public class SetImageView {
    public static int setImage(String string){
        if (string.equals("晴")){
            return R.drawable.w0;
        }else if (string.equals("多云")){
           return R.drawable.w1;
        }else if (string.equals("阴")){
            return R.drawable.w2;
        }else if (string.equals("小雨")){
           return R.drawable.w7;
        }else if (string.equals("中雨")){
           return R.drawable.w8;
        }else if (string.equals("大雨")){
            return R.drawable.w8;
        }else if (string.equals("暴雨")){
            return R.drawable.w8;
        }else if (string.equals("大暴雨")){
           return R.drawable.w8;
        }else if (string.equals("雾")){
           return R.drawable.w20;
        }else if (string.equals("霾")){
           return R.drawable.w45;
        }else if (string.equals("阵雨")){
           return R.drawable.w4;
        }else if (string.equals("冰雹")){
           return R.drawable.w15;
        }else if (string.equals("小雪")){
           return R.drawable.w16;
        }else if (string.equals("中雪")){
           return R.drawable.w16;
        }else if (string.equals("大雪")){
            return R.drawable.w16;
        }else if (string.equals("暴雪")){
            return R.drawable.w16;
        }else if (string.equals("大暴雪")){
          return R.drawable.w16;
        } else if (string.equals("雨夹雪")){
           return R.drawable.w6;
        }
        return 0;
    }
}
