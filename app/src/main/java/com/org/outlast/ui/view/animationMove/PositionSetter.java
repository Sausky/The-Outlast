package com.org.outlast.ui.view.animationMove;


import android.util.Log;

/**
 * Created by shen on 15/5/16.
 */
public class PositionSetter implements Runnable {
    private PositionManager positionManager;

    public PositionSetter(PositionManager positionManager){
        this.positionManager = positionManager;
    }

    private volatile int x;
    private volatile int y;

    public synchronized void setPosition(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {
        while (true){
            Log.d("debugging","start wait");

            positionManager.waitForSetPosition();

            Log.d("debugging", "complete wait");

            synchronized (this) {
                positionManager.setPosition(x, y);
            }
        }
    }
}
