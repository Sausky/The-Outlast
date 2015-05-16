package com.org.outlast.component.listener;

import android.app.Activity;
import android.view.View;

/**
 * Created by Jianwei on 2015/5/16.
 */
public class OnExbookClickedListener implements View.OnClickListener{
    private Activity activity;
    public OnExbookClickedListener(Activity activity){
    this.activity=activity;

    }

    @Override
    public void onClick(View v) {
     activity.finish();
    }
}
