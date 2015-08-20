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
import com.org.outlast.core.entity.GoodsList;

public class Ring extends Activity {
    private ImageView ring;
    private ImageView tv;
    public View.OnClickListener clickListener;
    public GoodsList data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
        ring = (ImageView) findViewById(R.id.ring);

        UIDecoder.setBackground(ring,getResources(),R.drawable.ring,300,300);
        tv = (ImageView) findViewById(R.id.tv);
        data = (GoodsList) getApplication();
        //addGoods
        data.addGoods("ring",R.drawable.ring);
        data.updateDrier(true);
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onDestroy();
            }
        };
        ring.setOnClickListener(clickListener);
        tv.setOnClickListener(clickListener);
    }
}
