package com.org.outlast.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.org.outlast.R;
import com.org.outlast.core.entity.GoodsList;

import java.util.ArrayList;
import java.util.Arrays;

public class desk_prompt extends Activity{
    private ImageView desk;
    private ImageView leftDrawer;
    private ImageView centerDrawer;
    private ImageView rightDrawer;
    private ImageView back;
    private static final String LEFTTAG="L";
    private static final String CENTERTAG="C";
    private static final String RIGHTTAG="R";
    private String[] correctSequence={CENTERTAG,LEFTTAG,LEFTTAG,LEFTTAG,CENTERTAG,CENTERTAG};
    private boolean isCorrect;
    private int attempt;
    public GoodsList data;
    /**铲子的标识*/
    private String shovel="shovel";

    public static boolean is_shovel_found=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_prompt);
        //获取提示实现点击关闭
        desk = (ImageView) findViewById(R.id.desk_feature);
        leftDrawer=(ImageView)findViewById(R.id.left_drawer);
        centerDrawer=(ImageView)findViewById(R.id.center_drawer);
        rightDrawer=(ImageView)findViewById(R.id.right_drawer);
        isCorrect=false;
        attempt=0;
        data = (GoodsList) getApplication();


        leftDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((attempt<=5)&&(correctSequence[attempt].equals(LEFTTAG))){
                    isCorrect=true;
                }
                else{
                    isCorrect=false;
                }
                attempt++;
                Toast.makeText(getApplicationContext(),"我晃动了左边的抽屉",Toast.LENGTH_SHORT).show();

            }
        });

        centerDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"我晃动了中间的抽屉",Toast.LENGTH_SHORT).show();
                if ((attempt==5)&&isCorrect){
                    is_shovel_found=true;
                    data.addGoods(shovel,R.drawable.shovel);
                    data.setShovel_state(true);
                    Intent intent = new Intent();
                    intent.setClass(desk_prompt.this,Shovel.class);

                    startActivity(intent);
                    finish();
                    onDestroy();
                }
                else if ((attempt<5)&&(correctSequence[attempt].equals(CENTERTAG))){
                    isCorrect=true;
                }else{
                    isCorrect=false;
                }
                attempt++;
            }
        });

        rightDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((isCorrect)&&(attempt<=5)&&(correctSequence[attempt].equals(RIGHTTAG))){
                    isCorrect=true;
                }else{
                    isCorrect=false;
                }
                attempt++;
                Toast.makeText(getApplicationContext(),"我晃动了右边的抽屉",Toast.LENGTH_SHORT).show();
            }
        });

        desk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(desk_prompt.this,LetteringDesk.class);
                startActivity(intent);

            }
        });
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onDestroy();
            }
        });

    }


}
