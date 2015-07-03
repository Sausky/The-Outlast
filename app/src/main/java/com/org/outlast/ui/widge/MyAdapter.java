package com.org.outlast.ui.widge;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


/**
 * Created by sausky1 on 15-5-14.
 */
public class MyAdapter extends BaseAdapter {
    //上下文对象
    private Context context;
    private int[] imgs;
    private String[] tag;
    public MyAdapter(Context context, int[] imgs, String[] tag){
        this.imgs = imgs;
        this.context = context;
        this.tag = tag;
    }
    public int getCount() {
        return imgs.length;
    }

    public Object getItem(int item) {
        return item;
    }

    public long getItemId(int id) {
        return id;
    }

    //创建View方法
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position < 26) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(100, 100));//设置ImageView对象布局
                imageView.setAdjustViewBounds(true);//设置边界对齐
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);//设置刻度的类型
                imageView.setPadding(8, 8, 8, 8);//设置间距
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(imgs[position]);//为ImageView设置图片资源
            imageView.setTag(tag[position]);
            return imageView;
        }return convertView;

    }
}

