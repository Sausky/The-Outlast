package com.org.outlast.ui.view;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.org.outlast.R;
import com.org.outlast.UIDecoder;

public class LetteringDesk extends Activity {
    ImageView lettering;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lettering_desk);
        lettering = (ImageView) findViewById(R.id.the_end_lettering);
        UIDecoder.setBackground(lettering, getResources(), R.drawable.lettering_big, 600, 400);
        lettering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onDestroy();
            }
        });
    }

}
