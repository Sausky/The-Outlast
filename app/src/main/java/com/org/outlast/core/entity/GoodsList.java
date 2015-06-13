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
    /**吹风机是否打开*/
    public static boolean drier_state = false;
    /**是否获得吹风机*/
    public static boolean drier_get = false;
    /**物品栏中选择物品的表示*/
    public static String name = "none";

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public void onCreate() {
        goodsList = new ArrayList<Goods>();
        /**测试添加物品开始*/
//        Goods goods = new Goods();
//        goods.setName("screwdriver");
//        goods.setSource(R.drawable.screwdriver);
//        goodsList.add(goods);
        /**测试添加物品结束*/
        super.onCreate();
    }
    /**
     * 添加使用物品
//     */
//    public void addPosition(int position){
//        this.position = position;
//    }
//    public int getPosition(){
//        return position;
//    }
    /**
     * 使用物品
     */
    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        GoodsList.name = name;
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

    /**
     * remove thing
     */
    public void removeThing(String name){
        for(int i = 0; i < goodsList.size();i++){
            if(goodsList.get(i).getName().equals(name)){
                goodsList.remove(i);
            }
        }
    }
    /**get the state of drier*/
    public boolean getState(){
        return drier_state;
    }
    /**update the state of drier*/
    public void updateState(boolean state){this.drier_state = state;}

    /**get the state of get drier*/
    public  boolean getDrier(){ return  drier_get;}
    /**update the state of drier*/
    public void updateDrier(boolean drier_get){ this.drier_get = drier_get;};
}
