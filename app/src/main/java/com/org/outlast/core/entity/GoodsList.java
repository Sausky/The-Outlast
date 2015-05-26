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
    /**物品栏中物品编号*/
    public static int position = 10;

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
    /**
     * 添加使用物品
     */
    public void addPosition(int position){
        this.position = position;
    }
    public int getPosition(){
        return position;
    }

    /**
     * 获取物品
     */
    public void addGoods(String name,Integer source){
        Goods goods = new Goods();
        goods.setName(name);
        goods.setSource(source);
        goodsList.add(goods);
    }
}
