package com.org.outlast.ui.view;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.org.outlast.R;

public class Bed_lettering extends Activity {
    ImageView bed_lettering;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_lettering);
        bed_lettering = (ImageView) findViewById(R.id.bed_lettering);
        bed_lettering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
