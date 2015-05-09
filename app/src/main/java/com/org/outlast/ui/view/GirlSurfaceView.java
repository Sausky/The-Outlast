package com.org.outlast.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.*;
import android.view.SurfaceView;
import android.view.animation.Animation;

import com.org.outlast.R;

import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by shen on 15/5/9.
 */
public class GirlSurfaceView extends SurfaceView implements Callback, Runnable {
    private SurfaceHolder sfh;
    private Thread th;
    private Canvas canvas;
    private Paint paint;
    private int ScreenW, ScreenH;

    private int GirlW,GirlH;

    private int controll_flag = 0;

    private Bitmap girl;

    public GirlSurfaceView(Context context){
        super(context);
        th = new Thread(this);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        this.setKeepScreenOn(true);


        girl = BitmapFactory.decodeResource(this.getResources(),R.drawable.girl);


        GirlW = girl.getWidth();
        GirlH = girl.getHeight();
    }

    @Override
    public void startAnimation(Animation animation) {
        super.startAnimation(animation);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        ScreenW = this.getWidth();
        ScreenH = this.getHeight();
        th.start();
    }
    private void draw() {
        canvas = sfh.lockCanvas();
        canvas.drawRect(0, 0, ScreenW, ScreenH, paint);
        canvas.save();

        canvas.clipRect(controll_flag*10, 0, GirlW/2 + controll_flag*10, GirlH);
        canvas.drawBitmap(girl, -(controll_flag%2)*(GirlW/2)+controll_flag*10, 0, null);
        canvas.restore();
        sfh.unlockCanvasAndPost(canvas);  // 将画好的画布提交
    }
    public void run() {
        while (controll_flag++<40) {
            draw();
            try {
                Thread.sleep(100);
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

//    @Override
//    public boolean onDown(MotionEvent e){
//        double x = e.getX();
//        double y = e.getY();
//
//
//
//    }
}
