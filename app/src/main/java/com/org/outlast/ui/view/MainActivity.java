package com.org.outlast.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.org.outlast.R;
import com.org.outlast.ui.view.animationMove.CanvasRefresher;


public class MainActivity extends Activity {
    private ImageView door;
    private ImageView secret_pic;
    private ImageView desk;
    private ImageView bed;
    private Intent intent;
    private Handler handler;
    private int bed_number = 0;

    private CanvasRefresher girlView;

    private Bitmap girl;

    private LinearLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        door = (ImageView) findViewById(R.id.door);
        secret_pic = (ImageView) findViewById(R.id.secret_pic);
        desk = (ImageView)findViewById(R.id.desk);
        bed = (ImageView) findViewById(R.id.bed);
        girlView = (CanvasRefresher)findViewById(R.id.girl_view);

        //开启床的线程
        bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(!Thread.currentThread().isInterrupted()){
                            Message message = handler.obtainMessage();
                            message.what = bed_number;
                            handler.sendMessage(message);
                        }
                    }
                }).start();
            }
        });
        handler = new Handler() {
            public void handleMessage(Message msg){
                //如果床未被点击过替换图片
                if(msg.what == 0){
                    bed.setImageResource(R.drawable.bed_moved);
                    bed_number =1;
                }
                //图片已经替换则修改监听事件
                else if(msg.what == 1){
                    bed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent = new Intent();
                            intent.setClass(MainActivity.this, Bed_lettering.class);
                            startActivity(intent);
                        }
                    });
                }
                super.handleMessage(msg);
            }
        };
        //密码纸特写
        secret_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(MainActivity.this, Secret_pic_feature.class);
                startActivity(intent);
            }
        });
        //桌子的点击提示
        desk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(MainActivity.this, desk_prompt.class);
                startActivity(intent);
            }
        });


//        call this method every time you want to move
        girlView.moveTo(300,300);

        girlView.moveTo(300,0);

    }


    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    protected void onRestart(){
        super.onRestart();
    }


    protected void onDestroy(){
        super.onDestroy();
    }
}
