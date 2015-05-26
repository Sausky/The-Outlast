package com.org.outlast.ui.view.graphics;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.org.outlast.R;
import com.org.outlast.core.entity.Goods;
import com.org.outlast.core.entity.GoodsList;

import java.util.List;

public class Drier extends Activity {
    private ImageView drier;
    private TextView tv;
    public View.OnClickListener clickListener;
    public GoodsList data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drier);
        drier = (ImageView) findViewById(R.id.drier);
        tv = (TextView) findViewById(R.id.tv);
        data = (GoodsList) getApplication();
        //addGoods
        data.addGoods("drier",R.drawable.drier);
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        drier.setOnClickListener(clickListener);
        tv.setOnClickListener(clickListener);

    }


}
