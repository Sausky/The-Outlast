package com.org.outlast.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.org.outlast.R;
import com.org.outlast.UIDecoder;

/**
 * Created by shen on 15/5/31.
 */
public class mirror_hidden_thing extends Activity {
    ImageView ring;
    ImageView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mirror_hidden);
        final Intent intent = new Intent(mirror_hidden_thing.this,MainActivity.class);
        ring = (ImageView) findViewById(R.id.portrait);
        info = (ImageView) findViewById(R.id.info_background);
        RelativeLayout view = (RelativeLayout) findViewById(R.id.mirror_back);
        UIDecoder.setBackground(view, getResources(), R.drawable.notebook, 300, 300);
        //获取提示实现点击关闭
        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
                onDestroy();
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
                onDestroy();
            }
        });
    }
}
