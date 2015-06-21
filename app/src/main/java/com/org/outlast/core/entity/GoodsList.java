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
    public  int position = 10;
    /**吹风机是否打开*/
    public  boolean drier_state = false;
    /**是否获得吹风机*/
    public  boolean drier_get = false;
    /**物品栏中选择物品的表示*/
    public  String name = "none";
    /**是否获得最终的密码*/
    public  boolean door_state = false;
    /**是否获得铲子*/
    public  boolean shovel_state = false;

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

    public  boolean isShovel_state() {
        return shovel_state;
    }

    public  void setShovel_state(boolean shovel_state) {
        this.shovel_state = shovel_state;
    }

    public  boolean isDoor_state() {
        return door_state;
    }

    public  void setDoor_state(boolean door_state) {
        this.door_state = door_state;
    }

    /**
     * 使用物品
     */
    public  String getName() {
        return name;
    }

    public  void setName(String name) {
        this.name = name;
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
