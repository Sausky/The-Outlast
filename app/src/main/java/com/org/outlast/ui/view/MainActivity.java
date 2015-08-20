package com.org.outlast.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.LinearLayout;

import com.org.outlast.R;
import com.org.outlast.UIDecoder;
import com.org.outlast.core.entity.GoodsList;
import com.org.outlast.ui.view.animationMove.CanvasRefresher;
import com.org.outlast.ui.view.graphics.SecretPackage;

import static com.org.outlast.R.id.background_main;


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
    private ImageView mushroom;
    private ImageView socket_drier;

    public GoodsList data;
    private Intent intent;


    /**
     * 吹风机的标识
     */
    private String drier = "drier";

    /**
     * 未在使用任何物品
     */
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
        LinearLayout ll = (LinearLayout) findViewById(background_main);
        UIDecoder.setBackground(ll, getResources(), R.drawable.background_empty, 1024, 600);
        initThings();
        mushroom = (ImageView) findViewById(R.id.mushroom);
        socket_drier = (ImageView) findViewById(R.id.socket_drier);
        if(!data.getMushroomState()) {

            //挖起蘑菇
            mushroom.setImageResource(R.drawable.mushroom);
            mushroom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = data.getName();
                    if (name == nothing_use) {
                        Toast.makeText(getApplication(), "如果有铲子我可以挖开它！", Toast.LENGTH_SHORT).show();
                    } else if (name.equals("shovel")) {
                        data.setName(nothing_use);
                        intent.setClass(MainActivity.this, Ring.class);
                        mushroom.setClickable(false);
                        mushroom.setAlpha(0);
                        data.setMushroom_state(true);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplication(), "这个东西可挖不了蘑菇呀！", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else{
            mushroom.setAlpha(0);
        }
        //为门设置透明
        door.setAlpha(0);
        door.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (data.isDoor_state()) {
                    intent.setClass(MainActivity.this, DoorPrompt.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(getApplicationContext(), "门锁着", Toast.LENGTH_LONG).show();
                }
            }
        });
        //插座
        if(!data.getDrier_connect()) {
            socket.setImageResource(R.drawable.socket);
            socket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name = data.getName();
                    Log.v("name", String.valueOf(name));
                    //Unused Items
                    if (name == nothing_use) {

                        Toast.makeText(getApplicationContext(), "啊是插座！可以连接电器！", Toast.LENGTH_LONG).show();
                    } else if (name.equals(drier)) {

                        data.removeThing(drier);

                        data.setName(nothing_use);
                        socket.setVisibility(View.INVISIBLE);

                        socket_drier.setVisibility(View.VISIBLE);
                        data.setDrier_connect(true);
                        socket_drier.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "吹风机被启动了", Toast.LENGTH_SHORT).show();
                                data.updateState(true);
                            }
                        });

                    } else {
                        //the wrong usage
                        data.setName(nothing_use);
                        Toast.makeText(getApplicationContext(), "这个可不能用在插座上呀", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else {
            socket.setVisibility(View.INVISIBLE);
            socket_drier.setVisibility(View.VISIBLE);
            socket_drier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "吹风机被启动了", Toast.LENGTH_SHORT).show();
                    data.updateState(true);
                }
            });
        }
        //进入密码箱特写


        //是否获得吹风机
        if (!data.getDrier()) {
            secret_package.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent.setClass(MainActivity.this, SecretPackage.class);
                    startActivity(intent);
                }
            });
        } else {
            //更换图片（密码箱打开）
            secret_package.setImageResource(R.drawable.package_open);
            secret_package.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.getState()) {
                        Toast.makeText(getApplicationContext(), "加热了密码箱，但是没有什么反应", Toast.LENGTH_SHORT).show();
                        data.updateState(false);
                    } else {
                        Toast.makeText(getApplication(), "里面已空空如也", Toast.LENGTH_SHORT);
                    }
                }
            });

        }

        //传输物品栏数据
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(MainActivity.this, Deposit.class);
                startActivity(intent);
            }
        });

        if (data.getBed_number() == 0) {
            bed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.setBed_number(1);
                    bed.setImageResource(R.drawable.bed_moved);
                    intent.setClass(MainActivity.this, Bed_lettering.class);
                    startActivity(intent);
                    finish();
                    onDestroy();
                }
            });
        } else if (data.getBed_number() == 1) {

            bed.setImageResource(R.drawable.bed_moved);
            bed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.getState()) {
                        data.updateState(false);
                        intent.setClass(MainActivity.this, TheEndLettering.class);
                        startActivity(intent);
                        finish();
                        onDestroy();

                    } else {
                        intent.setClass(MainActivity.this, Bed_lettering.class);
                        startActivity(intent);
                        finish();
                        onDestroy();
                    }
                }
            });
        }


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
        if (data.isShovel_state()) {
            desk.setImageResource(R.drawable.desk_open);
        } else {
            desk.setImageResource(R.drawable.desk_with_carve_closed);
            desk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent();
                    intent.setClass(MainActivity.this, desk_prompt.class);
                    startActivity(intent);
                }
            });
        }


        //镜子的点击提示
        mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mirror_searched && data.getName().equals(SCREWDRIVER_NAME)) {
                    intent = new Intent(MainActivity.this, mirror_hidden_thing.class);
                    startActivity(intent);

                    mirror_searched = true;
                    data.removeThing(SCREWDRIVER_NAME);
                } else if (mirror_searched) {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), "里面看起来空空如也", duration);
                    toast.show();
                } else if (data.getName().equals(nothing_use)) {
                    intent.setClass(MainActivity.this, MirrorPrompt.class);
                    startActivity(intent);
                } else {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(getApplicationContext(), "当前工具好像对镜子没什么用", duration);
                    toast.show();
                }

            }
        });

        if (screwdriver_found)
            ((ViewManager) screwdriver.getParent()).removeView(screwdriver);
        else {
            screwdriver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.addGoods(SCREWDRIVER_NAME, R.drawable.screwdriver_big);
                    ((ViewManager) screwdriver.getParent()).removeView(screwdriver);
                    screwdriver_found = true;
                    Toast.makeText(getApplication(), "获得螺丝刀！", Toast.LENGTH_SHORT).show();
                }
            });
        }


//        call this method every time you want to move
//        girlView.moveTo(100, 1);
//
//        girlView.moveTo(300, 0);

        Log.d("debug_helper", "complete create");

    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                return super.onTouchEvent(event);
//            case MotionEvent.ACTION_MOVE:
//                return super.onTouchEvent(event);
//            case MotionEvent.ACTION_UP:
//                int x = (int)event.getX();
//                int y = (int)event.getY();
//
//                Log.d("debug_helper",x+":"+y);
//                girlView.moveTo(x,y);
//                return true;
//        }
//        return false;
//        return super.onTouchEvent(event);
//    }

    /**
     * 初始化
     */
    public void initThings() {
        //girlView = (CanvasRefresher) findViewById(R.id.girl_view);
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
     */

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onRestart() {
        super.onRestart();
    }


    protected void onDestroy() {
        super.onDestroy();
    }
}
