package com.org.outlast.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.org.outlast.R;
import com.org.outlast.core.entity.CurrentTool;
import com.org.outlast.core.entity.GoodsList;
import com.org.outlast.ui.view.animationMove.CanvasRefresher;
import com.org.outlast.ui.view.graphics.SecretPackage;


public class MainActivity extends Activity {
    private ImageView door;
    private ImageView secret_pic;
    private ImageView desk;
    private ImageView bed;
    private ImageView deposit;
    private ImageView secret_package;
    private ImageView socket;
    private ImageView mirror;
    public GoodsList data;
    private Intent intent;
    private static Handler handler;
    /**床消息编号*/
    private int bed_number = 0;
    /**插座消息*/
    private int socket_number = 2;
    /**密码箱消息*/
    private int secret_package_message = 3;
    /**吹风机的编号*/
    private int drier_position = 0;
    /*螺丝刀的编号*/
    private int screwdriver_position = 4;
    /**未在使用任何物品*/
    private int nothing_use = 10;


    private static boolean mirror_clicked = false;

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
                Log.v("position", String.valueOf(position));
                //Unused Items
                if (position == nothing_use) {
                    //u can give some prompts or do nothing
                } else if (position == drier_position) {
                    //choose the right thing
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (!Thread.currentThread().isInterrupted()) {
                                data.removeThing(drier_position);
                                Message message = handler.obtainMessage();
                                message.what = socket_number;
                                handler.sendMessage(message);
                            }
                        }
                    }).start();
                } else {
                    //the wrong usage
                    data.addPosition(nothing_use);
                    Toast.makeText(getApplicationContext(), "物品使用失败", Toast.LENGTH_LONG).show();
                }
            }
        });

            //进入密码箱特写
            secret_package.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //是否获得吹风机
                    if(!data.getDrier()){
                    //the wrong usage of drier
                    if(data.getState()){
                        Toast.makeText(getApplicationContext(),"加热了密码箱，但是没有什么反应",Toast.LENGTH_SHORT);
                        data.updateState(false);
                    }

                    intent.setClass(MainActivity.this, SecretPackage.class);
                    startActivity(intent);
                }else {
                        //更换图片（密码箱打开）
                        secret_package.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Message message = handler.obtainMessage();
                                message.what = secret_package_message;
                                handler.sendMessage(message);
                            }
                        });
                    }
            }});

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
                            if(data.getState()){
                                data.updateState(false);
                                intent.setClass(MainActivity.this,TheEndLettering.class);
                                startActivity(intent);
                            }else {
                                intent.setClass(MainActivity.this, Bed_lettering.class);
                                startActivity(intent);
                            }
                        }
                    });
                }else if(msg.what == 2){

                    socket.setImageResource(R.drawable.drier);
                    socket.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),"吹风机被启动了",Toast.LENGTH_SHORT).show();
                            data.updateState(true);
                        }
                    });

                }else if(msg.what == secret_package_message){
                    secret_package.setImageResource(R.drawable.package_open);
                    secret_package.setClickable(false);
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


        CurrentTool.setCurrentTool((String)mirror.getTag());


        data.addGoods("mirror",R.drawable.mirror);
        //镜子的点击提示
        mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!mirror_clicked && screwdriver_position==data.getPosition()) {
                if (!mirror_clicked && CurrentTool.getCurrentTool().equals((String)bed.getTag())) {
                        intent = new Intent(MainActivity.this, mirror_hidden_thing.class);
                        startActivity(intent);

                        mirror_clicked = true;
                }

            }
        });


//        call this method every time you want to move
        girlView.moveTo(300,300);

        girlView.moveTo(300,0);

    }

    /**
     * 初始化
     */
    public void initThings() {
        girlView = (CanvasRefresher) findViewById(R.id.girl_view);
        door = (ImageView) findViewById(R.id.door);
        secret_pic = (ImageView) findViewById(R.id.secret_pic);
        desk = (ImageView) findViewById(R.id.desk);
        bed = (ImageView) findViewById(R.id.bed);
        deposit = (ImageView) findViewById(R.id.deposit);
        secret_package = (ImageView) findViewById(R.id.secret_package);
        socket = (ImageView) findViewById(R.id.socket);
        mirror = (ImageView) findViewById(R.id.mirror);
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
