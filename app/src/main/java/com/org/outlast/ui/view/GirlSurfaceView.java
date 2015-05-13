package com.org.outlast.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.*;
import android.view.SurfaceView;
import android.view.animation.Animation;

import com.org.outlast.R;



/**
 * Created by shen on 15/5/9.
 */
public class GirlSurfaceView extends SurfaceView implements Callback, Runnable {
    private SurfaceHolder sfh;
    private Thread th;
    private Canvas canvas;

    private int GirlW,GirlH;

    private int controll_flag = 0;

    private Bitmap girl;

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


        girl = BitmapFactory.decodeResource(this.getResources(), R.drawable.girl);

        GirlW = girl.getWidth();
        GirlH = girl.getHeight();

    }

    @Override
    public void startAnimation(Animation animation) {
        super.startAnimation(animation);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        th.start();
    }

    private void draw() {
        canvas = sfh.lockCanvas(new Rect(controll_flag>1?(controll_flag-2)*10:0 , 0, GirlW/2 + controll_flag*10, GirlH));

        canvas.save();


        canvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);
        canvas.drawBitmap(girl, -(controll_flag % 2) * (GirlW / 2) + controll_flag*10, 0, null);

        canvas.restore();
        sfh.unlockCanvasAndPost(canvas);  // 将画好的画布提交
    }
    public void run() {
        while (controll_flag++<40) {
            draw();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

}
