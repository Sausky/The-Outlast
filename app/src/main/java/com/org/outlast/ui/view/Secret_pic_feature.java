package com.org.outlast.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.org.outlast.R;
import com.org.outlast.UIDecoder;

public class Secret_pic_feature extends Activity {
    ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_pic_feature);
        home = (ImageView) findViewById(R.id.home);
        RelativeLayout view = (RelativeLayout) findViewById(R.id.secret_back);
        UIDecoder.setBackground(view, getResources(), R.drawable.secret_pic_big, 300, 300);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onDestroy();
            }
        });
    }

}
