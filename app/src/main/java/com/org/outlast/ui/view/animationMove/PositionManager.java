package com.org.outlast.ui.view.animationMove;


import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by shen on 15/5/16.
 */
public class PositionManager {
    private volatile boolean changing=false;
    private Location loc = new Location(0,0);
    private Location backup;

    volatile CountDownLatch changeLatch;

    public void setPosition(int x, int y){
//        using clone to reduce the cost of creating instance
        backup = (Location)loc.clone();

        changeLatch = new CountDownLatch(1);

        Log.d("debugging","start set position"+x+" "+y);

        changing = true;
        try {
//            wait the thread to use backup
//            wait();

            changeLatch.await();

            Log.d("debugging", "complete setting wait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        loc.setXY(x, y);

//        change complete
        changing = false;
    }

    public synchronized void waitForSetPosition(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void prepareSetPositon(){
        Log.d("debugging","prepare notify");
        notify();
    }

    public Location getLocation(){
        if (changing) {
            while (changeLatch == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            Log.d("debugging","count down");
            changeLatch.countDown();
            return (Location)backup.clone();
        }
        return loc;
    }
}
