package com.org.outlast.ui.view;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.org.outlast.R;
import com.org.outlast.UIDecoder;

public class RingBig extends Activity {
    ImageView ring;
    ImageView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring_big);
        ring = (ImageView) findViewById(R.id.portrait);
        info = (ImageView) findViewById(R.id.info_background);
        RelativeLayout view = (RelativeLayout) findViewById(R.id.back);
        UIDecoder.setBackground(view, getResources(), R.drawable.ring, 300, 300);
        ring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onDestroy();
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onDestroy();
            }
        });
    }

}
