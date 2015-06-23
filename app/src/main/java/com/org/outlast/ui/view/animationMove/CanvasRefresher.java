package com.org.outlast.ui.view.animationMove;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.Animation;

import com.org.outlast.ui.view.graphics.MyImageResources;

/**
 * Created by shen on 15/5/16.
 */
public class CanvasRefresher extends SurfaceView implements SurfaceHolder.Callback, Runnable{
    private PositionManager positionManager = new PositionManager();
    private PositionSetter positionSetter = new PositionSetter(positionManager);

    private volatile boolean moving = false;

    private volatile Boolean stopRefresh = false;

    private SurfaceHolder sfh;
    private Thread th;
    private Canvas canvas;
    private int controll_flag = 0;

    private int GirlW,GirlH;
    private Bitmap[] right_girls = new Bitmap[2];
    private Bitmap[] left_girls = new Bitmap[2];
    private MyImageResources imageResource;



    public void moveTo(int x,int y){
        keepMove();
        positionSetter.setPosition(x, y);

//      sleep a while
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        positionManager.prepareSetPositon();
    }

    public CanvasRefresher(Context context){
        super(context);
        init();
    }

    public CanvasRefresher(Context context, AttributeSet attrs) {     //Constructor that is called when inflating a view from XML
        super(context, attrs);
        init();

    }
    public CanvasRefresher(Context context, AttributeSet attrs, int defStyle) {     //Perform inflation from XML and apply a class-specific base style
        super(context,attrs,defStyle);
        init();
    }

    public void init() {
        th = new Thread(this);
        sfh = this.getHolder();
        sfh.addCallback(this);
        setZOrderOnTop(true);    // necessary
        sfh.setFormat(PixelFormat.TRANSPARENT);
        this.setKeepScreenOn(true);

        imageResource = MyImageResources.getInstance(this.getResources().getAssets());

        right_girls = imageResource.girl_right_move;
        left_girls = imageResource.girl_left_move;

        GirlW = imageResource.girl_width;
        GirlH = imageResource.girl_height;

        Thread setterTh = new Thread(positionSetter);
        setterTh.start();
    }


    public void run(){

        int base_pace = 3;

//        if x distance and y distance both smaller than 0.75*base_pace, then stop move
        double sqrt2_div2 = 0.75;
//        double min_dist = sqrt2_div2*base_pace;

        int newX,newY;
        Bitmap[] current_bitmaps = new Bitmap[2];

        int min_dist = 10;

        int x_pace = 0;
        int y_pace = 0;

        Location destLocation = positionManager.getLocation();
        Location currentLocation = new Location(0,0);

//        used to check if the destLocation has changed
        Location checkLocation = destLocation;


        while(true){

            controll_flag = (++controll_flag)%2;

            destLocation = positionManager.getLocation();


            Log.d("debug_helper","current:"+currentLocation.getX()+":"+currentLocation.getY()+",  dest:"+destLocation.getX()+":"+destLocation.getY());

            int x_dist = destLocation.getX() - currentLocation.getX();
            int y_dist = destLocation.getY() - currentLocation.getY();

            if ((Math.abs(x_dist)>min_dist ||
                    Math.abs(y_dist)>min_dist)&&
                    moving){

                if (x_dist > 0)
                    current_bitmaps = right_girls;
                else
                    current_bitmaps = left_girls;

                if (x_dist == 0 && y_dist!=0) {
                    x_pace = 0;
                    y_pace = y_dist > 0 ? 3 : -3;
                } else if (y_dist == 0 && y_dist!=0){
                    y_pace = 0;
                    x_pace = x_dist>0?3:-3;
                }
                else{
                    double k = Math.abs(y_dist / x_dist);
                    x_pace = (int) Math.ceil(base_pace * (1 / (k + 1)));
                    x_pace *= x_dist > 0 ? 1 : -1;
                    y_pace = (int) Math.ceil(base_pace * (k / (k + 1)));
                    y_pace *= y_dist > 0 ? 1 : -1;
                }

//                checkLocation = destLocation;
            }

//            if has get the destination or paused
            if ((Math.abs(currentLocation.getX()-destLocation.getX())<min_dist &&
                    Math.abs(currentLocation.getY()-destLocation.getY())<min_dist)||
                    !moving) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                continue;
            }


            newX = currentLocation.getX()+x_pace;
            newY = currentLocation.getY()+y_pace;
            canvas = sfh.lockCanvas(new Rect(newX - GirlW, newY - GirlH, GirlW + newX, GirlH + newY));

            if (canvas != null) {
                canvas.save();

                canvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);

                Bitmap bitmap = current_bitmaps[controll_flag % 2];

                canvas.drawBitmap(bitmap, newX, newY, null);

                canvas.restore();
                sfh.unlockCanvasAndPost(canvas);  // commit the finished canvas

                currentLocation.setXY(newX, newY);

            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


//            if the view paused or destroyed
            if (stopRefresh) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public void pauseMove(){
        moving = false;
    }

    public void keepMove(){
        moving = true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        stopRefresh = false;
        synchronized (this) {
            notify();
        }
        if (!th.isAlive()) {
            th.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        keepMove();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        pauseMove();
        stopRefresh = true;
    }


    @Override
    public void startAnimation(Animation animation) {
        super.startAnimation(animation);
    }
}
