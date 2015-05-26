package com.org.outlast.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.org.outlast.R;
import com.org.outlast.core.entity.Goods;
import com.org.outlast.core.entity.GoodsList;
import com.org.outlast.ui.view.animationMove.CanvasRefresher;
import com.org.outlast.ui.view.graphics.SecretPackage;

import java.util.List;


public class MainActivity extends Activity {
    private ImageView door;
    private ImageView secret_pic;
    private ImageView desk;
    private ImageView bed;
    private ImageView deposit;
    private ImageView secret_package;
    private ImageView socket;
    public GoodsList data;
    private Intent intent;
    private Handler handler;
    /**床消息编号*/
    private int bed_number = 0;

    private CanvasRefresher girlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initThings();

        //插座
        socket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = data.getPosition();
                Log.v("position",String.valueOf(position));
            }
        });
        //进入密码箱特写
        secret_package.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(MainActivity.this, SecretPackage.class);
                startActivity(intent);
            }
        });
        //传输物品栏数据
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(MainActivity.this, Deposit.class);
                startActivity(intent);
            }
        });
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

    /**
     * 初始化
     */
    public void initThings(){
        girlView = (CanvasRefresher)findViewById(R.id.girl_view);
        door = (ImageView) findViewById(R.id.door);
        secret_pic = (ImageView) findViewById(R.id.secret_pic);
        desk = (ImageView)findViewById(R.id.desk);
        bed = (ImageView) findViewById(R.id.bed);
        deposit = (ImageView) findViewById(R.id.deposit);
        secret_package = (ImageView) findViewById(R.id.secret_package);
        socket = (ImageView) findViewById(R.id.socket);
        data = (GoodsList) getApplication();
        intent = new Intent();
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
