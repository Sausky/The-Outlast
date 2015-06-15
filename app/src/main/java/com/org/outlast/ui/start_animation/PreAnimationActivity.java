package com.org.outlast.ui.start_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.org.outlast.R;
import com.org.outlast.ui.view.MainActivity;

public class PreAnimationActivity extends Activity implements View.OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_animation);

        ImageView mImageView = (ImageView) findViewById(R.id.anim_curtain);
        mImageView.setClickable(true);
        mImageView.setOnClickListener(this);
    }


    public void onClick(View view) {
        ImageView imageView = (ImageView)view;
        if (!AnimationTimeline.isFinish()) {
            imageView.setImageResource(AnimationTimeline.currentRes());
            Animation alphaAnim = AnimationUtils.loadAnimation(this, AnimationTimeline.currentAnim());
            view.startAnimation(alphaAnim);
            AnimationTimeline.next();
        }else{
            Intent intent = new Intent();
            intent.setClass(PreAnimationActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }

}
