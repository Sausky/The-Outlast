package com.org.outlast.ui.view.animationMove;


/**
 * Created by shen on 15/5/16.
 */
public class Location implements Cloneable{
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    protected Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object location){
        if (location instanceof Location) {
            return this.getX() == ((Location)location).getX() && this.getY() == ((Location)location).getY();
        }else{
            return false;
        }
    }
}