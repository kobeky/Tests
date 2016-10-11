package com.example.anzhuo.tests;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by anzhuo on 2016/9/26.
 */
public class Screen {
    public static Bitmap takeScreenShot(Activity activity){
        View v=activity.getWindow().getDecorView();
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        Rect r=new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        int statusHeight=r.top;
        int w=activity.getWindowManager().getDefaultDisplay().getWidth();
        int h=activity.getWindowManager().getDefaultDisplay().getHeight();
        Bitmap bitmap=Bitmap.createBitmap(v.getDrawingCache(),0,statusHeight,w,h-statusHeight);
        v.destroyDrawingCache();
        return bitmap;
    }

}
