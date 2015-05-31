package com.org.outlast.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.*;
import android.view.SurfaceView;
import android.view.animation.Animation;

import com.org.outlast.ui.view.graphics.MyImageResources;


/**
 * Created by shen on 15/5/9.
 */
public class GirlSurfaceView extends SurfaceView implements Callback, Runnable {
    private SurfaceHolder sfh;
    private Thread th;
    private Canvas canvas;

    private volatile Integer destX=0,destY=0;
    private int backupX,backupY;

    private volatile Boolean moving = false;

    /*
    * to avoid the time costing "synchronized" operation on loop, cus the synchronized operation
    *   occurs every loop while the "wait" and "notify" only occur when the destination is changed
    * if start to change the from and to position, backup the pre position first
    * then set "changing" to true
    * then the loop use backup position
    *
    * when the position change completed, set "changing" to false
    * the loop use completed position
    *
    * */
    private Boolean changing = false;


    private int GirlW,GirlH;

    private int controll_flag = 0;

    private Bitmap[] right_girls = new Bitmap[2];
    private Bitmap[] left_girls = new Bitmap[2];

    private MyImageResources imageResource;

    public GirlSurfaceView(Context context){
        super(context);
        init();
    }

    public GirlSurfaceView(Context context, AttributeSet attrs) {     //Constructor that is called when inflating a view from XML
        super(context,attrs);
        init();

    }
    public GirlSurfaceView(Context context, AttributeSet attrs, int defStyle) {     //Perform inflation from XML and apply a class-specific base style
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


//        girl = BitmapFactory.decodeResource(this.getResources(), R.drawable.girl_small);

        imageResource = MyImageResources.getInstance(this.getResources().getAssets());

        right_girls = imageResource.girl_right_move;
        left_girls = imageResource.girl_left_move;

        GirlW = imageResource.girl_width;
        GirlH = imageResource.girl_height;

    }

    @Override
    public void startAnimation(Animation animation) {
        super.startAnimation(animation);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        th.start();
    }



    /*
    * the moveTo method still should be synchronized
    * because we should guarantee that the position change can not be re-entry
    * */
    public synchronized void moveTo(int toX, int toY){
        keepMove();

        backupX = destX;
        backupY = destY;

        changing = true;
//        wait until the loop noticed the changing status

        synchronized (changing) {
            try {
                changing.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        destX = toX;
        destY = toY;

        changing = false;
    }

    public void pauseMove(){
        moving = false;
    }

    public void keepMove(){
        moving = true;
        synchronized (moving) {
            moving.notify();
        }
    }

    public void run() {

        int newX,newY;

        int base_pace = 3;

//        if x distance and y distance both smaller than 0.75*base_pace, then stop move
        double sqrt2_div2 = 0.75;
        double min_dist = sqrt2_div2*base_pace;


        int x_pace = 0;
        int y_pace = 0;

        int currentX = 0;
        int currentY = 0;

        Bitmap[] current_bitmaps = new Bitmap[2];

        int i=0;

//        while(true){
        while(i<2){
            i++;

            if (moving){
                controll_flag = (++controll_flag)%2;

                if (changing){
                    //notify the moveTo method
                    synchronized (changing) {
                        changing.notify();
                    }

//                    calculate the pace

                    int x_dist = backupX-currentX;
                    int y_dist = backupY-currentY;

                    if (x_dist>0)
                        current_bitmaps = right_girls;
                    else
                        current_bitmaps = left_girls;

                    if (x_dist==0) {
                        x_pace = 0;
                        y_pace = y_dist>0?3:-3;
                    }else{
                        double k = x_dist/y_dist;
                        base_pace*=x_dist>0?1:-1;
                        x_pace = (int)Math.ceil(base_pace*(1/(k+1)));
                        y_pace = (int)Math.ceil(base_pace*(k/(k+1)));
                    }
                }

                if (Math.abs(currentX-destX)<min_dist && Math.abs(currentY-destY)<min_dist) {
                    moving = false;
                    continue;
                }

                newX = currentX+x_pace;
                newY = currentY+y_pace;

                canvas = sfh.lockCanvas(new Rect(newX - GirlW, newY - GirlH, GirlW + newX, GirlH+newY));

                canvas.save();

                canvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);

                canvas.drawBitmap(current_bitmaps[controll_flag % 2], newX, newY, null);

                canvas.restore();
                sfh.unlockCanvasAndPost(canvas);  // commit the finished canvas

            }else{
                synchronized (moving) {
                    try {
                        moving.wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }
    public void surfaceDestroyed(SurfaceHolder holder) {
    }



}
