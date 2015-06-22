package com.org.outlast.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.org.outlast.R;
import com.org.outlast.core.entity.Goods;
import com.org.outlast.core.entity.GoodsList;
import com.org.outlast.ui.widget.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class Deposit extends Activity{
    public GoodsList data;
    public List<Goods> goodsList;
    public int[] imgs;
    public String[] tags;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
        data = (GoodsList) getApplication();
        goodsList = data.getGoodsList();
        imgs = new int[20];
        for (int i = 0; i < goodsList.size(); i++) {
            imgs[i] = goodsList.get(i).getSource();
        }

        //为物品配上标签
        tags = new String[20];
        for (int i = 0; i < goodsList.size(); i++){
            tags[i] = goodsList.get(i).getName();

        }
        GridView gv = (GridView)findViewById(R.id.goodsGrid);
        //为GridView设置适配器
        gv.setAdapter(new MyAdapter(this,imgs,tags));
        //注册监听事件
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener()
         {
         public void onItemClick(AdapterView<?> parent, View v, int position, long id)
         {
             //data.addPosition(position);
             data.setName(String.valueOf(v.getTag()));
             String name = data.getName();
             switch (name){
                 case "drier":Toast.makeText(getApplication(),"开始使用吹风机",Toast.LENGTH_SHORT).show();
                     break;
                 case "ring":Toast.makeText(getApplication(),"开始查看戒指",Toast.LENGTH_SHORT).show();
                     break;
                 case "screwdriver":Toast.makeText(getApplication(),"开始使用螺丝刀",Toast.LENGTH_SHORT).show();
                     break;
                 case "shovel":Toast.makeText(getApplication(),"开始使用铲子",Toast.LENGTH_SHORT).show();
                     break;
                 case "none":break;
             }
             Intent intent = new Intent();
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             if(data.getName().equals("ring")){
                intent.setClass(Deposit.this, RingBig.class);
                 startActivity(intent);

             }else {
                 intent.setClass(Deposit.this,MainActivity.class);
                 startActivity(intent);
                 finish();
             }


         }
         });
        //返回主界面
//        back = (ImageView) findViewById(R.id.back);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

}
