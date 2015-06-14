package com.org.outlast.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.LinearLayout;

import com.org.outlast.R;
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
    private ImageView screwdriver;

    public GoodsList data;
    private Intent intent;
    private static Handler handler;
    /**床消息编号*/
    private int bed_number = 0;
    /**插座消息*/
    private int socket_number = 2;
    /**密码箱消息*/
    private int secret_package_message = 3;
    /**吹风机的标识*/
    private String drier = "drier";
    /**螺丝刀的编号*/
    private int screwdriver_position = 4;
    /**未在使用任何物品*/
    private String nothing_use = "none";


    private static boolean mirror_searched = false;
    private static boolean screwdriver_found = false;

    private static final String SCREWDRIVER_NAME = "screwdriver";

    private CanvasRefresher girlView;

    private Bitmap girl;

    private LinearLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initThings();
        //为门设置透明
//        door.setAlpha(0);
//        door.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"door",Toast.LENGTH_LONG).show();
//            }
//        });
        //插座
        socket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = data.getName();
                Log.v("name", String.valueOf(name));
                //Unused Items
                if (name == nothing_use) {
                    //u can give some prompts or do nothing
                } else if (name.equals(drier)) {
                    //choose the right thing
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (!Thread.currentThread().isInterrupted()) {
                                data.removeThing(drier);
                                Message message = handler.obtainMessage();
                                message.what = socket_number;
                                handler.sendMessage(message);
                                data.setName(nothing_use);
                            }
                        }
                    }).start();
                } else {
                    //the wrong usage
                    data.setName(nothing_use);
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
                        Toast.makeText(getApplicationContext(),"加热了密码箱，但是没有什么反应",Toast.LENGTH_SHORT).show();
                        data.updateState(false);
                    }

                    intent.setClass(MainActivity.this, SecretPackage.class);
                    startActivity(intent);
                }else {
                        //更换图片（密码箱打开）
                        secret_package.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                secret_package.setImageResource(R.drawable.package_open);
                                secret_package.setClickable(false);
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



        //镜子的点击提示
        mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mirror_searched && GoodsList.getName().equals(SCREWDRIVER_NAME)) {
                    intent = new Intent(MainActivity.this, mirror_hidden_thing.class);
                    startActivity(intent);

                    mirror_searched = true;
                    data.removeThing(SCREWDRIVER_NAME);
                }else if(mirror_searched){
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), "里面看起来空空如也", duration);
                    toast.show();
                }else{
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), "当前工具好像对镜子没什么用", duration);
                    toast.show();
                }

            }
        });

        if (screwdriver_found)
            ((ViewManager)screwdriver.getParent()).removeView(screwdriver);
        else {
            screwdriver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.addGoods(SCREWDRIVER_NAME, R.drawable.screwdriver);
                    ((ViewManager) screwdriver.getParent()).removeView(screwdriver);
                    screwdriver_found = true;
                }
            });
        }


//        call this method every time you want to move
        girlView.moveTo(300, 300);

        girlView.moveTo(300, 0);

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
        screwdriver = (ImageView) findViewById(R.id.screwdriver);

        data = (GoodsList) getApplication();
        intent = new Intent();

    }

    /**
     * 获取物品栏数据
     * */

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
