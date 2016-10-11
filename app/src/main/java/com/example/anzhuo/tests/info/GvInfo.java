package com.example.anzhuo.tests.info;

import com.github.mikephil.charting.data.Entry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by anzhuo on 2016/9/28.
 */
public class GvInfo implements Serializable{
String cityName;
    int Image;
    String Range;


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getRange() {
        return Range;
    }

    public void setRange(String range) {
        Range = range;
    }
}
