package com.example.anzhuo.tests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anzhuo on 2016/10/9.
 */
public class AddCity extends AppCompatActivity {
    ImageButton iv_back;
    GridView gridView;
    String []city={"上海","北京","广州","深圳","厦门","杭州","南京","苏州","三亚","张家口","石家庄","哈尔滨","沈阳","郑州","合肥","南昌","长沙","太原","佛山","宜昌","九江","福州","济南","青岛","重庆","成都","昆明","武汉","无锡","洛阳"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        iv_back= (ImageButton) findViewById(R.id.back_add);
        gridView= (GridView) findViewById(R.id.view_add);
        final SimpleAdapter adapter=new SimpleAdapter(this,getDatas(),R.layout.item_gv_add,new String[]{"city"},new int[]{R.id.tv_gvadd});
        gridView.setAdapter(adapter);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.putExtra("city",city[i]);
                setResult(RESULT_OK,intent);
                finish();

            }
        });
    }
    private List<Map<String,Object>>getDatas(){
         List<Map<String,Object>>list=new ArrayList<Map<String,Object>>() ;
       for (int i=0;i<city.length;i++){
           Map<String,Object>map=new HashMap<>();
           map.put("city",city[i]);
           list.add(map);
       }
        return list;


    }
}
