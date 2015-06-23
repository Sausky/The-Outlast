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
import com.org.outlast.core.entity.GoodsList;

public class TheEndLettering extends Activity {
    private ImageView home;
    public GoodsList data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_end_lettering);
        home = (ImageView) findViewById(R.id.home);
        RelativeLayout view = (RelativeLayout) findViewById(R.id.end_lettering);
        UIDecoder.setBackground(view, getResources(), R.drawable.the_end_lettering, 300, 300);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = (GoodsList) getApplication();
                data.setDoor_state(true);
                Intent intent = new Intent();
                intent.setClass(TheEndLettering.this,MainActivity.class);
                startActivity(intent);
                finish();
                onDestroy();
            }
        });
    }


}
