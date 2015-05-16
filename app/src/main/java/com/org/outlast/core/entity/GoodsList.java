package com.org.outlast.core.entity;

import android.app.Application;

import com.org.outlast.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sausky1 on 15-5-14.
 */
public class GoodsList extends Application {
    public List<Goods> goodsList;
    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public void onCreate() {
        goodsList = new ArrayList<Goods>();
        /**测试添加物品开始*/
        Goods goods = new Goods();
        goods.setName("screwdriver");
        goods.setSource(R.drawable.screwdriver);
        goodsList.add(goods);
        /**测试添加物品结束*/
        super.onCreate();
    }
}