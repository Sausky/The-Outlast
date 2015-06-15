package com.org.outlast.ui.view.graphics;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.org.outlast.R;

public class Screwdriver extends Activity {
    ImageView screwdriver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screwdriver);
        screwdriver = (ImageView) findViewById(R.id.screwdriver_big);
        screwdriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
