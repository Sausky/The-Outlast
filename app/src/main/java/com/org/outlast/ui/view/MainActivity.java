package com.org.outlast.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.org.outlast.R;


public class MainActivity extends Activity {
    private ImageView door;
    private ImageView secret_pic;
    private ImageView desk;
    private Intent intent;

    private Bitmap girl;

    private LinearLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        door = (ImageView) findViewById(R.id.door);
        secret_pic = (ImageView) findViewById(R.id.secret_pic);
        desk = (ImageView)findViewById(R.id.desk);
        desk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(MainActivity.this,desk_prompt.class);
                startActivity(intent);
            }
        });


        girl = BitmapFactory.decodeResource(this.getResources(),R.drawable.girl);

        Canvas c = new Canvas(girl);

//        c.save();

//        background = (LinearLayout)findViewById(R.id.main_curtain);
//        background.setBackgroundResource(R.drawable.pre_start_animation);
//        AnimationDrawable animationDrawable = (AnimationDrawable)background.getBackground();
//        animationDrawable.start();
    }

}
