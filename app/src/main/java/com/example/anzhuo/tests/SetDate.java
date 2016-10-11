package com.example.anzhuo.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by anzhuo on 2016/9/27.
 */
public class SetDate {
  public static String setDate(String time){
      SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
      try {
          Date date = sdr.parse(time);
          Calendar cd = Calendar.getInstance();
          cd.setTime(date);
          int mydate = cd.get(Calendar.DAY_OF_WEEK);
          String week = null;
          if (mydate == 1) {
              week = "星期日";
          } else if (mydate == 2) {
              week = "星期一";
          } else if (mydate == 3) {
              week = "星期二";
          } else if (mydate == 4) {
              week = "星期三";
          } else if (mydate == 5) {
              week = "星期四";
          } else if (mydate == 6) {
              week = "星期五";
          } else if (mydate == 7) {
              week = "星期六";
          }
          return week;
      } catch (ParseException e) {
          e.printStackTrace();
      }


      return  null;

}

}
