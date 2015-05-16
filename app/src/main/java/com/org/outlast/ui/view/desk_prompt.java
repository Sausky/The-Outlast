package com.org.outlast.ui.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.org.outlast.R;

public class desk_prompt extends Activity{
    ImageView prompt_inf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desk_prompt);
        //获取提示实现点击关闭
        prompt_inf = (ImageView) findViewById(R.id.prompt_inf);
        prompt_inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
