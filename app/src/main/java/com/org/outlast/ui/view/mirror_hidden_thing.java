package com.org.outlast.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.org.outlast.R;

/**
 * Created by shen on 15/5/31.
 */
public class mirror_hidden_thing extends Activity {
    ImageView notebook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mirror_hidden);
        //获取提示实现点击关闭
        notebook = (ImageView) findViewById(R.id.notebook);
        notebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
