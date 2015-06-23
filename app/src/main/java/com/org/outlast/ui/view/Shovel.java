package com.org.outlast.ui.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.org.outlast.R;

public class Shovel extends Activity {
    private ImageView shovel;
    private ImageView tv;
    public View.OnClickListener clickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shovel);
        shovel = (ImageView) findViewById(R.id.shovel);
        tv = (ImageView) findViewById(R.id.tv);

        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Shovel.this,MainActivity.class);
                startActivity(intent);
                finish();
                onDestroy();
            }
        };
        shovel.setOnClickListener(clickListener);
        tv.setOnClickListener(clickListener);
    }

}
