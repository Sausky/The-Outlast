package com.org.outlast.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.org.outlast.R;
import com.org.outlast.ui.start_animation.PreAnimationActivity;
import com.org.outlast.ui.widge.MyAdapter;


public class SecretKeyboard extends Activity{
    private Button confirm;
    private Button deleteOne;
    // 显示的6个点的图片
    private ImageView[] imgs = new ImageView[6];
    // 展示位数的6个点
    LinearLayout showNumberLayout;
    //更改的密码
    private String password = "";
    //键盘的左下角按钮
    //左下角文字的显示
    String showButtonText;

    MyAdapter adapter;
    MyAdapter adapter2;
    MyAdapter adapter3;
    private String[] tags = {
            "q", "w", "e", "r", "t", "y", "u", "i", "o", "p"};
    private String[] tags2 = {"a", "s", "d", "f", "g", "h", "j", "k", "l"};
    private String[] tags3 = {"z", "x", "c", "v", "b", "n", "m"};
    //声明数字0-9
    private int[] phoneNumberImgs = {
            R.drawable.selector_password_button0,
            R.drawable.selector_password_button1,
            R.drawable.selector_password_button2,
            R.drawable.selector_password_button3,
            R.drawable.selector_password_button4,
            R.drawable.selector_password_button5,
            R.drawable.selector_password_button6,
            R.drawable.selector_password_button7,
            R.drawable.selector_password_button8,
            R.drawable.selector_password_button9
    };
    private int[] secondLineImgs = {
            R.drawable.selector_password_buttona,
            R.drawable.selector_password_buttons,
            R.drawable.selector_password_buttond,
            R.drawable.selector_password_buttonf,
            R.drawable.selector_password_buttong,
            R.drawable.selector_password_buttonh,
            R.drawable.selector_password_buttonj,
            R.drawable.selector_password_buttonk,
            R.drawable.selector_password_buttonl};
    private int[] thirdLineImgs = {
            R.drawable.selector_password_buttonz,
            R.drawable.selector_password_buttonx,
            R.drawable.selector_password_buttonc,
            R.drawable.selector_password_buttonv,
            R.drawable.selector_password_buttonb,
            R.drawable.selector_password_buttonn,
            R.drawable.selector_password_buttonm

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_keyboard);
        adapter = new MyAdapter(this, phoneNumberImgs, tags);
        adapter2 = new MyAdapter(this,secondLineImgs,tags2);
        adapter3 = new MyAdapter(this,thirdLineImgs,tags3);
        showNumberLayout = (LinearLayout) findViewById(R.id.llayout);
        for (int i = 0; i < 6; i++) {
            imgs[i] = (ImageView) showNumberLayout.getChildAt(i);
            imgs[i].setEnabled(true);
            imgs[i].setTag(i);
        }
        GridView dcMyGameMyGridView = (GridView) findViewById(R.id.phone_number);
        GridView gv2 = (GridView) findViewById(R.id.phone_number2);
        GridView gv3 = (GridView) findViewById(R.id.phone_number3);
        dcMyGameMyGridView.setAdapter(adapter);
        gv2.setAdapter(adapter2);
        gv3.setAdapter(adapter3);
        dcMyGameMyGridView.setSelector(new ColorDrawable(
                Color.TRANSPARENT));
        gv2.setSelector(new ColorDrawable(
                Color.TRANSPARENT));
        gv3.setSelector(new ColorDrawable(
                Color.TRANSPARENT));
        dcMyGameMyGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //添加数字

                changePasswrod(String.valueOf(view.getTag()) + "");
            }

        });
        gv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //添加数字

                changePasswrod(String.valueOf(view.getTag()) + "");

            }

        });
        gv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //添加数字

                changePasswrod(String.valueOf(view.getTag()) + "");



            }

        });
        confirm = (Button) findViewById(R.id.confirm);
        deleteOne = (Button) findViewById(R.id.delete);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getPassword().length() < 6){
                    Toast.makeText(getApplicationContext(), "密码不足6位", Toast.LENGTH_SHORT).show();
                }else if(getPassword().equals("cletxz")){
                    Intent intent = new Intent();
                    intent.setClass(SecretKeyboard.this,PreAnimationActivity.class);
                    startActivity(intent);
                    finish();
                    onDestroy();
                }else {

                    Animation alphaAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.shake2);
                    showNumberLayout.startAnimation(alphaAnim);
                }
            }
        });
        deleteOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePassword();
            }
        });
    }

    /**
     * 拼接传入进来的字符串输出密码
     *
     * @param alph
     */
    private void changePasswrod(String alph) {
        String passwordString = getPassword();
        if (TextUtils.isEmpty(passwordString) || passwordString.length() < 6) {
            setPassword(passwordString + alph);
            setHideImageView();
        } else {
            Toast.makeText(getApplicationContext(), "密码已为6位", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 删除一个密码
     */
    private void deletePassword() {
        String passwordString = getPassword();
        if (!TextUtils.isEmpty(passwordString) || passwordString.length() > 0) {
            setPassword(passwordString.substring(0, passwordString.length() - 1));
            setHideImageView();

        } else {
            Toast.makeText(getApplicationContext(), "密码已清空", Toast.LENGTH_SHORT).show();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 当前显示的有几位，然后剩下的隐藏
     */
    public void setHideImageView() {
        int length = getPassword().length();
        for (int i = 0; i < length; i++) {
            imgs[i].setEnabled(false);
        }
        for (int i = 0; i < 6 - length; i++) {
            imgs[5 - i].setEnabled(true);
        }
    }



}
