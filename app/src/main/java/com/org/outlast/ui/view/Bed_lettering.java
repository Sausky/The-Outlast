package com.org.outlast.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.org.outlast.R;
import com.org.outlast.UIDecoder;

public class Bed_lettering extends Activity {
    private ImageView info;
    private ImageView ground;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_lettering);
        RelativeLayout back = (RelativeLayout) findViewById(R.id.back);
        info = (ImageView) findViewById(R.id.info_secret);
        ground = (ImageView) findViewById(R.id.info_background);
        UIDecoder.setBackground(back, getResources(), R.drawable.bed_lettering, 300, 300);
        intent = new Intent();
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(Bed_lettering.this,MainActivity.class);
                startActivity(intent);
                finish();
                onDestroy();
            }
        });
        ground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(Bed_lettering.this,MainActivity.class);
                startActivity(intent);
                finish();
                onDestroy();
            }
        });
    }


}
