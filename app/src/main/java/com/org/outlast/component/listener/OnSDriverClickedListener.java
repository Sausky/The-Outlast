package com.org.outlast.component.listener;

import android.app.Activity;
import android.view.View;

import com.org.outlast.R;
import com.org.outlast.ui.view.MainActivity;
import com.org.outlast.ui.view.Tools;

/**
 * Created by Jianwei on 2015/5/16.
 */
public class OnSDriverClickedListener implements View.OnClickListener {
    private Activity activity;
    public OnSDriverClickedListener(Activity activity){
        this.activity=activity;

    }

    @Override
    public void onClick(View v) {
        Tools.setToolUsed(activity.getString(R.string.screwdriver));
        MainActivity.getToolbox().setImageResource(R.drawable.screwdriver);
       activity.finish();
    }
}