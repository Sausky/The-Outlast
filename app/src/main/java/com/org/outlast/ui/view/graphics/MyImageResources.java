package com.org.outlast.ui.view.graphics;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shen on 15/5/14.
 */
public class MyImageResources{
    private static volatile boolean created = false;
    private static MyImageResources instance;

    public final int girl_width = 50;
    public final int girl_height = 70;

    public Bitmap[] girl_right_move;
    public Bitmap[] girl_left_move;

    public Bitmap girl_stand;


    public static MyImageResources getInstance(AssetManager am){
        if (!created)
            try {
                instance = new MyImageResources(am);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return instance;
    }

    private MyImageResources(AssetManager am) throws IOException {
        InputStream is = am.open("girl_small.png");
        Bitmap girl_pics = BitmapFactory.decodeStream(is);

        girl_right_move = new Bitmap[2];
        girl_left_move = new Bitmap[2];

        for (int i=0;i<2;i++){
            girl_right_move[i] = Bitmap.createBitmap(girl_pics,i*girl_width,0,girl_width,girl_height);
            girl_left_move[i] = Bitmap.createBitmap(girl_pics,i*girl_width,girl_height,girl_width,girl_height);
        }
    }
}
