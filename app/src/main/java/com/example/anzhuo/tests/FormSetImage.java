package com.example.anzhuo.tests;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by anzhuo on 2016/9/27.
 */
public class FormSetImage {
    public static void setImage(String string, ImageView view){
        if (string.equals("晴")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("多云")){
            view.setImageResource(R.drawable.w1);
        }else if (string.equals("阴")){
            view.setImageResource(R.drawable.w2);
        }else if (string.equals("小雨")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("中雨")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("大雨")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("暴雨")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("大暴雨")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("雾")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("霾")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("雷阵雨")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("冰雹")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("小雪")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("中雪")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("大雪")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("暴雪")){
            view.setImageResource(R.drawable.w0);
        }else if (string.equals("大暴雪")){
            view.setImageResource(R.drawable.w0);
        } else if (string.equals("雨夹雪")){
            view.setImageResource(R.drawable.w0);
        }
    }
}
