package com.org.outlast.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.org.outlast.R;

public class Secret_pic_feature extends Activity {
    ImageView secret_pic_feature;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_pic_feature);
        secret_pic_feature = (ImageView) findViewById(R.id.secret_pic_feature);
        secret_pic_feature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
